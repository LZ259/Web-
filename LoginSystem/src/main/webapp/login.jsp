<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <style>
        body { font-family: Arial; margin: 0; padding: 20px; }
        .container { max-width: 400px; margin: 50px auto; padding: 30px; border: 1px solid #ddd; }
        .form-group { margin-bottom: 15px; }
        input { width: 100%; padding: 8px; }
        button { width: 100%; padding: 10px; background: #007bff; color: white; border: none; }
        .error { color: red; }
    </style>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div class="container">
    <h2>用户登录</h2>
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %><div class="error"><%= error %></div><% } %>
    <form action="LoginServlet" method="post">
        <div class="form-group">
            <label>用户名：</label>
            <input type="text" name="username" required>
        </div>
        <div class="form-group">
            <label>密码：</label>
            <input type="password" name="password" required>
        </div>
        <button type="submit">登录</button>
    </form>
    <div style="margin-top: 15px; font-size: 14px;">
        <strong>演示账号：</strong> admin / 123456
    </div>
</div>
</body>
</html>