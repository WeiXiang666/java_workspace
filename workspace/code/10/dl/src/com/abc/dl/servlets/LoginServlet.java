package com.abc.dl.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
	/*
	 * 如果用户知道此LoginServlet组件的URL地址，例如输入
	 * http://localhost:8080/dl/login，那么将请求转发到login.html，并且将 用户已登录过这个标志置为false。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("login", "false");
		request.getRequestDispatcher("/login.html").forward(request, response);
	}

	/*
	 * 正常情况下，用户在login.html页面输入用户名和密码，提交到此组件， 组件的doPost方法被调用，进行登录验证
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 用户名密码不为空，并且二者值一样，认为验证通过，这仅仅是个例子
		if (username != null && password != null && username.equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("login", "true"); // 设置登录成功标志
			/*
			 * 派发到main.html，下载列表，也可以将这个固定的URL地址修改一下， 如果用户未登录而直接进行文件下载，例如：
			 * http://localhost:8080/dl/servlet/download?fileid=1
			 * 由于过滤器拦截该请求，将请求转发到登录页面，如果登录成功，应该 再转发到最初的URL，可以从session中取出以前的URL地址
			 */
			request.getRequestDispatcher("/servlet/main.html").forward(request,
					response);
			return;
		}
		// 如果登录未成功，则重新登录，直接调用doGet方法
		doGet(request, response);
	}
}
