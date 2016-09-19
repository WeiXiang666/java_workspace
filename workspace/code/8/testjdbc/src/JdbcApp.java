import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*; //输出数据集，用到TableModel和DefaultTableModel
import java.util.*;
import java.sql.*;
import java.io.*;

public class JdbcApp extends JFrame implements ActionListener {
	/*
	 * 程序的GUI包括窗口，窗口中“拥有”JTextArea组件，用来输入SELECT
	 * 语句，JTable组件，用来输出显示查询的结果集数据，还包括菜单条和 菜单项等其他组件。
	 */
	// 多行文本输入组件，用来输入SQL SELECT语句
	JTextArea ta = new JTextArea();
	JTable table = new JTable(); // Swing表格组件，输出结果集中的数据
	JMenuBar mb1 = new JMenuBar(); // 菜单条
	JMenu menu1 = new JMenu("Setup"); // 菜单
	JMenu menu2 = new JMenu("Database"); // 菜单
	JMenuItem mi1 = new JMenuItem("jdbc config"); // 菜单项
	JMenuItem mi2 = new JMenuItem("connect"); // 菜单项
	JMenuItem mi3 = new JMenuItem("disconnect"); // 菜单项

	Connection con; // 数据库连接

	JTextField jtf1 = new JTextField(); // 单行文本输入组件，输入driverClassName
	JTextField jtf2 = new JTextField(); // 单行文本输入组件，输入jdbcUrl
	JTextField jtf3 = new JTextField(); // 单行文本输入组件，输入username
	JTextField jtf4 = new JTextField(); // 单行文本输入组件，输入password

	JButton btn1 = new JButton("保存"); // 保存配置信息
	JButton btn2 = new JButton("取消"); // 关闭对话框
	JButton btn3 = new JButton("执行"); // 执行SQL SELECT语句

	String driverClassName, jdbcUrl, username, password; // JDBC连接信息
	Properties props = new Properties(); // 属性集合，存储JDBC连接信息
	JDialog dialog; // JDBC 配置对话框

	/*
	 * ActionListener听众接口，处理按钮点击事件，菜单项点击事件，鼠标双击
	 * 列表框中的某一项等会产生ActionEvent事件，对应的事件处理方法为
	 * actionPerformed方法，定义在ActionListener接口中
	 */
	public void actionPerformed(ActionEvent ae) { // 菜单和按钮的公共的事件监听方法
		final Object s = ae.getSource(); // 得到事件源对象
		new Thread() { // 创建一个线程去处理事件
			public void run() {
				if (s == mi1) { // 点击配置JDBC连接信息
					dialog = new JDialog(JdbcApp.this, /* 创建模态对话框 */
					"JDBC Configuration Dialog", true);
					Container c = dialog.getContentPane();
					JPanel p1 = new JPanel();
					p1.setLayout(new GridLayout(4, 2)); // 设置网格布局为4行2列
					p1.add(new JLabel("JDBC Driver"));
					p1.add(jtf1);
					p1.add(new JLabel("JDBC URL"));
					p1.add(jtf2);
					p1.add(new JLabel("USERNAME"));
					p1.add(jtf3);
					p1.add(new JLabel("PASSWORD"));
					p1.add(jtf4);
					jtf1.setText(driverClassName); // 设置初始值
					jtf2.setText(jdbcUrl);
					jtf3.setText(username);
					jtf4.setText(password);

					JPanel p2 = new JPanel();
					p2.setLayout(new FlowLayout()); // 设置流布局
					p2.add(btn2);
					p2.add(btn1);

					btn2.addActionListener(JdbcApp.this); // 不能写this, this是指这个
					btn1.addActionListener(JdbcApp.this); // 匿名类实例

					c.setLayout(new BorderLayout());
					c.add(p1, BorderLayout.CENTER);
					c.add(p2, BorderLayout.SOUTH);

					dialog.setSize(new Dimension(350, 200));
					dialog.setVisible(true); // 显示对话框
				} else if (s == mi2) {
					try {
						if (con != null && !con.isClosed()) {// 如果已经连接数据库
							con.close(); // 则先断开数据库
						}
						Class.forName(driverClassName); // 开始连接数据库
						con = DriverManager.getConnection(jdbcUrl, username,
								password);
					} catch (Exception e) { // 如果加载驱动程序出错，或者连接错误
						JOptionPane.showMessageDialog(JdbcApp.this,
								e.getMessage(), "遇到异常",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (s == mi3) { // 执行关闭数据库连接，断开数据库连接
					try {
						if (con != null && !con.isClosed()) {
							con.close();
							con = null;
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(JdbcApp.this,
								e.getMessage(), "遇到异常",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (s == btn1) { // 保存JDBC配置信息并退出对话框
					try {
						/* 从单行文本输入组件中读取用户输入的配置信息 */
						driverClassName = jtf1.getText().trim();
						jdbcUrl = jtf2.getText().trim();
						username = jtf3.getText().trim();
						password = jtf4.getText().trim();
						/* 先保存到属性集合中 */
						props.setProperty("driverClassName", driverClassName);
						props.setProperty("jdbcUrl", jdbcUrl);
						props.setProperty("username", username);
						props.setProperty("password", password);
						/* 再保存到配置文件中 */
						props.store(new FileOutputStream("jdbc.properties"),
								"JDBC Configuration");
						dialog.dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(JdbcApp.this,
								e.getMessage(), "遇到异常",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (s == btn2) { // 取消按钮，直接关闭对话框
					dialog.dispose();
				} else if (s == btn3) { // 执行SQL SELECT语句
					try {
						if (con == null || con.isClosed()) { // 如果数据库没连接
							JOptionPane.showMessageDialog(JdbcApp.this,
									"数据库没有连接", "提示",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							String sql = ta.getText();
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(sql);
							// 根据查询结果创建一个表模型对象
							table.setModel(new MyTableModel(rs));
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(JdbcApp.this,
								e.getMessage(), "遇到异常",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}.start(); // 创建了一个匿名类对象，从Thread继承下来，程序显得稍长
	}

	/* 自定义表模型，从DefaultTableModel继承，实现了TableModel接口 */
	class MyTableModel extends DefaultTableModel {
		int rows, cols; // 行数，列数
		ResultSet rs; // 结果集数据
		ResultSetMetaData rsmd; // 结果集元数据，描述结果集的结构的类

		public MyTableModel(ResultSet rs) {
			this.rs = rs;
			try {
				if (rs != null) {
					rsmd = rs.getMetaData(); // 得到结果集元数据
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(JdbcApp.this, e.getMessage(),
						"遇到异常", JOptionPane.ERROR_MESSAGE);
			}
		}

		public int getRowCount() { // 返回表模型的行数
			try {
				if (rs.last()) { // 遍历到结果集的最后一条记录
					rows = rs.getRow(); // 得到当前游标的记录位置
				} else { // 如果结果集为空
					rows = 0;
				}
				return rows;
			} catch (Exception e) {
				rows = 0;
			}
			return rows;
		}

		public int getColumnCount() { // 返回表模型的列数
			try {
				if (rsmd != null) {
					cols = rsmd.getColumnCount(); // 从结果集元数据对象中读列数
					return cols;
				}
			} catch (Exception e) {
				cols = 0;
			}
			cols = 0;
			return cols;
		}

		public String getColumnName(int columnIndex) { // 得到列名
			try {
				if (rsmd != null) {// 结果集的列序号和表模型的列序号起始值不同
					return rsmd.getColumnName(columnIndex + 1);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(JdbcApp.this, e.getMessage(),
						"遇到异常", JOptionPane.ERROR_MESSAGE);
			}
			return null;
		}

		/* 得到表模型的列对应的类类型，使用了SQL TYPE MAPPING，见表8-1 */
		public Class<?> getColumnClass(int columnIndex) {
			try {
				int type = rsmd.getColumnType(columnIndex + 1);
				switch (type) {
				case Types.REAL:
					return Float.class;
				case Types.FLOAT:
				case Types.DOUBLE:
					return Double.class;
				case Types.INTEGER:
					return Integer.class;
				case Types.BOOLEAN:
				case Types.BIT:
					return Boolean.class;
				case Types.CHAR:
				case Types.VARCHAR:
					return String.class;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(JdbcApp.this, e.getMessage(),
						"遇到异常", JOptionPane.ERROR_MESSAGE);
			}
			return String.class;
		}

		/* 仅仅是显示结果集中的数据，所以表模型内容是不可编辑的 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		/*
		 * 读模型中的数据，输入是行列值，都是从0开始计数的， 返回的是Object类型，因为前面的getColumnClass已经定义了列的类型
		 * 所以返回的数据都用getObject方法读结果集，并没有再根据列的数据 类型来调用不同的ResultSet.getXXX(int
		 * colIndex)方法实现数据读入
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			try {
				if (rs != null) {
					rs.absolute(rowIndex + 1);
					return rs.getObject(columnIndex + 1);
				}
				return null;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(JdbcApp.this, e.getMessage(),
						"遇到异常", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		}
	}

	/* 下面是构造一个JFrame的初始化过程 */
	public JdbcApp() {
		super("Jdbc Application Demo");
		try { // 加载属性文件，从一个文件流中解析出属性数据集合
			props.load(new FileInputStream("jdbc.properties"));
			driverClassName = props.getProperty("driverClassName");
			jdbcUrl = props.getProperty("jdbcUrl");
			username = props.getProperty("username");
			password = props.getProperty("password");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "遇到异常",
					JOptionPane.ERROR_MESSAGE);
		}
		/* 下面是构建菜单，及为菜单项注册事件听众 */
		menu1.add(mi1);
		menu2.add(mi2);
		menu2.add(mi3);
		mb1.add(menu1);
		mb1.add(menu2);
		setJMenuBar(mb1);
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		// 设置布局
		ta.setPreferredSize(new Dimension(500, 100));
		ta.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel label = new JLabel("请输入SQL SELECT语句");
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());

		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(1, 2));
		panel4.add(label);
		JPanel panel5 = new JPanel();
		panel5.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btn3.setPreferredSize(new Dimension(80, 25));
		panel5.add(btn3);
		panel4.add(panel5);
		btn3.addActionListener(this);

		panel1.add(panel4, BorderLayout.NORTH);
		panel1.add(ta, BorderLayout.CENTER);

		table.setPreferredSize(new Dimension(500, 300));
		Container panel2 = getContentPane();
		panel2.setLayout(new BorderLayout());
		// table放入到滚动窗格中，适用于大量的表格数据显示，提供滚动条支持
		panel2.add(panel1, BorderLayout.NORTH);
		panel2.add(new JScrollPane(table), BorderLayout.CENTER);

		setSize(500, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new JdbcApp();
	}
}
