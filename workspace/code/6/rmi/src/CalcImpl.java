//下面是远程接口的实现类，通过继承复用RMI框架的多线程及网络连接管理等功能

public class CalcImpl extends java.rmi.server.UnicastRemoteObject implements Calc {
	
	public CalcImpl() throws Exception {
		super();  //UnicastRemoteObject构造方法会抛出异常
	}

	public int add(int i, int j) {
		return i + j;
	}

	public int sub(int i, int j) {
		return i - j;
	}
}
