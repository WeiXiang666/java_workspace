package impl;

import base.*;
import java.util.*;
import java.io.*;

import org.jdom.*;
import org.jdom.input.*;

import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;

public class XMLDataLoader implements DataLoader {
	public enum TAG_FLAG { // Java5提供的枚举类型声明
		none, id, name, email, age, gender, java
	};

	public ArrayList<Student> load() {
		try {
			/* 读属性配置文件 */
			Properties p = new Properties();
			p.load(new FileInputStream("loader.properties"));
			String xml = p.getProperty("xmlprocess");
			/* 根据xmlprocess属性值来确定使用何种解析方式 */
			if (xml.equalsIgnoreCase("sax")) {
				return load_sax();
			} else if (xml.equalsIgnoreCase("dom4j")) {
				return load_dom4j();
			} else if (xml.equalsIgnoreCase("jdom")) {
				return load_jdom();
			} else {
				return load_sax();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Student>();
		}
	}

	private ArrayList<Student> load_dom4j() {
		ArrayList<Student> r = new ArrayList<Student>();
		// 使用DOM4J来解析带有命名空间的XML文件
		try {
			Map map = new HashMap();
			org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader();
			reader.setValidation(false);
			org.dom4j.Document doc = reader.read(new FileInputStream(
					"student.xml"));
			org.dom4j.Element root = doc.getRootElement();

			String defaultNamespace = root.getNamespaceURI();
			map.put("default", defaultNamespace);
			reader.getDocumentFactory().setXPathNamespaceURIs(map);

			// 带有命名空间的解析，DOM4J也支持默认命名空间
			org.dom4j.XPath path = doc
					.createXPath("/default:students/default:student");
			path.setNamespaceURIs(map);
			List list = path.selectNodes(doc);

			for (Iterator it = list.iterator(); it.hasNext();) {
				org.dom4j.Node stu = (org.dom4j.Node) it.next();
				// XPATH路径举例：child::default:id
				org.dom4j.XPath pathc = stu.createXPath("child::*");
				pathc.setNamespaceURIs(map);
				List child = pathc.selectNodes(stu);

				String id = "", name = "", email = "";
				int age = 0, java = 0;
				boolean gender = false;
				// 遍历student元素的子元素
				for (int i = 0; i < child.size(); i++) {
					org.dom4j.Element e1 = (org.dom4j.Element) child.get(i);
					String tname = e1.getName();
					String tvalue = e1.getText();

					if (tname.equals("id")) {
						id = tvalue;
					} else if (tname.equals("name")) {
						name = tvalue;
					} else if (tname.equals("email")) {
						email = tvalue;
					} else if (tname.equals("gender")) {
						gender = tvalue.equals("男");
					} else if (tname.equals("age")) {
						age = Integer.parseInt(tvalue);
					} else if (tname.equals("java")) {
						java = Integer.parseInt(tvalue);
					}
				}
				Student s = new Student(id, name, email, java, age, gender);
				r.add(s);
			}
			/*
			 * //另外一种编程处理过程 org.dom4j.Element e = doc.getRootElement(); Iterator
			 * it = e.elementIterator("student"); while (it.hasNext()) {
			 * org.dom4j.Element s1 = (org.dom4j.Element) it.next(); String id =
			 * s1.element("id").getText(); String name =
			 * s1.element("name").getText(); String email =
			 * s1.element("email").getText(); boolean gender =
			 * s1.element("gender").getText().equals("男"); int age =
			 * Integer.parseInt(s1.element("age").getText()); int java =
			 * Integer.parseInt(s1.element("java").getText()); Student s = new
			 * Student(id, name, email, java, age, gender); r.add(s); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	private ArrayList<Student> load_jdom() {
		ArrayList<Student> r = new ArrayList<Student>();
		// 使用JDOM看起来要简单一些，包括命名空间的处理过程
		try {
			SAXBuilder builder = new SAXBuilder();
			Document d = builder.build(new FileInputStream("student.xml"));
			Element root = d.getRootElement();
			Namespace ns = root.getNamespace();
			List st = root.getChildren("student", ns);
			for (int i = 0; i < st.size(); i++) {
				Element stu = (Element) st.get(i);
				String id = stu.getChildText("id", ns);
				String name = stu.getChildText("name", ns);
				int age = Integer.parseInt(stu.getChildText("age", ns));
				boolean gender = stu.getChildText("gender", ns).equals("男");
				String email = stu.getChildText("email", ns);
				int java = Integer.parseInt(stu.getChildText("java", ns));
				Student s1 = new Student(id, name, email, java, age, gender);
				r.add(s1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	private ArrayList<Student> load_sax() {
		// 使用SAX编程，基于流和事件的处理方式，编程有点麻烦，但效率很高
		final ArrayList<Student> r = new ArrayList<Student>();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser p1 = factory.newSAXParser();
			p1.parse(new FileInputStream("student.xml"), new DefaultHandler() {
				// 匿名类定义
				private TAG_FLAG tag_flag = TAG_FLAG.none;
				private StringBuffer content = new StringBuffer();
				String id, name, email;
				int age, java;
				boolean gender;

				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					content = new StringBuffer();
					tag_flag = TAG_FLAG.none;
					// 解析到student元素的结束标记
					if (qName.equals("student")) {
						Student s = new Student(id, name, email, java, age,
								gender);
						r.add(s);
					}
				}

				// 解析到某元素的起始标记
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					if (qName.equals("id")) { // TAG_FLAG是枚举类型
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

				// 解析元素内容
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
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
}
