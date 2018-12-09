package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.antoine.entity.Driver;

public class DriverDAO {
	
	public static Driver getDriver(String lastName, String firstName) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			Statement stm= conn.createStatement();
			ResultSet rs= stm.executeQuery("SELECT * FROM driver WHERE lastName = "+lastName+" AND firstName= "+firstName);
			
			if(rs.next()) {
				return extractDriverFromResultSet(rs);
			}
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return null;
	}

	private static Driver extractDriverFromResultSet(ResultSet rs) throws SQLException {
		Driver driver= new Driver();
		
		driver.setLastName(rs.getString("lastName"));
		
		driver.setFirstName(rs.getString("firstName"));
		
		
		return driver;
	}
	
	public Set<Driver> getAllDriver() {

	    Connection connection = ConnectionFactory.getConnection();

	    try {

	        Statement stmt = connection.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM driver");

	        Set<Driver> drivers = new HashSet<>();

	        while(rs.next())

	        {

	            Driver driver = extractDriverFromResultSet(rs);

	            drivers.add(driver);

	        }

	        return drivers;

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return null;

	}
	
	public static boolean insertDriver(Driver driver) {
		Connection connection= ConnectionFactory.getConnection();
		
		try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO driver VALUES (lastName= ?, firstName= ?);");

	        ps.setString(1, driver.getLastName());

	        ps.setString(2, driver.getFirstName());
	        
	        if(ps.executeUpdate() == 1)
	        	return true;
	        
		}catch(SQLException sqle) {throw new RuntimeException();}
		
		return false;
	}
	
	public boolean updateDriver(Driver driver) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("UPDATE horse SET lastName= ?, firstName= ? "
					+ "WHERE lastName= ? AND firstName= ?;");
			
			ps.setString(1, driver.getLastName());

	        ps.setString(2, driver.getFirstName());
	        
	        if(ps.executeUpdate() == 1)
	        	return true;
	        
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}
	
	public static boolean deleteDriver(String lastName, String firstName) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM driver WHERE lastName= ? AND firstName= ?;");
			ps.setString(1, lastName);
			ps.setString(2, firstName);
			
			if(ps.executeUpdate() == 1)
				return true;
			
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}

}
