package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.UserGuiApp;

public class SendMessageListener implements ActionListener {
	private UserGuiApp app;
		
	public SendMessageListener(UserGuiApp app){
		this.app = app;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		app.SendMessage();
	}
}
