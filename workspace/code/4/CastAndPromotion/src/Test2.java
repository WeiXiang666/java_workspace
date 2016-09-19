class Test2 {
	public static void main(String[] args) {
		Fruit f1 = new Fruit();
		Fruit f2 = new Apple();
		Apple a1 = null;
		Apple a2 = null;
		if (f1 instanceof Apple) { // 判断f1 指向的对象是否是Apple类的实例
			a1 = (Apple) f1; // 或者是Apple子类的实例
		}
		if (f2 instanceof Apple) { // 如果是，则返回true，然后才进行安全的转换
			a2 = (Apple) f2;
		}
		System.out.println(a1); // 打印输出结果为null
		System.out.println(a2); // 这个当然不是null

	}
}
