public class Fibo {
	public static void main(String[] args) {
		Fibo f = new Fibo();
		System.out.println(f.fibo1(7));	//这两种方法哪种效率更高？
		System.out.println(f.fibo2(7));
	}

	public int fibo1(int n) {			//使用方法递归来实现
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fibo1(n - 1) + fibo1(n - 2);
		}
	}

	public int fibo2(int n) {			//使用循环来实现
		int a0 = 0, a1 = 1, r = 0;
		if (n == 0) {
			return a0;
		} else if (n == 1) {
			return a1;
		} else {
			for (int i = 2; i <= n; i++) {
				r = a0 + a1;			//当前项的值
				a0 = a1;				//前2项的值
				a1 = r;				//前1项的值
			}
			return r;
		}
	}
}
