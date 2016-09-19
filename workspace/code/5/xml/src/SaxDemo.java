import javax.xml.parsers.*; //JDK提供的XML解析器包
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*;

public class SaxDemo {
	public enum TAG_FLAG { // 声明一个枚举类型，表示正在解析哪个元素.
		none, id, name, email, age, gender, java
	};

	public static void main(String[] args) {
		try { // 工厂方法模式
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser p1 = factory.newSAXParser(); // 创建一个SAX解析器
			// 开始解析student.xml的内容，提供的事件通过ContentHandler来通知
			// 本程序采用匿名类继承DefaultHandler这个类来实现，DefaultHandler类
			// 实现了ContentHandler接口，在该匿名类中给出事件处理程序
			p1.parse(new FileInputStream("student.xml"), new DefaultHandler() {
				private TAG_FLAG tag_flag = TAG_FLAG.none; // 枚举类型变量
				private StringBuffer content = new StringBuffer();
				String id, name, email;
				int age, java;
				boolean gender;

				// 当解析到某个元素的起始标记时，下面这个方法被调用
				// 在这个方法里需要判断当前解析的元素标记的名字等信息
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					if (qName.equals("id")) {
						tag_flag = TAG_FLAG.id;
					} else if (qName.equals("name")) {
						tag_flag = TAG_FLAG.name;
					} else if (qName.equals("email")) {
						tag_flag = TAG_FLAG.email;
					} else if (qName.equals("age")) {
						tag_flag = TAG_FLAG.age;
					} else if (qName.equals("java")) {
						tag_flag = TAG_FLAG.java;
					} else if (qName.equals("gender")) {
						tag_flag = TAG_FLAG.gender;
					} else {
						tag_flag = TAG_FLAG.none;
					}
				}

				// 解析元素内容时，调用这个方法
				public void characters(char[] ch, int start, int length)
						throws SAXException {
					content.append(new String(ch, start, length));
					String s = content.toString();
					s = s.trim(); // trim whitespace
					switch (tag_flag) {
					case id:
						id = s;
						break;
					case name:
						name = s;
						break;
					case email:
						email = s;
						break;
					case age:
						age = Integer.parseInt(s);
						break;
					case java:
						java = Integer.parseInt(s);
						break;
					case gender:
						gender = s.equals("男");
						break;
					}
				}

				// 下面这个方法在解析到元素的结束标记时将会执行
				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					content = new StringBuffer(); // 新建一个内容缓冲区
					tag_flag = TAG_FLAG.none;
					if (qName.equals("student")) { // 如果遇到student结束标记
						// 将student元素转换为一个student对象，如果有Student类的话
						// Student s = new Student(id, name, email, java, age,
						// gender);
						if (java < 60) { // 输出不及格的学生名字
							System.out.println(name);
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
