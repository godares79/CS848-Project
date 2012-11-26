package CJD;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface CJDInterface extends Remote{
	double GetNodeScore(String IP) throws RemoteException;
	HashMap<String,Double> GetAllScores() throws RemoteException;
}


