import java.util.*;

public class MessageModel { // 一个简单的模型，封装了一个字符串信息
	private String message;

	public String getMessage() { // 视图对象将会访问模型对象的业务数据的读方法
		return message;
	}

	public void setMessage(String message) { // 当数据发生变化时，要通知视图更新
		this.message = message; // 遍历视图，进行视图更新操作
		for (Iterator<MessageView> it = views.iterator(); it.hasNext();) {
			it.next().refresh();
		}
	}

	List<MessageView> views = new LinkedList<MessageView>();

	public void addView(MessageView mv) { // 为模型注册视图
		views.add(mv);
	}
}
