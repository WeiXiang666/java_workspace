package impl;

import java.util.ArrayList;
import java.io.*;
import java.net.*;
import base.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NetDataLoader implements DataLoader {
	public ArrayList<Student> load() {
		ArrayList<Student> r = new ArrayList<Student>();
		try {
			Socket s = new Socket("localhost", 1234);
			InputStreamReader isr = new InputStreamReader(s.getInputStream(),
					"GBK");
			OutputStreamWriter osw = new OutputStreamWriter(
					s.getOutputStream(), "GBK");
			PrintWriter out = new PrintWriter(osw);
			out.println("get"); // 发送命令给服务器
			out.flush();

			StringBuffer sb1 = new StringBuffer();
			char[] buff1 = new char[4096];
			int len = 0;
			// 从服务器读字符串数据，直到读完为止
			while ((len = isr.read(buff1)) != -1) {
				sb1.append(buff1, 0, len);
			}
			// 再从字符串解析回Java List集合
			JSONArray j1 = JSONArray.fromObject(sb1.toString());
			r = (ArrayList<Student>) JSONArray.toCollection(j1, Student.class);
			isr.close();
			out.close();
			osw.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/*
	 * 下面的程序是JSON编程的测试程序，相当于序列化和反序列化， 将Java对象转换为JSON字符串，再从该字符串转换回Java对象。
	 * 下面的代码演示了JavaBean和Java数组的转换过程。
	 */
	public static void main(String[] args) throws Exception {
		Student s1 = new Student("008", "zxx", "zxx@163.com", 99, 20, true);
		Student s2 = new Student("007", "james", "james@163.com", 100, 70, true);
		Student[] sa = new Student[] { s1, s2 };

		JSONObject o = JSONObject.fromObject(s1);
		System.out.println(o.toString());

		JSONObject o2 = JSONObject.fromObject(o.toString());
		Student s3 = (Student) JSONObject.toBean(o2, Student.class);
		System.out.println(s3);

		JSONArray a = JSONArray.fromObject(sa);
		System.out.println(a.toString());

		JSONArray a2 = JSONArray.fromObject(a.toString());
		Student[] sa2 = (Student[]) JSONArray.toArray(a2, Student.class);
		System.out.println(sa2.length);
	}
}
