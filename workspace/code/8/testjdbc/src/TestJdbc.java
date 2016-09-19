import java.sql.*;

public class TestJdbc {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载MySQL驱动程序
			Connection con = DriverManager.getConnection( // 创建数据库连接
					"jdbc:mysql://localhost/test", "java", "java1234");
			Statement stmt = con.createStatement(); // 创建语句对象
			/* 执行MySQL内置时间函数，返回结果集中只有一条记录 */
			ResultSet rs = stmt.executeQuery("select now() as systime");
			rs.next(); // 移动游标到第一条记录，读结果集中的systime列的值
			System.out.println("now: " + rs.getTimestamp("systime"));
			
			rs.close(); // 关闭结果集
			rs = stmt.executeQuery("select * from student");
			while (rs.next()) {
				/* 遍历结果集，输出每条记录的name和gender列的值 */
				System.out.println("name: " + rs.getString("name")
						+ "\t\t gender: "
						+ (rs.getBoolean("gender") ? "男" : "女"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
