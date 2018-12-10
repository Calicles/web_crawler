package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.antoine.entity.Hippodrome;

public class HippodromeDAO {
	
	public static Hippodrome getHippodrome(int id) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			Statement stm= conn.createStatement();
			ResultSet rs= stm.executeQuery("SELECT * FROM hippodrome WHERE id = "+id+";");
			
			if(rs.next()) {
				return extractHippodromeFromResultSet(rs);
			}
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return null;
	}

	private static Hippodrome extractHippodromeFromResultSet(ResultSet rs) throws SQLException {
		Hippodrome hippodrome= new Hippodrome();
		
		hippodrome.setName(rs.getString("name"));
		
		hippodrome.setTown(rs.getString("town"));
		
		hippodrome.setCountry(rs.getString("country"));
		
		hippodrome.setTrack_type(rs.getString("track_type"));
		
		hippodrome.setLength(rs.getInt("length"));
		
		return hippodrome;
	}
	
	public Set<Hippodrome> getAllHippodrome() {

	    Connection connection = ConnectionFactory.getConnection();

	    try {

	        Statement stmt = connection.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM hippodrome");

	        Set<Hippodrome> hippodromes = new HashSet<>();

	        while(rs.next())

	        {

	            Hippodrome hippodrome = extractHippodromeFromResultSet(rs);

	            hippodromes.add(hippodrome);

	        }

	        return hippodromes;

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return null;

	}
	
	public static boolean insertHippodrome(Hippodrome hippodrome) {
		Connection connection= ConnectionFactory.getConnection();
		
		try {
	        PreparedStatement ps= connection.prepareStatement("INSERT INTO hippodrome (idhippodrome,name, town, country, track_type, length) VALUES (?,?,?,?,?,?);");
	        
	        ps.setInt(1, hippodrome.getId());
	        
	        ps.setString(2, hippodrome.getName());
	        
	        ps.setString(3, hippodrome.getTown());
	        
	        ps.setString(4, hippodrome.getCountry());
	        
	        ps.setString(5, hippodrome.getTrack_type());
	        
	       ps.setDouble(6, hippodrome.getLength());
	        
	        if(ps.executeUpdate() == 1) {
	        	System.out.println("hippodrome enregistré: "+hippodrome);
	        	System.out.println("*******************************************");
	        	return true;
	        }
		}catch(SQLException sqle) {throw new RuntimeException(sqle);}
		
		return false;
	}
	
	public boolean updateHippodrome(Hippodrome hippodrome) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("UPDATE hippodrome SET name= ?, town= ?, "
					+ "country= ?, track_type= ?, length= ? WHERE id= ?;");
			
			ps.setString(1, hippodrome.getName());

	        ps.setString(2, hippodrome.getTown());
	        
	        ps.setString(3, hippodrome.getCountry());
	        
	        ps.setString(4, hippodrome.getTrack_type());
	        
	        ps.setDouble(5, hippodrome.getLength());
	        
	        ps.setInt(6, hippodrome.getId());
	        
	        if(ps.executeUpdate() == 1)
	        	return true;
	        
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}
	
	public static boolean deleteHippodrome(int id) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM hippodrome WHERE idhippodrome= "+id+";");
			
			if(ps.executeUpdate() == 1)
				return true;
			
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}
	
	public static int getNextId() {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			Statement stm = conn.createStatement();
			ResultSet rs= stm.executeQuery("SELECT MAX(idhippodrome) FROM hippodrome;");
			rs.next();
			return rs.getInt("MAX(idhippodrome)") + 1;
		}catch(SQLException sqle) {throw new RuntimeException(sqle);}
		
	}

}
