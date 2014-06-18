package web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class HTMLParse {
	
	private static XMLReader reader = null;
	static {
		try {
			reader = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		} 
	}
	public static List<String> parse(String htmlName, String tagName){
		tagName = tagName.toLowerCase();
		List<String> list = new ArrayList<String>();
		try {
			reader.parse(new InputSource(new FileInputStream(htmlName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return list;
	}	
}
