public class Task2 implements Runnable {		//Task2.java
	public void run() {
		System.out.println(Thread.currentThread().getName() + " started.");
		int k = 0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<1000;j++) {
				k = i * j;
			}
			System.out.println("time millis: " + System.currentTimeMillis());
		}
		System.out.println(Thread.currentThread().getName() + " ended.");
	}
}

