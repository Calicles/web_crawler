package crawler;

import org.jsoup.nodes.Document;

import com.antoine.contracts.Crawler;
import com.antoine.contracts.Entity;
import com.antoine.entity.Hippodrome;

public class HippodromeCrawler extends AbstractCrawler implements Crawler {
	
	private String nameSelector, townSelector, track_typeSelector, countrySelector, lengthSelector;

	public void setNameSelector(String nameSelector) {
		this.nameSelector = nameSelector;
	}

	public void setTownSelector(String townSelector) {
		this.townSelector = townSelector;
	}

	public void setCountrySelector(String countrySelector) {
		this.countrySelector = countrySelector;
	}
	
	public void setTrack_typeSelector(String track_typeSelector) {
		this.track_typeSelector= track_typeSelector;
	}

	public void setLengthSelector(String lengthSelector) {
		this.lengthSelector = lengthSelector;
	}

	@Override
	public Entity crawl(Document doc) {
		String name, town, country;
		int length;
		Hippodrome hippodrome= new Hippodrome();
		
		name= doc.getElementById(nameSelector).text();
		hippodrome.setName(name);
		
		if(!this.townSelector.isEmpty()) {
			town= doc.getElementById(townSelector).text();
			hippodrome.setTown(town);
		}
		if(!this.countrySelector.isEmpty()) {
			country= doc.getElementById(countrySelector).text();
			hippodrome.setCountry(country);
		}
		if(!this.lengthSelector.isEmpty()) {
			length= Integer.parseInt(doc.getElementById(lengthSelector).text());
			hippodrome.setLength(length);
		}
		return hippodrome;
	}

}
