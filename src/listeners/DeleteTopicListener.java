package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.AdminGuiApp;

public class DeleteTopicListener implements ActionListener {
	private AdminGuiApp app;
	
	public DeleteTopicListener(AdminGuiApp app){
		this.app = app;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Remove topic");
		
		JTextField topic = new JTextField();
		Object[] message = {
		    "Topic:", topic,
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Delete Topic", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if( (topic.getText() != null) && (topic.getText().length() > 0)){
					this.app.admin.RemoveTopic(topic.getText());
					this.app.updateJMSTree();
			}
		} else {
		    System.out.println("Remove topic canceled");
		}
	}
}
