package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class ConnectionFactory {
	
	public static String user, passwd, url;
	
	static {
		
	}
	
	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(url, user, passwd);
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
	}

}
