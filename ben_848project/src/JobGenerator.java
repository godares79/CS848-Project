import java.util.Vector;

/*
 * 
 * authors: Ben
 * 
 */

public class JobGenerator {
	Vector<JobNode> m_jobs = new Vector<JobNode>();

	/**
	 * generate job by random number
	 * 
	 * @param _number
	 *          of max jobs Ben
	 */
	public void generateJobs(int _number) {
		System.out.print("Last list: ");
		for (int i = 0; i < _number; i++) {
			int randomNum = (int) (Math.random() * 10);
			JobNode job = new JobNode(randomNum, randomNum);
			System.out.print(randomNum + ", "); // print
			m_jobs.add(job);
		}
		System.out.println("\n" + "-----------------------------------------------");
	}

	/**
	 * start the program
	 * 
	 * Ben
	 */
	public void excute() {
		Thread master = new Thread(new CJD(m_jobs));
		master.start();
	}
}
