<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        .welcome-container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            color: #333;
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
        }

        .user-info {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 4px;
            margin: 20px 0;
        }

        .user-info p {
            margin: 10px 0;
        }

        .logout-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #dc3545;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-top: 20px;
        }

        .logout-btn:hover {
            background-color: #c82333;
        }

        .session-info {
            font-size: 12px;
            color: #666;
            margin-top: 30px;
            padding-top: 15px;
            border-top: 1px dashed #ddd;
        }
    </style>
</head>
<body>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="login.jsp"/>
</c:if>

<div class="welcome-container">
    <h2>欢迎您，${sessionScope.user.username}！</h2>

    <div class="user-info">
        <p><strong>用户ID：</strong> ${sessionScope.user.id}</p>
        <p><strong>用户名：</strong> ${sessionScope.user.username}</p>
        <p><strong>邮箱：</strong> ${sessionScope.user.email}</p>
        <c:if test="${not empty sessionScope.user.createdAt}">
            <p><strong>注册时间：</strong> ${sessionScope.user.createdAt}</p>
        </c:if>
    </div>

    <p>您已成功登录系统。</p>

    <a href="logout" class="logout-btn">退出登录</a>

    <div class="session-info">
        <p>Session ID: <%= session.getId() %></p>
        <p>创建时间: <%= new java.util.Date(session.getCreationTime()) %></p>
        <p>最后访问时间: <%= new java.util.Date(session.getLastAccessedTime()) %></p>
    </div>
</div>
</body>
</html>