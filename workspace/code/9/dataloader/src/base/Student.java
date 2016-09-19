package base;

public class Student implements java.io.Serializable, Comparable, Cloneable {
	private String id, name, email; // 学号，名字，邮件地址
	private int java, age; // Java成绩，年龄
	private boolean gender; // 性别，true：男；false：女；

	public Student() {
	}

	public Student(String id, String name, String email, int java, int age,
			boolean gender) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.java = java;
		this.age = age;
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setJava(int java) {
		this.java = java;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public int getJava() {
		return java;
	}

	public String getName() {
		return name;
	}

	public boolean getGender() {
		return gender;
	}

	public int hashCode() {
		return id.hashCode();
	}

	public boolean equals(Object o) {
		if (o instanceof Student) {
			return id.equals(((Student) o).id);
		}
		return false;
	}

	public int compareTo(Object o) {
		Student s = (Student) o;
		return s.java - this.java; // 成绩高者，对象“小”，成绩低者，对象“大”
	}

	public String toString() {
		return "Student{" + "id=" + id + ", name=" + name + ", email=" + email
				+ ", java=" + java + ", age=" + age + ", gender=" + gender
				+ '}';
	}

	public static void main(String[] args) throws Exception { // 测试程序
		Student s1 = new Student("007", "zxx", "zxx@163.com", 99, 20, true);
		Student s2 = (Student) s1.clone(); // 测试clone功能
		System.out.println("s1 == s2 " + (s1 == s2));
		System.out.println("s1.equals(s2) " + s1.equals(s2)); // 测试相等判断功能
		System.out.println(s1); // 测试toString方法
		System.out.println(s2);
	}
}
