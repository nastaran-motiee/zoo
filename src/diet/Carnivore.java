package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
import privateutil.Replicable;
import static food.EFoodType.MEAT;
import static privateutil.MyStrings.CANEAT;

/**
 * The class Carnivore defines carnivores (every one who eats MEAT)
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 March 27,22
 */
public class Carnivore implements IDiet, Replicable {
    //Constructors----------------------------------------------------------------------------------------------------------------------------------------------
    //Setters------------------------------------------------------------------------------------------------------------------------------------------------------
    //Getters------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     *checks the type of food
     * @param food food type
     * @return if the type of food is MEAT returns true
     */
    @Override
    synchronized public boolean canEat(EFoodType food) {
        return (food==MEAT);
    }

    /**
     * the method checks if animal can eat the edible first. if yes, adds 10% to the weight of animal.
     * Eating causes a 10 percent increase in current weight.
     * @param animal an animal
     * @param edible  an edible object
     * @return the weight the animal has gained
     */
    @Override
    synchronized public double eat(Animal animal, IEdible edible) {
        double gained_weight = 0;
        if(canEat(edible.getFoodtype())){
            gained_weight = ((10/100.0) * animal.getWeight());
            animal.setWeight(animal.getWeight() + gained_weight);
        }
        return gained_weight;
    }

    /**
     * @return a copy of this object
     */
    @Override
    public Carnivore replicate() {
        return new Carnivore();
    }

    /**
     *
     * @return "Carnivore"
     */
    public String toString(){
        return "Carnivore";
    }


}
