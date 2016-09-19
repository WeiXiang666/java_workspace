import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;

public class DOM4JDemo {
	public static void main(String[] args) throws Exception {
		// 使用SAX技术和DOM技术相结合，使用SAX将XML内容先读入
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("student.xml")); // 返回文档对象doc
		// 使用XPATH定位那些不及格的学生，输出他们的名字
		org.dom4j.XPath path = doc
				.createXPath("/students/student[java<60]/name");
		List list = path.selectNodes(doc);
		for (int i = 0; i < list.size(); i++) {
			Node node = (Node) list.get(i);
			System.out.println(node.getStringValue());
		}
	}
}
