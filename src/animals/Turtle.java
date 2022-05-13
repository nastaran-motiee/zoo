package animals;

import diet.Herbivore;
import graphics.ZooPanel;
import mobility.Point;
import privateutil.ChewerAnimal;
import static privateutil.MyStrings.TURTLE;

/**
 * The class Turtle.
 *Turtle is a herbivore.
 *initial location of a turtle is Point(x:80, y:0)
 *initial weight of a turtle to 1 kg.
 * @see Animal
 * @see Herbivore
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 March 27,22
 */
public class Turtle extends ChewerAnimal{
    private int Age = 1; // The default age of a turtle is 1 year.
    private final int min_age = 0; //minimum age of a turtle is 0 years.
    private final int max_age = 500; //maximum age of a turtle is 500 years.

    //Constructors------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * Turtle constructor
     * sets the weight of animal (weight = size * 0.5)
     * @param size size of animal
     * @param horSpeed horizontal speed of animal
     * @param verSpeed vertical speed of animal
     * @param col color of animal

     */
    public Turtle(int size, int horSpeed, int verSpeed, String col, ZooPanel pan){
        super(size, horSpeed, verSpeed,col,new Herbivore(), new Point(80, 0),pan);
        this.setWeight(size * 0.5);
    }

    //Setters------------------------------------------------------------------------------------------------------------------------------------------------------\

    /**
     * set the size of turtle
     * every the size of animal changes, the weight of animal changes too (weight = size * 0.5)
     * @param size size of the animal
     * @return if size > 0 && weight > 0 return true, else return false
     */
    public boolean setSize(int size){
        return super.setSize(size) && this.setWeight(size * 0.5);
    }


    //---------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * chew prints "Retracts its head in then eats quietly" when the turtle chews.
     */
    public void chew(){
       System.out.println(getName()+": Retracts its head in then eats quietly");

    }


}
