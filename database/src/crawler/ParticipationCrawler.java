package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
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
		
		Elements table= doc.getElementsByAttributeValue("class", id_horseSelector);
		Elements tr= table.select("tr");
		
		participations= new Participation[tr.size()];
		
		for(int i= 0; i < tr.size() -1; i++) {
			Element row= tr.get(i); 
			participations[i]= new Participation();
			for(int j= 0; j< row.childNodeSize(); j++) {
				if(j == 0) {
					String buffer= row.child(j).ownText(); 
					int index;
					if(i == 0)
						index= buffer.indexOf("e");
					else 
						index = buffer.indexOf("è");
					participations[i].setPositioning(Integer.parseInt(buffer.substring(0, index)));
				}else if(j == 1) {
					participations[i].setHorse_number(Integer.parseInt(row.child(j).ownText()));
				
				}else if(j == 2) {
					participations[i].setId_horse(row.child(j).text());
				}else if(j == 4) {
					participations[i].setRating(Double.parseDouble(row.child(j).ownText()));
				}else if(j == 5) {
					participations[i].setRedKm(row.child(j).ownText());
				}
				
			}
			
		}
		
		
		return participations;
	}
	

}
