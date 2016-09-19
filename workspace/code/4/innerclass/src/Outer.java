public class Outer {
	int i = 5;

	public void a() {
		class MethodEnclosed { // 方法内嵌类，定义在方法中
			int i = 6;

			public void a() {
				System.out.println(i); // 访问当前类的成员
				System.out.println(this.i); // 和上条语句是一样的
				System.out.println(Outer.this.i); // 外部类的成员
			}
		} // 定义结束，在其后才可以使用
		MethodEnclosed me = new MethodEnclosed();
		me.a(); // 和方法内的变量一样，只有在方法内可用
	}

	class Inner { // 成员内部类
		int i = 7;

		public void a() {
			System.out.println(i); // 默认访问的是当前类的成员
			System.out.println(this.i); // 和上条语句是一样的
			System.out.println(Outer.this.i); // 外部类成员的访问形式
		}
	}

	public static void main(String[] args) {
		Outer o = new Outer(); // 创建一个外部类对象
		Inner i = o.new Inner(); // 再创建其内部类对象
		o.a(); // 如果是静态内部类，就没有必要创建
		i.a(); // 外部类实例了。
	}
}
