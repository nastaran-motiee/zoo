package factory;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;
import builder.IAnimalDialogBuilder;
import graphics.AddAnimalDialog;
import graphics.ZooPanel;

import javax.swing.*;
import java.awt.*;

import static privateutil.MyStrings.*;
import static privateutil.MyStrings.TURTLE;

/**
 * The class HerbivoreFactory
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 June 5,2022
 */
public class HerbivoreFactory extends AddAnimalDialog implements IAnimalFactory, IAnimalDialogBuilder {

    /**
     * AddAnimalDialog constructor.
     * modal dialog
     *
     * @param observer
     */
    public HerbivoreFactory(ZooPanel observer) {
        super(observer);
        buildChooseAnimalSection();
    }



    @Override
    public void buildChooseAnimalSection() {
        JRadioButton elephantButton;
        elephantButton = new JRadioButton(ELEPHANT);
        elephantButton.setMnemonic('E');
        elephantButton.setSelected(true);
        elephantButton.setBackground(new Color(97, 250, 174));
        elephantButton.setActionCommand(ELEPHANT);
        elephantButton.addActionListener(this);
        setButtonToChooseAnimalSection(elephantButton);

        JRadioButton giraffeButton;
        giraffeButton = new JRadioButton(GIRAFFE);
        giraffeButton.setMnemonic('G');
        giraffeButton.setSelected(true);
        giraffeButton.setBackground(new Color(250, 170, 90));
        giraffeButton.setActionCommand(GIRAFFE);
        giraffeButton.addActionListener(this);
        setButtonToChooseAnimalSection(giraffeButton);


        JRadioButton turtleButton;
        turtleButton = new JRadioButton(TURTLE);
        turtleButton.setMnemonic('T');
        turtleButton.setSelected(true);
        turtleButton.setBackground(new Color(230, 103, 182));
        turtleButton.setActionCommand(TURTLE);
        turtleButton.addActionListener(this);
        setButtonToChooseAnimalSection(turtleButton);
    }


    @Override
    public Animal createAnimal(String selectedAnimal, String selectedColor, int sizeOfAnimal, int horizontalSpeed, int verticalSpeed) {
        Animal animal = null;
        switch(selectedAnimal){
            case ELEPHANT -> animal =  new Elephant(sizeOfAnimal, horizontalSpeed, verticalSpeed, selectedColor, this.observer);
            case GIRAFFE -> animal = new Giraffe(sizeOfAnimal, horizontalSpeed, verticalSpeed, selectedColor, this.observer);
            case TURTLE -> animal = new Turtle(sizeOfAnimal, horizontalSpeed, verticalSpeed, selectedColor, this.observer);
        }

        return animal;


    }
}
