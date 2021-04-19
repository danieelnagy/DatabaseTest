package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static String URL = "jdbc:mysql://localhost:3306/that_db?serverTimezone=UTC";
	private static String USER = "root";
	private static String PASSWORD = "ASD";
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		}
		
		catch(SQLException e) {
			throw new RuntimeException("Error" + e);
		}
	}
	
	public static Connection getConnection(String url, String user, String password) {
		
		try {
			return DriverManager.getConnection(url, user, password);
		}
		
		catch(SQLException e) {
			throw new RuntimeException("Error" + e);
		}
		
	}
	
}
