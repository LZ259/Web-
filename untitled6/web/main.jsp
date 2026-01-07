<%--
  Created by IntelliJ IDEA.
  com.model.User: 23512
  Date: 2025/11/18
  Time: 下午1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.model.User" %>
<%
    // 验证登录状态，未登录则重定向到登录页
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%@ include file="header.jsp" %>

<h4>欢迎回来，<%= user.getUsername() %>！</h4>
<p>这是受保护的主页面</p>
<a href="LogoutServlet">退出登录</a>
</body>
</html>