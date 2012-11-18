package CJD;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CJDInterface extends Remote{
	double GetNodeScore(String IP) throws RemoteException;
}


