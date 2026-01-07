<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object user = session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统主页</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div style="padding: 20px;">
    <h2>欢迎进入系统！</h2>
    <p>当前用户：<strong><%= ((com.example.model.User)user).getUsername() %></strong></p >
    <p>登录时间：<%= new java.util.Date() %></p >
</div>
</body>
</html>