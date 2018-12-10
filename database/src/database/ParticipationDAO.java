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
		participation.setPositioning(rs.getInt("positioning"));
		participation.setRating(rs.getDouble("rating"));
		participation.setRedKm(rs.getString("redKm"));
		
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
	        		+ "driver_lastName= ?, driver_firstName= ?, id_race= ?, horse_number= ?, positioning= ?, rating= ?, redKm= ?);");
	        
	        ps.setString(1, participation.getId_horse());
	        ps.setString(2, participation.getDriver_lastName());
	        ps.setString(3, participation.getDriver_firstName());
	        ps.setInt(4, participation.getId_race());
	        ps.setInt(5, participation.getHorse_number());
	        ps.setInt(6, participation.getPositioning());
	        ps.setDouble(7, participation.getRating());
	        ps.setString(8, participation.getRedKm());
	        
	        if(ps.executeUpdate() == 1) {
	        	System.out.println("participation enregistré: "+participation);
	        	System.out.println("**************************************************");
	        	return true;
	        }
	        
		}catch(SQLException sqle) {throw new RuntimeException(sqle);}
		
		return false;
	}
	
	public boolean updateParticipation(Participation participation) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("UPDATE participation SET id_horse= ?, "
					+ "driver_lastName= ?, driver_firstName= ?, id_race= ?, horse_number= ? positioning= ?, rating= ?, redKm= ? "
					+ "WHERE id_horse= ?, " + 
					"driver_lastName= ?, driver_firstName= ?, id_race= ?;");
			
			ps.setString(1, participation.getId_horse());
			ps.setString(2, participation.getDriver_lastName());
			ps.setString(3, participation.getDriver_firstName());
			ps.setInt(4, participation.getId_race());
			ps.setInt(5, participation.getHorse_number());
			ps.setInt(6, participation.getPositioning());
			ps.setDouble(7, participation.getRating());
			ps.setString(8, participation.getRedKm());
			
			ps.setString(9, participation.getId_horse());
			ps.setString(10, participation.getDriver_lastName());
			ps.setString(11, participation.getDriver_firstName());
			ps.setInt(12, participation.getId_race());
			
	        
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
