import java.util.*;

class Student {
	private String id, name; // 定义了类的私有属性成员
	private int age;
	private boolean gender; // 篇幅有限，没有提供属性的读写方法getter/setter

	public Student(String id, String name, int age, boolean gender) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String toString() { // 覆盖Object类的toString()方法
		return "id = " + id + "\t\tname = " + name;
	}
}

public class MapDemo {
	public static void main(String[] args) {
		Student s1 = new Student("001", "张三", 19, true);
		Student s2 = new Student("002", "李四", 19, true);
		Student s3 = new Student("003", "王五", 20, true);
		Map map = new TreeMap();
		map.put("001", s1); // 注意键值对的添加顺序和遍历顺序
		map.put("003", s3);
		map.put("002", s2);

		Set set = map.keySet(); // 返回关键字集合，
		Iterator iterator = set.iterator(); // 获得集合的遍历器对象
		while (iterator.hasNext()) { // 循环遍历集合
			String s = (String) iterator.next(); // 得到关键字对象
			Student stu = (Student) map.get(s); // 得到映射的值对象
			System.out.println(stu); // 输出值对象
		}
	}
}
