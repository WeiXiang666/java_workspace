public class Triangle extends Shape { // 抽象类的子类Triangle，Triangle.java
	private double b, h;

	public double area() {
		return 1.0 / 2.0 * b * h;
	}
}
