class StaticMember {
	int i;						//实例属性成员，实例成员
	static int j;				//静态属性成员，类成员
	
	public void print() {			//实例行为，实例方法
		System.out.println(i);
	}
	
	static public void print(int i) {	//静态行为，静态方法，修饰符组合没有顺序要求
		System.out.println(i);
	}
	
	public static void main(String[] args) {
		//i = 5;  				//这个语句是错误的，实例数据必须通过对象来操作
		j = 6;					//由于在StaticMember类内部，类名可以省略掉
							//如果在其它类中，应该写StaticMember.j = 6;
		print(5);				//也可以写StaticMember.print(5);
		
		//print();				//这个也是错误的语句
		
		StaticMember s1 = new StaticMember();	//创建了两个实例，
		StaticMember s2 = new StaticMember();	//两个对象分配了不同的存储空间
		
		s1.i = 5;   s2.i = 6;		//通过引用来分别访问两个不同对象的数据成员
		s1.print(); s2.print();	//访问这两个实例的行为	
}
}
