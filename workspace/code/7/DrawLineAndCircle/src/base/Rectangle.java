package base;

public class Rectangle implements java.io.Serializable, Comparable {
    private double x,y,w,h;

    public Rectangle(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    public double area() {
        return w * h;
    }
    
    public int compareTo(Object o) {
        Rectangle r = (Rectangle)o;
        return (int) ( area() - r.area());
    }
    
}
