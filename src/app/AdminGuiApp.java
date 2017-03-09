package app;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import interfaces.AdminWebService;
import listeners.DeleteQueueListener;
import listeners.DeleteTopicListener;
import listeners.ExitListener;
import listeners.NewQueueListener;
import listeners.NewTopicListener;
import listeners.UpdateListener;

public class AdminGuiApp extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree tree;
	private DefaultMutableTreeNode top;
	public AdminWebService admin;
	
	
	//Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;	
    
    
    public AdminGuiApp(String adminWebServiceAddress){
    	
    	URL url;
		try {
			if( adminWebServiceAddress != null){
				url = new URL(adminWebServiceAddress+"/admin?wsdl");
			}
			else{
				url = new URL("http://127.0.0.1:9999/admin?wsdl");
			}
	    	QName qname = new QName("http://webservice/","AdminWebServerService");
	    	Service ws = Service.create(url, qname);
	    	admin = ws.getPort(AdminWebService.class);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	   
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//Create the nodes.
		top = new DefaultMutableTreeNode("OpenJMSServer");
		
		//Create a tree that allows one selection at a time.
		tree = new JTree(top);
		
		updateJMSTree();
		
		tree.getSelectionModel().setSelectionMode
		        (TreeSelectionModel.SINGLE_TREE_SELECTION);
		
	
		if (playWithLineStyle) {
		    System.out.println("line style = " + lineStyle);
		    tree.putClientProperty("JTree.lineStyle", lineStyle);
		}
		
		//Create the scroll pane and add the tree to it. 
		JScrollPane treeView = new JScrollPane(tree);
		
		//Add the scroll panes to a split pane.
		        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		        splitPane.setTopComponent(treeView);
		 
		        Dimension minimumSize = new Dimension(300, 300);
		        treeView.setMinimumSize(minimumSize);
		        
		 
		        //Add the split pane to this panel.
		add(splitPane);
		
		JButton update = new JButton("Update");
		update.addActionListener(new UpdateListener(this));

		add(update);
		
		createAndShowGUI();
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
		//menu.getAccessibleContext().setAccessibleDescription(
	//	        "The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Exit",KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Exit application");
		menuItem.addActionListener(new ExitListener());
		menu.add(menuItem);
		
		//Build second menu in the menu bar.
		menu = new JMenu("Actions");
		menu.setMnemonic(KeyEvent.VK_O);
		menu.getAccessibleContext().setAccessibleDescription("Actions menu");
		
		JMenu queueMenu = new JMenu("Queue");

		menuItem = new JMenuItem("Create");
		menuItem.addActionListener(new NewQueueListener(this));
		queueMenu.add(menuItem);
		
		menuItem = new JMenuItem("Remove");
		menuItem.addActionListener(new DeleteQueueListener(this));
		queueMenu.add(menuItem);
		
		menu.add(queueMenu);
		
		JMenu topicMenu = new JMenu("Topic");
		menuItem = new JMenuItem("Create");
		menuItem.addActionListener(new NewTopicListener(this));
		
		topicMenu.add(menuItem);
		
		menuItem = new JMenuItem("Remove");
		menuItem.addActionListener(new DeleteTopicListener(this));
		topicMenu.add(menuItem);
		
		menu.add(topicMenu);
				
		menuBar.add(menu);
		return menuBar;
		
	}

    
	 /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private void createAndShowGUI() {
     	
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        JFrame frame = new JFrame("Adminstrator Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(this);
        frame.setJMenuBar(createJMenuBar());
        
	    frame.setSize(100, 100);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public void updateJMSTree(){
		System.out.println("Atualizando árvore");
		top.removeAllChildren();

		DefaultMutableTreeNode queue = new DefaultMutableTreeNode("Queue");
		DefaultMutableTreeNode topic = new DefaultMutableTreeNode("Topic");

		Iterator iterator = admin.GetAllQueues().iterator();
		try {
			while (iterator.hasNext()) {
				String destination = (String) iterator.next();
				int count = admin.getMessageCount(destination);
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(destination + "(" + count +")");
				queue.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		iterator = admin.GetAllTopics().iterator();
		try {
			while (iterator.hasNext()) {
				String destination = (String) iterator.next();
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(destination);
				topic.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		top.add(queue);
		top.add(topic);

		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		model.reload(top);
 	   
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if( args.length > 0 ){
					AdminGuiApp app = new AdminGuiApp(args[0]);
				} else {
					AdminGuiApp app = new AdminGuiApp(null);
				}
			}
		});
	}
}
