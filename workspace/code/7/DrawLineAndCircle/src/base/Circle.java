package base;


public class Circle implements java.io.Serializable, Comparable {

    private double x, y, r;

    public Circle(double x, double y, double r) {
        //private Point center;
        
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Circle(Point p, double r) {
        this.x = p.getX();
        this.y = p.getY();
        this.r = r;
    }
    
    public Point getCenter() {
        return new Point(x, y);
    }

    public double getRadius() {
        return r;
    }
    
    public boolean isIntersect(Circle c) {
        Point p1 = getCenter();
        Point p2 = c.getCenter();

        double d = p1.distance(p2);
        
        return d <= r + c.r;

    }
    
    public double area() {
        return Math.PI * r * r;
    }

    @Override
    public int compareTo(Object o) {
        Circle c = (Circle) o;
        return (int) (area() - c.area());
    }
}
