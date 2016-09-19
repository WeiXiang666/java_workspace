class A {
	public void a() {
		System.out.println("a");
	}

	public void b() {
		this.a();
	}
}

class B extends A {
	public void a() {
		System.out.println("b");
	}

	public void b() {
		System.out.println("hehe");
		super.b();
	}

	public static void main(String[] args) {
		A a = new B(); // 创建的子类的对象，引用类型是父类类型
		a.b(); // 发生了覆盖，子类的b方法被调用，然后在该方法中调用了父类的b方法
		       //父类的b方法调用了当前对象的a()方法，这里又有覆盖发生，当然调用的是子类的a()方法。
	}
}
