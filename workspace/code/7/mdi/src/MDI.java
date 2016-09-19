import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;

public class MDI extends JFrame // 顶级容器依然是JFrame
{ // 定义一个MDI父窗口成员
	JDesktopPane desktop;

	public MDI() {
		super("MDI Demo");
		try { // 设置外观，风格是Motif风格。
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			// 如果在运行之后修改了外观，那么还需要调用下面的语句来进行刷新外观
			// SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			System.out.println("Unsupported L&F");
		}
		// 创建父窗口
		desktop = new JDesktopPane();
		// 创建几个子窗口，同时设置窗口标题。
		JInternalFrame bottom = new MyInternalFrame("the bottom frame");
		JInternalFrame jif1 = new MyInternalFrame("the inter 1 frame");
		JInternalFrame jif2 = new MyInternalFrame("the inter 2 frame");
		JInternalFrame jif3 = new MyInternalFrame("the inter 3 frame");
		JInternalFrame up = new MyInternalFrame("the up frame");

		// JDesktopPane继承了JLayeredPane，支持分层
		desktop.add(bottom, JLayeredPane.DEFAULT_LAYER);
		desktop.add(jif1, new Integer(5));
		desktop.add(jif2, new Integer(6));
		desktop.add(jif3, new Integer(6));

		// 上层窗口，其他窗口遮盖不了这个窗口
		desktop.add(up, JLayeredPane.MODAL_LAYER);

		Container c = getContentPane();
		c.add(desktop); // 将JDesktopPane添加到JFrame中

		int inset = 100;
		// 取回屏幕尺寸大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 设置JFrame大小及位置，使之居于屏幕中央
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height
				- inset * 2);
		// 添加菜单条
		setJMenuBar(createMenuBar());
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Document"); // 添加菜单
		menu.setMnemonic(KeyEvent.VK_D); // 设置快捷键Alt+D
		JMenuItem menuItem = new JMenuItem("New");
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 创建子窗口
				JInternalFrame jif = new MyInternalFrame(
						"dynamic internal frame");
				desktop.add(jif, 0);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		return menuBar;
	}

	public static void main(String[] args) {
		new MDI();
	}
}
