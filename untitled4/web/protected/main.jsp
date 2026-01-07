<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.User" %>
<%
    // 额外的会话检查
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<html>
<head>
    <title>主页面</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 20px; }
        .container { max-width: 800px; margin: 20px auto; padding: 20px; }
        .welcome { background-color: #d4edda; padding: 15px; border-radius: 5px; margin-bottom: 20px; }
        .logout-btn { background-color: #dc3545; color: white; padding: 8px 15px; text-decoration: none; border-radius: 3px; }
    </style>
</head>
<body>
<%@ include file="../header.jsp" %>

<div class="container">
    <div class="welcome">
        <h2>欢迎您，<%= user.getUsername() %>！</h2>
        <p>您已成功登录系统。</p >
    </div>

    <div>
        <h3>系统功能</h3>
        <ul>
            <li>用户信息管理</li>
            <li>数据统计分析</li>
            <li>系统设置</li>
        </ul>
    </div>

    <div style="margin-top: 20px;">
        <a href=" " class="logout-btn">退出登录</a >
    </div>
</div>
</body>
</html>