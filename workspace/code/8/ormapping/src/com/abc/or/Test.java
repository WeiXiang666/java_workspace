package com.abc.or;

import java.lang.reflect.*; //Java反射包

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.io.*;

import org.apache.ibatis.io.Resources; //MyBatis包
import org.apache.ibatis.session.*; //MyBatis包
import org.apache.log4j.Logger; //Log4j
import org.apache.log4j.PropertyConfigurator;

import org.hibernate.Query; //Hibernate包
import org.hibernate.Session;

import org.dom4j.*; //Dom4j包
import org.dom4j.io.*;

public class Test {
	// 定义全局日志管理对象，获得dom4j的rootLogger
	public static Logger log = Logger.getRootLogger();

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure( "log4j.properties" );  //配置log4j
		
		Student s1 = new Student("002", "拓跋", "tb@sina.com", 60, 75, true);
		Class clazz = s1.getClass(); // 获得Student对应的元类Class实例

		Field[] fields = clazz.getDeclaredFields(); // 利用反射机制，获得类的数据成员
		for (int i = 0; i < fields.length; i++) { // 为了演示，这里一个一个的遍历
			if (fields[i].getName().equals("java")) { // 判断是否是java数据属性成员
				fields[i].setAccessible(true); // 允许访问，因为java成员是私有的
				fields[i].set(s1, new Integer(80)); // 修改该数据成员的值
			}// 实际上没有必要遍历，可以直接调用Field getDeclaredField(String name)
		}
		log.info(s1); // 日志输出修改了java成绩的学生对象
		Method[] methods = clazz.getDeclaredMethods(); // 反射机制，遍历类的方法成员
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals("study")) { // 方法名称可以由字符串来表示
				methods[i].invoke(s1, new Integer(10)); // 执行该方法，动态的反射机制
			} // 实现动态方法调用。
		}
		log.info(s1);

		Test t = new Test();
		s1 = new Student("111", "demo", "demo@abc.com", 18, 66, true);
		t.saveOrUpdate(s1); // 将该对象保存到数据库中

		t.hibernate(); // 使用Hibernate操作数据库，代码在下面。

		t.mybatis(); // 使用MyBatis操作数据库，代码在下面。
	}

	public void saveOrUpdate(Object o) {
		try {
			// 存放实体类到数据库表的映射
			Map<String, String> map1 = new HashMap<String, String>();
			// 存放实体类到数据库属性的映射，属性存放在一个List集合中
			Map<String, List<Property>> map2 = new HashMap<String, List<Property>>();
			// 存放实体类到主键属性的映射
			Map<String, Property> map3 = new HashMap<String, Property>();
			// 使用dom4j读demo.xml配置文件
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new File("demo.xml"));
			org.dom4j.XPath path = doc.createXPath("/demo/mapping");
			// 遍历每个mapping映射信息，本例子中只有一个mapping子元素
			List list = path.selectNodes(doc);
			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				Element c = node.element("class"); // 得到class子元素
				Element t = node.element("table"); // 得到table子元素
				map1.put(c.getText(), t.getText());
				List<Property> al = new ArrayList<Property>();// 存放所有的property
				List list2 = node.elements("property"); // 得到全部的property子元素
				for (int j = 0; j < list2.size(); j++) {
					Property p1 = new Property();
					Element p = (Element) list2.get(j);
					p1.setName(p.getText());
					p1.setType(p.attributeValue("type"));
					p1.setColumn(p.attributeValue("column"));
					String id = p.attributeValue("id");
					// 判断该属性是否是主键
					p1.setId((id == null) ? false : id.equals("true"));
					if (p1.getId()) {
						map3.put(c.getText(), p1); // 添加到实体类到主键的映射中
					}
					al.add(p1);
				}
				map2.put(c.getText(), al);
			}
			/*
			 * 上面的程序将配置文件demo.xml处理后，将全部映射信息放到三个 Map集合中，下面的代码将判断传入的实体的类型，根据映射文件
			 * 生成SQL语句，同时判断该数据实体是否已经在数据库中了，然后 选择执行INSERT语句或者UPDATE语句。
			 */
			Class clazz = o.getClass();
			String className = clazz.getName(); // 得到该实体类的类名

			/*
			 * 下面的程序继续使用dom4j读取demo.xml文件中连接信息， 为创建数据库连接做准备。
			 */
			String driver = ((Element) doc
					.selectSingleNode("/demo/jdbc/driver")).getText();
			String url = ((Element) doc.selectSingleNode("/demo/jdbc/url"))
					.getText();
			String user = ((Element) doc.selectSingleNode("/demo/jdbc/user"))
					.getText();
			String password = ((Element) doc
					.selectSingleNode("/demo/jdbc/password")).getText();

			Class.forName(driver); // 加载该驱动程序，获得数据库连接。
			Connection con = DriverManager.getConnection(url, user, password);

			String sqlSelect = "";
			Property id = map3.get(className); // 获得该实体类的主键属性
			/*
			 * count(*), count(1), count(id)三种写法执行效率有差别， 在有主键，并对主键进行count时最快
			 */
			sqlSelect = "SELECT count(" + id.getColumn() + ") FROM "
					+ map1.get(className) + " WHERE " + id.getColumn() + " = ?";

			PreparedStatement pstmt = con.prepareStatement(sqlSelect);
			/*
			 * 日志输出调试信息，如果想看到该调试信息，可以设置log4j.properties 将log4j.rootLogger=INFO,
			 * Console, LogFile修改为 log4j.rootLogger=DEBUG, Console, LogFile
			 */
			log.debug(sqlSelect); // 输出调试信息
			Field f = clazz.getDeclaredField(id.getName()); // 访问id的值
			f.setAccessible(true);

			if (id.getType().equals("int")) { // 这里仅支持主键属性类型为int和String
				pstmt.setInt(1, f.getInt(o));
			} else if (id.getType().equals("java.lang.String")
					|| id.getType().equals("String")) {
				pstmt.setString(1, f.get(o).toString());
			}
			// 给预编译语句设置完参数后，执行该语句
			ResultSet rs1 = pstmt.executeQuery();
			rs1.next();
			int rows = rs1.getInt(1);
			if (rows == 0) { // 该数据记录不存在，执行INSERT
				// 合成INSERT语句
				String sqlInsert = "INSERT INTO " + map1.get(className) + "(";
				// 遍历属性集合
				List<Property> list3 = map2.get(className);
				String params = "";
				for (int k = 0; k < list3.size(); k++) {
					// 同时生成属性列名字列表和占位符参数列表
					sqlInsert = sqlInsert + list3.get(k).getColumn() + ",";
					params = params + "?,";
				}
				params = params.substring(0, params.length() - 1); // 去掉最后的逗号
				sqlInsert = sqlInsert.substring(0, sqlInsert.length() - 1)
						+ ") VALUES(" + params + ")";

				pstmt.close();
				pstmt = con.prepareStatement(sqlInsert);
				log.debug(sqlInsert);

				// 预编译语句已经生成，下面开始设置参数数据
				for (int k = 0; k < list3.size(); k++) {
					Property p2 = list3.get(k);
					Field f1 = clazz.getDeclaredField(p2.getName());
					f1.setAccessible(true);
					/*
					 * 下面的程序实现类型映射，类型映射到setXXX方法， 这段代码需要重构来提高复用性。
					 */
					if (p2.getType().equals("int")) {
						pstmt.setInt(k + 1, f1.getInt(o));
					}
					if (p2.getType().equals("long")) {
						pstmt.setLong(k + 1, f1.getLong(o));
					}
					if (p2.getType().equals("short")) {
						pstmt.setShort(k + 1, f1.getShort(o));
					}
					if (p2.getType().equals("byte")) {
						pstmt.setByte(k + 1, f1.getByte(o));
					} else if (p2.getType().equals("java.lang.String")
							|| p2.getType().equals("String")) {
						pstmt.setString(k + 1, f1.get(o).toString());
					} else if (p2.getType().equals("float")) {
						pstmt.setFloat(k + 1, f1.getFloat(o));
					} else if (p2.getType().equals("double")) {
						pstmt.setDouble(k + 1, f1.getDouble(o));
					} else if (p2.getType().equals("java.math.BigDecimal")) {
						pstmt.setBigDecimal(k + 1,
								new BigDecimal(f1.getDouble(o)));
					} else if (p2.getType().equals("boolean")) {
						pstmt.setBoolean(k + 1, f1.getBoolean(o));
					}
				}
				// 参数全部设置完毕后，执行该预编译语句。
				pstmt.executeUpdate();
				pstmt.close();

			} else { // 如果该数据记录已经存在，那么执行UPDATE语句
				String sqlUpdate = "UPDATE " + map1.get(className) + " SET ";
				List<Property> list3 = map2.get(className);

				for (int k = 0; k < list3.size(); k++) {
					if (!list3.get(k).equals(id)) { // 主键属性作为WHERE条件处理
						sqlUpdate = sqlUpdate + list3.get(k).getColumn()
								+ " = ?,";
					}
				}

				sqlUpdate = sqlUpdate.substring(0, sqlUpdate.length() - 1)
						+ " WHERE " + id.getColumn() + " = ?";
				pstmt.close();
				pstmt = con.prepareStatement(sqlUpdate);
				log.debug(sqlUpdate);
				int col = 0; // 占位符的位置
				for (int k = 0; k < list3.size(); k++) {
					Property p2 = list3.get(k);
					// 由于主键属性放在UPDATE语句的最后，
					// 所以需要处理占位符的位置
					if (!p2.equals(id)) {
						col++;
						Field f1 = clazz.getDeclaredField(p2.getName());
						f1.setAccessible(true);
						if (p2.getType().equals("int")) {
							pstmt.setInt(col, f1.getInt(o));
						}
						if (p2.getType().equals("long")) {
							pstmt.setLong(col, f1.getLong(o));
						}
						if (p2.getType().equals("short")) {
							pstmt.setShort(col, f1.getShort(o));
						}
						if (p2.getType().equals("byte")) {
							pstmt.setByte(col, f1.getByte(o));
						} else if (p2.getType().equals("java.lang.String")
								|| p2.getType().equals("String")) {
							pstmt.setString(col, f1.get(o).toString());
						} else if (p2.getType().equals("float")) {
							pstmt.setFloat(col, f1.getFloat(o));
						} else if (p2.getType().equals("double")) {
							pstmt.setDouble(col, f1.getDouble(o));
						} else if (p2.getType().equals("java.math.BigDecimal")) {
							pstmt.setBigDecimal(col,
									new BigDecimal(f1.getDouble(o)));
						} else if (p2.getType().equals("boolean")) {
							pstmt.setBoolean(col, f1.getBoolean(o));
						}
					}
				}

				// 最后的主键属性参数处理
				col++;
				if (id.getType().equals("int")) {
					pstmt.setInt(col, f.getInt(o));
				} else if (id.getType().equals("java.lang.String")
						|| id.getType().equals("String")) {
					pstmt.setString(col, f.get(o).toString());
				}

				pstmt.executeUpdate();
				pstmt.close();

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} // saveOrUpdate(Object o)方法结束，可以保存任意类型的对象，只要该
	} // 类型的映射文件存在并且正确，本例子中假设映射文件书写正确。

	public void hibernate() { // 使用Hibernate来实现ORM，代码比上面的方法精简多了
		try {
			
			Student s1 = new Student("007", "邦德", "james@abc.com", 60, 100,
					true);
			Student s2 = new Student("008", "周星星", "zxx@abc.com", 40, 100, true);
			Student s3 = new Student("009", "张若兰", "zrl@abc.com", 20, 80, false);
		
			// 开始数据库操作，得到一个数据库Session
			Session session = HibernateSessionFactory.getSession();
			
			
			// 开始数据库事务
			session.beginTransaction();
			// 调用Hibernate提供的方法，保存这三个数据对象
			session.saveOrUpdate(s1);
			session.saveOrUpdate(s2);
			session.saveOrUpdate(s3);
			
			session.getTransaction().commit(); // 提交数据库事务
			// 执行数据库查询
			Query q = session
					.createQuery("from Student where java between :f1 and :f2");
			q.setInteger("f1", 60);
			q.setInteger("f2", 80);

			List<Student> l = q.list();
			session.getTransaction().begin();
			for (Iterator<Student> it = l.iterator(); it.hasNext();) {
				Student s = it.next();
				log.info(s);
				s.setJava(s.getJava() + 5); // 将Java成绩在60分和80分之间的，
											// session.update(s); //加上5分。
			}

			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 下面是利用MyBatis来实现ORM，和Hibernate比较类似。
	public void mybatis() {
		try {
			/*
			 * 和Hibernate一样，也是工厂模式，最后将数据库操作封装在session 中，
			 * session对象的创建也是通过session工厂，甚至类的命名都很相像
			 */
			String resource = "Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(reader);
			SqlSession session = sqlSessionFactory.openSession();
			/*
			 * 返回一条记录，注意com.abc.or.selectStudent是映射文件中
			 * 一条select语句对应的id，当然要加上namespace
			 */
			Student s = (Student) session.selectOne("com.abc.or.selectStudent",
					"008");
			log.info(s);

			s.setJava(95); // 修改成绩，执行对应的update语句，
			session.update("com.abc.or.updateStudent", s); // 传入参数s
			session.commit();

			HashMap map = new HashMap(); // 参数也可以是Map集合
			map.put("value1", 60); // 以名值对的形式传入数据
			map.put("value2", 100);

			List<Student> list = session.selectList(
					"com.abc.or.selectStudentBetween", map);
			for (Iterator<Student> it = list.iterator(); it.hasNext();) {
				s = it.next();
				log.info(s);
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // MyBatis实现结束，对于喜欢SQL的开发人员来说，MyBatis不错，
} // 效率比Hibernate高一些。
