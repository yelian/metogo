package download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import download.RemainDownloadObj.EveryThreadToDownloadObj;

public class MultiDownload {
	
	
	private static String FILE = "";
	private static final int DOWNLOAD_THREAD = 4;
	private static long FILE_SIZE = 0;
	public static Map<Integer, Map<String, String>> map = new HashMap<Integer,Map<String, String>>();
	public static RemainDownloadObj obj = new RemainDownloadObj();
	
	public MultiDownload(){
		this(null);
	}
	
	public MultiDownload(String FILE){
		this.FILE = FILE;
		map = Collections.synchronizedMap(map);
	}
	
	public void download() throws IOException{
		if(FILE == null){
			throw new NullPointerException("the is no FILE to download!!!");
		}
		URL url = null;
		url = new URL(FILE);
		System.out.println(url.openConnection().getContentLength());
		FILE_SIZE = getFileLength(url);
		System.out.println(FILE_SIZE);
		createEmptyGhostFile(FILE_SIZE);
		Thread[] threads = new Thread[MultiDownload.DOWNLOAD_THREAD];
		for(int x=0; x<MultiDownload.DOWNLOAD_THREAD; x++){
			threads[x] = new DownloadThread(x, FILE_SIZE, "todownload", FILE);
			threads[x].start();
		}
		long startTime = System.currentTimeMillis();
		for(int x=0; x<MultiDownload.DOWNLOAD_THREAD; x++){
			try {
				threads[x].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(map.isEmpty()){
			System.out.println("download success!!! time cost : " + (System.currentTimeMillis()-startTime)/1000);
		} else {
			List<EveryThreadToDownloadObj> objs = obj.getList();
			for(int x:map.keySet()){
				EveryThreadToDownloadObj o = obj.new EveryThreadToDownloadObj();
				System.out.println(map.get(x));
				
				/*map.put("start_position", this.startPosition+"");
				map.put("endPosition", this.endPosition+"");
				map.put("currentPostion", writeCount+"");
				MultiDownload.map.put(this.position, map);*/
				
				o.setCurrentPosition(Integer.parseInt(map.get(x).get("start_position")));
				o.setCurrentThreadPosition(Integer.parseInt(map.get(x).get("currentPostion")));
				o.setEndPosition(Integer.parseInt(map.get(x).get("currentPostion")));
				objs.add(o);
			}
			obj.setLocal_file("todownload");
			obj.setTarget_file(FILE);
			File storeFile = new File("store.dat");
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeFile));
			oos.writeObject(obj);
			oos.close();
			System.out.println("download failed!!! time cost : " + (System.currentTimeMillis()-startTime)/1000);
		}
		
	}
	
	public long getFileLength(URL url) throws IOException{
		URLConnection conn = url.openConnection();
		long size = conn.getContentLength();
		return size;
	}
	
	public void createEmptyGhostFile(long size) throws IOException{
		File file = new File("todownload");
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream os = new FileOutputStream(file);
		int _size = 1024*4;
		long remain = size;
		byte[] bytes = new byte[_size];
		while(remain > 0){
			if(remain > _size){
				os.write(bytes, 0, _size);
			} else {
				os.write(bytes, 0, (int)remain);
			}
			remain -= _size;
		}
		os.flush();
		os.close();
	}
	
	public static void main(String[] args){
		try {
			String url = "http://videomega.tv/iframe.php?ref=fXEVJZKSfT";
			//new MultiDownload("http://localhost:8080/DocumentOnlineView/resource/java.pdf").download();
			new MultiDownload(url).download();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
