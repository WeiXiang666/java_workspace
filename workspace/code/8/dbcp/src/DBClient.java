//下面是客户端的程序：
import bean.Student;
import java.util.*;
import java.net.*;
import java.io.*;

public class DBClient {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 4321);
			Thread.sleep(100); // 等待服务器处理连接请求，处理对象流
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			String echo = (String) ois.readObject();
			System.out.println(echo); // 输出“握手”信息
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeInt(4); // 保存一个学生的信息
			oos.writeObject(new Student("003", "fs", "fs@abc.com", 21, 58, true));
			oos.flush();
			Thread.sleep(100); // 客户的数据库服务请求之间一般都是有时间间隔的
			oos.writeInt(4); // 保存另一个学生的信息
			oos.writeObject(new Student("004", "ws", "ws@abc.com", 21, 48, true));
			oos.flush();
			Thread.sleep(100);
			oos.writeInt(1); // 查询不及格的学生的名称
			oos.flush();
			List<Student> list = (List<Student>) ois.readObject();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getName());
			}
			s.close(); // 直接关闭Socket对象
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
