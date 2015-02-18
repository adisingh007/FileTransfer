package com.adi.server;



import java.net.Socket;
import java.net.ServerSocket;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

import com.adi.photo.Photo;



public class ProjectServer {

	private static final String INBOX = "inbox";
	
	
	
	public static void main(String[] args) throws IOException {
	
		int port = Integer.parseInt(args[0]);
		ServerSocket serverSocket = new ServerSocket(port);
		
		
		while(true) {
		
			Thread thread = new Thread() {
		
				public void run() {
			
					try {
					
						Socket socket = serverSocket.accept();
						ObjectInputStream oistream = new ObjectInputStream(socket.getInputStream());
						Photo photo = (Photo)oistream.readObject();
						
						oistream.close();
						socket.close();
						
						String outFileName = String.format("%s/%s", INBOX, photo.getName());
						File file = new File(outFileName);
						FileOutputStream fostream = new FileOutputStream(file);
						byte[] pic = photo.getBytes();
						
						fostream.write(pic);
						fostream.flush();
						fostream.close();
					} catch(IOException | ClassNotFoundException exception) {
					
						exception.printStackTrace();
					}
				}
			};
			
			thread.start();
			while(thread.isAlive())
				/* Keep running this dead loop. */;
		} // End of while loop.
	} // End of main()
}