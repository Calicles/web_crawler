package crawler;

import org.jsoup.nodes.Document;

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
		
		String[] buffer= doc.getElementsContainingOwnText(lastNameSelector).text().split(" - ");
		//buffer = getNames(buffer[0]);
		lastName= buffer[0].trim();
		firstName= "**";
		
		driver.setLastName(lastName);
		driver.setFirstName(firstName);
		
		return driver;
	}
	
	private String[] getNames(String txt) {
		String[] buffer= null;
		if(txt.contains(" ")) {
			
			buffer= txt.split(" ");
			if(buffer.length > 2) {
				for(int i=1; i<buffer.length - 1; i++) {
					buffer[0] += " "+buffer[i];
				}
				buffer[1]= buffer[buffer.length -1];
			}
			return buffer;
		}else {
			buffer= txt.split("\\.");
			if(buffer.length > 2) {
				for(int i=1; i<buffer.length - 1; i++) {
					buffer[0] += buffer[i] + ".";
				}
				buffer[1]= buffer[buffer.length - 1];
			}else
				buffer[0] += ".";
			return buffer;
		}
	}

}
