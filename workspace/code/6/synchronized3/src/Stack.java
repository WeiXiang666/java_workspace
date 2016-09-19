class Stack {
	private int index = 0;
	private char[] data = new char[100];

	synchronized public void push(char c) {
		while (index == data.length) { // 不应该使用if来进行判断
			try { // 对阻塞条件的判断应该使用while
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		data[index] = c;
		index++;
		notify(); // 建议改为notifyAll(); 尽管效率稍低
	}

	synchronized public char pop() {
		while (index == 0) { // 不应该使用if来进行判断
			try { // 对阻塞条件的判断应该使用while
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		index--;
		notifyAll(); // 这样比调用notify()更好
		return data[index];
	}
}
