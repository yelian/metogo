package web.crawler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HTMLParse {
	
	private static DocumentBuilder builder = null;
	static {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
	}
		
	private String urls = "";
	public void access(String urls) throws IOException, SAXException{
		URL url = new URL(urls);
		InputStream inputStream = url.openStream();
		Document doc = builder.parse(new InputSource(inputStream));
		doc.getChildNodes();
	}
	
	@Test
	public void html() {
		InputStream is = null;
		FileOutputStream os = null;
		try {
			URL url = new URL("http://www.ibm.com/developerworks/cn/data/library/techarticle/dm-1406datastage-new/index.html");
			is = url.openStream();
			os = new FileOutputStream("d:\\out.html");
			byte[] bytes = new byte[1024];
			int read = 0;
			while((read = is.read(bytes)) > 0){
				os.write(bytes, 0, read);
			}
		} catch(IOException e){
			
		} finally {
			try {
				if(is != null){
					is.close();
				}
				if(os != null){
					os.close();
				}
			} catch (Exception e){}
		}
	}
}
