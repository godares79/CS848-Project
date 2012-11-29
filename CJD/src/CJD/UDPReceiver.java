package CJD; 

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class UDPReceiver {
  public static void ReceiveUDP() {
    try {
      int port = 9040;

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
        
        //assuming that I know the first 12 bytes are for IP
        // and the following 8 bytes are for score 
        String IP = msg.substring(0, 13);
        byte[] resource = new byte[8];
        
        for(int i=0;i<8;++i)
        {
        	resource[i]=buffer[13+i];
        }
        
        double score = ByteBuffer.wrap(resource).getDouble();
        NodeManager.Update(IP, score);
        
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