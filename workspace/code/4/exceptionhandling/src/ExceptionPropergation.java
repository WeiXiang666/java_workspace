public class ExceptionPropergation {

	public static void main(String[] args) throws Exception {
		new ExceptionPropergation().a();
	}

	public void a() {
		b();
	}

	public void b() {
		c();
	}

	public void c() {
		throw new ArithmeticException("divide by zero");
	}

}
