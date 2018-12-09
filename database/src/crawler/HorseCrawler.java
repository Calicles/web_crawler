package crawler;

import org.jsoup.nodes.Document;

import com.antoine.contracts.Crawler;
import com.antoine.contracts.Entity;
import com.antoine.entity.Horse;

public class HorseCrawler extends AbstractCrawler {
	
	private String nameSelector, fatherSelector, motherSelector, robeSelector, ownerSelector, coachSelector,
					price_moneySelector, perfSelector;

	public void setNameSelector(String nameSelector) {
		this.nameSelector = nameSelector;
	}

	public void setFatherSelector(String fatherSelector) {
		this.fatherSelector = fatherSelector;
	}

	public void setMotherSelector(String motherSelector) {
		this.motherSelector = motherSelector;
	}

	public void setRobeSelector(String robeSelector) {
		this.robeSelector = robeSelector;
	}

	public void setOwnerSelector(String ownerSelector) {
		this.ownerSelector = ownerSelector;
	}

	public void setCoachSelector(String coachSelector) {
		this.coachSelector = coachSelector;
	}

	public void setMoney_wonSelector(String price_moneySelector) {
		this.price_moneySelector = price_moneySelector;
	}

	public void setPerfSelector(String perfSelector) {
		this.perfSelector = perfSelector;
	}

	@Override
	public Entity crawl(Document doc) {
		String name, robe, owner, coach, perf, fatherName, motherName;
		Horse father, mother;
		int price_money;
		Horse horse= new Horse();
		
		name= doc.getElementById(nameSelector).text();
		horse.setName(name);
		
		fatherName= doc.getElementById(fatherSelector).text();
		if(fatherName.isEmpty()) horse.setFather(null);
		else horse.setFather(new Horse(fatherName));
		
		motherName= doc.getElementById(motherSelector).text();
		if(motherName.isEmpty()) horse.setMother(null);
		else horse.setMother(new Horse(motherName));
		
		price_money= Integer.parseInt(doc.getElementById(price_moneySelector).text());
		horse.setMoney(price_money);
		
		perf= doc.getElementById(perfSelector).text();
		horse.setPerf(perf);
		
		return horse;
	}

}
