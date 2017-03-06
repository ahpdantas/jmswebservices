package interfaces;

import javax.jms.MessageListener;

public interface JmsManagerInterface {
	void SendToQueue(String msg, String queue);
	String ReceiveFromQueue(String queue);
	void Publish(String msg, String topic);
	void Subscribe(String topic, MessageListener list );
}
