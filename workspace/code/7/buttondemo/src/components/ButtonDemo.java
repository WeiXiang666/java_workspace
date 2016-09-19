package components;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/* 
 * ButtonDemo.java 需要下列文件
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class ButtonDemo extends JPanel implements ActionListener {
	protected JButton b1, b2, b3;

	public ButtonDemo() {
		// 创建3个ImageIcon
		ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
		ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
		ImageIcon rightButtonIcon = createImageIcon("images/left.gif");

		b1 = new JButton("Disable middle button", leftButtonIcon);
		// 垂直方向，文字居中
		b1.setVerticalTextPosition(AbstractButton.CENTER);
		// 文字部分靠左边
		b1.setHorizontalTextPosition(AbstractButton.LEADING);
		b1.setMnemonic(KeyEvent.VK_D); // 快捷键设置为Alt+D
		b1.setActionCommand("disable"); // 设置actionCommand属性

		b2 = new JButton("Middle button", middleButtonIcon);
		b2.setVerticalTextPosition(AbstractButton.BOTTOM);
		b2.setHorizontalTextPosition(AbstractButton.CENTER);
		b2.setMnemonic(KeyEvent.VK_M);

		b3 = new JButton("Enable middle button", rightButtonIcon);
		// 使用默认的状态，垂直居中，水平居于右侧
		// b3.setHorizontalTextPosition(AbstractButton.TRAILING);
		b3.setMnemonic(KeyEvent.VK_E);
		// 在多个按钮共用一个事件听众时，在事件处理方法中判断
		// 哪个按钮被按下时，actionCommand属性将被使用
		b3.setActionCommand("enable");
		b3.setEnabled(false);

		// Listen for actions on buttons 1 and 3.
		b1.addActionListener(this);
		b3.addActionListener(this);
		// 为按钮添加工具提示，当鼠标放在按钮上一会儿时出现提示文本
		b1.setToolTipText("Click this button to disable the middle button.");
		b2.setToolTipText("This middle button does nothing when you click it.");
		b3.setToolTipText("Click this button to enable the middle button.");

		// 添加这些按钮到面板中，面板类的默认布局是FlowLayout
		add(b1);
		add(b2);
		add(b3);
	}

	public void actionPerformed(ActionEvent e) {
		if ("disable".equals(e.getActionCommand())) {
			b2.setEnabled(false); // 更改按钮状态
			b1.setEnabled(false);
			b3.setEnabled(true);
		} else {
			b2.setEnabled(true);
			b1.setEnabled(true);
			b3.setEnabled(false);
		}
	}

	/** 返回一个ImageIcon，如果路径不对或图标文件不存在则返回null */
	protected static ImageIcon createImageIcon(String path) {
		// 使用ButtonDemo类的类加载器来加载文件
		// 这要求资源文件和类在同一个加载目录
		java.net.URL imgURL = ButtonDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private static void createAndShowGUI() {
		// 创建窗口并设置
		JFrame frame = new JFrame("ButtonDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		ButtonDemo newContentPane = new ButtonDemo();
		newContentPane.setOpaque(true); // 不透明
		frame.setContentPane(newContentPane);
		frame.pack(); // 自动调整窗口大小
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// 视图窗口的建立，GUI组件的创建及设置都应该在GUI事件派发处理结束后
		// 再执行，对系统的UI外观刷新更有好处，比直接调用createAndShowGUI更
		// 安全一些。如果是直接调用createAndShowGUI方法，那么上面这些工作是
		// 由main主线程来执行的，而下面这样写程序是由EventDispatcher
		// 线程来执行的，它会等所有的AWT Event事件都处理后才执行。
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
