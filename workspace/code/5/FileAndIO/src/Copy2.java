import java.io.*;

public class Copy2 {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream(args[0]);
			FileOutputStream fos = new FileOutputStream(args[1]);
			int c = 0;
			byte[] buffer = new byte[8192]; 
			while ((c = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, c); 
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
