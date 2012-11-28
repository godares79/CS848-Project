package resource;

import java.io.IOException;
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
        
        Calendar cal;

        while(true)
        {
            System.out.print("\n");
            System.out.print("CPU: "+ getCPUUsage() +"\n");
            System.out.print("Memory: "+ getFreeFromMemory()  +"\n");
            System.out.print("Process table Length: " + monitor.processTable().length + "\n");
            System.out.print("Swap: " + getFreeSwap() + "\n");
            cal = Calendar.getInstance();
            double beforeTime = cal.getTime().getTime();
            //do the job
            Runtime rt = Runtime.getRuntime();
            try {
				Process p = rt.exec("/Users/daviddietrich/Downloads/unixbench-5.1.2/pgms/multi.sh 100");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            cal = Calendar.getInstance();
            double afterTime = cal.getTime().getTime();

            System.out.println("Time: " + (afterTime-beforeTime));
            System.out.print("\n");
        }
	}
	
	private static float getCPUUsage() {
		long idle = monitor.cpuTimes().getIdleMillis();
		long system = monitor.cpuTimes().getSystemMillis();
		long user = monitor.cpuTimes().getUserMillis();
		float cpuUsage = 0;
		// previous CPU time
		CpuTimes pre = new CpuTimes(user, system, idle);

		// sleep for 500 mm sec
		try {
			Thread.sleep(500);
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
		
		return (double) ( (totalSwap - freeSwap) / totalSwap);
	}

}
