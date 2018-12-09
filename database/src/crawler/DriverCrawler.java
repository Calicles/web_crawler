package crawler;

import org.jsoup.nodes.Document;

import com.antoine.contracts.Crawler;
import com.antoine.contracts.Entity;
import com.antoine.entity.Driver;

public class DriverCrawler extends AbstractCrawler {
	
	private String lastNameSelector;
	private String firstNameSelector;
	
	public void setLastNameSelector(String selector) {
		this.lastNameSelector= selector;
	}
	
	public void setFirstNameSelector(String selector) {
		this.firstNameSelector= selector;
	}
	
	@Override
	public Entity crawl(Document doc) {
		String lastName, firstName;
		Driver driver= new Driver();
		
		lastName= doc.getElementById(lastNameSelector).text();
		firstName= doc.getElementById(firstNameSelector).text();
		
		driver.setLastName(lastName);
		driver.setFirstName(firstName);
		
		return driver;
	}

}
