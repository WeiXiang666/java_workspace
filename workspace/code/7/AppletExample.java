import java.applet.*;
import java.awt.*;
public class AppletExample extends Applet {
    String message = "";
    public void init() {
        message = getParameter("Message");
    }
    public void paint(Graphics g) {
        g.drawString(message,20,50);
    }
}
