package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ExitListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int reply = JOptionPane.showConfirmDialog(null, "Do you really want to close the Aplication?", "Exit Aplication", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	System.exit(0);
        }
	}
}
