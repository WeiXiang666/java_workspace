interface K {
	void a();
}
class A {
	public void a() {
		System.out.println("a");
	}
}
class B extends A implements K {
	
}
class InstanceOfExample {
	public static void main(String[] args) {
		Object o = new int[0];
		if(o instanceof A) {		//返回false
			A a = (A)o;
		}
		if(o instanceof K) {		//返回false
			K k = (K)o;
		}
		if(o instanceof int[]) {	//返回true
			int[] ia = (int[])o;
		}
		A a1 = new A();
		B b1 = new B();
		if(a1 instanceof B) {	//返回false
			B b2 = (B)a1;
		}
		if(a1 instanceof K) {	//返回false，尽管a1对象有a方法特征，但是
			K k2 = (K)a1;		//类A声明中并没有实现接口K
		}
		if(b1 instanceof A) {	//返回true
			A a2 = (A)b1;
		}
		if(b1 instanceof K) {	//返回true
			K k2 = (K)b1;
		}
	}
}
