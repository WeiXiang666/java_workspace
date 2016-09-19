package mytags;

import java.util.*;
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

// BodyTagSupport类实现了IterationTag接口和BodyTag接口
public class IterationTag extends BodyTagSupport {
	private String var; // 存储自定义脚本变量的名称
	private String type; // 存储自定义脚本变量的类型
	private Vector list; // 待处理的集合数据

	private Iterator iterator = null; // 集合数据的迭代器对象

	public void setVar(String var) {
		this.var = var;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setList(Vector list) {
		this.list = list;
		iterator = list.iterator();
	}

	public int doStartTag() {
		int result = 0;
		// 如果迭代器为空，没有可迭代的数据，略过体内容
		if (iterator == null) {
			result = SKIP_BODY;
		} else {
			if (iterator.hasNext()) {
				// 如有迭代的数据，那么将集合中的数据取出，置入到pageContent
				// 在JSP页面中可以通过<jsp:getProperty/>来读取该数据Bean的值
				pageContext.setAttribute(var, iterator.next());

				// 可返回EVAL_BODY_BUFFERED，也可返回EVAL_BODY_INCLUDE
				// 如返回EVAL_BODY_INCLUDE，那么体内容中的JSP程序将直接
				// 输出到JspWriter类型的JSP隐含对象out中。
				// 如返回EVAL_BODY_BUFFERED，那么体内容中JSP程序将输出到
				// BodyContent类型的out对象中，在体内容中的JSP代码片段的
				// 隐含对象out，将不是元素外的JSP隐含对象out，类型
				// 也不是JspWriter，可以通过调用BodyTagSupport类提供的
				// getPreviousOut方法或者BodyContent类的getEnclosingWriter
				// 获得元素外的JSP隐含对象out。
				result = EVAL_BODY_BUFFERED;
				// result = EVAL_BODY_INCLUDE;
			} else {
				result = SKIP_BODY;
			}
		}
		return result;
	}

	// 如果doStartTag方法的返回值是EVAL_BODY_BUFFERED时，才执行此方法
	public void setBodyContent(BodyContent bc) {
		// 调用父类的setBodyContent方法，接受传入的BodyContent对象
		super.setBodyContent(bc);
		System.out.println("setBodyContent");
	}

	// doInitBody方法在setBodyContent方法执行后执行，也是在doStartTag方法
	// 中返回EVAL_BODY_BUFFERED时才调用此方法。
	public void doInitBody() throws JspException {
		System.out.println("init body");
	}

	// 体内容的迭代方法，执行完毕体内容中的JSP程序后，执行doAfterBody方法，
	// 此方法的返回值如果是EVAL_BODY_AGAIN，则重新执行体内容中的JSP程序。
	public int doAfterBody() {
		int result = 0;
		if (iterator.hasNext()) {
			// 如集合中还有未遍历的元素，则重新执行体内容，输出下一个元素。
			pageContext.setAttribute(var, iterator.next());
			result = EVAL_BODY_AGAIN;
		} else {
			result = SKIP_BODY;
		}
		return result;
	}

	public int doEndTag() throws JspException {
		BodyContent bc = getBodyContent();
		// 如doStartTag的返回值是EVAL_BODY_INCLUDE，则没有创建
		// BodyContent对象，getBodyContent方法将返回null，迭代的元素体内容
		// 将直接输出到JSP隐含对象out中。doEndTag方法无须实现。
		// 如doStartTag方法的返回值是EVAL_BODY_BUFFERED，那么元素体内容的
		// 执行结果都将输出到这个缓冲区对象BodyContent中，并没有直接输出到
		// 元素外的out对象中，需要将BodyContent对象中的内容读出来，输出
		// 到JspWriter类型的out对象中。
		if (bc != null) {
			// 这两个out对象是同一个对象，都是元素外的隐含对象out。
			JspWriter out = getPreviousOut();
			// JspWriter out = bc.getEnclosingWriter();

			try { // 读体内容对象中的内容，输出到外部的out对象。
				out.print(bc.getString());
			} catch (IOException e) {
				throw new JspException("iterator: " + e.getMessage());
			}
		}
		return EVAL_PAGE;
	}
}
