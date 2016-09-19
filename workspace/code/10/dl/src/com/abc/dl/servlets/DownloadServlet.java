package com.abc.dl.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DownloadServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得客户端提交的参数数据，通过查询字符串（QueryString）来完成
		String op = request.getParameter("fileid");
		// 取消浏览器对下载文件的缓存功能
		if (request.getProtocol().equals("HTTP/1.0")) {
			// HTTP 1.0
			response.setHeader("Pragma", "no-cache");
		} else {
			// HTTP 1.1 or later
			response.setHeader("Cache-Control", "no-cache");
		}
		if (op == null) {
			op = "";
		}
		if (op.equals("1")) { // 如果文件下载的编号为1，则下载a.rar
			// 定义组件输出的内容类型，为下载文件，也可以修改为：
			// response.setContentType("application/octet-stream");
			response.setContentType("application/x-download");
			op = "a.rar";
		} else if (op.equals("2")) {
			response.setContentType("application/x-download");
			op = "gj.pdf";
		} else if (op.equals("3")) {
			response.setContentType("application/x-download");
			op = "b.doc";
		} else { // 如果不提供下载的文件编号，那么转发到下载文件列表页面
			request.getRequestDispatcher("/servlet/main.html").forward(request,
					response);
			return;
		}
		// 输出内容类型设置完毕后，设置输出文件的名称
		response.setHeader("Content-Disposition", "attachment;filename=\"" + op
				+ "\"");
		// 得到下载文件所在的目录，通过组件的配置信息来获得
		String basePath = getInitParameter("software");
		// 将Web应用的虚拟路径转换为文件系统的物理路径
		op = getServletContext().getRealPath(basePath + "/" + op);
		// 设置输出文件长度
		File f = new File(op);
		response.setContentLength((int) f.length());
		// 准备输出，将服务器上文件以流的形式读入到虚拟机中，
		// 然后再以流数据的形式输出到response对象中
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(f);

		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(os);

		byte[] buff = new byte[8192];
		int r = 0;
		while ((r = bis.read(buff)) != -1) {
			bos.write(buff, 0, r);
			bos.flush();
		}
		bis.close();
		fis.close();
		bos.close();
		os.close();
	}
}
