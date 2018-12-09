package crawler;



import org.jsoup.nodes.Document;

import com.antoine.contracts.Crawler;
import com.antoine.contracts.Entity;


public abstract class AbstractCrawler implements Crawler{
	
	@Override
	public Entity[] crawlAll(Document doc) {return null;}

}
