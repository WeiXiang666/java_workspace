package base;


public class Line implements java.io.Serializable {

    private double a, b;  //y = a*x + b
    //private Point p1, p2;  

    public Line(double a, double b) {
        this.a = a;
        this.b = b;
    }
    
    static public Line fromTwoPoints(Point p1, Point p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        
        double a = (y2 - y1)/(x2-x1);
        //double b = y1 - a * y1;   //error!
        double b = y1 - a * x1;
        
        return new Line(a,b);
        
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }
    
    public double xToY(double x) {
        return a * x + b;
    }

    @Override
    public String toString() {
        return "Line{" + "a=" + a + ", b=" + b + '}';
    }

    public boolean isIntersect(Line l) {

        return !(Double.doubleToLongBits(a) == Double.doubleToLongBits(l.a));
    }

    public Point intersectAt(Line l) {
        return new Point((l.b - b) / (a - l.a), (a * l.b - l.a * b) / (a - l.a));
    }

    public boolean equals(Object o) {
        /*
        if (o instanceof Line) {
        Line l = (Line) o;
        return this.a == l.a && this.b == l.b;
        }
        return false;
         * 
         */

        if (o instanceof Line) {
            long la, lb, la2, lb2;
            Line l = (Line) o;
            la = Double.doubleToLongBits(a);
            lb = Double.doubleToLongBits(b);
            la2 = Double.doubleToLongBits(l.a);
            lb2 = Double.doubleToLongBits(l.b);
            return la == la2 && lb == lb2;

        } else {
            return false;
        }


    }

    public int hashCode() {
        return new Double(a).hashCode() ^ new Double(b).hashCode();
    }

    public static void main(String[] args) {
        Line l1 = new Line(5, 6);
        Line l2 = new Line(7, 9);

        System.out.println(l1.isIntersect(l2));
        System.out.println(l1.intersectAt(l2));

    }
}
