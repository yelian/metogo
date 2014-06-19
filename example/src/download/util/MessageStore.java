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
	}
	
	public static void main(String[] args){
		try {
			new MessageStore().getMessage();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
