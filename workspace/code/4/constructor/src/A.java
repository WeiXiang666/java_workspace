public class A {
	int i;

	public A(int j) { // 定义了构造方法，所以默认构造方法不提供了。
		i = j;
	}

	public static void main(String[] args) {
		A a1 = new A(5);
		//A a2 = new A(); // 这个语句是错误的，因为A()方法根本不存在
	} // 如何修改这个程序，让其编译通过呢？
}
