package services;

import java.util.List;

import org.xml.sax.helpers.DefaultHandler;

public class XMLListHandler extends DefaultHandler {
	
	List<String> list;
	
	public XMLListHandler(List<String> list) {
		super();
		this.list= list;
	}
	
	@Override
	public void characters(char[] characters, int start, int length) {
		String line= new String(characters);
		list.add(line);
	}

}
