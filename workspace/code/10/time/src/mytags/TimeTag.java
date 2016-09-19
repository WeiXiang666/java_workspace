package mytags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.util.*;
import java.text.*;

//这个标记处理器类实现按照指定的格式和颜色样式输出时间
public class TimeTag extends TagSupport {
	private String color, format;

	// 定义了color属性的写方法
	public void setColor(String color) {
		this.color = color;
	}

	// 定义了format属性的写方法
	public void setFormat(String format) {
		this.format = format;
	}

	// 因为是空元素，所以略过体内容处理过程，这个方法也可以不写。
	// 使用TagSupport类的默认实现也可以。
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		try {
			Date d = new Date();
			if (format == null) { // 如果JSP没有传递格式过来，format为null
				format = "yyyy-MM-dd"; // 默认的格式
			}
			if (color == null) { // 如果JSP没有传递颜色过来，color为null
				color = "black"; // 默认的颜色
			}
			// java.text.SimpleDateFormat可以按照指定的日期格式将Date数据
			// 转换为字符串，见JavaSE平台的SimpleDateFormat API规范
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String sd = sdf.format(d);

			// JspWriter类继承自Writer类，其输出数据的方法write，print，println
			// 等方法都有IOException的异常声明，所以需要捕获这个异常并处理。
			JspWriter out = pageContext.getOut();
			out.write("<font color=\"" + color + "\">" + sd + "</font>");
		} catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	// 在JSP页面中调用完毕标记处理器对象后，要调用release方法，
	// 将这个对象释放回对象池中，所以需要将format，color变量置为null
	public void release() {
		format = null;
		color = null;
	}
}
