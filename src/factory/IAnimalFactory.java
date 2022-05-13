package factory;

import animals.Animal;

/**
 * The class IAnimalFactory
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 June 5,2022
 */
public interface IAnimalFactory {

    /**
     * create Animal
     * @param selectedAnimal
     * @param selectedColor
     * @param sizeOfAnimal
     * @param horizontalSpeed
     * @param verticalSpeed
     * @return
     */
    public Animal createAnimal(String selectedAnimal,String selectedColor, int sizeOfAnimal, int horizontalSpeed, int verticalSpeed);


}
