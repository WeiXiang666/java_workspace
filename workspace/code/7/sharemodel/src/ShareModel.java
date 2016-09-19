import java.awt.*; //Swing借鉴了一些java.awt包中的容器类等
import java.awt.event.*; //还借鉴了一些AWT事件包中的一些类
import javax.swing.*; //Swing包
import java.util.*;

public class ShareModel extends JFrame { // ShareModel.java，两个组件共享一个模型
	MyModel model = new MyModel(); // 创建一个模型，代码在下面
	JSlider s1 = new JSlider(); // 创建滑块控件对象
	JScrollBar s2 = new JScrollBar(JScrollBar.HORIZONTAL); // 创建滚动条控件
	JButton btn = new JButton("random range!"); // 创建一个按钮对象

	public ShareModel() {
		super("JSlider and JScrollBar share model");
		Container c = getContentPane(); // 得到内容窗格，不能直接向JFrame中添加，
		c.setLayout(null); // 应该加到JFrame的某个窗格中
		s1.setModel(model); // 设置模型
		s2.setModel(model);
		s1.setBounds(100, 30, 120, 25); // setBounds(int x, int y, int width,
										// int height)
		s2.setBounds(100, 100, 120, 25);
		btn.setBounds(100, 180, 120, 25);

		btn.addActionListener(new ActionListener() { // 使用了匿名类
			public void actionPerformed(ActionEvent ae) {
				new Thread() { // 再次使用匿名类
					public void run() {
						Random r = new Random();
						for (int i = 0; i < 10; i++) {
							try {
								Thread.sleep(300);
							} catch (Exception e) {
							} // 点击按钮后，
							model.setValue(r.nextInt(100)); // 随机修改模型的数据
						}
					}
				}.start();
			}
		});

		c.add(s1); // 向内容窗格中添加组件
		c.add(s2);
		c.add(btn);

		setSize(350, 300);
		setVisible(true);
		/* 点击JFrame的关闭按钮所执行的行为，关闭则退出 */
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ShareModel();
	}
}
