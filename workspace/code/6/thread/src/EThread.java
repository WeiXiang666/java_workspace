public class EThread extends Thread {
	public void run() {
		for (int i = 0; i < 500; i++) {
			System.out
					.println(Thread.currentThread().getName() + " running...");
		}
	}

	public static void main(String[] args) {
		Thread t1 = new EThread();
		Thread t2 = new EThread();
		t1.start();
		t2.start();
	}
}
