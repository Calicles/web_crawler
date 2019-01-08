package services;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLListHandler extends DefaultHandler {
	
	private List<String> list;
	private StringBuffer line;
	private boolean datePassed;
	
	public XMLListHandler(List<String> list) {
		super();
		this.list= list;
		line= new StringBuffer();
		datePassed= false;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attr) {
		for(int i=0; i< attr.getLength(); i++) {
			if(attr.getLocalName(i).equals("date")) {
				String month= attr.getValue(i).split("-")[1];
				int monthNum= Integer.parseInt(month);
				if(monthNum > 7) datePassed= true;
			}
		}
	}
	
	@Override
	public void characters(char[] characters, int start, int length) {
		if(datePassed) {
			line.append(characters, start, length);
			
			if(length >5)
			{
			list.add(line.toString());
			}
			line.delete(0, line.length());
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) {
		
	}

}
