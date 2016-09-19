package cal;
public class Student implements Comparable {
	private String id, name; // 良好的封装应该将属性定义为私有
	private int age;
	private boolean gender;

	// 下面是重载的两个构造方法
	public Student(String id, String name, int age, boolean gender) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public Student() {
	}

	// 下面是属性的读写方法，getters/setters.
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	// 相等判断方法，覆盖Object类的equals方法
	public boolean equals(Object o) {
		if (o instanceof Student) {
			Student s = (Student) o;
			return s.id.equals(id) && // 调用字符串类的equals方法
					s.name.equals(name) && s.age == age && s.gender == gender;
		} else {
			return false;
		}
	}

	public int compareTo(Object o) { // 调用者程序应该保证相同类型的对象进行比较
		Student s = (Student) o; // 否则有可能抛出ClassCastException异常！
		return this.age - s.age; // 哪个同学的年龄大，则该同学对象就大
		// 也可以调用String类的compareTo方法，按照名字或学号进行比较判断
		// return this.name.compareTo(s.name);
		// return this.id.compareTo(s.id);
	}

}
