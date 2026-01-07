package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 检查是否有自动登录的 Cookie
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                } else if ("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }

        // 如果有自动登录 Cookie，尝试自动登录
        if (username != null && password != null) {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByUsername(username);

            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("welcome.jsp");
                return;
            }
        }

        // 没有自动登录或自动登录失败，显示登录页面
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // 登录成功
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // 处理"自动登录"Cookie
            if ("on".equals(remember)) {
                Cookie usernameCookie = new Cookie("username", username);
                Cookie passwordCookie = new Cookie("password", password);

                // 设置 Cookie 有效期为 7 天
                usernameCookie.setMaxAge(7 * 24 * 60 * 60);
                passwordCookie.setMaxAge(7 * 24 * 60 * 60);

                // 设置 Cookie 路径
                usernameCookie.setPath("/");
                passwordCookie.setPath("/");

                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }

            response.sendRedirect("welcome.jsp");
        } else {
            // 登录失败
            request.setAttribute("error", "用户名或密码错误！");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}