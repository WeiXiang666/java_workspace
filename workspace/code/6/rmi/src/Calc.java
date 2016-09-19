import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calc extends Remote { // 和本地方法不同，远程方法抛出远程异常
	
	public int add(int i, int j) throws RemoteException;

	public int sub(int i, int j) throws RemoteException;
}
