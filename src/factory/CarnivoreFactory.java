package factory;

import animals.Animal;
import animals.Lion;
import builder.IAnimalDialogBuilder;
import graphics.AddAnimalDialog;
import graphics.ZooPanel;
import javax.swing.*;
import java.awt.*;
import static privateutil.MyStrings.*;


/**
 * The class CarnivoreFactory
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 June 5,2022
 */
public class CarnivoreFactory extends AddAnimalDialog implements IAnimalFactory , IAnimalDialogBuilder {

    /**
     * AddAnimalDialog constructor.
     * modal dialog
     *
     * @param observer
     */
    public CarnivoreFactory(ZooPanel observer) {
        super(observer);
        buildChooseAnimalSection();
    }

    @Override
    public void buildChooseAnimalSection() {
        JRadioButton lionButton = new JRadioButton(LION);
        lionButton.setMnemonic('L');
        lionButton.setSelected(true);
        lionButton.setBackground(new Color(230, 229, 76));
        lionButton.setActionCommand(LION);
        lionButton.addActionListener(this);
        setButtonToChooseAnimalSection(lionButton);

    }

    @Override
    public Animal createAnimal(String selectedAnimal, String selectedColor, int sizeOfAnimal, int horizontalSpeed, int verticalSpeed) {
        Animal animal = null;
        if(selectedAnimal.equals(LION)){
            animal = new Lion(sizeOfAnimal, horizontalSpeed, verticalSpeed, selectedColor, this.observer);
        }
        return animal;
    }
}
