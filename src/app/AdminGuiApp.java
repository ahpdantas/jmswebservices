package app;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class AdminGuiApp extends JPanel implements TreeSelectionListener, TreeModelListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree tree;
	private DefaultMutableTreeNode top;
	
	//Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;	
    
    
    public AdminGuiApp(){
    	   
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		
		//Create the nodes.
		top = new DefaultMutableTreeNode("OpenJMSServer");
		
		//Create a tree that allows one selection at a time.
		tree = new JTree(top);
		
		tree.getSelectionModel().setSelectionMode
		        (TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		//Listen for when the selection changes.
		tree.addTreeSelectionListener(this);
		tree.addMouseListener(this);
		
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
		
		JButton refresh = new JButton("Update");
		//refresh.addActionListener(new RefreshListener(this));
		add(refresh);
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
		
		JMenu queueMenu = new JMenu("Queue");

		menuItem = new JMenuItem("Create");
		queueMenu.add(menuItem);
		
		menuItem = new JMenuItem("Remove");
		queueMenu.add(menuItem);
		
		menu.add(queueMenu);
		
		JMenu topicMenu = new JMenu("Topic");
		
		menuItem = new JMenuItem("Create");
		topicMenu.add(menuItem);
		
		menuItem = new JMenuItem("Remove");
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
    private static void createAndShowGUI() {
    	AdminGuiApp app = new AdminGuiApp();
    	
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
        frame.add(app);
        frame.setJMenuBar(createJMenuBar());
        
	    frame.setSize(100, 100);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesChanged(TreeModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesInserted(TreeModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeStructureChanged(TreeModelEvent arg0) {
		// TODO Auto-generated method stub
		
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
