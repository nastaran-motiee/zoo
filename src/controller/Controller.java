package controller;

import animals.Animal;
import decoretor.ColorDecorator;
import food.IEdible;
import graphics.AddAnimalDialog;
import graphics.ZooPanel;
import mobility.Point;
import plants.Cabbage;
import plants.Lettuce;
import plants.Plant;
import privateutil.AnimalModel;
import privateutil.Meat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static privateutil.MyStrings.*;
import static privateutil.MyStrings.EXIT;
public class Controller implements Observer,Runnable{
    private final ArrayList<Animal> animalsArray = new ArrayList<Animal>(); //represents all of animals at the zoo (Data Base)
    private final ArrayList<IEdible> foodArray = new ArrayList<IEdible>();
    private final JTable infoTable  = new JTable(AnimalModel.getInstance().getInfoModel());
    private final JFrame infoFrame = new JFrame();
    private boolean finish = false;
    private volatile boolean finished = false;
    private final int NUM_OF_THREADS = 10;

    private final ExecutorService pool = Executors.newFixedThreadPool(NUM_OF_THREADS);

    private static volatile Controller instance = null;


    /**

     * get instance of Controller
     * @return instance of Controller
     */
    public static Controller getInstance(){
        if(instance == null){
            synchronized (Controller.class){
                if(instance==null){
                    instance = new Controller();
                }
            }
        }
        return instance;
    }
    /**
     * zooPanel Constructor
     */
    private Controller(){
        infoFrame.add(new JScrollPane(infoTable));
        Thread controller = new Thread(this);
        controller.start();

    }

    @Override
    public void run() {
        while (true){
            try{

                Thread.sleep(10);
                if(animalsArray.size() > 0){
                    manageZoo();
                }
                if(finish){
                    synchronized (animalsArray){
                        Iterator<Animal> animalIterator = animalsArray.iterator();
                        while (animalIterator.hasNext()){
                            Animal animal = animalIterator.next();
                            animal.killAnimal();
                            while(!animal.isDead());
                            animalIterator.remove();

                        }


                        pool.shutdown();
                        finished = true;
                        return;
                    }



                }
            }catch (InterruptedException e){
                System.out.println(Arrays.toString(e.getStackTrace()));
            }

        }
    }

    /**
     * controller of zoo
     */
    public void manageZoo() {
        while (animalsArray.size() == 0){
            try {
                wait();
            }catch (InterruptedException e){
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
        refreshInfoModel();
        changesDone();


    }

    public ArrayList<IEdible> getFoodArray() {
        return foodArray;
    }
    public ArrayList<Animal> getAnimalsArray(){
        return animalsArray;
    }

    /**
     * it turns back the changes the coordChanged filed of animals back to false
     * this method is used when the changes is already done on the view
     */
    public void changesDone(){
        synchronized (animalsArray){
            for (Animal animal: animalsArray){
                animal.setChanges(false);
            }
        }

    }



    /**
     * refresh the model info view
     */
    public void refreshInfoModel() {
        synchronized (animalsArray){
            AnimalModel.getInstance().refreshInfoModel(this.animalsArray);
        }

    }

    /**
     * @param selectedAnimal
     * @return true if added successfully, else return false
     */
    public synchronized boolean addToDataBase(Object selectedAnimal){
        boolean is_successful = this.addAnimalToTheZoo(selectedAnimal);
        if(selectedAnimal instanceof Animal){
            AnimalModel.getInstance().updateInfoModel((Animal) selectedAnimal);
        }

        return is_successful;
    }




    /**
     * remove all elements from database
     */
    public void removeAllFromDataBase(){
        this.removeAllAnimals();
    }


    /**
     * if the number of animals in the zoo is less than 10,
     * checks if the object is instance of animal
     * then adds animal to the zoo
     * @param animal animal
     * @return true if animal was added to the zoo, else return false
     */
    public boolean addAnimalToTheZoo(Object animal){
        synchronized (animalsArray){
            boolean is_successfull = (animalsArray.size() < 15);
            if(is_successfull){
                if(animal instanceof Animal){
                    animalsArray.add((Animal) animal);
                    ((Animal) animal).goEatOtherAnimals(animalsArray);
                }
            }else{
                JOptionPane.showMessageDialog(null, "You cannot add more than 15 animals");
            }
            return is_successfull;
        }

    }

    /**
     * add plant to plantArray and make it visible
     * @param food some type of food
     */
    public void makeFoodVisible(Object food){
        if(food instanceof Plant){
            ((Plant) food).loadImages(food.toString());
            foodArray.add((Plant) food);

        }else if(food instanceof Meat){//this is for Meat
            ((Meat) food).loadImages(food.toString());
            foodArray.add((Meat)food);
        }
       // ZooPanel.getInstance().repaint();
    }

    /**
     * update the location of animal
     * @param indexOfAnimal
     */
    public void updateLocationOfAnimal(int indexOfAnimal, Point location) {
        synchronized (animalsArray){
            animalsArray.get(indexOfAnimal).move(location);
            System.out.println(animalsArray.get(indexOfAnimal).getLocation());
        }

    }


    /**
     * update the location of selected animal in the database
     * @param indexOfAnimal
     * @param location
     */
    public void updateLocationAtDataBase(int indexOfAnimal, Point location){
        this.updateLocationOfAnimal(indexOfAnimal, location);

        System.out.println("location updated");
    }

    /**
     * remove all elements from animal array
     */
    public void removeAllAnimals(){
        synchronized (animalsArray){
            animalsArray.clear();
        }

    }

    /**
     * removes the food that was eaten from the food array
     * @param food the food the was eaten
     *
     */
    public void foodWasEaten(IEdible food) {
        synchronized (foodArray){
            foodArray.remove(food);
        }


    }

    synchronized public void animalWasEaten(Animal animal) {
        synchronized (animalsArray){
            animalsArray.remove(animal);
        }


    }

    /**
     * TODO:CHANGED
     * adds the animal to ThreadPool and runs it
     * @param animal new animal
     */
    public synchronized void newAnimalEntered(Animal animal) {
        pool.execute(animal);


    }


    /**
     * shutdown the ThreadPool
     * finish running
     */
    public void finish() {
        this.finish = true;
    }


    /**
     * notify
     * @param notification
     */
    public void notify(String notification) {
        Window window = SwingUtilities.windowForComponent(ZooPanel.getInstance());
        switch (notification) {
            case ADD_ANIMAL -> {
                //Custom button text
                Object[] options = {CARNIVORE, HERBIVORE, OMNIVORE};
                int result = JOptionPane.showOptionDialog(null,
                        "Please choose the type of animal",
                        "Animal Type",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                AddAnimalDialog addAnimalDialog = AddAnimalDialog.getInstance(result,ZooPanel.getInstance());
                if(addAnimalDialog != null){
                    addAnimalDialog.setBounds( window.getBounds());
                    addAnimalDialog.setVisible(true);
                }


            }
            case CHANGE_COLOR -> {
                for (Animal animal:animalsArray){
                    animal.setSuspended();
                }
                ColorDecorator colorDecorator = new ColorDecorator(ZooPanel.getInstance(),animalsArray);
                colorDecorator.setBounds( window.getBounds());
                colorDecorator.setVisible(true);
            }
            case SLEEP -> {
                for (Animal animal:animalsArray){
                    animal.setSuspended();
                }


            }
            case WAKE_UP -> {

                for (Animal animal:animalsArray){
                    animal.setResumed();
                }
            }

            case CLEAR -> {
                for(Animal animal: animalsArray){
                    animal.killAnimal();
                }
                this.removeAllFromDataBase();
                AnimalModel.getInstance().clearInfoModel();
                System.out.println("DataBase has been removed!");
            }

            case FOOD -> {
                //Custom button text
                Object[] options = {LETTUCE, CABBAGE, MEAT};
                int result = JOptionPane.showOptionDialog(null,
                        "Please choose food",
                        "Food for animal",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                switch (result){
                    case 0-> this.makeFoodVisible(Lettuce.getInstance());

                    case 1-> this.makeFoodVisible(Cabbage.getInstance());

                    case 2-> this.makeFoodVisible(Meat.getInstance());
                }
                for(Animal animal : animalsArray){

                    (animal).goEatFood((ArrayList<IEdible>) foodArray.clone());
                }

            }

            case INFO -> {
                this.infoFrame.setBounds( window.getBounds());
                this.infoTable.setFillsViewportHeight(true);
                this.infoFrame.setVisible(true);
            }

            case EXIT -> {

                synchronized (this){
                    this.finish();
                    while (!finished) Thread.onSpinWait();
                    System.exit(0);

                }

            }
        }

        ZooPanel.getInstance().repaint();
    }


}
