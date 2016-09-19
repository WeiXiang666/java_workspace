//下面是第二个听众类，Classmate.java

public class Classmate implements AgeChangedListener {
	public void processAgeChanged(AgeChangedEvent ace) {
		// who? have a birthday party?
		// 调用从EventObject类继承下来的getSource()方法获得事件源
		Student s = (Student) ace.getSource();
		System.out.println("let's have a birthday party.");
	}
}
