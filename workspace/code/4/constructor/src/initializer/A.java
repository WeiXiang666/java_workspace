package initializer;

public class A {
	int i = 0;
	{
		System.out.println("first");
	} // 初始化代码

	A(int i) {
		this.i = i;
		System.out.println("contructor");
	}

	{
		System.out.println("second");
	} // 初始化代码

	public static void main(String[] args) {
		A a = new A(5);
		A b = new A(6);
	}
}
