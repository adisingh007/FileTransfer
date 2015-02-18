Image Transfer App
==================
  
    -- Author: Aditya R.Singh  
    -- Date: 16-02-2015  
    -- Company: SE CMPN, Shri L.R.Tiwari College of Engineering, Mira Road.  
  
This is a simple image transfer app intended to be embedded with other project.  
    
This is based on a client server model. What the app does is, all images clicked by a camera is put 
in the `outbox` directory on the client side. The image is then transferred to the server over Sockets 
and is placed in the `inbox` directory on the server.  
   
Files required on the `client` side:  
     
  1. outbox/  
  2. bin/com/adi/client/ProjectClient.class  
  3. bin/com/adi/photo/Photo.class  
  4. bin/com/adi/transfer/HostDetails.class  
  5. bin/com/adi/FileTransferListener.class  
  6. bin/com/adi/FileTransferListener$1.class  
    
File required on the `server` side:  
    
  1. inbox/  
  2. com/adi/photo/Photo.class  
  3. com/adi/server/ProjectServer.class  
  4. com/adi/server/ProjectServer$1.class  
    
To run the app:  
    
  1. Turn the server on.  
        
        java com.adi.server.ProjectServer <Port number to bind the server socket>  
     
  2. On the client side, turn the FileTransferListener on.  
      
        java com.adi.FileTransferListener <IP of the server> <Port of the server>  
          
  3. And we are done.  
