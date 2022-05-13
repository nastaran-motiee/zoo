package animals;

import diet.Carnivore;
import diet.Herbivore;
import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
import mobility.Point;
import privateutil.RoarerAnimal;


import java.util.Random;

import static privateutil.MyStrings.*;

/**
 * The class Lion.
 * Predator only (eats meat).
 * Lion can't be eaten.
 * initial location of a lion is Point(x:20, y:0)
 * initial weight of lion is 408.2 kg.
 * @see Animal
 * @see Carnivore
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 2.0 April,2022
 */
public class Lion extends RoarerAnimal {
    private int scarCount = 0;

    //Constructors---------------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * Lion constructor
     * sets the weight of animal (weight = size * 0.8)
     * @param size size of animal
     * @param horSpeed horizontal speed of animal
     * @param verSpeed vertical speed of animal
     * @param col color of animal
     */
    public Lion(int size, int horSpeed, int verSpeed, String col, ZooPanel pan){
        super(size, horSpeed, verSpeed,col,new Carnivore(), new Point(20, 0), pan);
        this.setWeight(size * 0.8);
    }

    //Setters------------------------------------------------------------------------------------------------------------------------------------------------------\

    /**
     * set the size of lion
     * every the size of animal changes, the weight of animal changes too (weight = size * 0.8)
     * @param size size of the animal
     * @return if size > 0 && weight > 0 return true, else return false
     */
    public boolean setSize(int size){
        return super.setSize(size) && this.setWeight(size * 0.8);
    }

    /**
     *set scar count
     * @param scarCount the number of the scars of the lion
     * @return true if scarCount >= 0 , else return false
     */
    public boolean setScarCount(int scarCount) {
        boolean is_successful = (scarCount >= 0);
        this.scarCount = scarCount;
        return is_successful;
    }




    //Getters------------------------------------------------------------------------------------------------------------------------------------------------------




    /**
     * get Scar Count
     * @return number of scars of the lion
     */
    public int getScarCount() {
        return scarCount;
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * each time a lion eats, there is a chance of 50% that it gets a new scar.
     * if lion can eat edible, then eat
     * @param edible edible
     * @return true if the lion could eat,  else return false
     */
    @Override
    public boolean eat(IEdible edible) {
        boolean is_successful = super.eat(edible);
        if(is_successful){
            if(new Random().nextInt(2)==1){
                setScarCount(getScarCount()+1);
            }
        }

        return is_successful;
    }

    /**
     * roar. prints "Roars, then stretches and shakes its mane" when the lion roars.
     */
    public void roar(){
        System.out.println(getName()+": Roars, then stretches and shakes its mane");

    }


}
