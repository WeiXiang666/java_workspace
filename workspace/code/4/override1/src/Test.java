class A {
	public void someMethod() {
		System.out.println("A");
	}
}

class B extends A {
	public void someMethod() {
		System.out.println("B");
	}
}

class Test {
	public static void main(String[] args) {
		A a = new A();
		a.someMethod(); // 执行的是A类的someMethod方法
		B b = new B();
		b.someMethod(); // 执行的是B类的someMethod方法
		A a2 = new B();
		a2.someMethod();  //执行的是B类的someMethod方法
		                  //实例成员函数（也就是非静态函数，都是后绑定实现，都是动态绑定）
	}
}
