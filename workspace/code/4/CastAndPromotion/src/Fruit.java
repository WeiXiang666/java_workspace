public class Fruit {
}

class Apple extends Fruit {
}

class Test {
	public static void main(String[] args) {
		int i1 = 5, i2 = 1000;
		byte b1 = (byte) i1;
		byte b2 = (byte) i2; // int转换到byte,
		// 转换操作直接将左侧高3个字节的数据丢弃掉
		System.out.println(b1); // 第一个数据转换后，值未变
		System.out.println(b2); // 第二个数据转换后，值发生变化

		Fruit f1 = new Fruit();
		Fruit f2 = new Apple(); // 父类的引用变量可以指向子类实例
		Apple a1 = new Apple();
		Apple a2;
		a1 = f1; // 语法错误，大类型数据直接赋给小类型变量。
		a2 = f2; // 语法错误，大类型数据直接赋给小类型变量。

		a1 = (Apple) f1; // 语法没有错误，但存在赋值风险！
		a2 = (Apple) f2;

		f1 = a1; // 这个是赋值提升，编译和运行都没有问题
		f2 = a2;
	}
}
