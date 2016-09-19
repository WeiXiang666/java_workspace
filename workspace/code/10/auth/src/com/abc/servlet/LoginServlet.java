package com.abc.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String authcode = request.getParameter("authcode");

		HttpSession session = request.getSession();

		if (username == null || username.length() == 0) {
			forward(request, response);
			return;
		}
		if (password == null || password.length() == 0) {
			forward(request, response);
			return;
		}
		if (authcode == null || authcode.length() == 0) {
			forward(request, response);
			return;
		}
		if (!username.equals(password)) { // 此处没有提供什么验证逻辑，只要
			forward(request, response); // 用户名和密码一样则登录成功
			return;
		}
		// 用户输入的验证码必须和产生的验证码一致
		// 产生的验证码存放在session中，可以在两次请求之间共享数据
		if (!authcode.equals(session.getAttribute("authcode"))) {
			forward(request, response);
			return;
		}

		response.setContentType("text/html");
		response.getWriter().println("login success!");

	}

	private void forward(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.html").forward(request, response);
		// response.sendRedirect("login.html”);
	}
}
