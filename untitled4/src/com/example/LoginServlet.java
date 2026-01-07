package com.example.controller;

import com.example.model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    // 模拟用户数据 - 实际项目中应该从数据库获取
    private boolean authenticate(String username, String password) {
        return "admin".equals(username) && "123456".equals(password);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authenticate(username, password)) {
            // 认证成功
            User user = new User(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("protected/main.jsp");
        } else {
            // 认证失败
            request.setAttribute("errorMessage", "用户名或密码错误！");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}