package server;

import java.net.*;
import java.io.*;
import java.util.*;
import bean.Student;

public class CrudProtocol implements IOStrategy { // IOStrategy.java见第6章的例子
	CrudService servant = new CrudService(); // 多线程共享服务对象，共用一个连接池

	public void service(Socket socket) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream()); // 对象输入流，对象输出流
			oos.writeObject("echo"); // 先输出一个字符串，“握手”
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			while (true) {
				int command = ois.readInt();
				switch (command) {
				case 1: // findFailed
					List<Student> list = servant.findFailed();
					oos.writeObject(list);
					oos.flush();
					break;
				case 2: // findExcellent
					List<Student> list2 = servant.findExcellent();
					oos.writeObject(list2);
					oos.flush();
					break;
				case 3: // delete
					String id = ois.readUTF();
					servant.delete(id);
					break;
				case 4: // insertOrUpdate
					Student s = (Student) ois.readObject();
					servant.insertOrUpdate(s);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("protocol finished.");
		}
	}
}
