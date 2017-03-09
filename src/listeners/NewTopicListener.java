package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.AdminGuiApp;

public class NewTopicListener implements ActionListener {
	private AdminGuiApp app;
	
	public NewTopicListener(AdminGuiApp app){
		this.app = app;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Create new Topic");
		
		JTextField topic = new JTextField();
		Object[] message = {
		    "Topic:", topic,
		};

		int option = JOptionPane.showConfirmDialog(null, message, "New Topic", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if( (topic.getText() != null) && (topic.getText().length() > 0)){
					this.app.admin.CreateTopic(topic.getText());
					this.app.updateJMSTree();
			}
		} else {
		    System.out.println("New topic canceled");
		}
	}
}
