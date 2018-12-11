package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.antoine.entity.Race;

public class RaceDAO {
	
	public static Race getPalmares(int reunion, int num_race, Date date, String championship) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			Statement stm= conn.createStatement();
			ResultSet rs= stm.executeQuery("SELECT * FROM race WHERE reunion = "+reunion+" num_race= "+num_race+""
					+ " date= "+date+" championship = "+championship+";");
			
			if(rs.next()) {
				return extractRaceFromResultSet(rs);
			}
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return null;
	}

	public static int getNextId() {
		Connection conn= ConnectionFactory.getConnection();
		try {
			Statement stm= conn.createStatement();
			ResultSet rs= stm.executeQuery("SELECT MAX(idrace) FROM race;");
			rs.next();
			return rs.getInt("MAX(idrace)")+ 1;
		}catch(SQLException sqle) {throw new RuntimeException(sqle);}
	}

	private static Race extractRaceFromResultSet(ResultSet rs) throws SQLException {
		Race race= new Race();
		
		race.setId(rs.getInt("idrace"));
		race.setReunion(rs.getInt("reunion"));
		race.setNum_race(rs.getInt("num_race"));
		race.setChampionship(ChampionshipDAO.getChampionship(rs.getInt("championship")));
		race.setHippodrome(HippodromeDAO.getHippodrome(rs.getInt("hippodrome")));
		race.setDate(rs.getDate("date"));
		
		return race;
	}
	
	public Set<Race> getAllRacess() {

	    Connection connection = ConnectionFactory.getConnection();

	    try {

	        Statement stmt = connection.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM race");

	        Set<Race> races = new HashSet<>();

	        while(rs.next())

	        {

	            Race race = extractRaceFromResultSet(rs);

	            races.add(race);

	        }

	        return races;

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return null;

	}
	
	public static boolean insertRace(Race race) {
		Connection connection= ConnectionFactory.getConnection();
		
		try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO race (idrace, reunion, num_race, "
	        		+ "championship, hippodrome, date) VALUES (?,?,?,?,?,?);");
	        
	        ps.setInt(1, race.getId());
	        ps.setInt(2, race.getReunion());
	        ps.setInt(3, race.getNum_race());
	        ps.setInt(4, race.getChampionship().getId());
	        ps.setInt(5, race.getHippodrome().getId());
	        ps.setDate(6, race.getDate());
	        
	        if(ps.executeUpdate() == 1) {
	        	System.out.println("race enregistré: "+race);
	        	System.out.println("********************************************");
	        	return true;
	        }
	        
		}catch(SQLException sqle) {throw new RuntimeException(sqle);}
		
		return false;
	}
	
	public boolean updateRace(Race race) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("UPDATE race SET reunion= ?, num_race= ?, "
	        		+ "championship= ?, hippodrome= ?, date= ?, palmares= ? WHERE id= ?;");
			
			ps.setInt(1, race.getReunion());
	        ps.setInt(2, race.getNum_race());
	        ps.setInt(3, race.getChampionship().getId());
	        ps.setInt(4, race.getHippodrome().getId());
	        ps.setDate(5, race.getDate());
	        ps.setInt(7, race.getId());
			
	        
	        if(ps.executeUpdate() == 1)
	        	return true;
	        
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}
	
	public static boolean deletePalmares(int id) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM race WHERE id= "+id+";");
			
			if(ps.executeUpdate() == 1)
				return true;
			
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}

}
