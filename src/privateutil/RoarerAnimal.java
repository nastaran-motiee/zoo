package privateutil;

import animals.Animal;
import diet.IDiet;
import diet.Omnivore;
import graphics.ZooPanel;
import mobility.Point;

import java.awt.*;

/**
 * The class RoarerAnimal defines the characteristics common to animals that roar.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 March 27,22
 */
public abstract class RoarerAnimal extends Animal {

    //Constructors---------------------------------------------------------------------------------------------------------------------------------------

    /**
     *RoarerAnimal constructor
     * @param size size of animal
     * @param horSpeed horizontal speed of animal
     * @param verSpeed vertical speed of animal
     * @param col color of animal
     * @param diet diet of animal
     */
    public RoarerAnimal(int size, int horSpeed, int verSpeed, String col, IDiet diet, Point location, ZooPanel pan){
        super(size, horSpeed, verSpeed, col, diet, location, pan);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void makeSound() {
        roar();
    }

    /**
     * prints individual message for the animal that roars
     */
    public abstract void roar();
}
