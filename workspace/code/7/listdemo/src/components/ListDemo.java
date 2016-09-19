package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ListDemo extends JPanel // DocumentListener是文档事件的听众接口
		implements ListSelectionListener, ActionListener, DocumentListener {

	private JList list;
	private DefaultListModel listModel;
	private static final String hireString = "Hire";
	private static final String fireString = "Fire";
	private JButton fireButton;
	private JButton hireButton;
	private JTextField employeeName; // 文本输入组件，其对应的模型是Document

	// Document会产生DocumentEvent事件
	public ListDemo() {
		super(new BorderLayout()); // 设置边界布局管理器

		listModel = new DefaultListModel(); // 创建列表模型对象
		listModel.addElement("Jane Doe"); // 添加列表项数据
		listModel.addElement("John Smith");
		listModel.addElement("Kathy Green");

		// 创建JList对象，并放入JScrollPane中
		list = new JList(listModel);
		// 设置列表组件的选择模式是单选模式，只能选中一个列表项，默认是多选
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0); // 选中第一个列表项
		list.addListSelectionListener(this); // 添加选项变化事件对应的事件听众
		list.setVisibleRowCount(5); // 设置为显示5行列表项
		// 添加到JScrollPane，提供滚动支持
		JScrollPane listScrollPane = new JScrollPane(list);

		hireButton = new JButton(hireString);
		hireButton.setActionCommand(hireString); // 并没有使用ActionCommand
		hireButton.addActionListener(this);
		hireButton.setEnabled(false); // 按钮灰掉

		fireButton = new JButton(fireString);
		fireButton.setActionCommand(fireString);
		fireButton.addActionListener(this);

		employeeName = new JTextField();
		employeeName.setPreferredSize(new Dimension(150, 25));
		employeeName.addActionListener(this);
		// 为文档模型对象注册听众
		employeeName.getDocument().addDocumentListener(this);

		// 创建一个面板，放置按钮和文本输入组件
		JPanel buttonPane = new JPanel();
		// 居中摆放，并指定水平方向和垂直方向的缝隙大小
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPane.add(fireButton);
		buttonPane.add(employeeName);
		buttonPane.add(hireButton);

		buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		add(listScrollPane, BorderLayout.CENTER);
		add(buttonPane, BorderLayout.PAGE_END);
	}

	/*
	 * 当前类实例是事件听众，是两个按钮和单行文本组件的共同的事件听众， 当用户点击按钮，或者在单行文本输入框中键入回车时会产生ActionEvent
	 * 事件，对应的听众接口为ActionListener，事件处理方法为actionPerform方法
	 */
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource(); // 得到事件源对象
		if (o == fireButton) {
			int index = list.getSelectedIndex(); // 返回选中的列表项
			listModel.remove(index); // 从模型中删除，“fire”，开除
			int size = listModel.getSize();
			if (size == 0) { // 如果列表模型已经没有数据了，那么灰掉fireButton
				fireButton.setEnabled(false); // 设置fireButton为不可用状态
			} else { // 如果模型不为空，则选中下一项
				if (index == listModel.getSize()) { // 如果是最后一项，则调整为前一项
					index--;
				}
				list.setSelectedIndex(index);
				list.ensureIndexIsVisible(index); // 确保选中的数据项可以显示出来
			}
		} else if (o == hireButton) {
			String name = employeeName.getText();
			name = name.trim();
			if (name.length() == 0 || listModel.contains(name)) {
				Toolkit.getDefaultToolkit().beep(); // 名字为空或者该名字已在列表中
				employeeName.requestFocusInWindow(); // 得到输入焦点
				employeeName.selectAll(); // 选中全部内容
				return;
			}

			int index = list.getSelectedIndex();
			if (index == -1) { // 如果没有选中的数据项，则添加在第一条的位置
				index = 0;
			} else { // 添加在选中的列表项之后的位置
				index++;
			}

			listModel.insertElementAt(name, index); // 向模型中插入数据
			employeeName.requestFocusInWindow(); // 设置输入焦点
			employeeName.setText(""); // 清空文本框内容
			list.setSelectedIndex(index); // 设置选中的列表项
			list.ensureIndexIsVisible(index);

		} else if (o == employeeName) {
			hireButton.doClick(); // 在单行文本输入框中按下回车键
		} // 调用hireButton的doClick方法，等价于
	} // 按下hireButton按钮。

	/* 下面这3个方法定义在DocumentListener接口中 */
	public void insertUpdate(DocumentEvent e) { // 文档中插入文本数据
		hireButton.setEnabled(true);
	}

	public void removeUpdate(DocumentEvent e) { // 文档中删除文本数据
		if (e.getDocument().getLength() <= 0) { // 如果文本内容为空，
			hireButton.setEnabled(false); // 就把hireButton灰掉
		} // 不输入名字，没办法“雇用”
	}

	public void changedUpdate(DocumentEvent e) { // 文档内容发生变化
		removeUpdate(e); // 处理等同于上面的方法
	}

	// ListSelectionListener中定义的事件处理方法
	// 当列表模型中数据项选择发生变化时，产生ListSelectionEvent事件
	// 如果当前列表框中已经有选项选中，那么通过鼠标点击另外一项，选项发生了
	// 变化了，会产生ListSelectionEvent事件，或者使用键盘在列表项中移动也会产生
	// 这个事件，但是鼠标点击会产生两次这个事件，键盘移动会产生一次事件
	// 如果是连续事件，则前几次事件的getValueIsAdjusting返回为true，应该取
	// 最后一次产生事件的状态，它的getValueIsAdjusting返回为false。
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) { // 该事件是否是一个连续事件
			if (list.getSelectedIndex() == -1) { // 如果为连续事件，最后一个是false
				fireButton.setEnabled(false); // 如果没有选中任何列表项，灰掉
			} else {
				fireButton.setEnabled(true);
			}
		}
	}

	private static void createAndShowGUI() {
		// 和以前的例子一样，构建窗口并设置。
		JFrame frame = new JFrame("ListDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent newContentPane = new ListDemo();
		newContentPane.setOpaque(true); // ContentPane不能是透明的。
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
