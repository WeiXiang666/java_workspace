import java.awt.*;
import java.awt.event.*;
public class LayoutDemo extends Frame {		//继承窗口类
	Button b1 = new Button("FlowLayout");	//有按钮对象成员
	List list = new List();					//有列表框对象成员
	CheckboxGroup g = new CheckboxGroup();  //复选按钮“放入”到CheckboxGroup
	Checkbox[] cb = new Checkbox[4];		//就具有单选按钮的效果了。

	FlowLayout fl = new FlowLayout();		
	BorderLayout bl = new BorderLayout();	
	GridLayout gl = new GridLayout(2, 2);		//网格布局，2行2列
	CardLayout cl = new CardLayout();

	Panel p = new Panel() {					//选择面板容器为中间容器
		public Dimension getPreferredSize() {	//放入4个单选按钮
			return new Dimension(90, 90);	//为面板设置优先尺寸
		}
	};
	public LayoutDemo() {
		setLayout(bl);						//先选择BorderLayout
		cb[0] = new Checkbox("FlowLayout", false, g);
		cb[1] = new Checkbox("BorderLayout", true, g);
		cb[2] = new Checkbox("GridLayout", false, g);
		cb[3] = new Checkbox("CardLayout", false, g);
		list.add("FlowLayout");
		list.add("BorderLayout");
		list.add("GridLayout");
		list.add("CardLayout");
		list.select(1);			//默认采用BorderLayout
		p.add(cb[0]);
		p.add(cb[1]);
		p.add(cb[2]);
		p.add(cb[3]);
		p.setLayout(new GridLayout(4, 1));	//面板的布局为4行1列
		ItemListener il = new ItemListener() {	//ItemEvent事件对应的听众接口
			public void itemStateChanged(ItemEvent ie) {
				Object source = ie.getSource();
				if (source instanceof List) {	//如果事件源是List
					int index = ((Integer) ie.getItem()).intValue();
					cb[index].setState(true);	//实现List和Checkbox联动效果
					changeLayout(list.getItem(index));
				} else if (source instanceof Checkbox) { //如果事件源是Checkbox
					for(int i=0;i<cb.length;i++) { //实现List和Checkbox联动效果
						if(source == cb[i]) { list.select(i); break; }
					}
					changeLayout((String) ie.getItem()); //更改容器布局	
				}
			}
		};
		list.addItemListener(il);		//为List对象注册听众对象
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				changeLayout("FlowLayout");	//按下按钮则切换布局FlowLayout
				cb[0].setState(true);
				list.select(0);
			}
		});
		add(b1, BorderLayout.NORTH);	//向容器添加组件，容器采用BorderLayout
		add(list, BorderLayout.CENTER);	 //所以调用这个add方法，不仅指定添加的
		add(p, BorderLayout.SOUTH);	//组件，同时，指定组件的布局信息
		cb[0].addItemListener(il);		//为这4个单选按钮注册听众
		cb[1].addItemListener(il);
		cb[2].addItemListener(il);
		cb[3].addItemListener(il);
		/* Frame容器产生WindowEvent事件，对应产生事件的行为为关闭窗口，
	 	 正在关闭，最小化，最大化，打开等等，对应的听众接口为WindowListener，
   		 由于WindowListener接口中的事件处理方法很多，所以提供了适配器类来
		 实现这个接口，当然实现方法都是空的，只是继承后覆盖某个需要实现的
		 方法就可以了。方便了编程，否则如果选择实现WindowListener接口的话，
		 需要实现接口中的7个方法。*/
		addWindowListener(new WindowAdapter() {	//如果不提供这个语句，则
			public void windowClosing(WindowEvent we) { //点击窗口的关闭按钮也
				System.exit(0);					//不会退出。
			}
		});
		setSize(350, 250);			//设置窗口的大小
		setVisible(true);			//设置窗口的显示状态
	}
	public void changeLayout(final String layout) {
		new Thread() {		//启动另外一个线程来执行布局的切换。
			public void run() {
				if (layout.equals("FlowLayout")) {
					setLayout(fl);
				} else if (layout.equals("CardLayout")) {
					setLayout(cl);
				} else if (layout.equals("BorderLayout")) {
					setLayout(bl);
				} else if (layout.equals("GridLayout")) {
					setLayout(gl);
				}
				doLayout();		//更改了布局，所以需要重新布置容器内的组件
			}
		}.start();
	}
	public static void main(String[] args) {
		new LayoutDemo();		
	}
}
