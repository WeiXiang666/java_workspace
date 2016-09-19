package mytags;

import javax.servlet.jsp.tagext.*;

public class MyTagExtraInfo extends TagExtraInfo {
	public VariableInfo[] getVariableInfo(TagData td) {
		return new VariableInfo[] { new VariableInfo(
				td.getAttributeString("var"), td.getAttributeString("type"),
				true, VariableInfo.NESTED) };
	}
}
