public class Promotion {
	public static void main(String[] args) {
		byte b1 = 5, b2 = 6;
		// b1 = b1 + b2; //这个语句是错误的，+操作返回的最小类型是int
		b1 += b2; // +=包括了一个隐含的强制转换
		// 等价于b1 = (byte) (b1+b2)
		float f = 3.0f;
		f = f + b1; // b1的值被自动提升为float类型的值，然后和f相加
		b1 = (byte) f; // float强制转换为byte
		f = 220.1234f;
		b1 = (byte) f; // 先舍弃小数部分，转换为int整型数据，再抛弃多余字节
		System.out.println(b1);
		double d = 10e44;
		b1 = (byte) d; // 先舍弃小数，转换为int类型，
		// 但是这个数大于int的最大值，所以赋给int最大值，
		// 再抛弃高3个字节，最后剩下一个字节，当然是全1
		System.out.println(b1); // 输出结果是-1
		int i = (int) d; // i的值为int类型的最大值
		                 //该数是欧拉在1772年所发现的一个梅森素数，它等于2^31-1，是32位操作系统中最大的符号型整型常量，
		System.out.println(i);
		new Promotion().a(b1, f); // 参数调用时，传递参数也发生了提升
	}

	public int a(int i, float f) {
		return (int) (i + f);
	}
}
