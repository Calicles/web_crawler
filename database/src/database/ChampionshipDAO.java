package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.antoine.entity.Championship;

import database.ConnectionFactory;

public class ChampionshipDAO {
	
	public static Championship getChampionship(int id) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			Statement stm= conn.createStatement();
			ResultSet rs= stm.executeQuery("SELECT * FROM hippodrome WHERE idchampionship = "+id+";");
			
			if(rs.next()) {
				return extractChampionshipFromResultSet(rs);
			}
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return null;
	}
	
	public static Championship getChampionship(String name) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("SELECT * FROM hippodrome WHERE name = ?;");
			ps.setString(1, name);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				return extractChampionshipFromResultSet(rs);
			}
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return null;
	}

	public static int getNextId() {
		Connection conn= ConnectionFactory.getConnection();
		try {
			Statement stm= conn.createStatement();
			ResultSet rs= stm.executeQuery("SELECT MAX(idchampionship) FROM championship;");
			rs.next();
			return rs.getInt("MAX(idchampionship)") + 1;
		}catch(SQLException sqle) {throw new RuntimeException(sqle);}
	}

	private static Championship extractChampionshipFromResultSet(ResultSet rs) throws SQLException {
		Championship championship= new Championship();
		
		championship.setId(rs.getInt("idchampionship"));
		
		championship.setName(rs.getString("name"));
		
		championship.setChallenge_type(rs.getString("challenge_type"));
		
		championship.setPrice_money(rs.getInt("prive_money"));
		
		return championship;
	}
	
	public Set<Championship> getAllChampionships() {

	    Connection connection = ConnectionFactory.getConnection();

	    try {

	        Statement stmt = connection.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM hippodrome");

	        Set<Championship> championships = new HashSet<>();

	        while(rs.next())

	        {

	            Championship championship = extractChampionshipFromResultSet(rs);

	            championships.add(championship);

	        }

	        return championships;

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return null;

	}
	
	public static boolean insertChampionship(Championship championship) {
		Connection connection= ConnectionFactory.getConnection();
		
		try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO championship (idchampionship, name, challenge_type, "
	        		+ "price_money) VALUES (?,?,?,?);");
	        
	        Championship c;
	        if((c= getChampionship(championship.getName())) != null) {
	        	championship.setId(c.getId());
	        }

		        ps.setInt(1, championship.getId());
		        
		        ps.setString(2, championship.getName());
	
		        ps.setString(3, championship.getChallenge_type());
		        
		        ps.setDouble(4, championship.getPrice_money());
		        
		        if(ps.executeUpdate() == 1) {
		        	System.out.println("championship enregistré: "+championship);
		        	System.out.println("*****************************************");
		        	return true;
		        }
	  
		}catch(SQLException sqle) {
			if(sqle.toString().toLowerCase().contains("duplicate")) {
				System.out.println("championship déja enregistré");
			}else
				throw new RuntimeException(sqle);}
		
		return false;
	}
	
	public boolean updateChampionship(Championship championship) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("UPDATE championship SET name= ?, challenge_type= ?, "
					+ "price_money= ? WHERE id= ?;");
			
			ps.setString(1, championship.getName());

			ps.setString(2, championship.getChallenge_type());
	       
			ps.setDouble(3, championship.getPrice_money());
	        
	        ps.setInt(4, championship.getId());
	        
	        if(ps.executeUpdate() == 1)
	        	return true;
	        
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}
	
	public static boolean deleteChampionship(int id) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM championship WHERE id= "+id+";");
			
			if(ps.executeUpdate() == 1)
				return true;
			
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}

}
