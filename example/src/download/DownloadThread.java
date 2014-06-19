package download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DownloadThread extends Thread{

	private String target_file = null; 
	private String localFileName = null;
	private long total_file_size = 0;
	private int startPosition = 0;
	private int endPosition = 0;
	private int current_download_size = 0;
	private int position = 0;
	
	public DownloadThread(int thread_position, long total_file_size, String localFileName, String target_file){
		this.target_file = target_file;
		this.total_file_size = total_file_size;
		this.localFileName = localFileName;
		this.position = thread_position;
		if(thread_position < 3){
			this.current_download_size = (int) (total_file_size / 4);
			this.startPosition = (thread_position * this.current_download_size);
			this.endPosition = ((thread_position+1) * this.current_download_size)-1;
		} else {
			this.startPosition = (int) (3 * (total_file_size / 4));
			this.endPosition = (int) (total_file_size - 1);
			this.current_download_size = (int) (this.total_file_size - ((int) (total_file_size / 4))*3);
		}
		System.out.println(toString());
	}
	public void run(){
		File localFile = new File(this.localFileName);
		URL url = null;
		InputStream is = null;
		try {
			url = new URL(target_file);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		try {
			is = url.openStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int writeCount = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile(localFile, "rw");
			raf.seek(this.startPosition);
			System.out.println("start write position: " + this.startPosition);
			is.skip(startPosition);
			byte[] bytes = new byte[1024*8];
			int readCount = 0;
			int toWriteCount = this.current_download_size;
			while(true){
				try{
					readCount=is.read(bytes);
				} catch(IOException e){
					e.printStackTrace();
					Map<String,String> map = null;
					if(MultiDownload.map.containsKey(this.position)){
						map = MultiDownload.map.get(this.position);
					} else {
						map = new HashMap<String, String>();
					}
					map.put("start_position", this.startPosition+"");
					map.put("endPosition", this.endPosition+"");
					map.put("currentPostion", writeCount+"");
					MultiDownload.map.put(this.position, map);
					break;
				}
				if(readCount>0){
					if(readCount < toWriteCount){
						raf.write(bytes, 0, readCount);
					} else {
						raf.write(bytes, 0, toWriteCount);
						break;
					}
					toWriteCount -= readCount;
					writeCount += readCount;
				} else {
					break;
				}
			}
			is.close();
			raf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString(){
		/*private String target_file = null; 
		private String localFileName = null;
		private long total_file_size = 0;
		private int startPosition = 0;
		private int endPosition = 0;
		private int current_download_size = 0;*/
		StringBuilder string = new StringBuilder("--------------" + Thread.currentThread().getName()+" thread download--------------\n");
		string.append("\t target file: " + this.target_file);
		string.append("\n\t local file: " + this.localFileName);
		string.append("\n\t start position: " + this.startPosition);
		string.append("\n\t end position: "+this.endPosition);
		string.append("\n\t every thread download size: "+this.current_download_size);
		string.append("\n--------------" + Thread.currentThread().getName()+" thread download--------------\n");
		return string.toString();
	}
}
