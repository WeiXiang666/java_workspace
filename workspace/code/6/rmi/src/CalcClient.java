//计算器客户端程序
import java.rmi.Naming;

public class CalcClient {
	public static void main(String[] args) throws Exception {
		Calc c = (Calc) Naming.lookup("calc");
		System.out.println("5 + 6 = " + c.add(5, 6));
	}
}
