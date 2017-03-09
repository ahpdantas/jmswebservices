package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.AdminGuiApp;

public class NewQueueListener implements ActionListener {
	private AdminGuiApp app;
	
	public NewQueueListener(AdminGuiApp app){
		this.app = app;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Create new Queue");
		
		JTextField queue = new JTextField();
		Object[] message = {
		    "Queue:", queue,
		};

		int option = JOptionPane.showConfirmDialog(null, message, "New Queue", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if( (queue.getText() != null) && (queue.getText().length() > 0)){
					this.app.admin.CreateQueue(queue.getText());
					this.app.updateJMSTree();
			}
		} else {
		    System.out.println("New queue canceled");
		}
	}
}
