package java.com.exam.servlet;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求编码
        request.setCharacterEncoding("UTF-8");

        int score = 0;

        // 处理单选题 (正确答案：C)
        String q1Answer = request.getParameter("q1");
        if (q1Answer != null && q1Answer.equals("C")) {
            score += 30;
        }

        // 处理多选题 (正确答案：A,B,C,D)
        String[] q2Answers = request.getParameterValues("q2");
        Set<String> correctQ2 = new HashSet<>(Arrays.asList("A", "B", "C", "D"));

        if (q2Answers != null) {
            Set<String> userQ2 = new HashSet<>(Arrays.asList(q2Answers));
            if (userQ2.equals(correctQ2)) {
                score += 40;
            }
        }

        // 处理填空题 (正确答案：main)
        String q3Answer = request.getParameter("q3");
        if (q3Answer != null && q3Answer.trim().equalsIgnoreCase("main")) {
            score += 30;
        }

        // 将分数存储到session中
        request.getSession().setAttribute("score", score);

        // 重定向到结果页面
        response.sendRedirect("result.html");
    }
}