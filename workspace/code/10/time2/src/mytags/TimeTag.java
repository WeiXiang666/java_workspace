package mytags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.util.*;
import java.beans.Beans;
import java.text.*;

public class TimeTag extends TagSupport {
	private String color, format;
	// 存放自定义脚本变量的名称和类型
	private String var, type;

	public void setColor(String color) {
		this.color = color;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		try {
			Date d = new Date();
			if (format == null) {
				format = "yyyy-MM-dd";
			}
			if (color == null) {
				color = "black";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String sd = sdf.format(d);

			JspWriter out = pageContext.getOut();
			out.write("<font color=\"" + color + "\">" + sd + "</font>");

			// 初始化该脚本变量，按照type指定的类型去实例化
			Object value = Beans.instantiate(getClass().getClassLoader(), type);
			pageContext.setAttribute(var, value);
		} catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	public void release() {
		format = null;
		color = null;
		var = null;
		type = null;
	}
}
