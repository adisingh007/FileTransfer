package com.adi;



import java.io.File;
import java.io.IOException;
import java.util.Stack;

import com.adi.transfer.HostDetails;
import com.adi.client.ProjectClient;



public class FileTransferListener {

	private static final String OUTBOX = "outbox";



	public static void main(String[] args) throws IOException {
	
		HostDetails hostDetails = new HostDetails(args[0], Integer.parseInt(args[1]));
		ProjectClient client = new ProjectClient(hostDetails);
		
		while(true) {
		
			Thread thread = new Thread() {
			
				@Override
				public void run() {
				
					try {
				
						Stack<File> stackOfFiles = new Stack<>();
						File outbox = new File(OUTBOX);
						String[] files = outbox.list();
		
						for(String file : files)
							if(!file.startsWith(".")) {
							
								File img = new File(OUTBOX+"/"+file);
								if(img.isFile() && img.length() != 0)
									stackOfFiles.push(img);
							}
							
						while(stackOfFiles.size() > 0) {
						
							File file = stackOfFiles.pop();
							client.transfer(file);
							System.out.println(file.getName());
							file.delete();
						}
					} catch(IOException exception) {
					
						exception.printStackTrace();
					}
				}
			}; 
			
			thread.start();
			while(thread.isAlive())
				/* Keep running the dead loop. */;
		}
	}
}