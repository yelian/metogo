package ivy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class IVYDownload {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	private static List<String> BASE_URL = new ArrayList<String>();
	private static List<Dependency> DEPENDENCY = new ArrayList<Dependency>();
	private static String DOWNLOADPATH = "D:/workspace/metogo/example/src/ivy/download/";
	
	static {
		BASE_URL.add("https://repository.apache.org/content/groups/public/[organisation]/[artifact]-[revision].[ext]");
		BASE_URL.add("https://repository.apache.org/content/groups/snapshots/[organisation]/[artifact]-[revision].[ext]");
		BASE_URL.add("https://repository.apache.org/content/groups/public/[organisation]/[artifact]/[revision]/[artifact]-[revision].[ext]");
		BASE_URL.add("https://repository.apache.org/content/groups/snapshots/[organisation]/[artifact]/[revision]/[artifact]-[revision].[ext]");
		BASE_URL.add("http://repo1.maven.org/maven2/[organisation]/[artifact]-[revision].[ext]");
		BASE_URL.add("https://oss.sonatype.org/content/repositories/releases/[organisation]/[artifact]/[revision]/[artifact]-[revision].[ext]");
		BASE_URL.add("http://maven.tmatesoft.com/content/repositories/releases/[organisation]/[artifact]/[revision]/[artifact]-[revision].[ext]");
		BASE_URL.add("https://openlaszlo-openmeetings-integration.googlecode.com/svn/repository/[artifact](-[revision]).[ext]");
		BASE_URL.add("https://openmeetings.googlecode.com/svn/repository/[artifact](-[revision]).[ext]");
		BASE_URL.add("https://openmeetings.googlecode.com/svn/repository/[organisation]/[artifact]/[revision]/[artifact](-[revision]).[ext]");
		BASE_URL.add("https://red5.googlecode.com/svn/repository/[organisation]/[artifact]/[revision]/[artifact](-[revision]).[ext]");
		BASE_URL.add("http://smslib.org/maven2/v3/[organisation]/[artifact]/[revision]/[artifact]-[revision].[ext]");
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		/*init();
		analysis();	*/
		download("https://repository.apache.org/content/groups/public/commons-io/commons-io/2.4/commons-io-2.4.jar");		
	}
	
	public static void analysis(){
		ExecutorService service = Executors.newFixedThreadPool(5);
		final CountDownLatch latch = new CountDownLatch(1);
		try {
			for(final Dependency dependency: DEPENDENCY){
				service.execute(new Runnable(){
					@Override
					public void run() {
						boolean successed = false;
						String downloadUrl = "";
						for(String url: BASE_URL){
							url = url.replace("[organisation]", dependency.getOrg().replace(".", "/"));
							url = url.replace("[artifact]", dependency.getName());
							url = url.replace("[revision]", dependency.getRev());
							url = url.replace("[ext]", "jar");
							String prefreURL = url;
							if(prefreURL.contains("(")){
								prefreURL = prefreURL.substring(0, prefreURL.indexOf("(")) + prefreURL.substring(prefreURL.indexOf(")")+1, prefreURL.length());
								successed = download(prefreURL);
								if(successed){
									downloadUrl = "";
									break;
								}
								downloadUrl += prefreURL + "\n";
								prefreURL = url.replace("(", "");
								prefreURL = prefreURL.replace(")", "");
							} 
							successed = download(prefreURL);
							if(successed){
								downloadUrl = "";
								break;
							}
							downloadUrl += prefreURL + "\n";
						}
						if(!successed){
							String msg = "<-------------------------"+dependency.getName()+"-"+dependency.getRev()+".jar下载失败-------------------------\n"+downloadUrl+
							"-------------------------"+dependency.getName()+"-"+dependency.getRev()+".jar下载失败------------------------->\n";
							System.out.println(msg);
						}
						latch.countDown();
					}				
				});
				break;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			service.shutdown();
		}
	}
	
	private static boolean download(String prefreURL){
		InputStream inputStream = null;
		OutputStream os = null;
		boolean ret = false;
		try {
			URL url = new URL(prefreURL);
			url.openConnection();
			inputStream = url.openStream();
			byte[] bytes = new byte[512];
			String fileName = prefreURL.substring(prefreURL.lastIndexOf("/")+1);
			os = new FileOutputStream(DOWNLOADPATH + fileName);
			int read = 0;
			long start = System.currentTimeMillis();
			while((read = inputStream.read(bytes)) != -1){
				System.out.println(read + ":" + bytes);
				os.flush();
				os.write(bytes, 0, read);
			}
			os.flush();
			ret = true;
			System.out.println(fileName + "文件下载成功！" + (System.currentTimeMillis() - start));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			/*if(!(e instanceof FileNotFoundException)){
				System.out.print(prefreURL);
				e.printStackTrace();
			}*/
		} finally {
			try {
				if(os != null){
					os.close();
				}
				if(inputStream != null){
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	public static void init() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("D:/workspace/metogo/example/src/ivy/ivy.xml");		
		NodeList rootNodeList = document.getChildNodes();
		Node root = rootNodeList.item(1);
		
		String defaultOrg = "";
		String defaultModule = "";
		NodeList children = root.getChildNodes();
		for(int x=0; x<children.getLength(); x++){
			Node child = children.item(x);
			if("info".equals(child.getNodeName())){
				NamedNodeMap attrs = child.getAttributes();
				defaultOrg = attrs.getNamedItem("organisation").getNodeValue();
				defaultModule = attrs.getNamedItem("module").getNodeValue();
			} else if("dependencies".equals(child.getNodeName())){
				NodeList dependencys = child.getChildNodes();
				for(int y=0; y<dependencys.getLength(); y++){
					Node dependency = dependencys.item(y);
					if("dependency".equals(dependency.getNodeName())){
						NamedNodeMap dependencyAttrs = dependency.getAttributes();
						//org="org.apache.axis2" name="mex" rev="1.7.0-SNAPSHOT" conf="openmeetings.axis2->*"
						Node orgNode = dependencyAttrs.getNamedItem("org");
						String org = orgNode == null ? defaultOrg : orgNode.getNodeValue();
						Node nameNode = dependencyAttrs.getNamedItem("name");
						String name = nameNode.getNodeValue();
						Node revNode = dependencyAttrs.getNamedItem("rev");
						String rev = revNode.getNodeValue();
						Dependency dp = new Dependency();
						dp.setName(name);
						dp.setOrg(org);
						dp.setRev(rev);
						DEPENDENCY.add(dp);
					}
				}
			}
		}
	}
}

class Dependency{
	private String org;
	private String name;
	private String rev;
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}		
}
