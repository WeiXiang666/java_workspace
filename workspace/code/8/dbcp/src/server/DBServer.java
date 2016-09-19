package server;

public class DBServer {
	public static void main(String[] args) throws Exception {
		// NwServer.java，ThreadSupport.java见第6章的例子，直接复用过来就可以
		new NwServer(new MultiThreadSupport(new CrudProtocol()));
	}
}
