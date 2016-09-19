import java.awt.*;
import java.awt.event.*;

public class ButtonExample {
	public static void main(String[] args) {
		Frame f = new Frame("button example");	//创建一个窗口
		//应该将变量声明为final常量
//		final Button b1 = new Button("OK");			//再创建3个按钮
//		final Button b2 = new Button("Cancel");
//		final Button b3 = new Button("Exit");
		
		Button b1 = new Button("OK");			//再创建3个按钮
		Button b2 = new Button("Cancel");
		Button b3 = new Button("Exit");
		ActionListener al = new ActionListener() {	//ActionListener是一个接口，
//创建的是匿名类对象
			public void actionPerformed(ActionEvent ae) {
				Button b = (Button)ae.getSource();	//得到事件源对象
				if(b==b1) {					//如果按OK按钮
					System.out.println("nihao");	//控制台输出nihao 
				}esle if(b==b2) {				//如果按Cancel按钮，什么也不做
					
				} else if(b==b3) {				//如果按Exit按钮，虚拟机退出
					System.exit(0);
				}
			}
		};		
		f.add(b1); f.add(b2); f.add(b3);		//把3个按钮添加到窗口中
		f.setSize(300,200);					//设置窗口的大小
		f.setVisible(true);					//把窗口显示出来
	}
}
