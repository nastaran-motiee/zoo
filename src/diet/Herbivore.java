package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
import privateutil.Replicable;
import static food.EFoodType.VEGETABLE;
import static privateutil.MyStrings.CANEAT;

/**
 * The class Herbivore defines herbivores.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 March 27,22
 */
public class Herbivore implements IDiet, Replicable {
    //Constructors-----------------------------------------------------------------------------------------------------------------------------------------------
    //Getters--------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Setters---------------------------------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * checks the type of food
     * @param food_type food type
     * @return true if the type food is VEGETABLE, else return false
     */
    @Override
    public boolean canEat(EFoodType food_type) {
        return food_type == VEGETABLE;
    }

    /**
     * the method checks if animal can eat the edible first. if yes, adds 7% to the weight of animal.
     * Eating causes a 7 percent increase in current weight.
     * @param animal an animal
     * @param edible  an edible object
     * @return the weight the animal has gained
     */
    @Override
    public double eat(Animal animal, IEdible edible) {
        double gained_weight = 0;
        if(canEat(edible.getFoodtype())){
            gained_weight = ((7/100.0) * animal.getWeight());
            animal.setWeight(animal.getWeight() + gained_weight);
        }
        return gained_weight;
    }

    /**
     *replicate
     * @return a copy of this object
     */
    @Override
    public Herbivore replicate() {
        return new Herbivore();
    }

    /**
     * @return "Herbivore"
     */
    public String toString(){
        return "Herbivore";
    }
}
