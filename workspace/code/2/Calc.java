public class Calc {
	//Java语言中的方法和C++语言中的成员函数类似
	public int add(int i, int j) {
		return i + j;
	}
	public int subtract(int i, int j) {
		return i - j;
	}
	public int factorial(int i) {		//求阶乘方法
		if(i==1) {
			return 1;
		}
		else {
			return factorial(i-1)*i;	//方法递归
		}
	}
	public static void main(String[] args) {
		Calc c = new Calc();
		System.out.println("5 + 6 = " + c.add(5, 6));
		System.out.println("11 - 5 = " + c.subtract(11, 5));
		System.out.println(c.factorial(5));
	}
}