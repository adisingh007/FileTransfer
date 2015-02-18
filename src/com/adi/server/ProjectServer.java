package com.adi.server;



import java.net.Socket;
import java.net.ServerSocket;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

import com.adi.photo.Photo;
import com.adi.database.DatabaseEngine;


public class ProjectServer {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
	
		int port = Integer.parseInt(args[0]);
		ServerSocket serverSocket = new ServerSocket(port);
		DatabaseEngine engine = new DatabaseEngine(new File(args[1]));
		engine.connect();
		
		while(true) {
		
			Thread thread = new Thread() {
		
				public void run() {
			
					try {
					
						Socket socket = serverSocket.accept();
						ObjectInputStream oistream = new ObjectInputStream(socket.getInputStream());
						Photo photo = (Photo)oistream.readObject();
						
						oistream.close();
						socket.close();
						
						engine.feed(photo.getName(), photo.getBytes());
					} catch(IOException | ClassNotFoundException | SQLException exception) {
					
						exception.printStackTrace();
					}
				}
			};
			
			thread.start();
			while(thread.isAlive())
				/* Keep running this dead loop. */;
				
			if(port == port + 1)
				break;	
		} // End of while loop.
		
		engine.close();
	} // End of main()
}