//下面是GUI显示处理程序，MainJFrame.java文件
package gui;

import java.awt.*;
import java.util.*;
import base.*;
import javax.swing.*;
import javax.swing.table.*;

import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.*;
import org.jfree.data.category.*;
import org.jfree.data.general.*;
import org.jfree.ui.RectangleEdge;

public class MainJFrame extends JFrame {
	JTabbedPane jTabbedPane = null; // 选项卡组件
	JScrollPane jscrollPane = null;
	JTable jTable = null; // Swing表格组件

	public MainJFrame(ArrayList<Student> model) {
		jTable = new JTable(new MyTableModel(model)); // 根据模型创建UI
		jTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12)); // 设置字体

		jTable.setFillsViewportHeight(true); // 高度和滚动窗格的高度一致
		// 设置列宽
		jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(170);
		// 设置行高
		jTable.setRowHeight(22);
		jscrollPane = new JScrollPane(jTable);
		// 设置滚动策略
		jscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jscrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		// 创建一个选项卡组件
		jTabbedPane = new JTabbedPane();
		// 添加一个选项卡，名称为Table
		jTabbedPane.add("Table", jscrollPane);

		// 统计成绩分布，不同区间的人数数量
		int l1 = 0, l2 = 0, l3 = 0, l4 = 0, l5 = 0;
		int sum = 0;
		for (int i = 0; i < model.size(); i++) {
			Student s = model.get(i);
			sum += s.getJava();
			if (s.getJava() < 60) { // 统计0---59分区间的学生人数
				l1++;
			} else if (s.getJava() < 70) { // 统计60---69分区间的学生人数
				l2++;
			} else if (s.getJava() < 80) { // 统计70---79分区间的学生人数
				l3++;
			} else if (s.getJava() < 90) { // 统计80---89分区间的学生人数
				l4++;
			} else {
				l5++; // 统计90---100分区间的学生人数
			}
		}
		int avg = sum / model.size(); // 计算平均成绩
		// 下面是饼状图的生成代码
		DefaultPieDataset ds1 = new DefaultPieDataset(); // 使用饼状图数据模型
		ds1.setValue("0--59", new Integer(l1)); // 设置模型数据，section及对应值
		ds1.setValue("60--69", new Integer(l2));
		ds1.setValue("70--79", new Integer(l3));
		ds1.setValue("80--89", new Integer(l4));
		ds1.setValue("90--100", new Integer(l5));

		PiePlot pp = new PiePlot(ds1); // 画饼状图
		pp.setIgnoreZeroValues(true);
		// 设置secion标签的内容，
		// {0}表示section名，{1}表示section的值，{2}表示百分比。
		pp.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
		// 设置图例内容及格式，
		// {0}表示section名，{1}表示section的值，{2}表示百分比。
		pp.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}:({1}人)"));
		pp.setLabelFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		// 对于不及格这个部分，脱离圆饼。下面设置的是脱离的比例大小
		pp.setExplodePercent("0--59", 0.1);

		JFreeChart chart1 = new JFreeChart("成绩分布(平均分：" + avg + ")",
				JFreeChart.DEFAULT_TITLE_FONT, pp, true);
		// 创建一个面板，放统计图
		ChartPanel panel1 = new ChartPanel(chart1);
		// 添加到选项卡中，名称为Pie Chart
		jTabbedPane.add("Pie Chart", panel1);

		// 下面是柱状图的生成代码，先创建模型数据集
		DefaultCategoryDataset ds2 = new DefaultCategoryDataset();
		// 统计男女生在各个分数段的人数
		int f1 = 0, f2 = 0, f3 = 0, f4 = 0, f5 = 0; // 女生人数统计变量
		int m1 = 0, m2 = 0, m3 = 0, m4 = 0, m5 = 0; // 男生人数统计变量
		for (int i = 0; i < model.size(); i++) {
			Student s = model.get(i);
			if (s.getJava() < 60) {
				if (s.getGender()) {
					m1++;
				} else {
					f1++;
				}
			} else if (s.getJava() < 70) {
				if (s.getGender()) {
					m2++;
				} else {
					f2++;
				}
			} else if (s.getJava() < 80) {
				if (s.getGender()) {
					m3++;
				} else {
					f3++;
				}
			} else if (s.getJava() < 90) {
				if (s.getGender()) {
					m4++;
				} else {
					f4++;
				}
			} else {
				if (s.getGender()) {
					m5++;
				} else {
					f5++;
				}
			}
		}
		ds2.addValue(f1, "女", "0--59");
		ds2.addValue(m1, "男", "0--59");
		ds2.addValue(f2, "女", "60--69");
		ds2.addValue(m2, "男", "60--69");
		ds2.addValue(f3, "女", "70--79");
		ds2.addValue(m3, "男", "70--79");
		ds2.addValue(f4, "女", "80--89");
		ds2.addValue(m4, "男", "80--89");
		ds2.addValue(f5, "女", "90--100");
		ds2.addValue(m5, "男", "90--100");
		// 直接产生3维柱状图
		JFreeChart chart2 = ChartFactory.createBarChart3D("Java成绩统计分析图表",
				"成绩分布", "人数", ds2, PlotOrientation.VERTICAL, true, true, false);
		chart2.setBackgroundPaint(Color.white);

		// 得到绘图对象，进而进行设置
		CategoryPlot cp = chart2.getCategoryPlot();
		cp.setBackgroundPaint(Color.lightGray); // 图表数据显示部分背景色
		cp.setDomainGridlinePaint(Color.white); // 横坐标网格线白色
		cp.setDomainGridlinesVisible(true); // 横坐标可见
		cp.setRangeGridlinePaint(Color.white); // 纵坐标网格线白色
		cp.setRangeGridlinesVisible(true); // 纵坐标可见
		// 设置标题字体
		chart2.getTitle().setFont(JFreeChart.DEFAULT_TITLE_FONT);

		Font font2 = new Font("微软雅黑", 10, 16); // 设定字体、类型、字号
		cp.getDomainAxis().setLabelFont(font2); // 相当于横轴或理解为X轴
		cp.getRangeAxis().setLabelFont(font2); // 相当于竖轴理解为Y轴

		// 设置图例对象
		Font font3 = new Font("微软雅黑", Font.BOLD, 16); // 设定字体、类型、字号
		chart2.getLegend().setItemFont(font3);
		chart2.getLegend().setPosition(RectangleEdge.TOP); // 图例在柱状图的上方

		// 设置柱状图的渲染对象
		BarRenderer3D renderer = (BarRenderer3D) cp.getRenderer();
		renderer.setItemMargin(0.1); // 组内柱之间间隔
		renderer.setSeriesPaint(0, Color.red); // 设置柱的颜色
		renderer.setSeriesPaint(1, Color.green);
		// 生成面板，并添加到选项卡中
		ChartPanel panel2 = new ChartPanel(chart2);
		jTabbedPane.add("Bar Chart", panel2);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jTabbedPane); // 添加选项卡到窗口中
		// 设置显示窗口居于屏幕中央位置
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((sd.width - 600) / 2, (sd.height - 400) / 2);
		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
}
