package services;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLListHandler extends DefaultHandler {
	
	List<String> list;
	StringBuffer line;
	
	public XMLListHandler(List<String> list) {
		super();
		this.list= list;
		line= new StringBuffer();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attr) {
		
	}
	
	@Override
	public void characters(char[] characters, int start, int length) {
		line.append(characters, start, length);
		
		if(length >5)
		{
		list.add(line.toString());
		}
		line.delete(0, line.length());
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) {
		
	}

}
