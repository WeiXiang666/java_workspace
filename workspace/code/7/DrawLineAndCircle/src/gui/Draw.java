package gui;

import java.awt.Frame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import base.*;

public class Draw extends Frame {

    Point p1 = new Point(166, 266);
    Point p2 = new Point(422, 233);
    Point p3 = new Point(288, 169);
    Line l1 = Line.fromTwoPoints(p1, p3);
    Line l2 = Line.fromTwoPoints(p2, p3);
    Circle c1 = new Circle(p1, 80);
    //Circle c2 = new Circle(p3, 90);
    Circle c2 = new Circle(p3, 50);

    public void init() {

        setBackground(Color.blue);
        setForeground(Color.yellow);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });


        setSize(600, 400);
        setVisible(true);

        System.out.println("circle 1 and 2 intersect? answer is: " + c1.isIntersect(c2));

    }

    public void paint(Graphics g) {
        drawPoint(p1, g);
        drawPoint(p2, g);
        drawPoint(p3, g);

        drawLine(l1, g);
        drawLine(l2, g);
        drawCircle(c1, g);
        drawCircle(c2, g);
    }

    public void drawPoint(Point p, Graphics g) {
        int x = (int) Math.round(p.getX());
        int y = (int) Math.round(p.getY());

        g.drawLine(x - 2, y - 2, x + 2, y + 2);
        g.drawLine(x - 2, y + 2, x + 2, y - 2);

    }

    public void drawLine(Line l, Graphics g) {
       int y1 = (int) Math.round(l.xToY(0));
       int y2 = (int) Math.round(l.xToY(600));
       g.drawLine(0, y1, 600, y2);
    }

    public void drawCircle(Circle c, Graphics g) {
        int x = (int) Math.round(c.getCenter().getX());
        int y = (int) Math.round(c.getCenter().getY());
        int r = (int) Math.round(c.getRadius());

        g.drawOval(x - r, y - r, r + r, r + r);
    }

    public static void main(String[] args) {
        Draw d = new Draw();
        d.init();
    }
}
