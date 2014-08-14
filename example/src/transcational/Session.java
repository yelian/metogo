package transcational;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Session {
	
	private static Connection conn = null;
	static{
		String CONFIG_FILE_PATH = "config/datasource.properties";
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(CONFIG_FILE_PATH));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = prop.getProperty("name");
		String password = prop.getProperty("password");
		String url = prop.getProperty("url");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return getConnection(true);
	}
	
	public static Connection getConnection(boolean autoCommit){
		try {
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
