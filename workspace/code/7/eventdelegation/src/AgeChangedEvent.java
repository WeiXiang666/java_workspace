//下面是事件类，AgeChangedEvent.java，定义了年龄变化这个事件
//所有事件都有事件源对象，这个特征被抽象到了java.util.EventObject这个事件类的
//根类中，要求所有事件类都要从该类继承。

public class AgeChangedEvent extends java.util.EventObject {
	private int oAge, nAge; // 事件类应该定义事件发生时的一些变化数据等等

	public AgeChangedEvent(Object source, int oAge, int nAge) {
		super(source); // 调用父类的构造方法，可以通过调用getSource()方法
		this.oAge = oAge; // 来获得事件源
		this.nAge = nAge;
	}

	public int getOldAge() {
		return oAge;
	}

	public int getNewAge() {
		return nAge;
	}
}
