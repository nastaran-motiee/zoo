package factory;

import animals.Animal;
import animals.Bear;
import animals.Lion;
import builder.IAnimalDialogBuilder;
import graphics.AddAnimalDialog;
import graphics.ZooPanel;

import javax.swing.*;
import java.awt.*;

import static privateutil.MyStrings.BEAR;
import static privateutil.MyStrings.LION;

/**
 * The class OmnivoreFactory
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 June 5,2022
 */
public class OmnivoreFactory extends AddAnimalDialog implements IAnimalFactory, IAnimalDialogBuilder {

    /**
     * AddAnimalDialog constructor.
     * modal dialog
     *
     * @param observer
     */
    public OmnivoreFactory(ZooPanel observer) {
        super(observer);
        buildChooseAnimalSection();
    }

    @Override
    public void buildChooseAnimalSection() {
        JRadioButton bearButton = new JRadioButton(BEAR);
        bearButton.setMnemonic('B');
        bearButton.setSelected(true);
        bearButton.setBackground(new Color(120, 129, 255));
        bearButton.setActionCommand(BEAR);
        bearButton.addActionListener(this);
        setButtonToChooseAnimalSection(bearButton);

    }

    @Override
    public Animal createAnimal(String selectedAnimal, String selectedColor, int sizeOfAnimal, int horizontalSpeed, int verticalSpeed) {
        Animal animal = null;
        if(selectedAnimal.equals(BEAR)){
            animal = new Bear(sizeOfAnimal, horizontalSpeed, verticalSpeed, selectedColor, this.observer);
        }
        return animal;
    }
}
