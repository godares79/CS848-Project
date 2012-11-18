package CJD; 

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver {
  public static void ReceiveUDP() {
    try {
      int port = 9050;
      
      //create the node manager
      NodeManager manager=new NodeManager();

      // Create a socket to listen on the port.
      DatagramSocket dsocket = new DatagramSocket(port);

      // Create a buffer to read datagrams into. If a
      // packet is larger than this buffer, the
      // excess will simply be discarded!
      //TODO: what is the best size for this buffer? 
      byte[] buffer = new byte[256];

      // Create a packet to receive data into the buffer
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

      // Now loop forever, waiting to receive packets and printing them.
      while (true) {
        // Wait to receive a datagram
        dsocket.receive(packet);

        // Convert the contents to a string, and display them
        String msg = new String(buffer, 0, packet.getLength());
        
        //assuming that I know the first 16 bytes are for IP
        // and the following 8 bytes are for score 
        //TODO: set the right offset 
        String IP = msg.substring(0, 15);
        String scoreString = msg.substring(16, 23);
        double score = Double.parseDouble(scoreString);
        manager.Update(IP, score);
        
        //System.out.println(packet.getAddress().getHostName() + ": "
          //  + msg);

        // Reset the length of the packet before reusing it.
        packet.setLength(buffer.length);
        
      }
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}