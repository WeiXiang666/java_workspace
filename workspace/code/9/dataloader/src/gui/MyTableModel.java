//接下来是自定义的表格数据模型，实现了TableModel接口，MyTableModel.java
package gui;

import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.util.*;
import base.*;

public class MyTableModel implements TableModel {
	private ArrayList<Student> al; // 根据加载的数据集合生成一个表格模型
	private int rowCount = 0; // 表格数据行数

	public MyTableModel(ArrayList<Student> al) {
		this.al = al;
		rowCount = al.size();
	}

	public int getRowCount() { // 返回表格模型的记录行数
		return rowCount;
	}

	public int getColumnCount() { // 返回表格模型的列数
		return 6;
	}

	public String getColumnName(int columnIndex) { // 返回表格模型的列名
		switch (columnIndex) {
		case 0:
			return "学号";
		case 1:
			return "姓名";
		case 2:
			return "性别";
		case 3:
			return "邮件地址";
		case 4:
			return "年龄";
		case 5:
			return "Java";
		}
		return null;
	}

	public Class<?> getColumnClass(int columnIndex) { // 表格列数据的类型
		switch (columnIndex) {
		case 0: // 学号，姓名，性别，邮件，显示为字符串类型
		case 1:
		case 2:
		case 3:
			return String.class;
		case 4: // Java成绩和年龄显示为整型数据
		case 5:
			return Integer.class;
		}
		return null;
	}

	// 表格中的数据能否编辑，设置为false，不可编辑。
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) { // 返回表格中的数据
		Student s = al.get(rowIndex); // 得到某行数据
		switch (columnIndex) { // 接下来返回该行数据的某个列的值
		case 0:
			return s.getId();
		case 1:
			return s.getName();
		case 2:
			return s.getGender() ? "男" : "女";
		case 3:
			return s.getEmail();
		case 4:
			return new Integer(s.getAge());
		case 5:
			return new Integer(s.getJava());
		}
		return null;
	}

	// 可以修改表格模型中的数据，在例子中，仅仅是显示，所以没有实现。
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		return;
	}

	// 处理模型的事件听众的方法，这里也没有必要实现。
	public void addTableModelListener(TableModelListener l) {
		return;
	}

	public void removeTableModelListener(TableModelListener l) {
		return;
	}
}
