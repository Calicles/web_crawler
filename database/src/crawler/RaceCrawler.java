package crawler;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jsoup.nodes.Document;

import com.antoine.contracts.Crawler;
import com.antoine.contracts.Entity;
import com.antoine.entity.Race;

public class RaceCrawler extends AbstractCrawler implements Crawler {
	
	private String reunionSelector, num_raceSelector, dateSelector;


	public void setReunionSelector(String reunionSelector) {
		this.reunionSelector = reunionSelector;
	}

	public void setNum_raceSelector(String num_raceSelector) {
		this.num_raceSelector = num_raceSelector;
	}

	public void setDateSelector(String dateSelector) {
		this.dateSelector = dateSelector;
	}

	@Override
	public Entity crawl(Document doc) {
		int reunion, num_race;
		Race race= new Race();
		
		String[] elems= doc.getElementsByAttributeValue("class", reunionSelector).text().split(" - ");
		elems= elems[1].split(" ");
		
		reunion= Integer.parseInt(elems[1]);
		race.setReunion(reunion);
		
		num_race= Integer.parseInt(elems[3]);
		race.setNum_race(num_race);
		
		return race;
	}

}
