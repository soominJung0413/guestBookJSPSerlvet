package com.guest.listener;

import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

@WebListener
public class AppListener implements ServletContextListener {

    public AppListener() {
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
         //1. 컨텍스트 패스를 app 에 넣어 재사용하는 작업.
    	addContextPath(arg0);
    	
    	//2. 커넥션 풀을 만드는 작업
    	createConnectionPool(); 
    }

	private void createConnectionPool() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbcUrl = 
					"jdbc:mysql://localhost/guestbook" +
					"?serverTimezone=Asia/Seoul";
			String username = "root";
			String pw = "doli0413@";
			
			ConnectionFactory connFactory = 
				new DriverManagerConnectionFactory(jdbcUrl, username, pw);
			
			PoolableConnectionFactory pcf = 
				new PoolableConnectionFactory(connFactory, null);
			pcf.setValidationQuery("SELECT 1");
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000 * 60 * 5);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);
			
			GenericObjectPool<PoolableConnection> connectionPool = 
				new GenericObjectPool<>(pcf, poolConfig);
			pcf.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)
				DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("guestbook", connectionPool);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void addContextPath(ServletContextEvent arg0) {
		ServletContext app = arg0.getServletContext();
		String rootPath = app.getContextPath();
		app.setAttribute("rooPath", rootPath);
	}
	
}
