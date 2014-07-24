package transfer.object2xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Object2wsXMLMarshaller {

	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		// TODO Auto-generated method stub
		
		String filePath = "output/o2ws.xml";
		Customer customer = new Customer();
		customer.setId(100);
		customer.setName("mkyong");
		customer.setAge(29);
		
		List<HashMap<String, String>> ls = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("first", "1");
		map.put("second", "2");
		ls.add(map);
		customer.setHw(ls);
		customer.setHashMap(map);
		
		File file = new File(filePath);
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
		jaxbMarshaller.marshal(customer, file);
		jaxbMarshaller.marshal(customer, System.out);
		
		
		unMarshalingExample(filePath);
/*		JAXBContext jc = JAXBContext.newInstance(Customer.class);
		Unmarshaller u = jc.createUnmarshaller();
		File file = new File( "foo.xml" );
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Object element = u.unmarshal(file);
		Marshaller m = jc.createMarshaller();
		
		OutputStream os = new FileOutputStream( "nosferatu.xml" );
		m.marshal( element, os );*/
	}

	@SuppressWarnings("unused")
	private static void unMarshalingExample(String path) throws JAXBException {
	    JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    Customer customer = (Customer) jaxbUnmarshaller.unmarshal( new File(path) );
	    System.out.println(customer.toString());
	}
}
