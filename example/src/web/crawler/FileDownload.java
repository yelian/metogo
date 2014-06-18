package web.crawler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileDownload {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL url = null;
		URLConnection connection = null;
		InputStream is = null;
		String fileName = "index.html";
		OutputStream os = null;
		try {
			url = new URL("http://localhost:8080/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			connection = url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			is = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytes = new byte[1024*8];
		int read = 0;
		try {
			os = new FileOutputStream(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			while((read=is.read(bytes)) > 0){
				os.write(bytes, 0, read);
			}
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			os.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
