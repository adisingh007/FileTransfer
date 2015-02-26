import java.io.IOException;

import com.adi.FileTransferListener;
import com.adi.transfer.HostDetails;
import com.adi.client.ProjectClient;



public class DemoTransfer {

	public static void main(String[] args) throws IOException {
	
		HostDetails hostDetails = new HostDetails(args[0], Integer.parseInt(args[1]));
		ProjectClient client = new ProjectClient(hostDetails);
		FileTransferListener.start(client, args[2]);
	}
}
