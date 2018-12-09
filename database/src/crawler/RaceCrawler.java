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
		Date date;
		Race race= new Race();
		
		reunion= Integer.parseInt(doc.getElementById(reunionSelector).text());
		race.setReunion(reunion);
		
		num_race= Integer.parseInt(doc.getElementById(num_raceSelector).text());
		race.setNum_race(num_race);
		SimpleDateFormat format= new SimpleDateFormat();
		try {
			date= new Date(format.parse(doc.getElementById(dateSelector).text()).getTime());
			race.setDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.toString());
		}
		
		return race;
	}

}
