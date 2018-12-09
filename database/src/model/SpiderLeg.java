package model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.antoine.contracts.Crawler;
import com.antoine.contracts.Entity;

public class SpiderLeg {
	
	private static final String USER_AGENT= "Mozilla/5.0 (Windows T 6.1; WOW64)"
			+ " AppleWebKit/535.1 (KHTM, like Gecko) Chrome/13.0.782.112 Safari/535.1";

	private Document htmlDocument;
	

	public Document getDocument() {
		return htmlDocument;
	}

	public List<String> bringUrlList(String selector) {
		
		List<String> links= new LinkedList<>();
		
		Elements linksOnPage= htmlDocument.select("a[href]");
		
		for(Element link:linksOnPage) {
		
			if(link.hasAttr("title")) {
				if(link.attr("title").contains(selector))	
					links.add(link.absUrl("href"));
			}
		}
		if(links.size() == 0) throw new RuntimeException("Liste Url vide");
		
		return links;
	}
	
	public void crawl(String url) throws IOException {
		
		Connection connection= Jsoup.connect(url).userAgent(USER_AGENT);
		htmlDocument= connection.get();
		
		if(connection.response().statusCode() == 200) {
			System.out.println("Visiting**Received web page at "+url);
		}
		
		if(!connection.response().contentType().contains("text/html")) {
			throw new RuntimeException("**Failure**Retrieved something other than HTML");
		}
		
	}
	
	public Entity bringEntity(Crawler crawler) {
		
		Entity entity= crawler.crawl(htmlDocument);
		
		return entity;
	}


}
