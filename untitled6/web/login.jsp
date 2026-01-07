<%--
  Created by IntelliJ IDEA.
  com.model.User: 23512
  Date: 2025/11/18
  Time: 下午1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ include file="header.jsp" %>

<h4>用户登录</h4>
<%
    // 获取错误信息
    String errorMsg = (String) request.getAttribute("errorMsg");
    if (errorMsg != null) {
        out.println("<font color='red'>" + errorMsg + "</font><br>");
    }
%>

<form action="LoginServlet" method="post">
    用户名: <input type="text" name="username" required><br><br>
    密码: <input type="password" name="password" required><br><br>
    <input type="submit" value="登录">
</form>
</body>
</html>