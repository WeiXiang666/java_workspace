package num;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/NumberGuessServlet")
public class NumberGuessServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Number Guess</title></head>");
		out.println("<body bgcolor=\"white\"><font size=4>");
		// 得到session对象
		HttpSession session = request.getSession();
		// 第一次创建session，则返回null，因为session为新建，不会有绑定的数据
		Object o = session.getAttribute("bean");
		NumberGuessBean bean = null;
		if (o == null) {
			// 如果为空，则新建一个游戏对象，并存储绑定在session中
			bean = new NumberGuessBean();
			session.setAttribute("bean", bean);
		} else {
			bean = (NumberGuessBean) o;
		}
		/*
		 * 下面的代码处理客户端提交的数据，在输出的页面中有FORM表单， 表单中有文本输入域，名称为guess，要注意大小写的问题，表单的
		 * action属性没有提供，默认为提交给当前页面，也就是这个Servlet
		 */
		String guess = "";
		guess = request.getParameter("guess"); // 客户端提交的guess数据
		if (guess != null && !guess.equals("")) {
			bean.setGuess(guess); // 调用模型提供的判断功能
		}
		// 模型功能调用完毕后，开始进行页面输出，充当VIEW和CONTROLLER
		if (bean.getSuccess()) {
			out.println(" Congratulations!  You got it.");
			out.println("And after just " + bean.getNumGuesses()
					+ "  tries.<p>");
			bean.reset();
			out.println("Care to <a href=\"NumberGuessServlet\">try again</a>?");
		} else if (bean.getNumGuesses() == 0) {
			out.println("Welcome to the Number Guess game.<p>");
			out.println("I'm thinking of a number between 1 and 100.<p>");
			out.println("<form method=get>What's your guess? ");
			out.println("<input type=\"text\" name=\"guess\"/>");
			out.println("<input type=\"submit\" value=\"Submit\"></form>");
		} else {
			out.println(" Good guess, but nope.  Try <b>" + bean.getHint()
					+ "</b>.");
			out.println("You have made " + bean.getNumGuesses()
					+ " guesses.<p>");
			out.println("I'm thinking of a number between 1 and 100.<p>");
			out.println("<form method=get>What's your guess? ");
			out.println("<input type=\"text\" name=\"guess\"/>");
			out.println("<input type=submit value=\"Submit\"></form>");
		}
		out.println("</font></body></html>");
	}
}
