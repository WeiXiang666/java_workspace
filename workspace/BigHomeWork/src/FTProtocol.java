import java.net.Socket;
import java.io.*;
import java.util.*;

public class FTProtocol implements IOStrategy {

	@Override
	public void service(Socket socket) {
		String client = socket.getInetAddress().getHostName() + "(" + socket.getInetAddress().getHostAddress() + ")";

		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);

			String filename = null;
			long len = 0;
			byte[] buffer = new byte[4096];
			long r = 0;
			int rr = 0;

			while (true) {
				int command = dis.readInt();
				switch (command) {
				case 1: // file upload
					filename = dis.readUTF();//读取文件名   即Client参数中的文件名参数
					len = dis.readLong();
					FileOutputStream fos = new FileOutputStream(new File(
							FTServer.share, filename));						//client中的uplode操作的传输数据
					BufferedOutputStream bos = new BufferedOutputStream(fos);//dos.writeInt(1);									
					dos.flush();											//dos.writeUTF(f.getName());
					r = 0;													//	dos.writeLong(f.length());
					rr = 0;
					while (r < len) {
						if (len - r >= buffer.length) {
							rr = dis.read(buffer, 0, buffer.length);
						} else {
							rr = dis.read(buffer, 0, (int) (len - r));
						}

						r = r + rr;
						bos.write(buffer, 0, rr);
					}

					bos.close();
					fos.close();
					break;
				case 2: // file download
//					dos.writeInt(2);  client中的download操作的传输参数
//					dos.writeUTF(filename);
//					dos.flush();
					filename = dis.readUTF();
					dos.writeUTF(filename);
					File t = new File(FTServer.share, filename);
					dos.writeLong(t.length());
					dos.flush();
					FileInputStream fis = new FileInputStream(t);
					BufferedInputStream bis = new BufferedInputStream(fis);

					while ((rr = bis.read(buffer)) != -1) {
						dos.write(buffer, 0, rr);
						dos.flush();
					}

					bis.close();
					fis.close();
					break;

				case 3: // list files
					String[] files = FTServer.share.list();
					List<String> list = new LinkedList<String>();
					for(int i=0;i<files.length;i++) {
						if(new File(FTServer.share, files[i]).isDirectory()) continue;
						list.add(files[i]);
					}
					
					files = list.toArray(new String[0]);
					
					dos.writeInt(files.length);
					dos.flush();
					for (int i = 0; i < files.length; i++) {
						dos.writeUTF(files[i]);
					}
					dos.flush();
					break;
				case 4:
				int i = dis.readInt();
				String[] files2 = FTServer.share.list();
				File file = new File(FTServer.share+File.separator+files2[i-1]);
				file.delete();
				dos.writeInt(1);
				dos.flush();
				break;
				}
			}
		} catch (Exception e) {
			if (e instanceof EOFException) {
				System.out.println(client + " disconnected");
			} else {
				e.printStackTrace();
			}

		}
	}
}
