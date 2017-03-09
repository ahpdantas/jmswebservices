package jms;

import java.util.*;
import javax.jms.*;
import javax.naming.*;



import interfaces.JmsManagerInterface;

public class JmsManager implements JmsManagerInterface{
	private QueueConnectionFactory qFactory = null;
	private TopicConnectionFactory tFactory = null;
	private Context context = null;

	public JmsManager(){
		try{
			Hashtable properties = new Hashtable();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.exolab.jms.jndi.InitialContextFactory");
			properties.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
	
			context = new InitialContext(properties);
			
			qFactory = (QueueConnectionFactory) context.lookup("ConnectionFactory");
			tFactory = (TopicConnectionFactory) context.lookup("ConnectionFactory");
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void SendToQueue(String msg, String queue) {
		// TODO Auto-generated method stub
		try{
			QueueConnection conn = qFactory.createQueueConnection();
			QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
			javax.jms.Queue dest = (javax.jms.Queue) context.lookup(queue);
			QueueSender sender = session.createSender(dest);
			
			TextMessage message = session.createTextMessage();
			message.setText(msg);
			
			System.out.println("User:"+queue);
			System.out.println("Message:"+msg);
			
			sender.send(message);
		
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}

	@Override
	public String ReceiveFromQueue(String queue) {
		// TODO Auto-generated method stub
		try{
			QueueConnection conn = qFactory.createQueueConnection();
			QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			conn.start();
			
			javax.jms.Queue dest = (javax.jms.Queue) context.lookup(queue);
			QueueReceiver receiver = session.createReceiver(dest);
			
			TextMessage msg = (TextMessage)receiver.receiveNoWait();
			
			conn.close();
			if( msg != null ){
				return msg.getText();
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void Publish(String topic, String msg) {
		// TODO Auto-generated method stub
		System.out.println(topic +" "+ msg);
		try{
			TopicConnection conn = tFactory.createTopicConnection();
			TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			conn.start();
			
			Topic dest = (Topic)context.lookup(topic);
			TopicPublisher publisher = session.createPublisher(dest);
			
			TextMessage m = session.createTextMessage();
			m.setText(msg);
			publisher.publish(m);
			
			conn.close();
			
		}catch( Exception e){
			e.printStackTrace();
		}

		
	}

	@Override
	public void Subscribe(String topic, MessageListener list) {
		// TODO Auto-generated method stub
		System.out.println(topic);
		try{
			TopicConnection conn = tFactory.createTopicConnection();
			TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			conn.start();
			
			Topic dest = (Topic)context.lookup(topic);
			TopicSubscriber subscriber = session.createSubscriber(dest);
			
			subscriber.setMessageListener(list);
			
		}catch( Exception e){
			e.printStackTrace();
		}
		
	}

}
