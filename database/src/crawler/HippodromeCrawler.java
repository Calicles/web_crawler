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
		
		String[] elems= doc.getElementsByAttributeValue("class", nameSelector).text().split(" - ");
		
		name= elems[2].split(" ")[0];
		hippodrome.setName(name);
		
		hippodrome.setTown(name);
		
		if(!this.countrySelector.isEmpty()) {
			country= doc.getElementById(countrySelector).text();
			hippodrome.setCountry(country);
		}
		length= Integer.parseInt(elems[3].substring(0, elems[3].length() - 1));
		hippodrome.setLength(length);
		
		return hippodrome;
	}

}
