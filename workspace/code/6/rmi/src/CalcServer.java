//计算器服务器程序
import java.rmi.Naming;

public class CalcServer {
	public static void main(String[] args) throws Exception {
		Calc c = new CalcImpl();
		Naming.bind("calc", c);
		System.out.println("ready.");
	}
}
