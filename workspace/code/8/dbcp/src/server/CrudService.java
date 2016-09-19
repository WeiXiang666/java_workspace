package server;

import java.util.*;
import java.io.*;
import javax.sql.DataSource;
import java.sql.*;
import org.apache.commons.dbcp.*; //可以到www.apache.org下载commons-dbcp
/* Student.java类，限于篇幅就不提供源码了，该类实现了java.io.Serializable接口，
 提供序列化支持，因为要通过对象流传递Student数据对象 */
import bean.Student;

public class CrudService {
	DataSource ds; // javax.sql包中的数据源接口，多线程共用一个连接池

	public CrudService() {
		ds = setupDataSource(); // 创建一个连接池并封装为数据源
	}

	public List<Student> find(String sql) { // 执行查询语句
		List<Student> list = new ArrayList<Student>();
		try {
			Connection con = ds.getConnection(); // 从连接池中获得一个数据库连接
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getString("id")); // 学生类有id属性
				s.setName(rs.getString("name")); // 学生类有name属性
				s.setEmail(rs.getString("email")); // 学生类有email属性
				s.setAge(rs.getInt("age")); // 学生类有age属性
				s.setJava(rs.getInt("java")); // 学生类有java属性
				s.setGender(rs.getBoolean("gender")); // 学生类有gender属性
				list.add(s);
			}
			rs.close();
			stmt.close();
			/*
			 * 使用完毕后，尽快把连接关闭，注意调用这个连接池中的实现 Connection接口的对象的close方法，该方法被重写为释放连接
			 * 送回到连接池，连接的状态为空闲，并非真正的把数据库连接关闭掉。
			 */
			con.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Student> findFailed() { // 查询不及格的学生名单
		return find("select * from student where java<60");
	}

	public List<Student> findExcellent() { // 查询优秀的学生名单
		return find("select * from student where java>=90");
	}

	public void insertOrUpdate(Student s) { // 保存Student对象到数据库
		try {
			Connection con = ds.getConnection();
			/*
			 * 预编译PreparedStatement语句，带参数的SQL语句， 参数使用占位符?来表示
			 */
			PreparedStatement pstmt = con
					.prepareStatement("select id from student where id=?");
			pstmt.setString(1, s.getId()); // 设置，替换参数的值，占位符从1开始计数
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 如果该记录已经在数据库，那么去update
				pstmt = con
						.prepareStatement("update student set name=?, email=?, java=?, age=?,gender=? where id = ?");
				pstmt.setString(1, s.getName()); // 第一个参数是name
				pstmt.setString(2, s.getEmail()); // 第二个参数是email
				pstmt.setInt(3, s.getJava());
				pstmt.setInt(4, s.getAge());
				pstmt.setBoolean(5, s.isGender());
				pstmt.setString(6, s.getId());
				pstmt.executeUpdate();
			} else { // 如果该记录不存在，则执行insert语句
				pstmt = con
						.prepareStatement("insert into student(id,name,email,java,age,gender) values(?,?,?,?,?,?)");
				pstmt.setString(1, s.getId());
				pstmt.setString(2, s.getName());
				pstmt.setString(3, s.getEmail());
				pstmt.setInt(4, s.getJava());
				pstmt.setInt(5, s.getAge());
				pstmt.setBoolean(6, s.isGender());
				pstmt.executeUpdate();
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String id) {
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("delete from student where id=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DataSource setupDataSource() {
		try {
			Properties p = new Properties(); // 读配置信息
			p.load(new FileInputStream("jdbc.properties"));
			// 创建DBCP提供的数据源，该类实现了DataSource接口
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName(p.getProperty("driverClassName"));
			ds.setUsername(p.getProperty("username"));
			ds.setPassword(p.getProperty("password"));
			ds.setUrl(p.getProperty("jdbcUrl"));
			ds.setMaxActive(100); // 设置连接池中最大连接数
			ds.setMaxIdle(20); // 设置连接池中最大空闲数
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}

	public static void printDataSourceStats(DataSource ds) { // 输出数据源的状态信息
		BasicDataSource bds = (BasicDataSource) ds;
		System.out.println("NumActive: " + bds.getNumActive());
		System.out.println("NumIdle: " + bds.getNumIdle());
	}
}
