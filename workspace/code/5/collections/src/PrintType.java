class G<T> { // 一个泛型类，封装了一个属性数据，属性数据的类型是T
	private T data; // 可以创建一个封装字符串，封装Integer类型的G对象

	public G(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}

class PrintType { // 这个类输出G泛型对象的封装的属性数据的类型的名称
	/*
	 * 这个方法定义错误 public void print(G<Object> o) { // 类型参数T可能是String，可能是Integer //
	 * 如何定义这个对象呢？能使用G<Object>么？
	 * System.out.println(o.getData().getClass().getName()); }
	 */

	public void print(G<?> o) { // 类型参数T可能是String，可能是Integer
		// 如何定义这个对象呢？能使用G<Object>么？
		System.out.println(o.getData().getClass().getName());
	}

	public static void main(String[] args) {
		// 创建了两个G类型的对象，一个封装了Integer对象，一个封装了String对象
		G<Integer> g1 = new G<Integer>(new Integer(5));
		G<String> g2 = new G<String>(new String("nihao"));

		PrintType p = new PrintType();
		p.print(g1); // 这个语句有错误，G<Object>和G<Integer>不能转换
		p.print(g2); // 这个语句有错误，G<Object>和G<String>不能转换
	}
}
