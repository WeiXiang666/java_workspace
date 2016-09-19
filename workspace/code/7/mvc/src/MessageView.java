import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MessageView extends Frame { // 为模型定制的视图，实现模型数据输入输出
	Label label = new Label("Message is: "); // 提供模型业务方法调用的命令
	TextField tf = new TextField() { // 标签，文本框，按钮
		public Dimension getPreferredSize() { // 使用匿名类，扩展TextField
			return new Dimension(80, 25); // 覆盖getPreferredSize方法
		}
	};
	Button b = new Button("submit"); // 一个命令按钮，实现调用模型功能

	MessageModel mm; // 视图要访问模型，定义关联关系

	public MessageView(MessageModel mm) { // 构造视图对象时要提供模型对象
		this.mm = mm;
		tf.setText(mm.getMessage());
		setLayout(new FlowLayout());
		add(label);
		add(tf);
		add(b);

		setSize(300, 200);
		Random r1 = new Random();
		setLocation(r1.nextInt(200), r1.nextInt(200)); // 视图位置，随机值。
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public void refresh() { // 视图中数据刷新
		tf.setText(mm.getMessage());
	}

	public void addController(MessageController mc) {// 视图的事件交给控制器去处理
		b.addActionListener(mc); // 将控制器直接注册给视图中产生事件的组件 } //并没有进行听众接口的转换处理
	}
}
