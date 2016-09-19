public class Stack { // Stack.java
	private char[] data = new char[100]; // 该字符栈最大容量是100个字符
	private int index;

	synchronized public void push(char c) {
		data[index] = c;
		index++;
	}

	synchronized public char pop() {
		index--;
		return data[index];
	}
}
