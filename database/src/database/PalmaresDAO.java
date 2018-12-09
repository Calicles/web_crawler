package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.antoine.entity.Palmares;

public class PalmaresDAO {
	
	public static int idAuto= 0;

	public static Palmares getPalmares(int id) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			Statement stm= conn.createStatement();
			ResultSet rs= stm.executeQuery("SELECT * FROM palmares WHERE id = "+id+";");
			
			if(rs.next()) {
				return extractPalmaresFromResultSet(rs);
			}
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return null;
	}

	private static Palmares extractPalmaresFromResultSet(ResultSet rs) throws SQLException {
		Palmares palmares= new Palmares();
		
		List<String> names= new ArrayList<>();
		
		int i= 1;
		rs.beforeFirst();
		while(rs.next()) {
			if(i == 1)
				names.add(rs.getString((i++)+"er"));
			else
				names.add(rs.getString((i++)+"e"));
		}
		palmares.setHorses(names);
		
		return palmares;
	}
	
	public Set<Palmares> getAllPalmares() {

	    Connection connection = ConnectionFactory.getConnection();

	    try {

	        Statement stmt = connection.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM palmares");

	        Set<Palmares> palmaresSet = new HashSet<>();

	        while(rs.next())

	        {

	            Palmares palmares = extractPalmaresFromResultSet(rs);

	            palmaresSet.add(palmares);

	        }

	        return palmaresSet;

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return null;

	}
	
	public static boolean insertPalmares(Palmares palmares) {
		Connection connection= ConnectionFactory.getConnection();
		
		try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO championship VALUES (1er= ?, 2e= ?, "
	        		+ "3e= ?, 4e= ?, 5e= ?, 6e= ?, 7e= ?, 8e= ?, 9e= ?, 10e= ?);");

	        String[] names= palmares.getHorses();
	        
	        for(int i= 1; i<=names.length; i++) {
	        	ps.setString(i, names[i-1]);
	        }
	        
	        if(ps.executeUpdate() == 1) {
	        	idAuto++;
	        	return true;
	        }
	        
		}catch(SQLException sqle) {throw new RuntimeException();}
		
		return false;
	}
	
	public boolean updatePalmares(Palmares palmares) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("UPDATE palmares SET 1e= ?, 2e= ?, "
					+ "3e= ?, 4e= ?, 5e= ?, 6e= ?, 7e= ?, 8e= ?, 9e= ?, 10e= ? WHERE id= ?;");
			
			String[] names= palmares.getHorses();
			
			for(int i= 1; i<names.length; i++) {
				ps.setString(i, names[i-1]);
			}
			
	        ps.setInt(names.length, palmares.getId());
	        
	        if(ps.executeUpdate() == 1)
	        	return true;
	        
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}
	
	public static boolean deletePalmares(int id) {
		Connection conn= ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM palmares WHERE id= "+id+";");
			
			if(ps.executeUpdate() == 1)
				return true;
			
		}catch(SQLException sqle) {throw new RuntimeException(sqle.toString());}
		
		return false;
	}

}
