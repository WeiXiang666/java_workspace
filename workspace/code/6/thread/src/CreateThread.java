public class CreateThread { // CreateThread.java
	public static void main(String[] args) {
		Runnable r1 = new Task1(); // 创建两个任务对象
		Runnable r2 = new Task2(); // 这两个任务对象提供 run方法

		Thread t1 = new Thread(r1); // 构造一个线程对象，名称采用默认的命名
		Thread t2 = new Thread(r2, "thread-2"); // 创建线程，命名为thread-2

		t1.start(); // 启动线程
		t2.start();
		System.out.println("done.");
		// 这个程序可以运行多次，并调整循环次数，可以看到线程的运行情况
		// 依赖于线程的调度，这个程序多次运行的结果可能不同。
	}
}
