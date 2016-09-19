//公开类，源文件名称只能为 Init.java

public class Init {
	MyDate m = new MyDate();
	MyDate n;		//自动初始化为null
	int i = 5;
	int j;				//自动初始化为0
	
	public static void main(String[] args) {
		MyDate x = new MyDate();
		MyDate y;	//未初始化
		int a = 5;
		int b;		//不会自动初始化
		
		Init in = new Init();
		System.out.println(in.m);
		System.out.println(in.n);
		System.out.println(in.i);
		System.out.println(in.j);
		
		System.out.println(x);
		System.out.println(y);		//这个语句是错误的，y还没有初始化
		System.out.println(a);
		System.out.println(b);		//这个语句也是错误的，b没有初始化
	}
}
