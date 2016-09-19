public class C extends B {
	public void some() throws Exception {
	}// 错误，抛出了更多的检查异常，Exception比IOException类型大

	public static void c() {
	} // 静态方法和实例方法之间不能发生覆盖
}
