<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background-color: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 300px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .checkbox-group {
            margin: 15px 0;
            display: flex;
            align-items: center;
        }

        .checkbox-group label {
            margin-left: 5px;
            margin-bottom: 0;
        }

        .error {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }

        .submit-btn {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .submit-btn:hover {
            background-color: #0056b3;
        }

        .test-users {
            margin-top: 20px;
            padding: 10px;
            background-color: #f8f9fa;
            border-radius: 4px;
            font-size: 12px;
        }

        .test-users h4 {
            margin-top: 0;
            color: #666;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>用户登录</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="login" method="post">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username" required>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="checkbox-group">
            <input type="checkbox" id="remember" name="remember">
            <label for="remember">记住我（自动登录）</label>
        </div>

        <button type="submit" class="submit-btn">登录</button>
    </form>

    <div class="test-users">
        <h4>测试账号：</h4>
        <p>用户名：admin, 密码：123456</p>
        <p>用户名：user1, 密码：password123</p>
        <p>用户名：user2, 密码：qwerty</p>
    </div>
</div>
</body>
</html>