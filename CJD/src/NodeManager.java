import java.util.HashMap;
import java.util.PriorityQueue;

public class NodeManager {
	HashMap<Integer,NodeStatus> NodeRecord = new HashMap<Integer,NodeStatus>();
	PriorityQueue<NodeStatus> NodeQueue = new PriorityQueue<NodeStatus>(10,NodeStatus.NodeScoreComparator);
	
	//Update the node if it already exits
	//Insert the node if it's new
	public void Insert(NodeStatus newNode)
	{
		int id = newNode.GetId();
		
		if(NodeRecord.containsKey(id))
		{
			//NodeRecord.get(id).UpdateCurrentNode(newNode.GetScore());
			NodeQueue.remove(NodeRecord.get(id));
			NodeQueue.add(newNode);
			
		}
		else
		{
			NodeRecord.put(id, newNode);
			NodeQueue.add(newNode);
		}
	}
	
	public int PopNode()
	{
		int id = NodeQueue.poll().GetId();
		NodeRecord.remove(id);
		return id;
	}
	
	public void swap(Integer a,Integer b)
	{
		Integer temp = a;
		a=30;
		b=temp;
	}
}
