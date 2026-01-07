<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object user = session.getAttribute("user");
    boolean isLoggedIn = (user != null);
    String username = isLoggedIn ? ((com.example.model.User)user).getUsername() : "";
%>
<div style="background: #667eea; color: white; padding: 15px 20px;">
    <div style="display: flex; justify-content: space-between; align-items: center;">
        <h1 style="margin: 0;">企业管理系统</h1>
        <div>
            <% if (isLoggedIn) { %>
            <span>欢迎，<strong><%= username %></strong></span>
            <a href=" >/LogoutServlet"
               style="color: white; margin-left: 15px;">退出登录</a >
            <% } else { %>
            <a href="<%= request.getContextPath() %>/login.jsp"
               style="color: white;">立即登录</a >
            <% } %>
        </div>
    </div>
</div>