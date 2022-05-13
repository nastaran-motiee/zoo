package builder;

import javax.swing.*;

/**
 * The class IAddAnimalDialogPlan
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 June 5,2022
 */
public interface IAddAnimalDialogPlan {
    public void addChooseAnimalSection();
    public void addChooseSizeSection();
    public void addChooseSpeedSection();
    public void addChooseColorSection();
    public void addAddButtonSection();
    public void setButtonToChooseAnimalSection(JRadioButton button);

}
