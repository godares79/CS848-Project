package resource;

import java.util.Calendar;
import com.jezhumble.javasysmon.*;

public class ResourceCollector {

	/**
	 * @param args
	 */
	static JavaSysMon monitor = new JavaSysMon();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("ResourceExperiment starts !!! ^_^ ");
        
        Calendar cal = Calendar.getInstance();
        while(true)
        {
            System.out.println("\n");
			System.out.println(cal.getTime());
            System.out.println("CPU: "+ getCPUUsage() +"\n");
            System.out.println("Memory: "  +"\n");
            System.out.println("Process table Length: " + monitor.processTable().length + "\n");
            System.out.println("Swap: " + getFreeSwap() + "\n");
            System.out.println(cal.getTime());
            //do the job
            System.out.println(cal.getTime());
            System.out.println("CPU: "+ getCPUUsage() +"\n");
            System.out.println("Memory: "  +"\n");
            System.out.println("Process table Length: " + monitor.processTable().length + "\n");
            System.out.println("Swap: " + getFreeSwap() + "\n");
            System.out.println(cal.getTime());
            System.out.println("\n");
        }
	}
	
	private static float getCPUUsage() {
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
	
	private static double getFreeFromMemory() {
		long remainMemory = 0;
		remainMemory = monitor.physical().getFreeBytes();
		long totalMemory = monitor.physical().getTotalBytes();
		double memory = (double) (totalMemory - remainMemory) / totalMemory;
		
		return memory;		
	}
	
	private static double getFreeSwap(){
		long freeSwap = monitor.swap().getFreeBytes();
		long totalSwap = monitor.swap().getTotalBytes();
		
		return (double) (totalSwap - freeSwap / totalSwap);
	}

}
