package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.UserGuiApp;

public class NewUserListener implements ActionListener {
	private UserGuiApp app;
		
	public NewUserListener(UserGuiApp app){
		this.app = app;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Create new User");
		
		if( app.user != null ){
			JOptionPane.showMessageDialog(null, "There is a user interface oppened!!!");
			return;
		}
		
		JTextField user = new JTextField();
		Object[] message = {
		    "User:", user,
		};

		int option = JOptionPane.showConfirmDialog(null, message, "New User", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if( (user.getText() != null) && (user.getText().length() > 0)){
					this.app.userManager.CreateNewUser(user.getText());
					this.app.createUserInterface(user.getText());
			}
		} else {
		    System.out.println("New user canceled");
		}
	}
}
