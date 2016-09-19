package com.abc.or;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	/*
	 * ThreadLocal为解决多线程程序的并发问题提供了一种新的思路，使用这个工具类可以很简洁地编写出优美的多线程程序，
	 * ThreadLocal并不是一个Thread，而是Thread的局部变量。它提供当前线程中局部数据对象的存取操作。
	 */
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static Configuration configuration = new Configuration();
	private static org.hibernate.SessionFactory sessionFactory;
	private static String configFile = CONFIG_FILE_LOCATION;

	/*
	 * 静态初始化代码，加载类后，执行类的静态程序之前，或者创建该类对象等操作 都会引起静态初始化代码的执行。
	 */
	static {
		try {
			configuration.configure(configFile); // 读配置文件，生成SessionFactory对象
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			/* 输出日志信息 */
			Test.log.error("Error Creating SessionFactory.");
			Test.log.error(e.getMessage());
		}
	}

	private HibernateSessionFactory() { // 抽象类禁止实例化，也可以通过将
	} // 构造方法定义为私有的来禁止实例化，
		// 除了类内部可以实例化，类外不可以。

	public static Session getSession() throws HibernateException {
		/*
		 * 先从当前线程中取出，如果没有则新建一个工厂对象，进而创建Session
		 * 对象，再放到ThreadLocal对象中，由于不同的线程代表不同的网络客户，
		 * 不同的客户应该访问不同的数据库会话，所以通过这个threadLocal对象可以
		 * 实现线程局部对象的管理，ThreadLocal类对线程局部数据的支持非常方便。
		 */
		Session session = (Session) threadLocal.get();

		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session); // 放入到ThreadLocal对象中后，以后这个线程
		} // 再取这个session对象就非常方便了。
			// 保证每个线程都独占的一个session。
		return session;
	}

	public static void rebuildSessionFactory() {
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null); // 释放掉session

		if (session != null) {
			session.close();
		}
	}

	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setConfigFile(String configFile) {
		HibernateSessionFactory.configFile = configFile;
		sessionFactory = null;
	}

	public static Configuration getConfiguration() {
		return configuration;
	}

}
