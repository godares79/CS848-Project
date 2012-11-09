import java.util.Vector;

/*
 * author: Tzu-Yang.(Ben) Yu
 */
public class CJD implements Runnable {
	Vector<JobNode> m_jobs = new Vector<JobNode>();
	Vector<Server> m_servers = new Vector<Server>();
	final int m_numOfServers = 2; // number of servers in our simulation.
	final int m_maxJobInQueue = 5; // max job that can assign to server

	// private boolean m_isRunning = false;

	public CJD(Vector<JobNode> _jobs) {
		m_jobs = _jobs;
	}

	@Override
	public void run() {
		// First step: generate and run severs according to m_numOfServers
		for (int i = 0; i < m_numOfServers; i++) {
			Server server = new Server((double) 0, "server" + i);
			server.setM_isRunning(true);
			server.runServer();
			m_servers.add(server);

		}

		// Second step: directing the job to the server that has least resources
		// usage
		while (m_jobs.size() > 0) {
			boolean isServerFound = false;
			JobNode job = m_jobs.get(0);
			// Default: the first server get chosen
			Server electedServer = m_servers.get(0);
			double minResourcesUsage = 0;
			for (Server server : m_servers) {
				if (server.getQueueSize() < m_maxJobInQueue) {
					if ((server.getM_currentResourcesUsage() < minResourcesUsage) || minResourcesUsage == 0) {
						minResourcesUsage = server.getM_currentResourcesUsage();
						electedServer = server;
						isServerFound = true;
					}
				}
			}
			if (isServerFound == true) {
				electedServer.addJobs(job);
				m_jobs.remove(0);
			}
			Thread.yield();
		}

		// if all jobs done shutdown all servers here
		mainLoop: 
		while (true) {
			for (Server server : m_servers) {
				if (server.getQueueSize() != 0) {
					continue mainLoop;
				}
			}
			break;
		}

		for (Server s : m_servers) {
			s.setM_isRunning(false);
		}

	}
}
