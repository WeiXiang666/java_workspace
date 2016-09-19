import java.awt.*;
import java.awt.event.*;
public class GUI  {
	public static void main(String[] args) 
	{
		//创建一个窗口，窗口标题为gui outlook under different platform
		Frame f = new Frame("gui outlook under different platform");
		//创建两个按钮对象
		Button ok = new Button(" OK ");
		Button exit = new Button(" Exit ");
		//设置字体
		ok.setFont(new Font("SansSerif",Font.PLAIN,16));
		exit.setFont(new Font("SansSerif",Font.PLAIN,16));
		//创建一个列表框对象
		List list = new List();
		//添加3个数据项
		list.add("Zhang San");
		list.add("Li Si");
		list.add("Wang Wu");
		//为exit按钮注册一个听众对象，使用了匿名类
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	//事件处理方法
				System.exit(0); }});
		//设置窗口f的布局为流布局管理器
		f.setLayout(new FlowLayout());
		//向窗口中添加按钮和列表框
		f.add(ok);  f.add(exit); f.add(list);
		//设置窗口大小并显示出来
		f.setSize(200,150);
		f.setVisible(true);
	}
}
