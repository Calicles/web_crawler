package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
		
		Elements elems= doc.getElementsByAttributeValue("class", nameSelector);
		
		name= elems.get(0).ownText().split(" - ")[2];
		hippodrome.setName(name);
		
		hippodrome.setTown(name);
		
		if(!this.countrySelector.isEmpty()) {
			country= doc.getElementById(countrySelector).text();
			hippodrome.setCountry(country);
		}
		String buffer= elems.select("small").text().split(" - ")[1].trim();
		length= Integer.parseInt(buffer.substring(0, buffer.length() -1));
		hippodrome.setLength(length);
		
		return hippodrome;
	}

}
