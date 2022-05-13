package animals;

import diet.Herbivore;
import graphics.ZooPanel;
import mobility.Point;
import privateutil.ChewerAnimal;

/**
 * The class Giraffe.
 * Giraffe is a herbivore
 * initial location of a giraffe is Point(x:0, y:50)
 * @see Animal
 * @see Herbivore
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 2.0 April 28,2022
 */
public class Giraffe extends ChewerAnimal{
    private double neckLength = 1.5; // The default value for giraffe neck length is 1.5 meters.
    private final double min_neckLength = 1; //minimum neck length of a giraffe is 1 meter.
    private final double max_neckLength = 2.5;//maximum neck length of a giraffe is 2.5 meters.
    private Herbivore herbivore = new Herbivore(); //Giraffe is a herbivore :

    //Constructors--------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Giraffe constructor
     * sets the weight of animal (weight = size * 2.2)
     * @param size size of animal
     * @param horSpeed horizontal speed of animal
     * @param verSpeed vertical speed of animal
     * @param col color of animal

     */
    public Giraffe(int size, int horSpeed, int verSpeed, String col, ZooPanel pan){
        super(size, horSpeed, verSpeed,col,new Herbivore(), new Point(0, 50), pan);
        this.setWeight(size * 2.2);
    }


    //Setters---------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * set the size of giraffe
     * every the size of animal changes, the weight of animal changes too (weight = size * 2.2)
     * @param size size of the animal
     * @return if size > 0 && weight > 0 return true, else return false
     */
    public boolean setSize(int size){
        return super.setSize(size) && this.setWeight(size * 2.2);
    }

    //Getters---------------------------------------------------------------------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * chew prints "Bleats and Stomps its legs, then chews" when the giraffe chews.
     */
    public void chew(){
        System.out.println(getName()+": Bleats and Stomps its legs, then chews");
    }


}
