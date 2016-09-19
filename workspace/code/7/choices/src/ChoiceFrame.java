/* ChoiceFrame.java，选择题练习应用对应的窗口，封装了各个GUI组件，
以及事件处理方法，也封装了选择题模型对象及其处理方法  */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.dom4j.io.*;
import org.dom4j.*;

public class ChoiceFrame extends JFrame implements ActionListener {
	JComboBox subjects; // 科目下拉列表框对象成员
	JComboBox categories; // 题型下拉列表框对象成员
	JTextField amount; // 题量输入框对象成员
	JRadioButton rand; // 随机方式和顺序方式单选按钮对象成员
	JRadioButton seq;
	JTextField begin; // 如为顺序方式的话，起始题号文本框对象成员
	JButton start; // 开始按钮
	JCheckBox showAnswer; // 是否显示答案选项
	JButton next; // 下一道题
	JButton prev; // 上一道题
	JButton submit; // 交卷

	JFileChooser fc; // 文件选择组件

	JPanel pm = new JPanel(); // 放试题题干和提问及选项面板的面板
	JPanel pa = new JPanel(); // 放试题选项的面板
	JEditorPane qcontent = new JEditorPane(); // 题干和提问编辑显示组件

	JMenuItem open = new JMenuItem("打开题库文件(O)");
	JMenuItem exit = new JMenuItem("退出(E)");

	java.util.List<Subject> model; // 存放所有题库内容的模型对象成员
	java.util.List<Question> select; // 存放练习题的对象成员
	int current = 0; // 当前试题的编号，从1开始

	public ChoiceFrame() {
		super("选择题练习软件"); // 窗口标题

		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("文件(F)");
		m1.setMnemonic(KeyEvent.VK_F); // 定义菜单项的快捷键ALT+F
		open.setMnemonic(KeyEvent.VK_O);
		exit.setMnemonic(KeyEvent.VK_E);
		m1.add(open);
		m1.add(exit);
		mb.add(m1);
		setJMenuBar(mb);

		JPanel p = showPractice();
		setContentPane(p);

		open.addActionListener(this);
		exit.addActionListener(this);

		setSize(760, 560); // 设置窗口大小及显示位置，窗口在屏幕中央区域显示
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((scr.width - 760) / 2, (scr.height - 560) / 2);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JPanel showPractice() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());

		JLabel label1 = new JLabel("科目");
		subjects = new JComboBox();
		JLabel label2 = new JLabel("题型");
		categories = new JComboBox();

		Dimension d1;
		d1 = subjects.getPreferredSize();
		subjects.setPreferredSize(new Dimension(120, d1.height));
		categories.setPreferredSize(new Dimension(120, d1.height));

		JLabel label3 = new JLabel("题量");
		amount = new JTextField(4);

		rand = new JRadioButton("随机", true);
		seq = new JRadioButton("顺序", false);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rand);
		bg.add(seq);

		JLabel label4 = new JLabel("起始题号");
		begin = new JTextField(4);
		start = new JButton("开始");

		showAnswer = new JCheckBox("显示答案", false);
		showAnswer.setForeground(Color.red);
		next = new JButton("下一道题");
		prev = new JButton("上一道题");
		submit = new JButton("交卷");

		begin.setEnabled(false);

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEADING, 4, 4));

		p1.add(label1);
		p1.add(subjects);
		p1.add(label2);
		p1.add(categories);
		p1.add(label3);
		p1.add(amount);
		p1.add(rand);
		p1.add(seq);
		p1.add(label4);
		p1.add(begin);
		p1.add(start);

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.TRAILING));
		p2.add(showAnswer);
		p2.add(prev);
		p2.add(next);
		p2.add(submit);

		pm.setLayout(new BorderLayout());
		pa.setLayout(new BoxLayout(pa, BoxLayout.Y_AXIS)); // 从上往下顺序摆放选项

		JScrollPane jsp2 = new JScrollPane(qcontent); // 支持滚动策略
		pm.add(jsp2, BorderLayout.CENTER);
		qcontent.setEditable(false);

		pm.add(pa, BorderLayout.SOUTH);

		qcontent.setText("");
		qcontent.setEditable(false);

		p.add(p1, BorderLayout.NORTH);
		p.add(p2, BorderLayout.SOUTH);
		p.add(pm, BorderLayout.CENTER);

		p.setOpaque(true);
		/*
		 * 为这些组件注册听众，为了编程简单方便，对于下拉列表框，选项按钮等，
		 * 没有使用ItemEvent事件对应的听众类，都统一使用ActionEvent事件， 共用一个听众，然后在事件处理方法中根据事件源去区分。
		 */
		rand.addActionListener(this);
		seq.addActionListener(this);
		showAnswer.addActionListener(this);
		subjects.addActionListener(this);
		categories.addActionListener(this);
		prev.addActionListener(this);
		next.addActionListener(this);
		submit.addActionListener(this);
		start.addActionListener(this);

		return p;
	}

	public int getAmount(String subject, String category) {
		if (model == null) {
			return 0;
		} // 如果没有模型，返回题量为0
		// 如果没有科目参数，返回题量数据也为0
		if (subject == null || subject.length() == 0) {
			return 0;
		}
		if (category == null || category.length() == 0) {// 只有科目，没有题型参数情况下
			int a1 = 0;
			for (int i = 0; i < model.size(); i++) {
				Subject s = model.get(i);
				if (subject.equals(s.getName())) {
					a1 += s.getQuestions().size(); // 累加不区分题型的试题总量
					// 再累加该科目下所有题型下的试题总量
					java.util.List<Category> categories = s.getCategories();
					for (int j = 0; j < categories.size(); j++) {
						a1 += categories.get(j).getQuestions().size();
					}
				}
			}
			return a1;
		} else { // 否则，返回指定科目和题型下的试题总量
			int a1 = 0;
			for (int i = 0; i < model.size(); i++) {
				Subject s = model.get(i);
				if (subject.equals(s.getName())) {
					java.util.List<Category> categories = s.getCategories();
					for (int j = 0; j < categories.size(); j++) {
						if (category.equals(categories.get(j).getName())) {
							a1 += categories.get(j).getQuestions().size();
						}
					}
				}
			}
			return a1;
		}
	}

	// 显示某个试题，包括试题的题干，提问，选项，以及用户的选择等数据
	public void displayQuestion(Question q) {
		StringBuffer sb = new StringBuffer();
		// 先输出显示做题的提示，科目和题型下可能有做题指南，instruction数据
		String s1 = q.getSubject();
		Subject sub1 = getSubject(s1);
		String ins1 = sub1.getInstruction();
		if (ins1 != null && ins1.length() != 0) {
			sb.append(ins1);
			sb.append("\r\n\r\n");
		}

		String ca1 = q.getCategory();
		if (ca1 != null && ca1.length() > 0) {
			Category ca2 = getCategory(q.getSubject(), ca1);
			if (ca2 != null && ca2.getInstruction() != null
					&& ca2.getInstruction().length() != 0) {
				sb.append(ca2.getInstruction());
				sb.append("\r\n\r\n");
			}
		}
		/*
		 * 输出题号，如果随机方式下，则输出选出试题的顺序编号，否则， 显示原题标号，还要选择在题干之前输出，还是在提问之前输出题号
		 */
		int num = 0;
		if (rand.isSelected()) { // 随机方式，显示current，当前题号的值
			num = current;
		} else {
			num = q.getId(); // 顺序方式，显示试题本身的编号
		}

		String stem = q.getStem(); // 试题的题干内容
		String ask = q.getAsk(); // 试题的提问内容
		if (stem == null) {
			stem = "";
		}
		if (ask == null) {
			ask = "";
		}

		if (stem.length() != 0) { // 如果题干内容不为空，则题号显示在题干之前
			sb.append(num + ". " + stem + "\r\n");
		}
		if (ask.length() != 0) {
			if (stem.length() == 0) { // 如果题干内容为空，则题号显示在提问之前
				sb.append(num + ". " + ask + "\r\n");
			} else {
				sb.append(ask + "\r\n");
			}
		}

		qcontent.setText(sb.toString()); // 输出到JEditorPane组件中
		pa.removeAll(); // 先清空选项面板容器中所有组件

		/*
		 * 将选项组件放到选项面板pa中，根据单选题和多选题生成不同的选项 按钮，再根据是否显示答案，设置选项按钮的不同的前景颜色，
		 * 再根据该选项是否在用户的输入中，设置该选项按钮是否被选中
		 */
		java.util.List<Option> ops = q.getOptions();
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < ops.size(); i++) {
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout(FlowLayout.LEFT));
			JToggleButton jtb = null;
			if (q.getType().equals("single")) { // 单选题
				jtb = new JRadioButton(ops.get(i).getId() + ". "
						+ ops.get(i).getContent());
				bg.add(jtb);
			} else if (q.getType().equals("multiple")) { // 多选题
				jtb = new JCheckBox(ops.get(i).getId() + ". "
						+ ops.get(i).getContent());
			}
			jtb.setActionCommand(ops.get(i).getId()); // 为了区分点击哪个选项按钮
			jtb.addActionListener(this);
			if (q.getChoice().contains(ops.get(i).getId())) { // choice是用户的输入选择
				jtb.setSelected(true);
			} else {
				jtb.setSelected(false);
			}
			/* 答案中包括这个选项，并且是否显示答案按钮被选中状态 */
			if (q.getAnswer().contains(ops.get(i).getId())
					&& showAnswer.isSelected()) {
				jtb.setForeground(Color.red);
			}

			p.add(jtb);
			pa.add(p);
		}
		pm.validate(); // 重画这个面板，因为面板内容已经发生变化
	}

	public void displayAmount() { // 根据科目和题型两个下拉框的内容来输出题量
		String s1 = (String) subjects.getSelectedItem();
		String c1 = (String) categories.getSelectedItem();

		int a1 = getAmount(s1, c1);
		amount.setText("" + a1);
	}

	// 根据科目和题型的字符串参数数据得到模型中Category对象
	public Category getCategory(String subject, String category) {
		if (model == null) {
			return null;
		}
		if (subject == null || subject.length() == 0) {
			return null;
		}
		if (category == null || category.length() == 0) {
			return null;
		}
		for (int i = 0; i < model.size(); i++) {
			Subject s1 = model.get(i);
			if (s1.getName().equals(subject)) {
				java.util.List<Category> categories = s1.getCategories();
				for (int j = 0; j < categories.size(); j++) {
					if (categories.get(j).getName().equals(category)) {
						return categories.get(j);
					}
				}
			}
		}
		return null;
	}

	// 根据科目的字符串参数数据得到模型中Subject对象
	public Subject getSubject(String subject) {
		if (subject == null || subject.length() == 0) {
			return null;
		}
		if (model == null) {
			return null;
		}
		for (int i = 0; i < model.size(); i++) {
			if (model.get(i).getName().equals(subject)) {
				return model.get(i);
			}
		}
		return null;
	}

	// 根据科目的字符串参数数据得到模型中该科目下的所有题型数据
	public java.util.List<Category> getCategories(String subject) {
		java.util.List<Category> r = new java.util.ArrayList<Category>();
		if (model == null) {
			return r;
		}

		for (int i = 0; i < model.size(); i++) {
			Subject s = model.get(i);
			if (subject.equals(s.getName())) {
				r.addAll(s.getCategories());
			}
		}
		return r;
	}

	/*
	 * 根据界面中用户的输入数据生成练习题，依据科目和题型的数据参数。 如果指定了科目和题型，则根据该题型下的数据进行随机地或者顺序地
	 * 生成练习题，或者只指定了科目而没有指定题型，那么只取该科目下的试题， 而不取具体的某些题型下的试题，根据这些选择的试题范围再随机地或者
	 * 顺序地选取指定题量的练习题。
	 */
	public java.util.List<Question> createQuestions(String subject,
			String category, boolean mode, int index, int amount) {
		index--;
		current = 0;
		java.util.List<Question> s = null;
		java.util.List<Question> temp = new java.util.ArrayList<Question>();
		if (subject == null) {
			subject = "";
		}
		if (category == null) {
			category = "";
		}
		if (subject.length() != 0 && category.length() != 0) {
			Category c1 = getCategory(subject, category);
			temp.addAll(c1.getQuestions()); // 添加该科目和题型下的所有试题
		} else if (subject.length() != 0 && category.length() == 0) {
			Subject s1 = getSubject(subject); // 添加该科目下的试题
			temp.addAll(s1.getQuestions());
		}

		if (temp.size() == 0) { // 如果没有试题可供练习，则返回一个空的集合
			return temp;
		}

		for (int i = 0; i < temp.size(); i++) {
			temp.get(i).setChoice(""); // 把用户的输入的选择都清空
		}

		if (mode) { // 随机方式
			if (amount > temp.size()) {
				amount = temp.size();
			}
			Collections.shuffle(temp); // 调用工具类Collections的乱序方法
			s = temp.subList(0, amount); // 随机地返回指定题量的试题
		} else { // 顺序方式
			if (index + amount > temp.size()) { // 试题数量不足的情况下
				amount = temp.size() - index; // 重新调整返回的试题数量
			}
			s = temp.subList(index, index + amount); // 返回顺序的试题集
		}
		return s;
	}

	// 从XML文件中的question元素加载试题对象
	public Question processQuestion(Element qe, String subject, String category) {
		Question q = new Question();
		q.setSubject(subject);
		q.setCategory(category);
		q.setId(Integer.parseInt(qe.attributeValue("id"))); // 试题编号
		q.setType(qe.attributeValue("type")); // 试题类型
		q.setAnswer(qe.attributeValue("answer")); // 试题答案

		Element stem = qe.element("stem"); // 试题的题干内容
		Element ask = qe.element("ask"); // 试题的提问内容
		if (stem != null) {
			q.setStem(stem.getText());
		}
		if (ask != null) {
			q.setAsk(ask.getText());
		}
		/* 添加试题的选项内容 */
		java.util.List options = qe.elements("option");
		for (int i = 0; i < options.size(); i++) {
			Element oe = (Element) options.get(i);
			Option o = new Option();
			o.setId(oe.attributeValue("id"));
			o.setContent(oe.getText());
			o.setParent(q);
			q.addOption(o);
		}
		return q;
	}

	// 根据XML元素category，来生成Category对象
	public Category processCategory(Element ce, String subject) {
		Category c1 = new Category();
		c1.setName(ce.attributeValue("name"));
		c1.setSubject(subject);
		java.util.List list = ce.elements();
		for (int i = 0; i < list.size(); i++) {
			Element e1 = (Element) list.get(i);
			String ename = e1.getName();
			if (ename.equals("instruction")) { // 添加题型对应的提示内容
				c1.setInstruction(e1.getText());
			} else if (ename.equals("question")) { // 添加试题对象数据
				c1.addQuestion(processQuestion(e1, subject, c1.getName()));
			}
		}
		return c1;
	}

	// 根据XML元素subject，来生成Subject对象
	public Subject processSubject(Element se) {
		Subject s1 = new Subject();
		s1.setName(se.attributeValue("name"));
		java.util.List list = se.elements();
		for (int i = 0; i < list.size(); i++) {
			Element e1 = (Element) list.get(i);
			String ename = e1.getName();
			if (ename.equals("instruction")) { // 科目对应的试题提示内容
				s1.setInstruction(e1.getText());
			} else if (ename.equals("category")) { // 科目下的题型数据
				s1.addCategory(processCategory(e1, s1.getName()));
			} else if (ename.equals("question")) { // 科目下的试题数据，题型为空
				s1.addQuestion(processQuestion(e1, s1.getName(), null));
			}
		}
		return s1;
	}

	// 根据用户输入的题库文件，例如samples.xml，将题库文件内容加载到试题集合。
	public java.util.List<Subject> loadQuestions(File file) {
		current = 0;
		java.util.List<Subject> model = new java.util.ArrayList<Subject>();// 试题库模型
		try { // 以下内容利用DOM4J的API来读取XML数据
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new FileInputStream(file));
			XPath path = doc.createXPath("/library/subject");
			java.util.List list = path.selectNodes(doc); // 处理所有的subject元素
			for (int i = 0; i < list.size(); i++) {
				Element e1 = (Element) list.get(i);
				model.add(processSubject(e1)); // 向模型中添加数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/*
	 * 初始化界面，清空科目，题型等内容，根据加载的题库文件内容， 添加科目数据项，题型数据项，显示题库中题量数据等
	 */
	public void initSelection() { // after load library file.
		subjects.removeActionListener(this); // 设置过程中发生的事件，暂不处理
		categories.removeActionListener(this); // 所以先把这些听众对象取消注册

		subjects.removeAllItems(); // 清空科目和题型
		categories.removeAllItems();

		amount.setText(""); // 清空题量，复原练习方式及是否显示答案
		rand.setSelected(true);
		begin.setText("");
		showAnswer.setSelected(false);

		for (int i = 0; i < model.size(); i++) { // 向科目下拉框中添加科目数据项
			Subject s1 = model.get(i);
			subjects.addItem(s1.getName());
		}

		if (subjects.getItemCount() == 1) { // 如果只有一个科目，则同时选择该科目
			subjects.setSelectedIndex(0);
			String s1 = (String) subjects.getSelectedItem();
			if (s1 != null) { // 如果有选定的科目，则同时显示该科目下的题型数据
				java.util.List<Category> list = getCategories(s1);
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						categories.addItem(list.get(i).getName());
					}
				}
			}
		} else {
			subjects.setSelectedIndex(-1); // 如果题库中不是一个科目，则不选
		}

		displayAmount(); // 输出题量数据

		current = 0; // 练习题编号也复原为0
		select = null; // 清空选择的练习题集合

		pa.removeAll(); // 界面中的试题选项内容都清空
		qcontent.setText(""); // 界面实现的题干，提问等都清空
		pm.validate();

		subjects.addActionListener(this); // 重新注册听众
		categories.addActionListener(this);
	}

	public String addAnswer(String ch, String answer) { // 向用户选择添加选项
		if (!ch.contains(answer)) { // 如果不存在该选项则添加
			char[] ca = ch.toCharArray(); // 转换为字符数组
			char[] cb = new char[ca.length + 1]; // 新建数组
			System.arraycopy(ca, 0, cb, 0, ca.length); // 复制以前数组内容
			cb[ca.length] = answer.charAt(0); // 添加新的选择
			Arrays.sort(cb); // 排序这个选项数组
			return new String(cb); // 返回为字符串格式
		} else { // 如果已有该用户选择，则不添加
			return ch;
		}
	}

	public String removeAnswer(String ch, String answer) { // 从用户选择中删除选项
		if (ch.contains(answer)) {
			int p1 = ch.indexOf(answer); // 去掉该选项
			return ch.substring(0, p1) + ch.substring(p1 + 1);
		} else {
			return ch;
		}
	}

	/* 以下代码是事件处理程序 */
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource(); // 获得事件源对象
		if (source == open) { // 用户点击的是菜单项，打开题库文件
			processOpenClick();
		} else if (source == exit) { // 用户点击的是菜单项，退出系统
			System.exit(0);
		} else if (source == subjects) { // 用户点击科目下拉框
			processSubjectsClick();
		} else if (source == categories) {// 用户点击题型下拉框
			displayAmount();
		} else if (source == rand) { // 用户点击随机方式
			processRandClick();
		} else if (source == seq) { // 用户点击顺序方式
			processSeqClick();
		} else if (source == showAnswer) { // 用户点击是否显示答案
			processShowAnswer();
		} else if (source == start) { // 用户点击开始按钮，开始练习
			processStartClick();
		} else if (source == next) { // 用户点击下一道题按钮
			processNextClick();
		} else if (source == prev) { // 用户点击上一道题按钮
			processPrevClick();
		} else if (source == submit) { // 用户点击交卷按钮
			processSubmitClick();
		} else if (source instanceof JToggleButton) { // 用户点击选项按钮
			JToggleButton jtb = (JToggleButton) source;
			String actionCommand = jtb.getActionCommand();
			if (actionCommand != null && actionCommand.length() == 1) {
				if (jtb instanceof JRadioButton) {
					// 选项按钮是单选按钮，则直接设置用户选项即可
					select.get(current - 1).setChoice(actionCommand);
				} else if (jtb instanceof JCheckBox) {
					// 用户点击的是复选按钮，则需要根据复选按钮的状态
					// 来决定是添加选项还是从用户选择输入中删除选项
					String ch = select.get(current - 1).getChoice();
					if (jtb.isSelected()) { // 是在选择中添加选项
						ch = addAnswer(ch, actionCommand);
					} else { // 是从选择中删除选项
						ch = removeAnswer(ch, actionCommand);
					}
					select.get(current - 1).setChoice(ch);
				}
			}
		}
	}

	public void processOpenClick() { // 用户点击菜单，打开题库文件
		new Thread() {
			public void run() {
				fc = new JFileChooser(); // 打开文件选择对话框
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"题库文件(XML)", "xml", "XML");
				fc.setFileFilter(filter); // 设置文件后缀选择
				int returnVal = fc.showOpenDialog(ChoiceFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					model = loadQuestions(f); // 加载题库文件到题库模型对象

					initSelection(); // 初始化界面，供用户输入
					ChoiceFrame.this.setTitle("选择题练习软件---" + f.getName());
					// 设置焦点在科目下拉列表框，供用户选择科目，开始练习
					subjects.requestFocusInWindow();
				}
			}
		}.start();
	}

	public void processSubjectsClick() {
		categories.removeActionListener(this); // 先暂时停止题型下拉框的事件响应

		String s1 = (String) subjects.getSelectedItem(); // 获得科目下拉框的选项
		if (s1 != null) {
			java.util.List<Category> list = getCategories(s1); // 得到该科目对应的题型

			categories.removeAllItems(); // 清空题型下拉框

			if (list != null) { // 添加该科目下的所有题型到题型下拉框
				for (int i = 0; i < list.size(); i++) {
					categories.addItem(list.get(i).getName());
				}
				if (categories.getItemCount() == 1) { // 如果只有一个题型
					categories.setSelectedIndex(0); // 则直接设置选择该题型
				} else {
					categories.setSelectedIndex(-1); // 否则，不选
				}
				displayAmount(); // 更新显示题量
				categories.requestFocusInWindow(); // 设置焦点在题型下拉框组件
			}
		}
		categories.addActionListener(this); // 重新为题型下拉框注册听众
	}

	public void processRandClick() { // 点击随机选项按钮，禁止输入起始题号
		begin.setText("");
		begin.setEnabled(false);
	}

	public void processSeqClick() { // 点击顺序选项按钮
		begin.setEditable(true);
		begin.setEnabled(true);
		begin.requestFocusInWindow(); // 起始题号得到输入焦点
	}

	public void processShowAnswer() {
		// 点击是否显示答案，如果在练习过程中，需要刷新显示，重新显示试题
		if (select != null && select.size() > 0 && current != 0) {
			displayQuestion(select.get(current - 1));
		}
	}

	public void processStartClick() { // 点击开始按钮
		String ss = (String) subjects.getSelectedItem();
		String sc = (String) categories.getSelectedItem();

		if (ss == null || ss.length() == 0) { // 如果没选中科目，那么弹出消息提示框窗口
			JOptionPane.showMessageDialog(ChoiceFrame.this, "请选择科目", "科目输入提示",
					JOptionPane.WARNING_MESSAGE);
			subjects.requestFocusInWindow();
			return;
		}

		String s1 = amount.getText();
		String s2 = begin.getText();
		s1 = s1.trim();
		s2 = s2.trim();
		int a1 = 0, a2 = 0;
		int index = 0;

		if (s1.length() == 0) { // 题量数据为0，或者为空，提示用户
			JOptionPane.showMessageDialog(ChoiceFrame.this, "请输入题量", "题量输入提示",
					JOptionPane.WARNING_MESSAGE);
			amount.requestFocusInWindow();
			return;
		} else {
			a1 = getAmount(ss, sc);
			try {
				a2 = Integer.parseInt(s1); // 用户输入的题量数据为非法数字
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(ChoiceFrame.this, "请输入正确的题量数值",
						"题量输入提示", JOptionPane.WARNING_MESSAGE);
				amount.requestFocusInWindow();
				return;
			}
			if (a2 > a1) { // 用户输入的练习题量超过题库中的试题数量
				JOptionPane.showMessageDialog(ChoiceFrame.this,
						"输入的题量数值不能超过题库中的题量数值", "题量输入提示",
						JOptionPane.WARNING_MESSAGE);
				amount.requestFocusInWindow();
				return;
			}
		}
		if (rand.isSelected()) { // 随机方式产生练习题
			select = createQuestions(ss, sc, true, -1, a2);
		} else {
			if (s2.length() == 0) {
				JOptionPane.showMessageDialog(ChoiceFrame.this, "请输入起始题号",
						"起始题号输入提示", JOptionPane.WARNING_MESSAGE);
				begin.requestFocusInWindow();
				return;
			}
			try {
				index = Integer.parseInt(s2);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(ChoiceFrame.this, "请输入正确的起始题号",
						"起始题号输入提示", JOptionPane.WARNING_MESSAGE);
				begin.requestFocusInWindow();
				return;
			}
			if (index > a1) {
				JOptionPane.showMessageDialog(ChoiceFrame.this, "起始题号超过题库试题数量",
						"起始题号输入提示", JOptionPane.WARNING_MESSAGE);
				begin.requestFocusInWindow();
				return;
			}
			select = createQuestions(ss, sc, false, index, a2); // 顺序方式产生练习题
		}
		/*
		 * 产生的练习题在select集合中，如果练习题集合不为空，则开始练习， 设置当前练习题编号为1， 同时显示该练习题。如果练习题集合为空，
		 * 则清空屏幕，并设置编号为0
		 */
		if (select.size() > 0) {
			current = 1;
			displayQuestion(select.get(current - 1));
		} else {
			qcontent.setText("");
			pa.removeAll();
			pm.validate();
			current = 0;
		}
	}

	public void processNextClick() { // 用户点击下一道题按钮
		if (select != null && select.size() >= current + 1) { // 如果存在下一道题，则显示
			current++;
			displayQuestion(select.get(current - 1)); // 试题编号从1开始，并非0
		}
	}

	public void processPrevClick() { // 用户点击上一道题按钮
		if (select != null && current - 1 >= 1) {
			current--;
			displayQuestion(select.get(current - 1));
		}
	}

	public void processSubmitClick() { // 用户点击交卷按钮
		if (select != null && current != 0) {
			int right = 0; // 做对的题数计数
			for (int i = 0; i < select.size(); i++) {
				/*
				 * 如果答案和用户的选择是一样的，则累加计数， 需要注意多项选择题的答案的编号顺序，是从A到B，到C，
				 * 例如某道题的答案是AC，如果在题库文件中写成CA则是错误的。
				 */
				if (select.get(i).getAnswer().equals(select.get(i).getChoice())) {
					right++;
				}
			}
			double d = 1.0 * right / select.size() * 100.0;
			d = d + 0.5; // 四舍五入
			int d2 = (int) d;
			JOptionPane.showMessageDialog(this, "做对了" + right + "道题，正确率是" + d2
					+ "%", "练习结果", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new ChoiceFrame();
	}
}
