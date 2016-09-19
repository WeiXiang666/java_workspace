package mytags;

import javax.servlet.jsp.tagext.*;

public class MyTagExtraInfo extends TagExtraInfo {
	public VariableInfo[] getVariableInfo(TagData td) {
		return new VariableInfo[] {
		// 并没有在此处直接给出变量的名字，而是读取标记的属性var的值，来决定
		// 脚本变量的名字，一样的道理，通过标记属性type的值来确定变量类型
		// TagData封装了标记的属性数据
		new VariableInfo(td.getAttributeString("var"),
				td.getAttributeString("type"), true, VariableInfo.AT_END) };
	}
}
