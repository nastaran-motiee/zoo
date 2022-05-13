package memento;
import food.IEdible;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The class CareTaker
 * keeps track of multiple memento
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 June 7,2022
 */
public class CareTaker {

    private static volatile CareTaker instance = null;
    private List<Memento> stateList = new ArrayList<>();



    public void addMemento(Memento memento){
        if(stateList.size() >= 3){
            stateList.remove(0);
        }
        stateList.add(memento);
    }

    public Memento getMemento(){
        Memento mem = null;
        if(stateList.size() != 0){
             mem = stateList.get(stateList.size()-1);
            stateList.remove(stateList.size() - 1);
        }
        return mem;
    }


}
