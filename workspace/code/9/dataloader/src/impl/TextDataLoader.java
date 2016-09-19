package impl;

import base.*;
import java.util.*;
import java.io.*;

public class TextDataLoader implements DataLoader {
	public ArrayList<Student> load() {
		ArrayList<Student> r = new ArrayList<Student>();

		try {
			/*
			 * 以流的方式，读取数据，先使用字节流来读取文件， 数据当然是一个字节一个字节的方式读取的，然后使用
			 * 字节流到字符流的转换处理流，再以字符形式读取文件内容， 最后使用BufferedReader来缓存字符数据，并处理回车换行，
			 * 以行文本数据的形式读入数据
			 */
			FileInputStream fis = new FileInputStream("student.txt");
			InputStreamReader isr = new InputStreamReader(fis, "GBK");
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			while ((s = br.readLine()) != null) { // 读取数据直到全部读完
				s = s.trim();
				if (s.length() == 0)
					continue; // 读到空行数据则继续读下一行
				/*
				 * 使用StringTokenizer来分割字符串， 也可以使用String类的split方法，分隔符是逗号
				 */
				StringTokenizer st = new StringTokenizer(s, ",");
				/* 逐个数据项的读取过程 */
				String id = st.nextToken().trim();
				String name = st.nextToken().trim();
				String email = st.nextToken().trim();
				int age = Integer.parseInt(st.nextToken().trim());
				int java = Integer.parseInt(st.nextToken().trim());
				boolean gender = st.nextToken().trim().equals("男");
				// 将数据记录转换处理为Student对象
				Student stu = new Student(id, name, email, java, age, gender);
				r.add(stu);
			}
			br.close();
			isr.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	public static void main(String[] args) {
		//测试程序
		DataLoader loader = new TextDataLoader();
		ArrayList<Student> st = loader.load();
		for(Iterator<Student> it = st.iterator(); it.hasNext(); ) {
			Student s = it.next();
			System.out.println(s);
		}
	}
}
