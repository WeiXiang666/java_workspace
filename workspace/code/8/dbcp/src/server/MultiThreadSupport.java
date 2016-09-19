package server;

import java.net.*;
import java.io.*;

/*
实现协议部分

下面的程序缩进有点过了，*_^
 */
public class MultiThreadSupport implements IOStrategy, Runnable {

    private Socket socket = null;
    private IOStrategy ios = null;

    public MultiThreadSupport(IOStrategy ios) {
        this.ios = ios;
    }

    public void service(Socket socket) {
        this.socket = socket;
        new Thread(this).start();
    }

    public void run() {
        ios.service(socket);
    }
}
