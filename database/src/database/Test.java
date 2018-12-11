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
		
		Horse horse= new Horse();
		horse.setName("jolyjumper");
		horse.setRobe("BAI");
		horse.setAge_sex("H7");
		horse.setFather(new Horse("Henriette"));
		
		HorseDAO.insertHorse(horse);

	}

}
