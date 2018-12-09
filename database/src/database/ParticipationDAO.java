package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.antoine.entity.Participation;

public class ParticipationDAO {
	
	public static Participation getPalmares(String id_horse, String driver_lastName, String driver_firstName, int id_race) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			Statement stm= conn.createStatement();
			ResultSet rs= stm.executeQuery("SELECT * FROM participation WHERE id_horse = "+id_horse+
					" AND driver_lastName= "+driver_lastName+
					" AND driver_firstName= "+driver_firstName+" AND id_race= "+id_race+";");
			
			if(rs.next()) {
				return extractParticipationFromResultSet(rs);
			}
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return null;
	}

	private static Participation extractParticipationFromResultSet(ResultSet rs) throws SQLException {
		Participation participation= new Participation();
		
		participation.setId_horse(rs.getString("id_horse"));
		participation.setDriver_lastName(rs.getString("driver_lastName"));
		participation.setDriver_firstName(rs.getString("driver_firstName"));
		participation.setId_race(rs.getInt("id_race"));
		
		return participation;
	}
	
	public Set<Participation> getAllRacess() {

	    Connection connection = ConnectionFactory.getConnection();

	    try {

	        Statement stmt = connection.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM rparticipation");

	        Set<Participation> participations = new HashSet<>();

	        while(rs.next())

	        {

	            Participation participation = extractParticipationFromResultSet(rs);

	            participations.add(participation);

	        }

	        return participations;

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return null;

	}
	
	public static boolean insertParticipation(Participation participation) {
		Connection connection= ConnectionFactory.getConnection();
		
		try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO participation VALUES (id_horse= ?, "
	        		+ "driver_lastName= ?, driver_firstName= ?, id_race= ?);");
	        
	        ps.setString(1, participation.getId_horse());
	        ps.setString(2, participation.getDriver_lastName());
	        ps.setString(3, participation.getDriver_firstName());
	        ps.setInt(4, participation.getId_race());
	        
	        if(ps.executeUpdate() == 1)
	        	return true;
	        
		}catch(SQLException sqle) {throw new RuntimeException();}
		
		return false;
	}
	
	public boolean updateParticipation(Participation participation) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("UPDATE participation SET id_horse= ?, "
					+ "driver_lastName= ?, driver_firstName= ?, id_race= ? WHERE id_horse= ?, " + 
					"driver_lastName= ?, driver_firstName= ?, id_race= ?;");
			
			ps.setString(1, participation.getId_horse());
			ps.setString(2, participation.getDriver_lastName());
			ps.setString(3, participation.getDriver_firstName());
			ps.setInt(4, participation.getId_race());
			
			ps.setString(5, participation.getId_horse());
			ps.setString(6, participation.getDriver_lastName());
			ps.setString(7, participation.getDriver_firstName());
			ps.setInt(8, participation.getId_race());
			
	        
	        if(ps.executeUpdate() == 1)
	        	return true;
	        
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}
	
	public static boolean deleteParticipation(String id_horse, String driver_lastName, String driver_firstName, int id_race) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM race WHERE WHERE id_horse = "+id_horse+
					" AND driver_lastName= "+driver_lastName + 
					"AND driver_firstName= "+driver_firstName+" AND id_race= "+id_race+";");
			
			if(ps.executeUpdate() == 1)
				return true;
			
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}


}
