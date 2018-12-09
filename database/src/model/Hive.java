package model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.antoine.entity.Championship;
import com.antoine.entity.Driver;
import com.antoine.entity.Hippodrome;
import com.antoine.entity.Horse;
import com.antoine.entity.Participation;
import com.antoine.entity.Race;

import database.DriverDAO;
import database.HippodromeDAO;
import database.HorseDAO;
import database.ParticipationDAO;
import database.RaceDAO;
import services.XML_Parser;

public class Hive {
	
	private List<String> listUrl, urlVisited;
	private Thread thread;
	private Spider spider;
	private Horse horses[];
	private Driver drivers[];
	private Participation participations[];
	private Race race;
	private Championship championship;
	private Hippodrome hippodrome;
	
	public Hive(String xmlFile) throws ParserConfigurationException, SAXException, IOException {
		listUrl= XML_Parser.parse(xmlFile);
		urlVisited= new LinkedList<>();
		buildThreadPattern();
	}
	
	public void setSpider(Spider spider) {
		this.spider= spider;
	}

	public void start() {thread.start();}

	private void buildThreadPattern() {
		thread= new Thread(()-> {
			while(listUrl.size() > 0) {
				try {
					spider.crawl(listUrl.get(0));
					
					horses= spider.bringHorses();
					drivers= spider.bringDrivers();
					participations= spider.bringParticipations();
					race= spider.bringRace();
					championship= spider.bringShampionship();
					hippodrome= spider.bringHippodrome();
					
					race.setChampionship(championship);
					race.setHippodrome(hippodrome);
					
					persistEntity();

					urlVisited.add(listUrl.remove(0));
				}catch (IOException e) {
					throw new RuntimeException(e.toString());
					
				}
			}
			
		});
		
	}

	private void persistEntity() {
		for(int i=0; i<horses.length; i++) {
			
			HorseDAO.insertHorse(horses[i]);
			DriverDAO.insertDriver(drivers[i]);
			participations[i].setDriver_lastName(drivers[i].getLastName());
			participations[i].setDriver_firstName(drivers[i].getFirstName());
		}
		
		HippodromeDAO.insertHippodrome(hippodrome);
		RaceDAO.insertRace(race);
		
		for(int i=0; i<horses.length; i++) {
			
			participations[i].setId_race(race.getId());
			ParticipationDAO.insertParticipation(participations[i]);
		}
	}
	

}
