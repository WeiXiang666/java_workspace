package gui;

import base.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		/* 加载数据，并将数据输出到控制台 */
		DataLoaderFactory factory = new DataLoaderFactory();
		DataLoader loader = factory.getDataLoader();
		ArrayList<Student> s = loader.load();
		for (Iterator<Student> it = s.iterator(); it.hasNext();) {
			System.out.println(it.next());
		}
		/* 将加载的数据集合输出到student2.xml文件中 */
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("GBK");
		Element root = document.addElement("students");
		root.addNamespace("", "http://www.abc.com/ns/test"); // 添加命名空间
		root.addNamespace("t", "http://www.abc.com/ns/test");
		root.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.addAttribute("xsi:schemaLocation",
				"http://www.abc.com/ns/test student.xsd");

		for (int i = 0; i < s.size(); i++) {
			Student stu = s.get(i);
			Element e1 = root.addElement("student",
					"http://www.abc.com/ns/test");
			// e1.addNamespace("", "http://www.abc.com/ns/test");

			e1.addElement("id").addText(stu.getId());
			e1.addElement("name").addText(stu.getName());
			e1.addElement("email").addText(stu.getEmail());
			e1.addElement("gender").addText(stu.getGender() ? "男" : "女");
			e1.addElement("age").addText("" + stu.getAge());
			e1.addElement("java").addText("" + stu.getJava());

		}
		FileOutputStream fos = new FileOutputStream("student2.xml");
		OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
		OutputFormat of = new OutputFormat();
		of.setEncoding("GBK"); // 设置编码
		of.setIndent(true); // 设置XML元素缩进格式
		of.setIndent("    "); // 设置缩进空白字符串为4个空白符
		of.setNewlines(true); // 分行存储
		XMLWriter writer = new XMLWriter(osw, of);
		writer.write(document);
		writer.close();

		MainJFrame mj = new MainJFrame(s); // 显示GUI窗口
	}
}
