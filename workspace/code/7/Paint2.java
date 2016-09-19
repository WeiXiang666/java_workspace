import java.awt.*;
import java.awt.event.*;
public class Paint2 extends Frame implements MouseListener, MouseMotionListener
{
    int[] xa = new int[100000];		//存放所有轨迹点
    int[] ya = new int[100000];
    int count = 0;					//实际点数计数器
	public static void main(String[] args) 	{	new Paint2();   }
    public Paint2() {
        setBackground(Color.blue);
        setForeground(Color.red);
        addMouseListener(this);
        addMouseMotionListener(this);
        setSize(400,300);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0); }});
    }
    public void mouseClicked(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}
    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {
        xa[count] = -1; ya[count++] = -1;
    }
    public void mouseMoved(MouseEvent me) {}
    public void mouseDragged(MouseEvent me) {
        xa[count] = me.getX();  ya[count++] = me.getY();
        repaint();       //请求刷新，而不是直接调用paint，没有必要频繁的调用paint
    }
    public void update(Graphics g) {	//将绘图背景清空这个步骤就免掉了
        paint(g);					//直接调用paint
    }
    public void paint(Graphics g) {	//组件外观绘制方法
        for(int i=0; i<count-1; i++) {
            if (xa[i]!=-1 && xa[i+1]!=-1)
            {
                g.drawLine(xa[i],ya[i],xa[i+1],ya[i+1]);
            }
        }
    }
}
