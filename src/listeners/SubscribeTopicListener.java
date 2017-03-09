package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.UserGuiApp;

public class SubscribeTopicListener implements ActionListener {
	private UserGuiApp app;
		
	public SubscribeTopicListener(UserGuiApp app){
		this.app = app;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Subscribe Topic");
		
		JTextField topic = new JTextField();
		Object[] message = {
		    "Topic:", topic,
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Subscribe Topic", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if( (topic.getText() != null) && (topic.getText().length() > 0)){
					this.app.userManager.Subscribe(topic.getText(), this.app.subsList);;
			}
		} else {
		    System.out.println("subscribe canceled");
		}
	}
}
