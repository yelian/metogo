package download.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import download.RemainDownloadObj;

public class MessageStore {
	
	private static final String MESSAGE_FILE_PATH = "store.dat"; 
	public void getMessage() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MESSAGE_FILE_PATH));
		RemainDownloadObj r = (RemainDownloadObj) ois.readObject();
		System.out.println(r.toString());
		ois.close();
	}
	
	public static void main(String[] args){
		try {
			new MessageStore().getMessage();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
