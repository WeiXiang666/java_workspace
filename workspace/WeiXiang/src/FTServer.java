import java.util.*;
import java.io.*;

public class FTServer {
	
	public static File share = null;
	
	public static void main(String[] args) throws Exception  {
		
		int port = 4321;
		
		Properties p = new Properties();
		p.load(FTServer.class.getClassLoader().getResourceAsStream("server.properties"));
		System.out.println(p.getProperty("share"));
		share = new File(p.getProperty("share"));
		
		if(!share.isDirectory()) {
			System.out.println("share directory not exists or isn't a directory");
			System.exit(-4);
		}
		
		port = Integer.parseInt(p.getProperty("port"));
		
		FTProtocol protocol = new FTProtocol();
		AdvancedSupport as = new AdvancedSupport(protocol);
		NwServer nw = new NwServer(as,port);
			
	}

}
