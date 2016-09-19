package com.abc.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/SessionExample")
public class SessionExample extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body bgcolor=\"white\">");
		out.println("<head>");
		String title = "Session Example";
		out.println("<title>" + title + "</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>" + title + "</h3>");
		/*
		 * 得到session对象，如果是当前浏览器进程第一次访问，则新创建一个 session对象，并通过cookie，url
		 * rewriting，hidden field技术将session id
		 * 字符串传递给客户端，一般都是使用cookie技术，然后，下次该浏览器
		 * 进程再发送请求过来，再次调用getSession方法，则获得的session对象
		 * 就不是新创建了，而是上次创建的那个session对象，直至关闭该浏览器 进程，或者执行invalidate方法，退出session。
		 */
		HttpSession session = request.getSession(true);
		out.println("Session Id: " + session.getId());
		out.println("<br>");
		out.println("Session Created: " + new Date(session.getCreationTime())
				+ "<br>");
		out.println("Session Last Accessed Time: "
				+ new Date(session.getLastAccessedTime()));
		String dataName = request.getParameter("dataname");
		String dataValue = request.getParameter("datavalue");
		if (dataName != null && dataValue != null) {
			// 将客户端提交的数据绑定（存储）在session中
			session.setAttribute(dataName, dataValue);
		}
		out.println("<P>");
		out.println("Session Data: <br>");
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = session.getAttribute(name).toString();
			out.println(name + " = " + value + "<br>");
		}
		out.println("<P>");
		out.print("<form action=\"");
		// 根据是否关闭Cookie，添加session id到URL中
		out.print(response.encodeURL("SessionExample"));
		out.print("\" ");
		out.println("method=POST>");
		out.println("Session Data Name:");
		out.println("<input type=text size=20 name=dataname><br>");
		out.println("Session Data Value:");
		out.println("<input type=text size=20 name=datavalue><br>");
		out.println("<input type=submit>");
		out.println("</form>");
		out.println("<P>GET based form:<br>");
		out.print("<form action=\"");
		out.print(response.encodeURL("SessionExample"));
		out.print("\" ");
		out.println("method=GET>");
		out.println("Session Data Name:");
		out.println("<input type=text size=20 name=dataname><br>");
		out.println("Session Data Value:");
		out.println("<input type=text size=20 name=datavalue><br>");
		out.println("<input type=submit>");
		out.println("</form>");
		out.print("<p><a href=\"");
		// 根据是否关闭Cookie，添加session id到URL中
		out.print(response
				.encodeURL("SessionExample?dataname=foo&datavalue=bar"));
		out.println("\" >URL encoded </a>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
