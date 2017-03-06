package interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface AdminWebService {
	@WebMethod void CreateQueue(String queue);
	@WebMethod void RemoveQueue(String queue);
	@WebMethod int getMessageCount(String queue);
	@WebMethod void CreateTopic(String topic);
	@WebMethod void RemoveTopic(String topic);	
}
