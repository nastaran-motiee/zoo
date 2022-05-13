package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
import privateutil.Replicable;


import static food.EFoodType.MEAT;
import static privateutil.MyStrings.CANEAT;

/**
 * The class Omnivore defines omnivores
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 March 27,22
 */
public class Omnivore implements IDiet, Replicable {
    private final Carnivore carnivore = new Carnivore(); //Omnivore can eat MEAT so Omnivore is also a Carnivore
    private final Herbivore herbivore = new Herbivore(); //Omnivore can eat VEGETABLE so Omnivore is also a Herbivore

    //Constructors-----------------------------------------------------------------------------------------------------------------------------------------------
    //Getters--------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Setters---------------------------------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param food_type food type
     * @return true if the omnivore can eat the food (if the food type is MEAT or VEGETABLE, else return false
     */
    @Override
    public boolean canEat(EFoodType food_type) {
        return ( carnivore.canEat(food_type) || herbivore.canEat(food_type) );
    }

    /**
     * if food type is MEAT add 10 percent to the weight of animal , else add 7 percent to the weight of animal
     * @param animal an animal
     * @param food   edible object
     * @return the weight the animal has gained
     */
    @Override
    public double eat(Animal animal, IEdible food) {
            if ((animal.getDiet() instanceof Omnivore) && canEat(food.getFoodtype())) {
                if (food.getFoodtype() == MEAT) {
                    return carnivore.eat(animal, food);
                }
                return herbivore.eat(animal, food);
            }
            return 0;
    }

    /**
     *replicate
     * @return a copy of this object
     */
    @Override
    public Omnivore replicate() {
        return new Omnivore();
    }

    /**
     * @return Omnivore
     */
    @Override
    public String toString() {
        return "Omnivore";
    }
}
