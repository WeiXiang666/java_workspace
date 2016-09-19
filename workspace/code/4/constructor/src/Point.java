public class Point {		//Point.java
	
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x) {		//构造方法重载，这个现象在后续内容中有说明
		this(x, 0);			//调用本类的构造方法
	}
	
	public Point() {			//调用本类的构造方法
		this(0,0);
	}
	
	public static void main(String[] args) {
		Point p1 = new Point();		//创建实例，其成员变量x,y的为0,0
		Point p2 = new Point(5); 	//创建实例，其成员变量x,y的为5,0
		Point p3 = new Point(5,6); 	//创建实例，其成员变量x,y的为5,6
	}
}	
