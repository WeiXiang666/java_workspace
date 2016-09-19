package mytags;

import java.io.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
//SimpleTagSupport实现接口SimpleTag，不像BodyTagSupport，TagSupport类那样
//定义保护成员pageContext，在此类中不提供该保护成员，取而代之的是通过
//公开方法getJspContext获得JspContext对象，再将该对象强制转换为PageContext

public class StringTag extends SimpleTagSupport {
	private String c; // 保存大小写的设定属性数据
	private int count; // 保存体内容循环输出次数属性数据

	public void setCase(String c) {
		this.c = c;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void doTag() throws JspException, IOException {
		// 声明抛出JspException和IOException，程序中不再需要写try-catch了。
		StringWriter sw = new StringWriter(); // 创建一个临时的字符串输出流对象
		getJspBody().invoke(sw); // 执行体内容的输出，输出到缓存流中
		String s = sw.toString(); // 将输出内容转换为字符串
		if (c != null) { // 进行大小写转换
			if (c.equalsIgnoreCase("lower")) {
				s = s.toLowerCase();
			} else if (c.equalsIgnoreCase("upper")) {
				s = s.toUpperCase();
			}
		}
		// 按照指定的次数来循环输出处理后的体内容数据
		for (int i = 0; i < count; i++) {
			getJspContext().getOut().write(s);
		}
	}
}
