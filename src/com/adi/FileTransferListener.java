package com.adi;



import java.io.File;
import java.io.IOException;
import java.util.Stack;

import com.adi.client.ProjectClient;



public class FileTransferListener {

	public static void start(ProjectClient client, String outbox) throws IOException {
	
		while(true) {
		
			Thread thread = new Thread() {
			
				@Override
				public void run() {
				
					try {
				
						Stack<File> stackOfFiles = new Stack<>();
						File dir = new File(outbox);
						String[] files = dir.list();
		
						for(String file : files) {
						
							if(!file.startsWith(".")) {
							
								File img = new File(outbox+"/"+file);
								if(img.isFile() && img.length() != 0)
									stackOfFiles.push(img);
							}
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