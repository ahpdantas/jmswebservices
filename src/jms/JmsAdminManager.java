package jms;

import javax.jms.JMSException;

import org.exolab.jms.administration.AdminConnectionFactory;
import org.exolab.jms.administration.JmsAdminServerIfc;

import interfaces.JmsAdminInterface;

public class JmsAdminManager implements JmsAdminInterface {
	private JmsAdminServerIfc admin = null;
	
	public JmsAdminManager(){
		
		try{
			String url = "tcp://localhost:3035/";
			String user = "admin";
			String password = "openjms";
			admin = AdminConnectionFactory.create(url, user, password);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void CreateQueue(String queue) {
		// TODO Auto-generated method stub
	    Boolean isQueue = Boolean.TRUE;
	    try {
	    	if( !admin.destinationExists(queue)){
		    	if (!admin.addDestination(queue, isQueue)) {
				    System.err.println("Failed to create queue " + queue);
				}
	    	}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void RemoveQueue(String queue) {
		// TODO Auto-generated method stub
	    try {
			if (!admin.removeDestination(queue)) {
			   System.err.println("Failed to remove destination " + queue);
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void CreateTopic(String topic) {
		// TODO Auto-generated method stub
	    Boolean isQueue = Boolean.FALSE;
	    try {
	    	if( !admin.destinationExists(topic)){
				if (!admin.addDestination(topic, isQueue)) {
				    System.err.println("Failed to create topic " + topic);
				}
	    	}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void RemoveTopic(String topic) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	    try {
			if (!admin.removeDestination(topic)) {
			   System.err.println("Failed to remove destination " + topic);
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getMessageCount(String queue) {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			count = admin.getQueueMessageCount(queue);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

}
