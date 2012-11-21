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
		
		String url = new String("rmi://ugster02:1099//CJD");

		CJDInterface client = (CJDInterface) Naming.lookup(url);
		
		while(true){
			String input = "5555.1817729874";
			double reply = client.GetNodeScore(input);
			System.out.println("\nSever reply:" + reply + "\n");
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
