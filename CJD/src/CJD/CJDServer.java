package CJD;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CJDServer{

	/**
	 * @param args
	 */
	static NodeManager manager=new NodeManager();

	public static void main(String[] args) throws Exception {
		try{
			CJDImplement cjd = new CJDImplement(); 
            Naming.rebind("//127.0.0.1:1099//CJD",cjd);
			System.out.println("CJD Server is ready!");
			
			//TODO:this should be another thread, i think
			UDPReceiver.ReceiveUDP();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
