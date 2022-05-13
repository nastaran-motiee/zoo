package memento;
/**
 * The class Memento
 * is responsible to maintain the state of originator
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 June 7,2022
 */
public class Memento{
    private String state;

    public Memento(String state){
        this.state = state;
    }

    /**
     * get state
     * @return state
     */
    public String getState(){
        return state;
    }
}
