package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckBoxDemo extends JPanel implements ItemListener {
	JCheckBox chinButton; // 对应4个复选框
	JCheckBox glassesButton;
	JCheckBox hairButton;
	JCheckBox teethButton;

	StringBuffer choices; // 复选框中的数据以字符串的形式存储
	JLabel pictureLabel; // 该标签用作显示图片，JLabel不仅可以显示

	// 文本，还可以显示Icon图像
	public CheckBoxDemo() {
		super(new BorderLayout()); // 选择边界布局
		chinButton = new JCheckBox("Chin");
		chinButton.setMnemonic(KeyEvent.VK_C);
		chinButton.setSelected(true);

		glassesButton = new JCheckBox("Glasses");
		glassesButton.setMnemonic(KeyEvent.VK_G);
		glassesButton.setSelected(true);

		hairButton = new JCheckBox("Hair");
		hairButton.setMnemonic(KeyEvent.VK_H);
		hairButton.setSelected(true);

		teethButton = new JCheckBox("Teeth");
		teethButton.setMnemonic(KeyEvent.VK_T);
		teethButton.setSelected(true);

		chinButton.addItemListener(this); // 注册共同的听众
		glassesButton.addItemListener(this);
		hairButton.addItemListener(this);
		teethButton.addItemListener(this);

		// 表示这个杂耍演员的当前状态
		choices = new StringBuffer("cght");

		// 设置图像标签
		pictureLabel = new JLabel();
		// pictureLabel.setFont(pictureLabel.getFont().deriveFont(Font.ITALIC));
		updatePicture(); // 显示图像

		// Put the check boxes in a column in a panel
		JPanel checkPanel = new JPanel(new GridLayout(0, 1));// X行1列
		checkPanel.add(chinButton); // 可以设置为4行1列
		checkPanel.add(glassesButton); // 不指定行也可以
		checkPanel.add(hairButton);
		checkPanel.add(teethButton);

		add(checkPanel, BorderLayout.LINE_START);
		add(pictureLabel, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}

	/** 监听JCheckBox的点击事件. */
	public void itemStateChanged(ItemEvent e) { // ItemEvent对应的事件处理方法
		int index = 0;
		char c = '-';
		Object source = e.getItemSelectable(); // 获得事件源
		if (source == chinButton) { // 判断事件源
			index = 0; // 设置字符位置
			c = 'c';
		} else if (source == glassesButton) {
			index = 1;
			c = 'g';
		} else if (source == hairButton) {
			index = 2;
			c = 'h';
		} else if (source == teethButton) {
			index = 3;
			c = 't';
		}

		// 如果状态是未选中则字符为-
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			c = '-';
		}
		// 更改显示控制字符串.
		choices.setCharAt(index, c);
		updatePicture(); // 更新图像
	}

	protected void updatePicture() {
		// 得到相应的Icon图像文件，由choices的内容决定
		ImageIcon icon = createImageIcon("images/geek/geek-"
				+ choices.toString() + ".gif");
		pictureLabel.setIcon(icon);
		pictureLabel.setToolTipText(choices.toString());
		if (icon == null) {
			pictureLabel.setText("Missing Image");
		} else {
			pictureLabel.setText(null);
		}
	}

	/* 加载图像，返回一个ImageIcon */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = CheckBoxDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("CheckBoxDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent newContentPane = new CheckBoxDemo();
		newContentPane.setOpaque(true); // conentPane必须为不透明
		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
