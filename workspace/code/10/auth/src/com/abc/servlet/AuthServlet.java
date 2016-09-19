package com.abc.servlet;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//组件的URL路径为auth.jpg
@WebServlet("/auth.jpg")
public class AuthServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 产生一个1000至9999的数字随机数
		String s = String.valueOf(new Random().nextInt(9000) + 1000);
		HttpSession session = request.getSession();
		session.setAttribute("authcode", s); // 保存到session中
		// Servlet组件输出到浏览器客户端的内容类型为图片
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "no-cache"); // 关闭客户端缓存图片功能
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 创建一个图片缓冲区，输出数字，并转换为流，输出到response对象
		OutputStream os = response.getOutputStream();
		BufferedImage image = new BufferedImage(38, 20,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 38, 20);
		g.setColor(Color.BLUE);
		g.drawString(s, 6, 14);
		ImageIO.write(image, "JPEG", os);
		os.close();
	}
}
