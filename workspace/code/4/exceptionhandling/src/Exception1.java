public class Exception1 {

	public static void main(String[] args) {
		int[] a = new int[10]; // 下面会抛出java.lang.ArrayIndexOutOfBoundsException
		a[10] = 5; // 数组下标计数从0开始，数组a没有第11个元素

		MyDate d1 = new MyDate();
		d1.year = 2011;
		d1 = null; // 下行程序会抛出java.lang.NullPointerException
		d1.year = 2012; // 引用变量d1不指向任何对象，何来year数据？

		int i = 5, j = 0; // 下行程序会抛出java.lang.ArithmeticException
		i = i / j; // 整数除零

		ProjectManager p1 = new ProjectManager(); // ProjectManager继承Employee
		Employee e1 = new Employee(); // 下行程序会抛出java.lang.ClassCastException
		p1 = (ProjectManager) e1;

	}

}
