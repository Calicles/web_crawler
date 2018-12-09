package model;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.antoine.contracts.Crawler;
import com.antoine.contracts.Entity;
import com.antoine.entity.Championship;
import com.antoine.entity.Driver;
import com.antoine.entity.Hippodrome;
import com.antoine.entity.Horse;
import com.antoine.entity.Participation;
import com.antoine.entity.Race;

public class Spider {
	
	private List<String> urlDriversList, urlHorsesList;
	private String listDriverUrlSelector, listHorseUrlSelector, horse_numberSelector;
	private Crawler horseCrawler, driverCrawler, raceCrawler, championshipCrawler, hippodromeCrawler;
	private SpiderLeg leg;

	public void setHorseCrawler(Crawler horseCrawler) {
		this.horseCrawler = horseCrawler;
	}

	public void setDriverCrawler(Crawler driverCrawler) {
		this.driverCrawler = driverCrawler;
	}

	public void setRaceCrawler(Crawler raceCrawler) {
		this.raceCrawler = raceCrawler;
	}

	public void setChampionshipCrawler(Crawler championshipCrawler) {
		this.championshipCrawler = championshipCrawler;
	}

	public void setHippodromeCrawler(Crawler hippodromeCrawler) {
		this.hippodromeCrawler = hippodromeCrawler;
	}

	public void setHorse_numberSelector(String horse_numberSelector) {
		this.horse_numberSelector= horse_numberSelector;
	}
	
	public void setListDriverUrlSelector(String driverUrlSelector) {
		this.listDriverUrlSelector = driverUrlSelector;
	}

	public void setListHorseUrlSelector(String horseUrlSelector) {
		this.listHorseUrlSelector = horseUrlSelector;
	}

	public void crawl(String url) throws IOException {
		leg= new SpiderLeg();
		leg.crawl(url);
		urlDriversList= leg.bringUrlList(listDriverUrlSelector);
		urlHorsesList= leg.bringUrlList(listHorseUrlSelector);
	}
	
	public Horse[] bringHorses() throws IOException {
		Horse[] horses= new Horse[urlHorsesList.size()];
		int i= 0;
		
		for(String s:urlHorsesList) {
			SpiderLeg leg= new SpiderLeg();
			leg.crawl(s);
			horses[i++]= (Horse) leg.bringEntity(horseCrawler);
		}
		
		return horses;
	}
	
	public Driver[] bringDrivers() throws IOException {
		Driver[] drivers= new Driver[urlDriversList.size()];
		int i=0;
		
		for(String s:urlHorsesList) {
			SpiderLeg leg= new SpiderLeg();
			leg.crawl(s);
			drivers[i++]= (Driver) leg.bringEntity(driverCrawler);
		}
		
		return drivers;
	}
	
	public Participation[] bringParticipations() {
		Participation[] participations= new Participation[urlDriversList.size()];
		Document doc= leg.getDocument();
		Elements elements= doc.getElementsByAttribute(horse_numberSelector);
		int i=0;
		
		for(Element e:elements) {
			participations[i]= new Participation();
			participations[i].setHorse_number(Integer.parseInt(e.text()));
			i++;
		}
		return participations;
	}
	
	public Championship bringShampionship() {
		return (Championship) leg.bringEntity(championshipCrawler);
	}
	
	public Race bringRace() {
		return (Race) leg.bringEntity(raceCrawler);
	}
	
	public Hippodrome bringHippodrome() {
		return (Hippodrome) leg.bringEntity(hippodromeCrawler);
	}

}
