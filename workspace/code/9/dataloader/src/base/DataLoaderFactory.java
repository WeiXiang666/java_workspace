//DataLoader的工厂类，DataLoaderFactory.java文件
package base;

import java.beans.Beans;
import java.io.*;
import java.util.*;

public class DataLoaderFactory {
	// 读取配置文件内容，返回DataLoader对象
	public DataLoader getDataLoader() {
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("loader.properties"));
			String loaderClass = p.getProperty("loaderClass");
			// 通过java.beans.Beans类来加载某个类并实例化该类
			return (DataLoader) Beans.instantiate(getClass().getClassLoader(),loaderClass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 直接指定DataLoader接口的实现类的类名称
	public DataLoader getDataLoader(String loaderClass) {
		try {
			return (DataLoader) Beans.instantiate(getClass().getClassLoader(),
					loaderClass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
