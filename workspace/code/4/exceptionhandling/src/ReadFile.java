import java.io.*;

public class ReadFile {
	public static void main(String[] args) {
		// FileInputStream fis = new FileInputStream("a.dat");
		// int c = fis.read();
		// fis.close();

		try {
			FileInputStream fis = new FileInputStream("a.dat");
			int c = fis.read();
			fis.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} catch (Throwable e) {
			while (e != null) {		//±È¿˙“Ï≥£°∞¡¥°±
				System.out.println(e.getMessage());
				e = e.getCause();
			}
		}
	}

	public int readFile() throws IOException {
		FileInputStream fis = new FileInputStream("a.dat");
		int c = fis.read();
		fis.close();
		return c;
	}

}
