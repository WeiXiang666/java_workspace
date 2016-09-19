package impl;

import java.util.ArrayList;
import base.*;
import java.sql.*;

public class DatabaseDataLoader implements DataLoader {
	public ArrayList<Student> load() {
		ArrayList<Student> r = new ArrayList<Student>();
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载驱动程序
			Connection con = DriverManager.getConnection(// 创建数据库连接
					"jdbc:mysql://localhost/student", "java", "java1234");
			Statement stmt = con.createStatement(); // 创建语句对象
			ResultSet rs = stmt.executeQuery("select * from student");
			while (rs.next()) { // 遍历结果集
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				boolean gender = rs.getBoolean("gender");
				int age = rs.getInt("age");
				int java = rs.getInt("java");
				Student s = new Student(id, name, email, java, age, gender);
				r.add(s);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/*
	 * 下面这个应用的入口方法中实现了数据库表中初始数据的加载过程， 从Excel文件中把数据读出来存入到数据表中
	 */
	public static void main(String[] args) {
		try { // DataLoaderFactory.java在后面会有介绍
			DataLoaderFactory factory = new DataLoaderFactory();
			DataLoader loader = factory.getDataLoader("impl.ExcelDataLoader");
			ArrayList<Student> r = loader.load(); // 加载到集合中

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/student", "java", "java1234");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("truncate table student"); // 删除表内容
			stmt.close();

			PreparedStatement pstmt = con
					.prepareStatement("insert into student(id,name,email,gender,age,java) values(?,?,?,?,?,?)");
			for (int i = 0; i < r.size(); i++) { // 遍历数据集合
				Student s = r.get(i);
				// 设置上面预编译语句中6个占位符数据
				pstmt.setString(1, s.getId());
				pstmt.setString(2, s.getName());
				pstmt.setString(3, s.getEmail());
				pstmt.setBoolean(4, s.getGender());
				pstmt.setInt(5, s.getAge());
				pstmt.setInt(6, s.getJava());
				pstmt.executeUpdate();
			}
			pstmt.close();
			con.close();
			System.out.println("done.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
