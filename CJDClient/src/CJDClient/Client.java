package CJDClient;

import java.rmi.Naming;

import CJD.CJDInterface;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		String url = new String("rmi://what/CJD");

		CJDInterface client = (CJDInterface) Naming.lookup("url");
		double reply = client.GetNodeScore("randomeString");
		System.out.println("\nSever reply:" + reply + "\n");
	}

}
