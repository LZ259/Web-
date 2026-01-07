import com.model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        // 获取表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 模拟数据库验证（实际项目中应连接数据库）
        boolean isSuccess = "admin".equals(username) && "123456".equals(password);

        if (isSuccess) {
            // 登录成功，将用户对象存入session
            User user = new User(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            // 跳转到主页面
            response.sendRedirect("main.jsp");
        } else {
            // 登录失败，返回登录页并显示错误信息
            request.setAttribute("errorMsg", "用户名或密码错误");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response); // 处理GET请求
    }
}