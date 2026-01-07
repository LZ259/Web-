package com.boda.xy;

import java.io.*;
        import javax.servlet.*;
        import javax.servlet.http.*;

public class FirstServlet extends HttpServlet {

    @Override
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

        // 获得RequestDispatcher并转发到SecondServlet
        RequestDispatcher dispatcher = request.getRequestDispatcher("SecondServlet");
        dispatcher.forward(request, response);
    }
}