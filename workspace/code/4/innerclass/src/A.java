public class A { // A.java
	public void a(K k) { // 方法a需要传入一个实现接口K的对象
		k.a(); // 调用该接口的a，b方法
		k.b();
	}

	public static void main(String[] args) {
		A a1 = new A(); // 创建一个A对象
		a1.a(new K() { // 调用其a方法
			public void a() {
				System.out.println("a");
			}

			public void b() {
				System.out.println("b");
			}
		});
	}
}
