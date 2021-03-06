package edu.vtc.guppitus;

/**
 * Created by Seth Lunn on 6/16/2017.
 * Key class defines a paramterized Key object used as Keys for Cash.
 *
 * https://www.youtube.com/watch?v=SFLSOIufuhM
 */
public class Key<X,Y> {

    /** long holding the creation time in nanoseconds  */
    private long creation;


    /** identifier Object */
    final X identifier;

    /** class type of Value */
    final Class<Y> type;

    /**
     * gets the creation time
     * @return long: the creation time in nanoseconds
     */
    public long getCreation() {
        return creation;
    }

    public synchronized void setCreation(long creation) {
        this.creation = creation;
    }

    /**
     * Constructor for Key
     * @param identifier String identification
     * @param type of class
     */
    Key(X identifier, Class<Y> type){
        this.identifier = identifier;
        this.type = type;
        this.creation = 0;
    }
}
