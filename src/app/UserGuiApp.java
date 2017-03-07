package app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class UserGuiApp extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree tree;
	private DefaultMutableTreeNode top;
	
	private JTextArea messageLog;
	private JTextArea message;
	private JTextArea news;
	private JTextField destination;
	
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;	
    
    
    public UserGuiApp(){
    	   
		this.setLayout(new BorderLayout());
		
	    messageLog = new JTextArea(15,20);
	    messageLog.setBorder(BorderFactory.createEtchedBorder(Color.BLUE,Color.BLUE));
	    messageLog.setEditable(false);
	    
	    destination = new JTextField(10);
	    destination.setBorder(BorderFactory.createEtchedBorder(Color.BLUE,Color.BLUE));
	    	    
	    message = new JTextArea(10,20);
	    message.setBorder(BorderFactory.createEtchedBorder(Color.BLUE,Color.BLUE));
	    //message.addKeyListener(this);
	    	    
	    JLabel lblMsgLog     = new JLabel("Message Log");
	    JLabel lblMsg        = new JLabel("Mensage");
	    JLabel lblSend        = new JLabel("Publish/Send to:");
	    
	    JButton btnSend = new JButton("Send");
	    btnSend.setToolTipText("Send Message");
	    
	    JButton btnRefresh = new JButton("Refresh");
	    btnSend.setToolTipText("Refresh Messages");
 
	    JScrollPane scroll = new JScrollPane(messageLog);
	    messageLog.setLineWrap(true);  
		
	    JPanel ChatPanel = new JPanel();
	    ChatPanel.setLayout(new BoxLayout(ChatPanel,BoxLayout.Y_AXIS ));
	    
	    ChatPanel.add(lblMsgLog);
	    ChatPanel.add(scroll);
	    ChatPanel.add(btnRefresh, BorderLayout.WEST);
	    ChatPanel.add(lblSend);
	    ChatPanel.add(destination);
		ChatPanel.add(lblMsg);
		ChatPanel.add(message);
		ChatPanel.add(btnSend, BorderLayout.WEST);
		
		this.add(ChatPanel, BorderLayout.WEST);
		
		JLabel lblNews     = new JLabel("News:");
		
		news = new JTextArea(8,20);
		news.setBorder(BorderFactory.createEtchedBorder(Color.BLUE,Color.BLUE));
		 
		JPanel NewsPanel = new JPanel();
		NewsPanel.setLayout(new BoxLayout(NewsPanel,BoxLayout.Y_AXIS ));
				 
		NewsPanel.add(lblNews);
		NewsPanel.add(news);
		 
		this.add(NewsPanel, BorderLayout.EAST);

    }
	
    private static JMenuBar createJMenuBar(){
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_G);
		//menu.getAccessibleContext().setAccessibleDescription(
	//	        "The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Exit",KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Exit application");
		menu.add(menuItem);
		
		//Build second menu in the menu bar.
		menu = new JMenu("Actions");
		menu.setMnemonic(KeyEvent.VK_O);
		menu.getAccessibleContext().setAccessibleDescription("Actions menu");
		
		JMenu userMenu = new JMenu("User");

		menuItem = new JMenuItem("New");
		userMenu.add(menuItem);
		
		menuItem = new JMenuItem("Configure");
		userMenu.add(menuItem);
		
		menu.add(userMenu);
				
		menuBar.add(menu);
		return menuBar;
		
		
	}

    
	 /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
    	UserGuiApp app = new UserGuiApp();
    	
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        JFrame frame = new JFrame("User Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(app);
        frame.setJMenuBar(createJMenuBar());
        
	    frame.setSize(100, 100);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub	        
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGUI();
        }
    });

	}

}
