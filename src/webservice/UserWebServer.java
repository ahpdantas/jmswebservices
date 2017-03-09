package webservice;

import javax.jms.MessageListener;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import interfaces.UserWebService;
import jms.JmsAdminManager;
import jms.JmsManager;
import listeners.SubscribeListener;

@WebService(endpointInterface = "interfaces.UserWebService")
public class UserWebServer implements UserWebService {
	private JmsAdminManager admin = new JmsAdminManager();
	private JmsManager manager = new JmsManager();
	
	@Override
	public void CreateNewUser(String user) {
		// TODO Auto-generated method stub
		admin.CreateQueue(user);
	}

	@Override
	public void Publish(String topic, String msg) {
		// TODO Auto-generated method stub
		manager.Publish(topic,msg);
	}

	@Override
	public void Subscribe(String topic, SubscribeListener list) {
		// TODO Auto-generated method stub
		manager.Subscribe(topic, list);
		
	}

	@Override
	public void SendTo(String user, String msg) {
		// TODO Auto-generated method stub
		manager.SendToQueue(msg, user);
	}

	@Override
	public String Receive(String user) {
		// TODO Auto-generated method stub
		return manager.ReceiveFromQueue(user);
	}
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9998/user",  new UserWebServer());
		System.out.println("User Server running...");
	}


}
