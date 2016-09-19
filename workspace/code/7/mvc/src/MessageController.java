import java.awt.*;
import java.awt.event.*;

public class MessageController implements ActionListener { // 控制器类
	MessageModel mm; // 控制器要依赖模型和视图
	MessageView mv; // 控制器要建立到模型和视图的关联关系

	public MessageController(MessageModel mm, MessageView mv) {
		this.mm = mm;
		this.mv = mv;
		mv.addController(this); // 将控制器注册到视图，充当视图的事件听众
	}

	public void actionPerformed(ActionEvent e) { // 事件处理程序
		mm.setMessage(mv.tf.getText()); // 将用户事件映射到模型业务方法的执行
	}
}