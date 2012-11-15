package mainPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting Resource Monitor...");
		
		//Need to just have a loop that will collect resource information every few
		//seconds and sent it back to the central server
		while (true) {
			System.out.println("Collecting resource information");
			
			//int resourceValue = collectResources();
			//just put some random things here for now 
			String resourceValue = "01234567890123456789012345678901234567890";
			sendResourceUsage(resourceValue);
			
			//Sleep for 10 seconds then do again
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Collection, calculation of the resources required
	private static int collectResources() {
		
		return 0;
	}
	
	//Send a packet to the cjd with the usage information
	private static void sendResourceUsage(String resourceUsage) {
		//Will need to change the host to be the central server
		try {
			String host = " 127.0.0.1";
			int port = 9050;
			
			byte[] message = new byte[] { resourceUsage.byteValue() };
			InetAddress address = InetAddress.getByName(host);
			DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
			
			DatagramSocket socket = new DatagramSocket();
			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
