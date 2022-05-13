package privateutil;


import animals.Animal;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class AnimalModel extends AbstractTableModel {

    protected final static String ANIMAL = "Animal";
    protected final static String COLOR = "Color";
    protected final static String WEIGHT = "Weight";
    protected final static String HOR_SPEED = "Hor. speed";
    protected final static String VER_SPEED = "Ver. speed";
    protected final static String EAT_COUNTER = "Eat counter";
    private final String[] columnNames = {ANIMAL, COLOR, WEIGHT, HOR_SPEED, VER_SPEED, EAT_COUNTER};
    private static AnimalModel instance = null;
    private final DefaultTableModel infoModel = new DefaultTableModel(columnNames, 0);


    private AnimalModel() {
        super();

    }

    /**
     * singleton
     * @return instance of model
     */
    public static AnimalModel getInstance() {
        if (instance == null)
            synchronized (AnimalModel.class){
            if(instance == null){
                instance = new AnimalModel();
            }
        }
        return instance;
    }

    /**
     * gets an animal as parameter and adds it to infoModel
     * @param animal
     */
    synchronized public void updateInfoModel(Animal animal){
        if(animal != null){
            infoModel.addRow(new Object[]{animal.toString(),animal.getColor(),animal.getWeight(),animal.getHorSpeed(),animal.getVerSpeed(),animal.getEatCount()});
        }
    }


    /**
     * refresh the info model
     * @param animalArrayList
     */
    public void refreshInfoModel(ArrayList<Animal> animalArrayList) {
        clearInfoModel();
        for(Animal animal: animalArrayList){
            this.updateInfoModel(animal);
        }

    }



    /**
     * @return info model (model for information of animals)
     */
     public DefaultTableModel getInfoModel(){
        return this.infoModel;
    }


    /**
     * remove all rows in infoModel
     */
     public void clearInfoModel() {
        int rowCount = infoModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            infoModel.removeRow(i);
        }

    }

    @Override
    public String getColumnName(int column) { return columnNames[column]; }

    @Override
    public Class<?> getColumnClass(int column) { return getValueAt(0, column).getClass(); }

    @Override
    public int getRowCount() {
        synchronized (infoModel){
            return infoModel.getRowCount();
        }
    }

    @Override
    public int getColumnCount() {
        synchronized (infoModel){
            return infoModel.getColumnCount();
        }

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        synchronized (infoModel){
            return infoModel.getValueAt(rowIndex,columnIndex);
        }

    }




}
