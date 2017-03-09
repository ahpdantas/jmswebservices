package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import app.AdminGuiApp;

public class UpdateListener implements ActionListener {
	private AdminGuiApp app;
	
	public UpdateListener(AdminGuiApp app){
		this.app = app;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Atualizando lista de dispositivos");
		this.app.updateJMSTree();
	}

}
