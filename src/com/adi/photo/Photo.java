package com.adi.photo;



import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileInputStream;



public class Photo implements Serializable {

	private int fileLength;
	private String fileName;
	private byte[] bytes;
	
	
	
	public Photo(String fileName) throws IOException {
	
		File file = new File(fileName);
		new Photo(file);
	}
	
	
	
	public Photo(File file) throws IOException {
	
		fileName = file.getName();
		fileLength = (int)file.length();
		bytes = new byte[fileLength];
		
		FileInputStream fistream = new FileInputStream(file);
		fistream.read(bytes);
		fistream.close();
	}
	
	
	
	public String getName() {
	
		return fileName;
	}
	
	
	
	public int getLength() {
	
		return fileLength;
	}
	
	
	
	public byte[] getBytes() {
	
		return bytes;
	}
}