package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
import privateutil.Replicable;

/**
 * The interface IDiet describes the functionality of eating.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 March 27,22
 */
public interface IDiet extends Replicable {
    /**
     * canEat
     * @param food food type
     * @return true if the animal can eat the food, else return false
     */
    boolean canEat(EFoodType food);

    /**
     * eat
     * @param animal an animal
     * @param food  edible object
     * @return double
     */
    double eat(Animal animal, IEdible food);
}
