public class Test { // Test.java，主程序
	public static void main(String[] args) {
		final Stack stack = new Stack(); // main方法内嵌类可以访问这个变量
		Thread t1 = new Thread() { // 匿名类继承了Thread类
			public void run() { // 覆盖了run方法
				try {
					for (int i = 0; i < 10; i++) {// 产生a-z之间的随机字符
						char c = (char) ('a' + ((int) (Math.random() * 26)));
						stack.push(c); // 压入栈中
						System.out.println(c);
						Thread.sleep(c * 10); // 让当前程序休眠一会儿
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread t2 = new Thread() { // 上一个线程对象压入字符，这个线程弹出字符
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						Thread.sleep(100); // 休眠一会儿，等待字符栈中有数据
						char c = stack.pop();
						System.out.println(c);
						Thread.sleep(c * 10);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();
		t2.start();
	}
}
