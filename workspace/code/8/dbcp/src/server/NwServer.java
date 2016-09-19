package server;

import java.net.*;

/*
实现网络通信，可以服务于任何应用，没有提供协议，
也就是说NwServer可以适用于任何协议。
 */
public class NwServer {

    private int port = 4321;

    public NwServer(IOStrategy io) throws Exception {
        //它只负责接受客户端的连接请求，建立网络建立（socket连接）
        //然后将连接提交给协议处理程序。

        ServerSocket server = new ServerSocket(port);
        System.out.println("Server is ready");

        while (true) {
            Socket socket = server.accept();

            InetAddress ia = socket.getInetAddress();
            System.out.println(ia.getHostName() + "(" + ia.getHostAddress() + ") connected.");

            io.service(socket);
        }
    }
}
