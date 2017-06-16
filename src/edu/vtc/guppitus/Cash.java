package edu.vtc.guppitus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seth Lunn on 6/15/2017.
 *
 * https://www.youtube.com/watch?v=PBwAxmrE194
 */
public class Cash {

    private final Map<Key<?>, Object> map = new HashMap<>();
    private final Map values = Collections.synchronizedMap(map);

    public <T> void storeValue(Key<T> key, T value){
        values.put( key, value);
    }

    public <T> T getValue(Key<T> key){
        return key.type.cast( values.get( key ) );
    }

    public <T> boolean hasKey (Key<T> key){
        return values.containsKey(key);
    }

    private <T> void removeValue(Key<T> key){
        values.remove(key);
    }



}
