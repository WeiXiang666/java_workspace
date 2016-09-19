
//应用类
public class MvcApp {
	public static void main(String[] args) {
		MessageModel mm = new MessageModel();		//创建了一个模型
		MessageView mv1 = new MessageView(mm);	//创建了两个视图
		MessageView mv2 = new MessageView(mm);
		MessageController mc1 = new MessageController(mm,mv1);//创建两个控制器
		mm.addView(mv1);	mm.addView(mv2);	//为模型指定视图
	}	//可以修改程序，让两个视图共用一个控制器。
}
		MessageController mc2 = new MessageController(mm,mv2);
