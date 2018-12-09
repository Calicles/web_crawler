package services;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XML_Parser {
	
	
	public static List<String> parse(String xmlFile) throws ParserConfigurationException, SAXException, IOException{
		List<String> listUrl= new LinkedList<>();
		SAXParserFactory factory= SAXParserFactory.newInstance();
		SAXParser parser= factory.newSAXParser();
		parser.parse(new File(xmlFile), new XMLListHandler(listUrl));
		
		return listUrl;
	}

}
