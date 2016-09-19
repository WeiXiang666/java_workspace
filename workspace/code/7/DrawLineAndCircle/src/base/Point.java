package base;


public class Point implements java.io.Serializable {

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public Point() {
        this(0, 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean equals(Object o) {
        /*
        if (o instanceof Point) {
        Point p = (Point) o;
        return this.x == p.x && this.y == p.y;
        } else {
        return false;
        }
         */

        if (o instanceof Point) {
            Point p = (Point) o;
            long lx, ly, lpx, lpy;
            lx = Double.doubleToLongBits(x);
            ly = Double.doubleToLongBits(y);
            lpx = Double.doubleToLongBits(p.x);
            lpy = Double.doubleToLongBits(p.y);

            return lx == lpx && ly == lpy;

        }
        return false;
    }

    public int hashCode() {
        return new Double(x).hashCode() ^ new Double(y).hashCode();
    }

    public double distance(Point p) {
        return  Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));

    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }

    public static void main(String[] args) {
        Point p1 = new Point(15.3, 6.4);
        Point p2 = new Point(2.1, 3.5);
        Point p3 = new Point();

        System.out.println(p1.distance(p3));

    }
}
