package weixiao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileTst implements java.io.Serializable{
	String name;
	public FileTst()
	{
		name = "weixiang";
	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		FileOutputStream f1 = new FileOutputStream("a.txt");
		BufferedOutputStream b1  = new BufferedOutputStream(f1);
		ObjectOutputStream oos = new ObjectOutputStream(b1);
		oos.writeInt(5);
		oos.writeUTF("weixiang");
     	oos.writeObject(new FileTst());
		oos.close();
		b1.close();
		f1.close();
		FileInputStream fis = new FileInputStream("a.txt");
		ObjectInputStream oi = new ObjectInputStream(fis);
		int i = oi.readInt();
		String myString = oi.readUTF();
		FileTst myfile = (FileTst)(oi.readObject());
		System.out.println(myfile.name);
	}

}
