package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.antoine.entity.Championship;
import com.antoine.entity.Driver;
import com.antoine.entity.Hippodrome;
import com.antoine.entity.Horse;
import com.antoine.entity.Participation;
import com.antoine.entity.Race;

public class Test {
	
	public static String userName="antoine", passwd= "123456";
	public static String con= "jdbc:mysql://localhost:3306/hippo";

	public static void main(String[] args) throws SQLException {
		
		Participation p= new Participation();
		p.setDriver_lastName("Jones");
		p.setDriver_firstName("Indiana");
		p.setHorse_number(10);
		p.setId_horse("JolyJumper");
		p.setId_race(1);
		p.setRating(10);
		p.setPositioning(1);
		p.setRedKm("111.2");
		ParticipationDAO.insertParticipation(p);

	}

}
