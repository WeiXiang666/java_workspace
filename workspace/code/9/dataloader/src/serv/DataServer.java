package serv;

import java.net.*;
import java.io.*;
import java.util.*;
import base.*;
import net.sf.json.*;

public class DataServer {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(1234);
			System.out.println("data server is ready.");
			while (true) {
				Socket s = ss.accept();
				new ServThread(s).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// ServThread类也定义在DataServer.java文件中，并没有单独放在一个源文件中。
class ServThread extends Thread {
	private Socket s;

	public ServThread(Socket s) {
		this.s = s;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(s.getInputStream(),
					"GBK");
			BufferedReader br = new BufferedReader(isr);
			OutputStreamWriter osw = new OutputStreamWriter(
					s.getOutputStream(), "GBK");
			/*
			 * 服务器中的数据集合来自于服务器上的Excel文件，然后通过网络 共享给客户端
			 */
			DataLoaderFactory factory = new DataLoaderFactory();
			DataLoader loader = factory.getDataLoader("impl.ExcelDataLoader");
			ArrayList<Student> r = loader.load();

			String command = br.readLine();
			if (command.equals("get")) { // JSON编程非常简单
				JSONArray j1 = JSONArray.fromObject(r);
				// 将数据集合以JSON字符串的形式传输给客户端
				osw.write(j1.toString());
				osw.flush();
			}
			osw.close();
			isr.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
