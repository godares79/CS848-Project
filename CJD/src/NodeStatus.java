
//the info that needs to be recorded for each node server 

public class NodeStatus {
	//unique identifier for each node
	private int id; 
	
	//info of the node server
	private float cpuUsage;
	private float memoryUsage;
	private float bandwidth;
	
	//map that 
	
	public NodeStatus(int newId, float newCpuUsage, float newMemoryUsage, float newBandwidth)
	{
		id = newId;
		cpuUsage = newCpuUsage;
		memoryUsage = newMemoryUsage;
		bandwidth = newBandwidth;
	}
	
	public void AddIntoQueue()
	{
		
	}
	
	public void UpdateCurrentNode(float updatedCpuUsage, float updatedMemoryUsage, float updatedBandwidth)
	{
		cpuUsage = updatedCpuUsage;
		memoryUsage = updatedMemoryUsage;
		bandwidth = updatedBandwidth;
	}

	// maybe we don't need this, LOL 
	public void DeleteCurrentNode()
	{
		
	}
}
