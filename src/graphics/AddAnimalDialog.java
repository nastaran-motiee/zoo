package graphics;


import animals.*;
import builder.IAddAnimalDialogPlan;
import controller.Controller;
import factory.CarnivoreFactory;
import factory.HerbivoreFactory;
import factory.IAnimalFactory;
import factory.OmnivoreFactory;
import privateutil.AnimalModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static privateutil.MyStrings.*;

/**
 * The type Add animal dialog.
 * makes a "conversion" with ZooFrame
 *
 * @author Nastaran Motiee - 329022727
 * @version 1.0 April 20,22
 * @campus Ashdod
 */
public  class AddAnimalDialog extends JDialog implements ActionListener, IAddAnimalDialogPlan,IAnimalFactory {

    BufferedImage img1 = null;
    BufferedImage img2 = null;
    //animal radio buttons
    private ArrayList<JRadioButton> radioButtonArray = new ArrayList<JRadioButton>();

    protected ButtonGroup group1;
    //sliders
    protected JSlider sizeSlider;
    protected JSlider sliderX;
    protected JSlider sliderY;
    //color radio buttons
    private JRadioButton blueButton;
    private JRadioButton redButton;
    private JRadioButton naturalButton;
    protected ButtonGroup group2;
    protected JButton addButton;
    protected final ZooPanel observer;
    private static volatile AddAnimalDialog instance = null;




    /**
     * AddAnimalDialog constructor.
     * modal dialog
     */

    public AddAnimalDialog(ZooPanel observer) {
        super(ZooFrame.getInstance(),true);
        this.observer = observer;
        SwingUtilities.invokeLater(() -> {
            GridLayout layout = new GridLayout(11, 1);
            setLayout(layout);
            addChooseAnimalSection();
            addChooseSizeSection();
            addChooseSpeedSection();
            addChooseColorSection();
            addAddButtonSection();
        });


    }

    /**
     * get instance of AddAnimalDialog _specific option
     * @param typeOption 0 : Carnivore , 1 : Herbivore, 2 : Omnivore
     * @param observer
     * @return specific instance
     */
    public static AddAnimalDialog getInstance(int typeOption, ZooPanel observer){
        switch (typeOption){
            case 0-> instance = new CarnivoreFactory(observer);
            case 1-> instance = new HerbivoreFactory(observer);
            case 2-> instance = new OmnivoreFactory(observer);

        }
        return instance;

    }





    /**
     * add label to choose animal section
     */
    public void setChooseAnimalLabel(){
        //create the label for this section
        JLabel label1 = new JLabel("Choose an animal: ");
        label1.setFont(new Font("", Font.PLAIN, 20));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setOpaque(true);
        this.add(label1);
    }



    public void setButtonToChooseAnimalSection(JRadioButton button){
        radioButtonArray.add(button);
    }

    /**
     * addChooseAnimalSection method creates components for choosing animal and add them to the dialog
     */
    public void addChooseAnimalSection() {
        setChooseAnimalLabel();

        //Group the radio buttons.
        group1 = new ButtonGroup();
        for(JRadioButton button:radioButtonArray){
            group1.add(button);
        }

        JPanel panel1 = new JPanel();

        GridLayout layout = new GridLayout(1, 5);
        layout.setHgap(5);
        panel1.setLayout(layout);
        for(JRadioButton button:radioButtonArray){
            panel1.add(button);
        }

        this.add(panel1);
    }



    /**
     * addChooseSizeSection method creates components for choosing the size of animal in pixels
     */
    public void addChooseSizeSection() {
        JLabel label2 = new JLabel("Choose the size of the animal: ");
        label2.setFont(new Font("", Font.PLAIN, 20));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setOpaque(true);
        this.add(label2);

        sizeSlider = new JSlider(JSlider.HORIZONTAL, 50, 300, 50);
        sizeSlider.setMinorTickSpacing(1);
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);

        this.add(sizeSlider);

    }


    /**
     * addChooseSpeedSection method create components for choosing the vertical and the horizontal speed of animal
     */
    public void addChooseSpeedSection() {
        GridLayout layout = new GridLayout(1, 2);
        layout.setHgap(20);

        JLabel label1 = new JLabel("Choose the horizontal speed of the animal: ");
        label1.setFont(new Font("", Font.PLAIN, 20));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setOpaque(true);

        JLabel label2 = new JLabel("Choose the vertical speed of the animal: ");
        label2.setFont(new Font("", Font.PLAIN, 20));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setOpaque(true);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(layout);
        titlePanel.add(label1);
        titlePanel.add(label2);

        this.add(titlePanel);


        sliderX = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        sliderX.setMinorTickSpacing(1);
        sliderX.setMajorTickSpacing(1);
        sliderX.setPaintTicks(true);
        sliderX.setPaintLabels(true);

        sliderY = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        sliderY.setMinorTickSpacing(1);
        sliderY.setMajorTickSpacing(1);
        sliderY.setPaintTicks(true);
        sliderY.setPaintLabels(true);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(layout);
        sliderPanel.add(sliderX);
        sliderPanel.add(sliderY);

        this.add(sliderPanel);
    }

    public void addChooseColorSection() {

        //create the label for this section
        JLabel label = new JLabel("Choose the color of the animal: ");
        label.setFont(new Font("", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        this.add(label);

        //Create the radio buttons.
        blueButton = new JRadioButton("Blue");
        blueButton.setSelected(true);
        blueButton.setBackground(new Color(91, 123, 245));
        blueButton.setActionCommand(BLUE);

        redButton = new JRadioButton("Red");
        redButton.setSelected(true);
        redButton.setBackground(new Color(250, 50, 66));
        redButton.setActionCommand(RED);

        naturalButton = new JRadioButton("Natural");
        naturalButton.setSelected(true);
        naturalButton.setBackground(new Color(250, 202, 193));
        naturalButton.setActionCommand(NATURAL);

        //Group the radio buttons.
        group2 = new ButtonGroup();
        group2.add(redButton);
        group2.add(blueButton);
        group2.add(naturalButton);

        JPanel panel = new JPanel();

        GridLayout layout = new GridLayout(1, 5);
        layout.setHgap(5);

        panel.setLayout(layout);

        panel.add(redButton);
        panel.add(blueButton);
        panel.add(naturalButton);

        this.add(panel);

    }

    public void addAddButtonSection() {
        this.add(new JPanel());//add empty panel just for the decoration
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(new JPanel());//add empty panel just for the decoration

        //add a button in the middle
        addButton = new JButton("ADD ANIMAL TO THE ZOO");
        addButton.setBackground(new Color(62, 219, 119));
        addButton.setBorder(BorderFactory.createLineBorder(new Color(73, 97, 82), 1));
        addButton.addActionListener(this);


        panel.add(addButton);
        panel.add(new JPanel());//add empty panel just for the decoration

        this.add(panel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // if add button was clicked , the add the animal to zoo panel
        if (e.getSource() == addButton) {
            String selectedAnimal = group1.getSelection().getActionCommand();
            String selectedColor = group2.getSelection().getActionCommand();

            int sizeOfAnimal = sizeSlider.getValue();
            int horizontalSpeed = sliderX.getValue();
            int verticalSpeed = sliderY.getValue();

            IAnimalFactory factory = this;
            Animal animal = factory.createAnimal(selectedAnimal,selectedColor,sizeOfAnimal,horizontalSpeed,verticalSpeed);
            animal.loadImages(selectedAnimal);
            Controller.getInstance().addToDataBase(animal);
            Controller.getInstance().newAnimalEntered(animal);

            synchronized (observer){
                observer.notify();
            }

            observer.repaint();
            this.dispose();


        }
    }

    @Override
    public Animal createAnimal(String selectedAnimal, String selectedColor, int sizeOfAnimal, int horizontalSpeed, int verticalSpeed) {
        return null;
    }
}

