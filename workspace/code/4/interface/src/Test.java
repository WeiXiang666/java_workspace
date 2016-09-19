public class Test { // Test.java
	public static void main(String[] args) {
		A a = new A();
		a.a();
		a.b();
		a.c();
		// a.d(); 这个语句当然是错误的，因为d()是私有的
		// 如果在main方法中，仅仅是想访问A对象的a,b方法，
		// a.c()是错误的调用，那么可以使用更加抽象的类型，接口K
		K k = new A();
		k.a();
		k.b();
		// k.c(); 这个语句当然是错误的，接口K中没有c方法
		// 尽管引用变量k指向的对象提供了c方法，但是，引用的类型决定了对象的访问能力
	}
}
