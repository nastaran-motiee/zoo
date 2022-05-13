package graphics;
import controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import static privateutil.MyStrings.*;

/**
 * The type Zoo frame.
 * contains the main method
 *
 * @author Nastaran Motiee - 329022727
 * @version 1.0 April 20,22
 * @campus Ashdod
 */
public class ZooFrame extends JFrame implements ActionListener{
    private final ZooPanel zooPanel = ZooPanel.getInstance();

    protected final static Toolkit tk = Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
    protected final static Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.

    private static volatile ZooFrame instance = null;

    /**
     * TODO:CHANGED
     * get instance of ZooFrame
     * @return instance of ZooFrame
     */
    public static ZooFrame getInstance(){
        if(instance == null){
            synchronized (ZooFrame.class){
                if(instance==null){
                    instance = new ZooFrame();
                }
            }
        }
        return instance;
    }

    /**
     * ZooFrame constructor
     */
    private ZooFrame() {
        super("Zoo"); //set title
        SwingUtilities.invokeLater(() -> {
            setUp();
            add(zooPanel);
            pack();
        });

    }

    /**
     * main method
     * sets up zooFrame and makes it visible
     *
     * @param args
     */
    public static void main(String[] args) {
        ZooFrame zooFrame = ZooFrame.getInstance(); //create zooFrame
        zooFrame.setVisible(true);

    }

    /**
     * setUp for ZooFrame view
     */
    private void setUp() {
        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(800, 600));//Set the maximum width and height of zoo frame
        this.setMinimumSize(new Dimension(800, 600));//Set the minimum width and height of zoo frame
        this.setSize(new Dimension(800,600));
        this.setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);//center the zoo frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addCustomMenuBar();

    }

//GUI Building Methods--------------------------------------------------------------------------------------------------------------------------------------

    /**
     * add Custom MenuBar method creates a menu bar for zoo frame
     * adds menus to the menu bar
     * adds item menus to each menu
     */
    private void addCustomMenuBar() {
        //create file menu
        JMenu fileMenu = new JMenu(FILE);//create file menu

        //add items to file menu + add action listener for each item
        JMenuItem exitItem = new JMenuItem(EXIT);
        exitItem.addActionListener(e -> System.exit(0));//add action listener to exit item
        fileMenu.add(exitItem);// add exit item to file menu

        //create background menu
        JMenu backGroundMenu = new JMenu(BACKGROUND);

        //add items to background menu
        JMenuItem imageItem = new JMenuItem(IMAGE);
        imageItem.setMnemonic('I');
        imageItem.addActionListener(this);
        backGroundMenu.add(imageItem);

        JMenuItem greenItem = new JMenuItem(GREEN);
        greenItem.addActionListener(this);
        backGroundMenu.add(greenItem);

        JMenuItem noneItem = new JMenuItem(NONE);
        noneItem.addActionListener(this);
        backGroundMenu.add(noneItem);

        //create help menu
        JMenu helpMenu = new JMenu(HELP);

        //add items to help menu
        JMenuItem helpItem = new JMenuItem(HELP);
        helpItem.addActionListener(this);
        helpMenu.add(helpItem);

        //create the menu bar and add all the menus to it
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar); //add menu bar to zoo frame
        menuBar.add(fileMenu);
        menuBar.add(backGroundMenu);
        menuBar.add(helpMenu);
    }



    /**
     * perform action depending on menu item that was clicked.
     * @param e Actions on MenuItems and Buttons on zoo frame
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case NONE -> {
                zooPanel.setBackgroundImage(null);
                zooPanel.setBackground(null);
            }

            case GREEN -> {
                zooPanel.setBackgroundImage(null);
                zooPanel.setBackground(new Color(0x239354));//change the background color of zoo frame to green
            }

            case IMAGE -> zooPanel.setBackgroundImage(new ImageIcon("src/assignment2_pictures/savanna.jpg"));

            case HELP -> JOptionPane.showMessageDialog(getContentPane(), "Home work 2 GUI");

            case EXIT -> {
                Controller.getInstance().finish();
            }

        }

        zooPanel.repaint();
    }
}
