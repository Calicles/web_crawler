package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.antoine.entity.Horse;

public class HorseDAO {
	
	public static Horse getHorse(String name) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			Statement stm= conn.createStatement();
			ResultSet rs= stm.executeQuery("SELECT * FROM horse WHERE name = "+name);
			
			if(rs.next()) {
				return extractHorseFromResultSet(rs);
			}
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return null;
	}

	private static Horse extractHorseFromResultSet(ResultSet rs) throws SQLException {
		Horse horse= new Horse();
		
		horse.setName(rs.getString("horse_name"));
		
		horse.setAge_sex(rs.getString("age_sex"));
		
		horse.setFather(getHorse(rs.getString("father")));
		
		horse.setMother(getHorse(rs.getString("mother")));
		
		horse.setOwner(rs.getString("owner"));
		
		horse.setCoach(rs.getString("coach"));
		
		horse.setRobe(rs.getString("robe"));
		
		horse.setMoney(rs.getInt("money_won"));
		
		horse.setPerf(rs.getString("perf"));
		
		return horse;
	}
	
	public Set<Horse> getAllHorses() {

	    Connection connection = ConnectionFactory.getConnection();

	    try {

	        Statement stmt = connection.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM horse");

	        Set<Horse> horses = new HashSet<>();

	        while(rs.next())

	        {

	            Horse horse = extractHorseFromResultSet(rs);

	            horses.add(horse);

	        }

	        return horses;

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return null;

	}
	
	public static boolean insertHorse(Horse horse) {
		Connection connection= ConnectionFactory.getConnection();
		
		try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO horse VALUES (horse_name= ?, age_sex= ?, father= ?, mother= ?,"
	        		+ " robe= ?, owner= ?, coach= ?, money_won= ?, perf= ?);");

	        ps.setString(1, horse.getName());
	        
	        ps.setString(2, horse.getAge_sex());

	        ps.setString(3, horse.getFather().getName());
	        
	        ps.setString(4, horse.getMother().getName());
	        
	        ps.setString(5, horse.getRobe());
	        
	        ps.setString(6, horse.getOwner());
	        
	        ps.setString(7, horse.getCoach());
	        
	        ps.setDouble(8, horse.getMoney_won());
	        
	        ps.setString(9, horse.getPerf());
	        
	        
	        if(ps.executeUpdate() == 1) {
	        	System.out.println("horse enregistré: "+horse);
	        	System.out.println("*********************************************");
	        	return true;
	        }
	        
		}catch(SQLException sqle) {throw new RuntimeException(sqle);}
		
		return false;
	}
	
	public boolean updateHorse(Horse horse) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("UPDATE horse SET horse_name= ?, age_sex= ?, father= ?, mother= ?, "+ 
				"robe= ?, owner= ?, coach= ?, money_won= ?, perf= ? WHERE name= ?;");
			
			ps.setString(1, horse.getName());
			
			ps.setString(2, horse.getAge_sex());

	        ps.setString(3, horse.getFather().getName());
	        
	        ps.setString(4, horse.getMother().getName());
	        
	        ps.setString(5, horse.getRobe());
	        
	        ps.setString(6, horse.getOwner());
	        
	        ps.setString(7, horse.getCoach());
	        
	        ps.setDouble(8, horse.getMoney_won());
	        
	        ps.setString(9, horse.getPerf());
	        
	        ps.setString(10, horse.getName());
	        
	        if(ps.executeUpdate() == 1)
	        	return true;
	        
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}
	
	public static boolean deleteHorse(String name) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM horse WHERE horse_name= ?;");
			ps.setString(1, name);
			
			if(ps.executeUpdate() == 1)
				return true;
			
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}

}
