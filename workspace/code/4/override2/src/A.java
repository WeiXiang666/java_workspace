public class A implements K { // A.java
	void a() { System.out.println("a"); }	//访问权限不正确，应该是public权限

	void b() { System.out.println("b"); }	//和上一个方法一样，也是错误的

	public void c() { System.out.println("c"); } //这是正确的，可以不抛出异常
}
