package crawler;

import java.util.Arrays;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.antoine.contracts.Entity;
import com.antoine.entity.Championship;

public class ChampionshipCrawler extends AbstractCrawler {
	
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
		String name, challenge_type= "";
		double price_money;
		Championship championship= new Championship();
		
		String[] elems= doc.getElementsByAttributeValue("class", nameSelector).text().split(" - ");
	
		name= elems[0].split("[1-9]")[0].trim();
		championship.setName(name);
		
		
		String[] buffer= elems[2].split(" ");
		for(int i=1; i<buffer.length; i++) {
			challenge_type += buffer[i]+" ";
		}
		challenge_type= challenge_type.substring(0, challenge_type.length() -1);
		championship.setChallenge_type(challenge_type);
		
		price_money= Double.parseDouble(elems[3].substring(0, elems[3].length() - 1));
		championship.setPrice_money(price_money);

		System.out.println(name+" "+challenge_type+" "+price_money);
		return championship;
	}

}
