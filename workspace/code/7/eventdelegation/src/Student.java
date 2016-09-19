//第一个程序，Student.java，作为事件源
import java.util.*;

public class Student {
	private int age;

	// 听众集合，用来保存和听众对象的关联关系的
	List<AgeChangedListener> listeners = new LinkedList<AgeChangedListener>();

	// 听众注册方法，不允许某个听众对象重复注册
	public void addAgeChangedListener(AgeChangedListener acl) {
		if (!listeners.contains(acl)) {
			listeners.add(acl);
		}
	}

	// 年龄修改方法中，年龄数据发生变化，产生了一个事件
	public void setAge(int age) {
		// 这个事件封装了事件源，事件发生时的新旧年龄数据
		AgeChangedEvent ace = new AgeChangedEvent(this, this.age, age);
		this.age = age;
		process(ace); // 调用处理这个事件的方法
	}

	// 内部类，使用多线程机制来完成听众对象的广播，否则，一旦某个听众的
	// 事件处理程序执行缓慢会影响其他听众对象的事件处理程序的执行。
	class DispatchThread extends Thread {
		private AgeChangedListener acl; // 引用一个听众对象
		private AgeChangedEvent ace; // 待处理的事件

		public DispatchThread(AgeChangedListener acl, AgeChangedEvent ace) {
			this.acl = acl;
			this.ace = ace;
		}

		public void run() { // 线程任务就是事件委托过程
			acl.processAgeChanged(ace); // 执行听众对象的事件处理程序
		}
	}

	public void process(AgeChangedEvent ace) {
		// source internal processing
		// 事件源内部的事件处理过程，如果没有，则不需要提供什么代码
		// internal processing end.

		// 开始听众的事件广播处理
		for (int i = 0; i < listeners.size(); i++) { // 遍历听众集合，使用多线程进行广播
			new DispatchThread(listeners.get(i), ace).start();
		}
	}
}
