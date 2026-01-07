<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>退出登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .logout-container {
            text-align: center;
            background-color: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            color: #333;
        }

        p {
            color: #666;
            margin: 20px 0;
        }

        .redirect-link {
            color: #007bff;
            text-decoration: none;
        }

        .redirect-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="logout-container">
    <h2>您已成功退出登录</h2>
    <p>感谢使用本系统！</p>
    <p>页面将在 <span id="countdown">3</span> 秒后跳转到登录页面...</p>
    <p>如果页面没有自动跳转，请 <a href="login.jsp" class="redirect-link">点击这里</a></p>
</div>

<script>
    var countdown = 3;
    var countdownElement = document.getElementById('countdown');

    var timer = setInterval(function() {
        countdown--;
        countdownElement.textContent = countdown;

        if (countdown <= 0) {
            clearInterval(timer);
            window.location.href = 'login.jsp';
        }
    }, 1000);
</script>
</body>
</html>