package com.adi.client;



import java.net.Socket;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;

import com.adi.photo.Photo;
import com.adi.transfer.HostDetails;



public class ProjectClient {

	private HostDetails host;



	public ProjectClient(HostDetails host) {
		
		this.host = host;
	}
	
	
	
	public void transfer(File file) throws IOException {
	
		Photo photo = new Photo(file);
		Socket socket = new Socket(host.getIP(), host.getPort());
		
		ObjectOutputStream oostream = new ObjectOutputStream(socket.getOutputStream());
		oostream.writeObject(photo);
		
		oostream.flush();
		oostream.close();
		socket.close();
	}
	
	
	
	public void transfer(String fileName) throws IOException {
		
		transfer(new File(fileName));
	}
}