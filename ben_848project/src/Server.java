import java.util.Vector;

/*
 * Thread of a server node
 * author: Tzu-Yang.(Ben) Yu
 */
public class Server {
	private double m_currentResourcesUsage;
	/** Is server running **/
	private boolean m_isRunning = true;
	/** Queue of jobs **/
	private Vector<JobNode> m_ServerQueue = new Vector<JobNode>();

	/** max job that can run on sever, if exceed stay in queue **/
	private int m_maxJobs = 1;
	private Thread m_serverThread;
	private String m_serverName;

	/**
	 * @param _resourcesUsage
	 *          pass the current CPU usage value (type: double)
	 * @param _name
	 */
	public Server(double _resourcesUsage, String _name) {
		m_currentResourcesUsage = _resourcesUsage;
		m_serverName = _name;
	}

	/**
	 * fire the server thread
	 * 
	 * Ben
	 */
	public void runServer() {
		m_serverThread = new Thread(new ServerThread());
		m_serverThread.start();
	}

	/**
	 * to add jobs in queue
	 * 
	 * Ben
	 */
	public void addJobs(JobNode _job) {
		m_ServerQueue.add(_job);
	}

	/**
	 * this is the server thread, all things are handled in here
	 * 
	 * @author Ben
	 * 
	 */
	private class ServerThread implements Runnable {
		/**
		 * call JobNode to run. The max num of Job can run in server is 1
		 */
		public void run() {
			while (true) {
				if (m_isRunning == false) {
					break;
				}
				// if no job in the queue, do nothing
				if (m_ServerQueue.size() != 0) {
					if (m_maxJobs > 0) {
						System.out.println(m_serverName + ": Last= " + m_ServerQueue.get(0).getM_resourcesUsage() + ", Size of queue= " + m_ServerQueue.size());
						Thread jobThread = new Thread(new JobThread(m_ServerQueue.get(0)));
						jobThread.start();
						m_ServerQueue.remove(0); // remove the first job in the queue
						m_maxJobs--;
					}
				}
			}
		}
	}

	/**
	 * subclass in Server for running the job
	 * 
	 * @author Ben
	 * 
	 */
	private class JobThread implements Runnable {
		private double resourceUsage;
		private int timeLast;

		public JobThread(JobNode _jobNode) {
			this.resourceUsage = _jobNode.getM_resourcesUsage();
			this.timeLast = _jobNode.getM_TimeLast();
		}

		/**
		 * JobNode slept by given second
		 */
		@Override
		public void run() {
			m_currentResourcesUsage += resourceUsage;
			for (int i = 0; i < timeLast; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.err.println("Job node is causing error");
					e.printStackTrace();
				}
			}

			// job finished, release resources
			m_maxJobs++;
			m_currentResourcesUsage -= resourceUsage;
			System.out.println(resourceUsage + " is finished by " + m_serverName);
		}
	}

	public double getM_currentResourcesUsage() {
		return m_currentResourcesUsage;
	}

	public Thread getM_serverThread() {
		return m_serverThread;
	}

	public void setM_isRunning(boolean m_isRunning) {
		this.m_isRunning = m_isRunning;
	}

	public int getQueueSize() {
		return m_ServerQueue.size();

	}

}
