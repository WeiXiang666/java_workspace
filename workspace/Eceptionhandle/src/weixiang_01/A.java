package weixiang_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class A {
	public void set()
	{
		System.out.println("set");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(args[0]);
//		Object o = new A();
//		A a=(A)o;    
//		o.set()			//ClassCastException 引用转换异常
		File a = new File("a.bat");
		try {
			FileInputStream fis = new FileInputStream(a);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
