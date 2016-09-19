package b; //源文件名称为B.java

import a.A;

public class B extends A {
	public static void main(String[] args) {
		B b = new B();
		b.i = 9; // 这个保护成员的访问是正确的
		A a = new A();
		// a.i = 9; // 这个保护成员的访问是错误的，编译错误！
	}
}
