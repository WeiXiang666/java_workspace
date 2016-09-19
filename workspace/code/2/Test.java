/*
   并不建议在一个源文件中声明多个类，这里仅仅是举例，
   在一个源文件中，外层的公开类仅能定义一个，并且，
   文件名称和类名要一致。
*/

class MyDate {
	int year, month, day;
}

class Test {
	public static void main(String[] args) {
		new MyDate();
		MyDate d1 = new MyDate();
	}
}

class Test2 {
	public static void main(String[] args) {
		
		MyDate d1 = new MyDate();
		
		MyDate d2, d3;	//这里并没有声明和创建MyDate对象，
		//仅仅是声明了两个引用变量，
		//Java引用变量如同C/C++语言中的指针。
		
		MyDate d4 = new MyDate();		//定义引用变量并创建对象
		MyDate d5 = new MyDate();
		
		d1.year = 1997; d1.month = 7; d1.day = 1;	//通过.操作符来访问对象的成员
		//d2.year = 1999; d2.month = 12; d2.day = 20;  //d2引用并没有指向某个对象
													//这样的程序是不允许的。
		d2 = d4;
		d3 = d5;
		d2 = null;
		d3 = null;
		d4 = null;
		d5 = null;
	}
}

class ArrayTest {
	public static void main(String[] args) {
		int[ ] a;				//仅仅声明了一个整型数组类型的引用变量
		int b[ ];				//另外一种形式，不建议使用
		int[ ] c = new int[10];	//声明数组引用变量，创建数组对象，长度是10
		byte[ ] ba = new byte[0];
		b = c;
		a = c;
		a[0] = 1;
		b[1] = 3;
		c[2] = 5;				//a, b, c这三个引用变量指向的是同一个数组对象
		System.out.println(b.length);	//访问某个对象的成员，使用成员操作符.
		System.out.println(ba.length);
		
		MyDate[] da = new MyDate[3];	//创建了一个日期类型数组
		da[0] = new MyDate();			//为这三个元素初始化
		da[1] = new MyDate();
		da[2] = new MyDate();
									//运行   java ArrayTest
		da[3] = new MyDate();  		//这个语句是错误的！数组下标计数从0开始
									//注意运行时报错的具体信息
		int[ ][ ] m = new int[2][3];		//二维数组，常用的定义，2*3个整数
		int[ ][ ] n = new int[2][ ];			//不常用
		n[0] = new int[3];
		n[1] = new int[4];
		int[ ] array[ ] = new int[3][4];		//不常用，这种形式也是正确的。
	}
}


class VariableTest {
	MyDate m = new MyDate();	//成员变量在堆中，该成员变量是一个引用，指向的
	public void print() {			//对象也在堆中
		MyDate m = new MyDate();	//局部变量m 在栈中，其指向的对象当然在堆中
		System.out.println(m.year);		//这个m变量引用的是上个语句创建的对象
	}
	public static void main(String[] args) {
		VariableTest vt = new VariableTest();
		vt.print();
	}
}





