package user.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DatabaseConnection {
	static Logger logger = Logger.getLogger(DatabaseConnection.class);

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			throw new SQLException(ex.getMessage(), ex);
		} catch (Exception e) {
			logger.error("Error occured creating db connection: ", e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("Error occured closing db connection: ", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
