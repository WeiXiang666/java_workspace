package com.abc.bean;

import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;
import java.beans.*;

public class BeanFactory {
	private Properties props = new Properties(); // Properties属性集合，名值对数据集合
	private Map beans = new TreeMap();

	public BeanFactory() {
		try {
			props.load(new FileInputStream("app.properties")); // 加载属性配置文件
			String xmlFile = props.getProperty("configFile"); // 属性名
			SAXReader reader = new SAXReader(); // DOM4J编程
			Document doc = reader.read(new File(xmlFile));
			org.dom4j.XPath path = doc.createXPath("/beans/bean");// 遍历bean元素
			List list = path.selectNodes(doc);
			ClassLoader cl1 = getClass().getClassLoader(); // 获得类加载器
			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				String name = node.attributeValue("name");
				String clazz = node.attributeValue("class");
				// 利用java.beans.Beans类来完成动态的创建某个类的实例
				// 可以比较下面两行程序
				// Object a = new A();
				// Object b = Beans.instantiate(cl1, "A");
				beans.put(name, Beans.instantiate(cl1, clazz));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getBean(String name) {
		return beans.get(name);
	}

	public static void main(String[] args) {
		BeanFactory factory = new BeanFactory();
		StringBuffer str = (StringBuffer) factory.getBean("b2");
		str.append("hello world!");
		System.out.println(str.toString());
	}
}
