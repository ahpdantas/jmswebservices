package app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import interfaces.UserWebService;
import listeners.ExitListener;
import listeners.GetMessagesListener;
import listeners.NewUserListener;
import listeners.SendMessageListener;
import listeners.SubscribeListener;
import listeners.SubscribeTopicListener;

public class UserGuiApp extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea messageLog;
	private JTextArea message;
	private JTextArea news;
	private JTextField destination;
	private JComboBox<String> option;

	public String user = null;
	public UserWebService userManager;
	public SubscribeListener subsList;
    
    public UserGuiApp(String userWebServiceAddress){
    	
    	URL url;
		try {
			if( userWebServiceAddress != null){
				url = new URL(userWebServiceAddress+"/user?wsdl");
			}
			else{
				url = new URL("http://127.0.0.1:9998/user?wsdl");
			}
	    	QName qname = new QName("http://webservice/","UserWebServerService");
	    	Service ws = Service.create(url, qname);
	    	userManager = ws.getPort(UserWebService.class);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createAndShowGUI();

    }
	
    public void createUserInterface(String user){
    	
    	this.user = user;
    	
        this.setTitle(user + " Application");
    	
    	this.setLayout(new BorderLayout());
		
	    messageLog = new JTextArea(15,20);
	    messageLog.setBorder(BorderFactory.createEtchedBorder(Color.BLUE,Color.BLUE));
	    messageLog.setEditable(false);
	    
	    destination = new JTextField(10);
	    destination.setBorder(BorderFactory.createEtchedBorder(Color.BLUE,Color.BLUE));
	    	    
	    message = new JTextArea(10,20);
	    message.setBorder(BorderFactory.createEtchedBorder(Color.BLUE,Color.BLUE));
	    	    
	    JLabel lblMsgLog     = new JLabel("Message Log");
	    JLabel lblMsg        = new JLabel("Message");
	    JLabel lblSend        = new JLabel("Publish/Send to:");
	    JLabel lblName        = new JLabel("Name:");
	    
	    JButton btnSend = new JButton("Send");
	    btnSend.setToolTipText("Send Message");
	    btnSend.addActionListener(new SendMessageListener(this));
	    
	    JButton btnRefresh = new JButton("Refresh");
	    btnRefresh.addActionListener(new GetMessagesListener(this));
	    btnSend.setToolTipText("Refresh Messages");
 
	    JScrollPane scroll = new JScrollPane(messageLog);
	    messageLog.setLineWrap(true);  
		
	    JPanel ChatPanel = new JPanel();
	    ChatPanel.setLayout(new BoxLayout(ChatPanel,BoxLayout.Y_AXIS ));
	    
	    ChatPanel.add(lblMsgLog);
	    ChatPanel.add(scroll);
	    ChatPanel.add(btnRefresh, BorderLayout.WEST);
	    ChatPanel.add(lblSend);
	    
	    
	    String[] possibilities = {"user", "topic"};
	    option = new JComboBox<String>(possibilities);
	    
	    ChatPanel.add(option);
	     
	    ChatPanel.add(lblName);
	    ChatPanel.add(destination);
		ChatPanel.add(lblMsg);
		ChatPanel.add(message);
		ChatPanel.add(btnSend, BorderLayout.WEST);
		
		this.add(ChatPanel, BorderLayout.WEST);
	
		this.pack();
	
    }
    
    private JMenuBar createJMenuBar(){
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_G);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(new NewUserListener(this));
		menu.add(menuItem);

		//a group of JMenuItems
		menuItem = new JMenuItem("Exit",KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Exit application");
		menuItem.addActionListener(new ExitListener());
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		//Build second menu in the menu bar.
		menu = new JMenu("Topic");
		menu.setMnemonic(KeyEvent.VK_T);
		menu.getAccessibleContext().setAccessibleDescription("Topic menu");
		
		menuItem = new JMenuItem("Subscribe",KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Subscribe Topic");
		menuItem.addActionListener(new SubscribeTopicListener(this));
		menu.add(menuItem);
					
		menuBar.add(menu);
		
		return menuBar;
		
	}

    
	 /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private void createAndShowGUI() {
        
      //Add content to the window.
        this.setJMenuBar(createJMenuBar());
      //Default configurations    
	    this.setSize(400, 550);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	  //Display the window.
	    this.setVisible(true);

    }
    
    public void SendMessage(){
    	try{
    		if( option.getSelectedItem().equals("user")){
    			System.out.println("Send to user");
    			this.userManager.SendTo(destination.getText(), this.user + " said: "+ message.getText() );
    		} else {
    			System.out.println("Send to topic");
    			this.userManager.Publish(destination.getText(),this.user + " commented: "+ message.getText() );
    		}
    		
    		this.message.setText("");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void GetMessages(){
    	String messages = this.userManager.Receive(this.user);
    	while( messages != null ){
    		this.messageLog.append( messages + "\r\n");
    		messages = this.userManager.Receive(this.user);
    	}
    }

    public void updateNews(String news){
    	this.news.append(news);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub	        
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        	if( args.length > 0 ){
        		UserGuiApp app = new UserGuiApp(args[0]);
        	} else {
        		UserGuiApp app = new UserGuiApp(null);
        	}
        }
    });

	}

}
