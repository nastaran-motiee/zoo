package animals;

import diet.Herbivore;
import graphics.ZooPanel;
import privateutil.ChewerAnimal;
import mobility.Point;


/**
 * The class Elephant.
 * Elephant is herbivore.
 * initial location of an elephant is Point(x:50, y:90)
 * @see Herbivore
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 2.0 April 28,2022
 */
public class Elephant extends ChewerAnimal{
    private double trunkLength = 1; // the default value of the elephant's trunk length is 1 meter.
    private final double min_trunk_length = 0.5; //minimum trunk length of an elephant can be 0.5 meter.
    private final double max_trunk_length = 3; //maximum trunk length of an elephant can be 3 meters.

    //Constructors----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Elephant constructor.
     * sets the weight of animal (weight = size * 10)
     * @param size size of animal
     * @param horSpeed horizontal speed of animal
     * @param verSpeed vertical speed of animal
     * @param col color of animal
     */
    public Elephant(int size, int horSpeed, int verSpeed, String col, ZooPanel pan){
        super(size , horSpeed ,verSpeed ,col , new Herbivore(), new Point(50, 90), pan);
        this.setWeight(size * 10 );

    }





    //Setters--------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * set the size of elephant
     * every the size of animal changes, the weight of animal changes too (weight = size * 10)
     * @param size size of the animal
     * @return if size > 0 && weight > 0 return true, else return false
     */
    public boolean setSize(int size){
        return super.setSize(size) && this.setWeight(size * 10);
    }


    /**
     * sets trunk length of the elephant if  0.5 <= trunk length <= 3
     * @param trunkLength trunkLength
     * @return true is trunk length was set, else return false
     */
    public boolean setTrunkLength(double trunkLength){
        boolean is_successful = (trunkLength >= 0.5 && trunkLength <= 3);
        if(is_successful){
            this.trunkLength = trunkLength;
        }
        return is_successful;
    }
    //Getters--------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * get trunk length of the elephant
     * @return the trunkLength of the elephant
     */
    public double getTrunkLength(){
        return this.trunkLength;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * chew prints "Trumpets with joy while flapping its ears, then chews" when the elephant chews.
     */
    public void chew(){
        System.out.println(getName()+": Trumpets with joy while flapping its ears, then chews");

    }



}
