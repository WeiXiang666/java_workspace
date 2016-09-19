package weixiang_02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Exceptions_test {
	
	
	void ExceptionTransfer() throws AException
	{
		try {
			//你的可能会出异常的代码块
		} catch (Exception e) {
			// 在此处将异常类型进行特定转换
			throw new AException("自定义异常");
		}
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Exceptions_test exc=new Exceptions_test();
		try {
			exc.ExceptionTransfer();
		} catch (AException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
