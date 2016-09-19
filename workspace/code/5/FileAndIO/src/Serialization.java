import java.io.*;

public class Serialization { // Serialization.java
	public static void main(String[] args) throws Exception {
		Student s1 = new Student();
		s1.setName("zs");
		s1.setId("001");
		s1.setAge(20);

		FileOutputStream fos = new FileOutputStream("a.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s1); // �����������a.ser�ļ��У�ʵ�����л����
		oos.close();
		fos.close();

		FileInputStream fis = new FileInputStream("a.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Student s2 = (Student) ois.readObject(); // ���ļ��ж������л�����
		ois.close();
		fis.close();

		System.out.println(s2.getId()); // ��ӡ�����������zs

	}
}
