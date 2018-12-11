package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.antoine.contracts.Crawler;
import com.antoine.contracts.Entity;
import com.antoine.entity.Horse;

public class HorseCrawler extends AbstractCrawler {
	
	private String nameSelector, age_sexSelector, fatherSelector, motherSelector, robeSelector, ownerSelector, coachSelector,
					price_moneySelector, perfSelector;

	public void setNameSelector(String nameSelector) {
		this.nameSelector = nameSelector;
	}
	
	public void setAge_sexSelector(String age_sexSelector) {this.age_sexSelector= age_sexSelector;}

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
		String name, age_sex, robe, owner, coach, perf, fatherName, motherName;
		double price_money;
		Horse horse= new Horse();
		
		name= doc.getElementsContainingOwnText(nameSelector).text().split("-")[0]; 
		horse.setName(name.substring(0, name.length()-1));
		
		age_sex= findElement(doc, age_sexSelector).text();
		horse.setAge_sex(age_sex);												
		
		fatherName= findElement(doc,fatherSelector).text();
		if(fatherName.isEmpty()) horse.setFather(null);
		else { horse.setFather(new Horse(fatherName));}
		
		motherName= findElement(doc, motherSelector).text();
		if(motherName.isEmpty()) horse.setMother(null);
		else { horse.setMother(new Horse(motherName));}
		
		robe= findElement(doc, robeSelector).text();
		if(robe.length() < 20)
			horse.setRobe(robe);
		
		owner= findElement(doc, ownerSelector).text();
		horse.setOwner(owner);
		
		coach= findElement(doc, coachSelector).text();
		horse.setCoach(coach);
		
		price_money= Double.parseDouble(findElement(doc, price_moneySelector).text());
		horse.setMoney(price_money);
		
		perf= findElement(doc, perfSelector).text();
		horse.setPerf(perf);			
		
		return horse;
	}
	
	public Element findElement(Document doc, String selector) {
		Elements elem= doc.getElementsContainingOwnText(selector);
		Elements elements= elem.parents(); 
		
		return elements.get(0).child(1);
		
	}

}
