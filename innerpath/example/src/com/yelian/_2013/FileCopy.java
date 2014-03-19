package com.yelian._2013;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String fileName = "E:/photo/114MSDCF/DSC02864.JPG";
		FileInputStream fis = new FileInputStream(fileName);
		String newfile = fileName.replace(".", "1.");
		System.out.println(fileName);
		System.out.println(newfile);
		File file = new File(newfile);
		FileOutputStream fos  = new FileOutputStream(file);
		byte[] bytes = new byte[1000*8];
		int readCount = 0;
		while((readCount = fis.read(bytes, 0, 1000*8))>0){
			fos.write(bytes, 0, readCount);
		}
		fos.flush();
		fis.close();
		fos.close();
		System.out.println("create edded!");
	}

}
