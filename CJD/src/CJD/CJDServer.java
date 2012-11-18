package CJD;

import java.rmi.Naming;

public class CJDServer{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		try{
			CJDImplement cjd = new CJDImplement(); 
			Naming.rebind("CJD",cjd);
			System.out.println("CJD Server is ready!");
			
			//TODO:this should be another thread, i think
			UDPReceiver.ReceiveUDP();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
