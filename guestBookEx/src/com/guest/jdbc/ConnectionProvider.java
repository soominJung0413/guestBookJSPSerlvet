package com.guest.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@Connection
public class ConnectionProvider {
	public static Connection getConnection () throws SQLException {
			return DriverManager.getConnection("jdbc:apache:commons:dbcp:guestbook");		
	}
}
