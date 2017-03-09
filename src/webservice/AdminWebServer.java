package webservice;

import java.util.ArrayList;
import java.util.Vector;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import interfaces.AdminWebService;
import jms.JmsAdminManager;
import jms.JmsManager;

@WebService(endpointInterface = "interfaces.AdminWebService")
public class AdminWebServer implements AdminWebService {
	private JmsAdminManager admin = new JmsAdminManager();
	
	@Override
	public void CreateQueue(String queue) {
		// TODO Auto-generated method stub
		admin.CreateQueue(queue);
		
	}

	@Override
	public void RemoveQueue(String queue) {
		// TODO Auto-generated method stub
		admin.RemoveQueue(queue);
		
	}

	@Override
	public int getMessageCount(String queue) {
		// TODO Auto-generated method stub
		return admin.getMessageCount(queue);
	}

	@Override
	public void CreateTopic(String topic) {
		// TODO Auto-generated method stub
		admin.CreateTopic(topic);
	}

	@Override
	public void RemoveTopic(String topic) {
		// TODO Auto-generated method stub
		admin.RemoveTopic(topic);
	}
	
	@Override
	public ArrayList<String> GetAllQueues() {
		// TODO Auto-generated method stub
		return admin.GetAllQueues();
	}

	@Override
	public ArrayList<String> GetAllTopics() {
		// TODO Auto-generated method stub
		return admin.GetAllTopics();
	}		
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/admin",  new AdminWebServer());
		System.out.println("Admin Server running...");
	}

}
