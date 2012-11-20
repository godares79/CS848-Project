package CJD; 

import java.util.HashMap;
import java.util.PriorityQueue;

public class NodeManager {
	//we currently have 10 nodes 
	static int numberOfNodes = 10; 
	static HashMap<String,Double> NodeRecord = new HashMap<String,Double>(numberOfNodes);
	
	//Update the node if it already exits
	//Insert the node if it's new
	public static void Update(String IP, Double score)
	{
		NodeRecord.put(IP,score);
		System.out.println(NodeRecord.toString());
	}
	
	public static Double GetScore(String IP)
	{
		Double result;
		if(NodeRecord.containsKey(IP))
		{
			result = NodeRecord.get(IP);
		}
		else
		{
			result = (double) -1;
		}
		System.out.println("Searching for " + IP + " returning " + result +"\n");

		return result;
	}
	
}
