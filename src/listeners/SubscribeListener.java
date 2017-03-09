package listeners;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import app.UserGuiApp;

public class SubscribeListener implements MessageListener {

	private UserGuiApp app;
	
	public SubscribeListener(UserGuiApp app){
		this.app = app;
	}
	
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		System.out.println("Oi aqui");
		 if(message instanceof TextMessage){
             try{
            	 app.updateNews(((TextMessage)message).getText());
            	 System.out.println(((TextMessage)message).getText());
             }catch(Exception e){
            	 e.printStackTrace();
             }
         }

		
	}

}
