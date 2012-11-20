package CJDClient;

import java.rmi.Naming;

import CJD.CJDInterface;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		try{
		
		String url = new String("rmi://127.0.0.1:1099//CJD");

		CJDInterface client = (CJDInterface) Naming.lookup(url);
		double reply = client.GetNodeScore("5555.1817729874");
		System.out.println("\nSever reply:" + reply + "\n");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
