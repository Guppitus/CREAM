package edu.vtc.guppitus;

/**
 * Created by Seth Lunn on 6/16/2017.
 */
public class Key<T> {

    static long creation;
    final String identifier;
    final Class<T> type;

    public static long getCreation() {
        return creation;
    }



    Key(String identifier, Class<T> type){
        this.identifier = identifier;
        this.type = type;
        this.creation = System.currentTimeMillis();
    }
}
