public class B extends A {
	float f;

	public B(float f) { // 这个程序是错误的，编译不通过
						// super(0);  //通过传入一个默认值来完成初始化父类的成员
		this.f = f; // 在此语句前编译器会添加一条super()方法调用
	} // 但是，父类没有无参数的构造方法
	
	//下面这个程序是正确的构造方法，因为B类拥有两个成员，应该对这两个成员进行初始化
	//通过调用父类的构造方法来完成继承的父类的成员的初始化。然后是初始化本类的成员
	public B(float f, int i) {
		super(i);
		
		this.f = f;
	}

	public static void main(String[] args) {
		B b1 = new B(3f);
	}
}
