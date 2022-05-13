package graphics;
import animals.Animal;
import controller.Controller;
import food.IEdible;
import plants.Plant;
import privateutil.AnimalModel;
import privateutil.Meat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static privateutil.MyStrings.*;

/**
 * The type Zoo panel.
 * private manageZoo method in this class represents the Controller
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 April 20,22
 */
public class ZooPanel extends JPanel implements ActionListener {
    Controller controller;

    private ArrayList<Animal> animalsArray = new ArrayList<Animal>(); //represents all of animals at the zoo (Data Base)
    private ArrayList<IEdible> foodArray = new ArrayList<IEdible>();
    private ImageIcon backgroundImage = null;
    private static volatile ZooPanel instance = null;

    /**
     * get instance of ZooPanel
     *
     * @return instance of ZooPanel
     */
    public static ZooPanel getInstance() {
        if (instance == null) {
            synchronized (ZooPanel.class) {
                if (instance == null) {
                    instance = new ZooPanel();
                }
            }
        }
        return instance;
    }

    /**
     * zooPanel Constructor
     */
    public ZooPanel() {
        SwingUtilities.invokeLater(this::setUp);
        this.controller = Controller.getInstance();
        foodArray = this.controller.getFoodArray();
        animalsArray = this.controller.getAnimalsArray();


    }


    /**
     * set background image
     *
     * @param backgroundImage background image
     * @return true if the background image is not null, else return false
     */
    public void setBackgroundImage(ImageIcon backgroundImage) {
        System.out.println("set Background image");
        this.backgroundImage = backgroundImage;

    }





    public void setUp() {
        this.setLayout(new BorderLayout());
        setSize(new Dimension(800, 600));
        this.addCustomButtonPanel();

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    /**
     * createButtonPanel create a panel including buttons in it
     * adds the panel to the south of zoo frame
     */
    private void addCustomButtonPanel() {
        //layout for the panel
        GridLayout layout = new GridLayout(1, 8);

        //create panel
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setBackground(Color.GRAY);

        //add buttons to the panel
        JButton addAnimalButton = new JButton(ADD_ANIMAL);
        addAnimalButton.setBackground(new Color(131, 141, 242));
        addAnimalButton.addActionListener(this);

        JButton changeColorButton = new JButton(CHANGE_COLOR);
        changeColorButton.setBackground(new Color(239, 93, 93));
        changeColorButton.addActionListener(this);

        JButton sleepButton = new JButton(SLEEP);
        sleepButton.setBackground(new Color(235, 89, 218));
        sleepButton.addActionListener(this);

        JButton wakeUpButton = new JButton(WAKE_UP);
        wakeUpButton.setBackground(new Color(245, 153, 84));
        wakeUpButton.addActionListener(this);

        JButton clearButton = new JButton(CLEAR);
        clearButton.setBackground(new Color(188, 242, 131));
        clearButton.addActionListener(this);

        JButton foodButton = new JButton(FOOD);
        foodButton.setBackground(new Color(235, 202, 120));
        foodButton.addActionListener(this);

        JButton infoButton = new JButton(INFO);
        infoButton.setBackground(new Color(129, 219, 199));
        infoButton.addActionListener(this);

        JButton exitButton = new JButton(EXIT);
        exitButton.setBackground(new Color(235, 120, 120));
        exitButton.addActionListener(this);
        panel.add(addAnimalButton);
        panel.add(changeColorButton);
        panel.add(sleepButton);
        panel.add(wakeUpButton);
        panel.add(clearButton);
        panel.add(foodButton);
        panel.add(infoButton);
        panel.add(exitButton);

        this.add(panel, BorderLayout.SOUTH);//add panel to the bottom of zoo frame
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            backgroundImage.paintIcon(this, g, 0, 0);//display background

        }

        synchronized (animalsArray) {
            for (Animal animal : animalsArray) {
                animal.drawObject(g);

            }


        }

        synchronized (foodArray) {
            for (Object food : foodArray) {
                if (food instanceof Plant) {
                    ((Plant) food).drawObject(g);
                } else if (food instanceof Meat) {
                    ((Meat) food).drawObject(g);
                }
            }
        }
    }

    /**
     * perform action depending on button that was clicked.
     *
     * @param e Actions on MenuItems and Buttons on zoo frame
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.notify(e.getActionCommand());

    }
}