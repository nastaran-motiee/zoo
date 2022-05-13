package decoretor;


import animals.Animal;
import graphics.AddAnimalDialog;
import graphics.ZooFrame;
import graphics.ZooPanel;
import memento.CareTaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static privateutil.MyStrings.*;

public class ColorDecorator extends JDialog implements ActionListener,IColorDecoretor {
    private ZooPanel observer;
    private JComboBox<String> comboBox ;
    private ButtonGroup group2;
    private JButton changeColorButton;
    private JButton  previousColorButton;

    private final ArrayList<Animal> animalsArray;


    /**
     * MoveAnimal constructor
     *
     * @param observer
     * @param animalsArray
     */
    public ColorDecorator(ZooPanel observer, ArrayList<Animal> animalsArray) {
        super(ZooFrame.getInstance(), true);
        this.animalsArray = animalsArray;
        this.observer = observer;
        SwingUtilities.invokeLater(() -> {
            GridLayout layout = new GridLayout(6,1);
            this.setLayout(layout);

            comboBox= new JComboBox<>();
            addCustomComboBox();
            addChooseColorSection();
            addPreviouseAndChangeButtonSection();


        });

    }

    /**
     * create a custom CompoBox
     */
    private void addCustomComboBox(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));


        JLabel label= new JLabel("Choose an animal: ");
        label.setFont(new Font("", Font.PLAIN,20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        panel.add(label);

        ArrayList<String> dataBaseStrings = new ArrayList<String>();
        synchronized (animalsArray){
            for (Animal animal : animalsArray) {
                dataBaseStrings.add(animal.toString());
            }
        }

        // add a string for each animal in the database

        int i = 0;

        for (String animalString : dataBaseStrings) {
            comboBox.addItem(animalString + " " + i);
            i++;
        }

        panel.add(comboBox);
        this.add(panel);

    }
    public void addChooseColorSection() {

        //create the label for this section
        JLabel label = new JLabel("Choose new color for animal: ");
        label.setFont(new Font("", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        this.add(label);

        //Create the radio buttons.
        JRadioButton blueButton = new JRadioButton("Blue");
        blueButton.setSelected(true);
        blueButton.setBackground(new Color(91, 123, 245));
        blueButton.setActionCommand(BLUE);

        JRadioButton redButton = new JRadioButton("Red");
        redButton.setSelected(true);
        redButton.setBackground(new Color(250, 50, 66));
        redButton.setActionCommand(RED);

        JRadioButton naturalButton = new JRadioButton("Natural");
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
    public void addPreviouseAndChangeButtonSection() {
        this.add(new JPanel());//add empty panel just for the decoration
        JPanel panel = new JPanel(new GridLayout(1, 4,20,5));
        panel.add(new JPanel());//add empty panel just for the decoration

        previousColorButton = new JButton("PREVIOUS COLOR");
        previousColorButton.setBackground(new Color(219, 182, 62));
        previousColorButton.setBorder(BorderFactory.createLineBorder(new Color(73, 97, 82), 1));
        previousColorButton.addActionListener(this);
        panel.add(previousColorButton);


        //add a button in the middle
        changeColorButton = new JButton("CHANGE COLOR");
        changeColorButton.setBackground(new Color(62, 219, 119));
        changeColorButton.setBorder(BorderFactory.createLineBorder(new Color(73, 97, 82), 1));
        changeColorButton.addActionListener(this);
        panel.add(changeColorButton);



        panel.add(new JPanel());//add empty panel just for the decoration

        this.add(panel);

    }





    @Override
    public void changeColor(int selectedAnimalIndex, String color) {
        synchronized (animalsArray){
            animalsArray.get(selectedAnimalIndex).saveColorHistory();
            animalsArray.get(selectedAnimalIndex).setColor(color);
            animalsArray.get(selectedAnimalIndex).loadImages(animalsArray.get(selectedAnimalIndex).toString());
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedAnimalIndex = comboBox.getSelectedIndex();;
        String selectedColor = group2.getSelection().getActionCommand();
        if(selectedAnimalIndex == -1){
            JOptionPane.showMessageDialog(getContentPane(), "Please select an animal");
        }
        if(selectedAnimalIndex != -1){
            if(e.getSource() == changeColorButton){
                changeColor(selectedAnimalIndex,selectedColor);
            }
            if(e.getSource() == previousColorButton){

                animalsArray.get(selectedAnimalIndex).backToPreviousColor();
            }
            for (Animal animal:animalsArray){
                animal.setResumed();
            }
            this.dispose();
        }

    }

}
