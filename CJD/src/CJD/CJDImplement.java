package CJD;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CJDImplement extends UnicastRemoteObject implements CJDInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected CJDImplement() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public double GetNodeScore(String IP) throws RemoteException {
		// TODO Auto-generated method stub
		return NodeManager.GetScore(IP);
	}
}
