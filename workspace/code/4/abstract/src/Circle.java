public class Circle extends Shape { // 抽象类的子类Circle，Circle.java
	private double radius;

	public double area() { // 覆盖方法，具体的实现方法
		return Math.PI * radius * radius; // Math类的静态成员，常量PI
	}
}
