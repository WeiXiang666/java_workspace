package com.abc.dl.filters;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SecureFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		// 将请求转换到HttpServletRequest类型
		HttpServletRequest request = (HttpServletRequest) arg0;
		// 获得session对象
		HttpSession session = request.getSession();
		// 取得是否登录成功的标志数据
		String s = (String) session.getAttribute("login");
		if (s == null) { // 如果为空，没有登录过，则转发到login.html页面
			request.getRequestDispatcher("/login.html").forward(arg0, arg1);
			return; // 处理结束
		}
		if (s.equals("true")) { // 如果登录成功，那么“穿过”过滤器
			arg2.doFilter(arg0, arg1);
		} else { // 否则，值为false，那么也转发到登录页面
			request.getRequestDispatcher("/login.html").forward(arg0, arg1);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
