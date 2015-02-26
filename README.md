# Image Transfer App #
  
    -- Author: Aditya R.Singh  
    -- Date: 16-02-2015  
    -- Company: SE CMPN, Shri L.R.Tiwari College of Engineering, Mira Road.  
  
This is a simple image transfer app intended to be embedded with other project.  
    
This is based on a client server model. What the app does is, all images clicked by a camera is put 
in the `outbox` directory on the client side. The image is then transferred to the server over Sockets 
and is placed in the `inbox` directory on the server.  
  
     
### Files required on the `client` side:  ###
     
  1. outbox/  
  2. bin/com/adi/client/ProjectClient.class  
  3. bin/com/adi/photo/Photo.class  
  4. bin/com/adi/transfer/HostDetails.class  
  5. bin/com/adi/FileTransferListener.class  
  6. bin/com/adi/FileTransferListener$1.class  
  
     
### File required on the `server` side:  ###  

  1. inbox/  
  2. com/adi/photo/Photo.class  
  3. com/adi/server/ProjectServer.class  
  4. com/adi/server/ProjectServer$1.class  
    
      
### To run the app:  ###
    
  1. Turn the server on.  
        
        java -cp bin com.adi.server.ProjectServer <Port number to bind the server socket> <relative path of the properties file> 
     
     Make sure to turn on the database server too and configure your database details in the properties/database.properties file.  
  
  2. On the client side, turn the DemoTransfer on.  
      
        java -cp bin DemoTransfer <IP of the server> <Port of the server> <relative path of the directory containing the images clicked by camera> 
          
  3. And we are done.
  
  
### Using the API!  ###

  1. Create an object of `com.adi.transfer.HostDetails` with Server IP address and Port number to connect the socket to.  
     Example:  
          
         com.adi.transfer.HostDetails host = new com.adi.transfer.HostDetails("64.78.223.98", 80);
	
     This will save the details of the server to be connected to.
  
  2. Create an object of `com.adi.client.ProjectClient` with the object of `com.adi.transfer.HostDetails` as a parameter.  
     Example:  
  
         com.adi.client.ProjectClient client = new com.adi.client.ProjectClient(host);  
  
     This will connect the Client socket to IP address `64.78.223.98` on port `80`. See `step 1` for clarification.  
  
  3. Call the `static` method `start()` from the `com.adi.FileTransfer` class with `com.adi.client.ProjectClient` object and the absolute or relative path of the directory where your camera will 
     store images as parameters.  
     Example:  
  
         com.adi.FileTransferListener.start(client, "/Users/aditya/Desktop/Workspace/Java/FileTransfer/outbox/");  // Make sure the end the path of the directory with a '/'(forward slash).  
  
     This will start listening to the directory specified ("outbox" in this case) and will transfer any image that comes in the directory to the server specified in the `com.adi.transfer.HostDetails` object.
  
  4. Good Luck!  
 