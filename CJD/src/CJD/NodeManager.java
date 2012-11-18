package CJD; 

import java.util.HashMap;
import java.util.PriorityQueue;

public class NodeManager {
	//we currently have 10 nodes 
	int numberOfNodes = 10; 
	HashMap<String,Double> NodeRecord = new HashMap<String,Double>(numberOfNodes);
	
	//Update the node if it already exits
	//Insert the node if it's new
	public void Update(String IP, Double score)
	{
		NodeRecord.put(IP,score);
	}
	
}
