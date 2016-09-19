//下面是第一个听众类，Parent.java

public class Parent implements AgeChangedListener {
	public void processAgeChanged(AgeChangedEvent ace) {
		// buy iphone as a gift?
		System.out.println("buy something......");
	}
}
