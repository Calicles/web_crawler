package com.antoine.contracts;

import org.jsoup.nodes.Document;

public interface Crawler {
	
	Entity crawl(Document doc);

}
