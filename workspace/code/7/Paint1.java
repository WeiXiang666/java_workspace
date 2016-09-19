/*下面是Paint1.java，一个绘图的例子，程序有不合适的地方，当然现在windows7操作系统提供了缓存和内容恢复，所以体现不出来。*/
import java.awt.*;
import java.awt.event.*;
public class Paint1 extends Frame implements MouseListener, MouseMotionListener
{
	/* 画鼠标拖动轨迹，就是在两次产生鼠标拖动的鼠标事件的位置上连线
	   所以，需要保存上次鼠标拖动事件的位置信息，可以和当前鼠标拖动事件
	   的位置进行画线操作  */
    int oldX = -1, oldY = -1;		//-1就表示不画线
	public static void main(String[] args) 
	{
                new Paint1();
	}
    public Paint1() {	//构造方法，进行绘图操作的一些初始化设置
        setBackground(Color.blue);	
        setForeground(Color.yellow);
        addMouseListener(this);		//听众就是Paint1对象本身
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
        oldX = -1; //当鼠标松开，那么这段轨迹就停止绘画了。
    }
    public void mouseMoved(MouseEvent me) {}
    public void mouseDragged(MouseEvent me) {
        int x = me.getX();  //得到产生鼠标事件的当时位置信息
        int y = me.getY();
        if (oldX == -1)
        {	//开始画线，保存轨迹线的起始位置
            oldX = x;   oldY = y;	
        } else {
        	Graphics g = getGraphics();		//获得当前的外观对象
			//在上一次鼠标拖动事件的位置和当前鼠标事件的位置之间画线
        	g.drawLine(oldX, oldY, x, y);		
        	oldX = x;   oldY = y;			//保存当前点坐标
		}
    }
}
