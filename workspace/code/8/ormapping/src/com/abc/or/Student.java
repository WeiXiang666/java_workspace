package com.abc.or;

public class Student implements java.io.Serializable {
	public Student() {
	}

	public Student(String id, String name, String email, int age, int java,
			boolean gender) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.java = java;
		this.gender = gender;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getJava() {
		return java;
	}

	public void setJava(int java) {
		this.java = java;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public void study(int score) { // 只要学习，成绩一定会有所提高
		java += new java.util.Random().nextInt(score) + 1; // 产生随机数，并提高成绩
		if (java > 100) {
			java = 100;
		} // 100分是满分，不能高于100
	}

	// 输出字符串
	public String toString() {
		return "id=" + id + "  name=" + name + "\tjava=" + java;
	}

	// 私有成员
	private String id, name, email;
	private int age, java;
	private boolean gender;
}
