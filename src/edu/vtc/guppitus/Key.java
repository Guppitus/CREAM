package edu.vtc.guppitus;

/**
 * Created by Seth Lunn on 6/16/2017.
 * Key class defines a paramterized Key object used as Keys for Cash.
 */
public class Key<T> {

    /** long holding the creation time in nanoseconds  */
    static long creation;

    /** identifier string */
    final String identifier;

    /** class type of Key */
    final Class<T> type;

    /**
     * gets the creation time
     * @return long: the creation time in nanoseconds
     */
    public static long getCreation() {
        return creation;
    }

    /**
     * Constructor for Key
     * @param identifier String identification
     * @param type of class
     */
    Key(String identifier, Class<T> type){
        this.identifier = identifier;
        this.type = type;
        this.creation = System.nanoTime();
    }
}
