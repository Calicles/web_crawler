package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.antoine.contracts.Entity;
import com.antoine.entity.Participation;

public class ParticipationCrawler extends AbstractCrawler {
	
	private String id_horseSelector, redKmSelector, horse_numberSelector, positioningSelector, ratingSelector;
	
	public void setId_horseSelector(String id_horseSelector) {
		this.id_horseSelector = id_horseSelector;
	}
	public void setRedKmSelector(String redKmSelector) {
		this.redKmSelector = redKmSelector;
	}
	public void setHorse_numberSelector(String horse_numberSelector) {this.horse_numberSelector= horse_numberSelector;}
	public void setPositioningSelector(String positioningSelector) {
		this.positioningSelector = positioningSelector;
	}
	public void setRatingSelector(String ratingSelector) {
		this.ratingSelector = ratingSelector;
	}
	
	@Override
	public Entity crawl(Document doc) {
		Participation participation= new Participation();
		int horse_number, positioning= 0;
		double rating= 0;
		String id_horse= null, redKm= null;
		
		id_horse= doc.getElementById(id_horseSelector).text();
		horse_number= Integer.parseInt(doc.getElementById(horse_numberSelector).text());
		positioning= Integer.parseInt(doc.getElementById(positioningSelector).text());
		rating= Double.parseDouble(doc.getElementById(ratingSelector).text());
		redKm= doc.getElementById(redKmSelector).text();
		
		participation.setId_horse(id_horse);
		participation.setHorse_number(horse_number);
		participation.setPositioning(positioning);
		participation.setRating(rating);
		participation.setRedKm(redKm);
		
		return null;
	}
	
	@Override
	public Participation[] crawlAll(Document doc) {
		Participation[] participations;
		Elements id_horseElem= doc.getElementsByAttribute(id_horseSelector);
		Elements horse_numberElem= doc.getElementsByAttribute(horse_numberSelector);
		Elements positionElem= doc.getElementsByAttribute(positioningSelector);
		Elements ratingElem= doc.getElementsByAttribute(ratingSelector);
		Elements redKmElem= doc.getElementsByAttribute(redKmSelector);
		
		participations= new Participation[id_horseElem.size()];
		
		for(int i=0; i< id_horseElem.size(); i++) {
			participations[i]= new Participation();
			participations[i].setId_horse(id_horseElem.get(i).text());
			participations[i].setPositioning(Integer.parseInt(positionElem.text()));
			participations[i].setRating(Double.parseDouble(positionElem.text()));
			participations[i].setRedKm(redKmElem.text());
		}
		
		
		return participations;
	}
	

}
