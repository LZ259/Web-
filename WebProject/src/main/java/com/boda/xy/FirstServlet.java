package com.boda.xy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置字符编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取表单参数
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        // 创建Student对象
        Student student = new Student(id, name);

        // 将Student对象存储在请求对象中
        request.setAttribute("student", student);

        // 获得RequestDispatcher对象并转发到SecondServlet
        RequestDispatcher dispatcher = request.getRequestDispatcher("SecondServlet");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}