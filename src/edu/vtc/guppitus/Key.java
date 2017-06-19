package edu.vtc.guppitus;

/**
 * Created by Seth Lunn on 6/16/2017.
 */
public class Key<T> {

    final String identifier;
    final Class<T> type;
    final Long creation;

    Key(String identifier, Class<T> type){
        this.identifier = identifier;
        this.type = type;
        this.creation = System.currentTimeMillis();
    }
}
