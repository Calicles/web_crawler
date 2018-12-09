package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Test {
	
	public static String userName="antoine", passwd= "123456";
	public static String con= "jdbc:mysql://localhost:3306/hippo";

	public static void main(String[] args) throws SQLException {
		Connection conn= null;
		Properties connectionProps= new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", passwd);
		
		conn= DriverManager.getConnection(con, connectionProps);
		Statement stm= conn.createStatement();

		stm.executeUpdate("INSERT INTO driver VALUES ('Potter', 'Harry');");
		

	}

}
