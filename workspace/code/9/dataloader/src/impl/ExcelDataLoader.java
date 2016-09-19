package impl;

import java.util.*;
import java.io.*;
import base.*;

import org.apache.poi.hssf.usermodel.*;

public class ExcelDataLoader implements DataLoader {
	public ArrayList<Student> load() {
		ArrayList<Student> r = new ArrayList<Student>();
		try {
			// 打开student.xls文件，创建一个工作簿对象
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(
					"student.xls"));
			// 第一个工作表就是学生数据所在的工作表
			HSSFSheet sheet = wb.getSheetAt(0);
			// 得到数据记录的行数
			int rows = sheet.getPhysicalNumberOfRows();
			// 列头信息，学号，姓名等等
			HSSFRow row = sheet.getRow(0);
			int cols = row.getPhysicalNumberOfCells();
			// 从第1行开始读取数据，因为第0行是列名数据
			for (int i = 1; i < rows; i++) {
				row = sheet.getRow(i);
				HSSFCell cell = row.getCell(0);
				String id = cell.getStringCellValue();
				id = id.trim();
				cell = row.getCell(1);
				String name = cell.getStringCellValue();
				name = name.trim();
				cell = row.getCell(3);
				String email = cell.getStringCellValue();
				email = email.trim();
				cell = row.getCell(4);
				String gender = cell.getStringCellValue();
				gender = gender.trim();
				cell = row.getCell(5);
				// 读取数值数据
				double age = cell.getNumericCellValue();
				cell = row.getCell(6);
				double java = cell.getNumericCellValue();
				// 将Excel行数据转换为Java对象数据
				Student s = new Student(id, name, email, (int) java, (int) age,
						gender.equals("男"));
				r.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	public static void main(String[] args) {
		//测试程序
		DataLoader loader = new ExcelDataLoader();
		ArrayList<Student> st = loader.load();
		for(Iterator<Student> it = st.iterator(); it.hasNext(); ) {
			Student s = it.next();
			System.out.println(s);
		}
	}
	
}
