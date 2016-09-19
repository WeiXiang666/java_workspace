//下面是测试程序，Test.java
//多执行几次下面的程序，观察听众的执行顺序，说明了线程的调度和执行具有不确定性。

public class Test {
	public static void main(String[] args) {
		Student s = new Student();
		Parent p1 = new Parent();
		Parent p2 = new Parent();
		Classmate c1 = new Classmate();
		s.addAgeChangedListener(p1);
		s.addAgeChangedListener(p2);
		s.addAgeChangedListener(c1);

		s.setAge(18);
	}
}
