/*
 * Node of a job
 * author: Tzu-Yang.(Ben) Yu
 */
public class JobNode{

	private double m_resourcesUsage;
	
	private int m_timeLast;

	public JobNode(double _resourceUsage, int _TimeLast) {
		m_resourcesUsage = _resourceUsage;
		m_timeLast = _TimeLast;
	}

	public double getM_resourcesUsage() {
		return m_resourcesUsage;
	}

	public void setM_resourcesUsage(double m_resourcesUsage) {
		this.m_resourcesUsage = m_resourcesUsage;
	}

	public int getM_TimeLast() {
		return m_timeLast;
	}

	public void setM_TimeLast(int m_TimeLast) {
		this.m_timeLast = m_TimeLast;
	}

}
