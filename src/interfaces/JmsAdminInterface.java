package interfaces;

public interface JmsAdminInterface {
	void CreateQueue(String queue);
	void RemoveQueue(String queue);
	int getMessageCount(String queue);
	void CreateTopic(String topic);
	void RemoveTopic(String topic);	
	
}
