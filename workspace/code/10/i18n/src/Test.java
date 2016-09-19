import java.util.*;

public class Test {
	public static void main(String[] args) throws Exception {
		/*
		 * 资源文件的内容封装在一个ResourceBundle对象中，可以通过
		 * ResourceBundle来加载资源文件，如果不指定Locale，那么使用 平台默认的区域和语言。加载资源文件时需要指定基本名称，
		 * 在下面的例子代码中，基本资源名称为a，对应的资源文件为
		 * a.properties，或者a_zh_CN.properties，或者a_ja_JP.properties。
		 */
		// ResourceBundle rb = ResourceBundle.getBundle("a",
		// Locale.getDefault());
		ResourceBundle rb = ResourceBundle.getBundle("a",
				new Locale("ja", "JP"));
		System.out.println(rb.getString("greeting"));
	}
}
