class MyDate {
	int year, month, day;
	public String toString() {
		return "year=" + year + ", month=" + month + ", day="+day;
	}
}
class PassParameter {
	public static void main(String[] args) {
		PassParameter p1 = new PassParameter();
		MyDate m = new MyDate();		//在内存堆中创建了一个MyDate对象
		m.year = 2012; m.month = 1; m.day = 1;
		p1.display(m);					//传递引用变量m的值给display方法
										//输出结果是year=2012, month=1, day=1
		System.out.println(m.year);
	}
	
	public void display(MyDate m) {		//参数变量m的值指向哪个对象呢？
										//m的值是调用方法传入的，值传递。
		System.out.println(m.toString());	
		m.year = m.year - 1;			//通过m变量访问year数据，
										//将那个对象的year数据减1
		m = new MyDate();				//display方法中的变量m的值更改
										//指向一个新的对象
		m.year = 1997;					//方法结束，m指向的对象被释放
	}
}
