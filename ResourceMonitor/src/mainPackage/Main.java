package mainPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import com.jezhumble.javasysmon.*;

public class Main {

	public static void main(String[] args) {
		Main m = new Main();
		// TODO Auto-generated method stub
		System.out.println("Starting Resource Monitor...");

		// Need to just have a loop that will collect resource information every few
		// seconds and sent it back to the central server
		while (true) {
			System.out.println("Collecting resource information");

			// int resourceValue = collectResources();
			// just put some random things here for now
			String resourceValue = "01234567890123456789012345678901234567890";
			sendResourceUsage(resourceValue);

			// Sleep for 10 seconds then do again
			System.out.println("resource usage: " + m.collectResources());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * The higher the utilization of a resource, the greater the resource usage;
	 * if multiple resources are heavily utilized, the above product results in a
	 * correspondingly higher resource usage.
	 * 
	 * Formula: resourceUsage = 1/(1-cpu) * 1/(1-mem)
	 * 
	 * @return resource usage Ben
	 */
	private double collectResources() {
		JavaSysMon monitor = new JavaSysMon();
		float CPU = this.getCPUUsage();
		long totalMemory = monitor.physical().getTotalBytes();
		long remainMemory = this.getFreeBytesFromMemory();
		double memory = (double) (totalMemory - remainMemory) / totalMemory;

		// never have 100% cpu and memory usage
		if (memory == 1)
			memory = (long) 0.999999999;
		if (CPU == 1)
			CPU = (long) 0.999999999;

		double resourceUsage = (1 / (1 - CPU)) * (1 / (1 - memory));

		return resourceUsage;
	}

	/**
	 * 
	 * @return available memory in bytes Ben
	 */
	private long getFreeBytesFromMemory() {
		JavaSysMon monitor = new JavaSysMon();
		long currentMemoryRemain = 0;
		currentMemoryRemain = monitor.physical().getFreeBytes();
		return currentMemoryRemain;
	}

	/**
	 * @return current CPU usage Ben
	 */
	private float getCPUUsage() {
		JavaSysMon monitor = new JavaSysMon();
		long idle = monitor.cpuTimes().getIdleMillis();
		long system = monitor.cpuTimes().getSystemMillis();
		long user = monitor.cpuTimes().getUserMillis();
		float cpuUsage = 0;
		// previous CPU time
		CpuTimes pre = new CpuTimes(user, system, idle);

		// sleep for 10 mm sec
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// calculating CPU usage
		cpuUsage = monitor.cpuTimes().getCpuUsage(pre);
		return cpuUsage;
	}

	/**
	 * 
	 * @param resourceUsage
	 *          Ben
	 */
	// Send a packet to the cjd with the usage information
	private static void sendResourceUsage(String resourceUsage) {
		// Will need to change the host to be the central server
		try {
			String host = " 127.0.0.1";
			int port = 9050;

			// byte[] message = new byte[] { resourceUsage.byteValue() };
			// InetAddress address = InetAddress.getByName(host);
			// DatagramPacket packet = new DatagramPacket(message, message.length,
			// address, port);

			// DatagramSocket socket = new DatagramSocket();
			// socket.send(packet);
			// socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
