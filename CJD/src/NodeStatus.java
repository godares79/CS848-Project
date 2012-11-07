import java.util.Comparator;


//the info that needs to be recorded for each node server 

public class NodeStatus implements Comparable<NodeStatus> {
	//unique identifier for each node
	private int id; 
	
	//info of the node server
	//private float cpuUsage;
	//private float memoryUsage;
	//private float bandwidth;
	private float score;
	
	//map that 
	
	/*
	public NodeStatus(int newId, float newCpuUsage, float newMemoryUsage, float newBandwidth)
	{
		id = newId;
		cpuUsage = newCpuUsage;
		memoryUsage = newMemoryUsage;
		bandwidth = newBandwidth;
	}
	*/
	
	public NodeStatus(int newId, float newScore)
	{
		id = newId;
		score = newScore;
	}
	
	public int GetId()
	{
		return id;
	}
	
	public float GetScore()
	{
		return score;
	}
	
	/*
	public void UpdateCurrentNode(float updatedCpuUsage, float updatedMemoryUsage, float updatedBandwidth)
	{
		cpuUsage = updatedCpuUsage;
		memoryUsage = updatedMemoryUsage;
		bandwidth = updatedBandwidth;
	}
	*/
	
	public void UpdateCurrentNode(float newScore)
	{
		score = newScore;
	}
	

	// maybe we don't need this, LOL 
	public void DeleteCurrentNode()
	{
		
	}

	@Override
	public int compareTo(NodeStatus compareNode) {
		//descending order
		return (int)(this.score-compareNode.GetScore());
	}
	
	public static Comparator<NodeStatus> NodeScoreComparator
		= new Comparator<NodeStatus>()
		{
			@Override
			public int compare(NodeStatus node1, NodeStatus node2) {
				return node2.compareTo(node1);
			}
			
		};

}
