package app;

import jms.JmsAdminManager;
import jms.JmsManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello JMS WEB");
		JmsManager manager = new JmsManager();
		JmsAdminManager admin = new JmsAdminManager();
		
		//admin.CreateQueue("queue6");
		
		//manager.SendToQueue("Hello World", "queue6");
		//System.out.println(manager.ReceiveFromQueue("queue6"));
		
		admin.RemoveQueue("queue6");
		
	}

}
