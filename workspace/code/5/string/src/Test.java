public class Test {
	public static void main(String[] args) {
		EmailValidator ev = new RegExProc(); // 哪个实现速度更快？
		// EmailValidator ev = new CharProc();
		// EmailValidator ev = new StringProc();

		String e1 = new String("abc@");
		String e2 = new String("@abc");
		String e3 = new String("abc");
		String e4 = new String("abc@def@gh");
		String e5 = new String("abc@def");

		System.out.println(ev.validate(e1));
		System.out.println(ev.validate(e2));
		System.out.println(ev.validate(e3));
		System.out.println(ev.validate(e4));
		System.out.println(ev.validate(e5));
	}
}
