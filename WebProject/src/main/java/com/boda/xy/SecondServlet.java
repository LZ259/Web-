package com.boda.xy;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SecondServlet")
public class SecondServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 从请求对象中获取Student对象
        Student student = (Student) request.getAttribute("student");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>学生信息显示</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>学生信息显示</h2>");

        if (student != null) {
            out.println("<p><strong>学号：</strong>" + student.getId() + "</p>");
            out.println("<p><strong>姓名：</strong>" + student.getName() + "</p>");
        } else {
            out.println("<p>未找到学生信息</p>");
        }

        // 添加返回input.html的超链接
        out.println("<br><a href='input.html'>返回输入页面</a>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}