package interfaces;

import javax.jms.MessageListener;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface UserWebService {
	@WebMethod void CreateNewUser(String user);
	@WebMethod void Publish(String topic, String msg );
	@WebMethod void Subscribe(String topic, MessageListener list );
	@WebMethod void SendTo(String user, String msg );
	@WebMethod String Receive(String user);
}
