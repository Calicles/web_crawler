package crawler;

import org.jsoup.nodes.Document;

import com.antoine.contracts.Crawler;
import com.antoine.contracts.Entity;
import com.antoine.entity.Championship;

public class ChampionshipCrawler extends AbstractCrawler implements Crawler {
	
	private String nameSelector, challenge_typeSelector, price_moneySelector;
	

	public void setNameSelector(String nameSelector) {
		this.nameSelector = nameSelector;
	}

	public void setChallenge_typeSelector(String challenge_typeSelector) {
		this.challenge_typeSelector = challenge_typeSelector;
	}

	public void setPrice_moneySelector(String price_moneySelector) {
		this.price_moneySelector = price_moneySelector;
	}

	@Override
	public Entity crawl(Document doc) {
		String name, challenge_type;
		int price_money;
		Championship championship= new Championship();
		
		name= doc.getElementById(nameSelector).text();
		championship.setName(name);
		
		if(!this.challenge_typeSelector.isEmpty()) {
			challenge_type= doc.getElementById(challenge_typeSelector).text();
			championship.setChallenge_type(challenge_type);
		}
		if(!this.price_moneySelector.isEmpty()) {
			price_money= Integer.parseInt(doc.getElementById(price_moneySelector).text());
			championship.setPrice_money(price_money);
		}
		
		return championship;
	}

}
