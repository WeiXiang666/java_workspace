package weixiao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Statement;

import cal.Student;

public class GetClass {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchFieldException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchFieldException {
		// TODO Auto-generated method stub
		
		Class c = Class.forName("cal.Calc");
		Object o = c.newInstance();
		Class[] args1 = new Class[2];
		args1[0]=int.class;
		args1[1]=int.class;
		Method add =c.getDeclaredMethod("add",args1);
		Method sub = c.getDeclaredMethod("sub", args1);
		Integer []argments = new Integer[2];
		argments[0]=new Integer(5);argments[1]=new Integer(9);
		Object result1=add.invoke(o,argments);
		Object result2=sub.invoke(o, argments);
		System.out.println((Integer)result1);
		System.out.println((Integer)result2);
		Class student = Class.forName("cal.Student");
		Object s = student.newInstance();
		Field field=student.getDeclaredField("name");
		field.setAccessible(true);
		field.set(s, "weixiang");
		System.out.println(((Student)s).getName());
		
	}

}
