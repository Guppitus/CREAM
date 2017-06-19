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

    public static class unboundedCash {
        private static final Map<Key<?>, Object> map = new HashMap<>();
        private static final Map mapUncapped = Collections.synchronizedMap(map);

        static <T> void storeValue(Key<T> key, T value){
            mapUncapped.put( key, value);
        }

        public <T> T getValue(Key<T> key){
            return key.type.cast( mapUncapped.get( key ) );
        }

        public <T> boolean hasKey (Key<T> key){
            return mapUncapped.containsKey(key);
        }

        private <T> void removeValue(Key<T> key){
            mapUncapped.remove(key);
        }
    }

    public static class boundedCash extends Thread {

        private static int cap;
        private static final Map<Key<?>, Object> mapCap = new HashMap<>();
        private static final Map mapCapped = Collections.synchronizedMap(mapCap);

        public int getCap() {
            return cap;
        }

        static void setCap(int cap) {
            boundedCash.cap = cap;
        }

        public static <T> void storeValue(Key<T> key, T value){
            mapCapped.put( key, value);
        }

        public <T> T getValue(Key<T> key){
            return key.type.cast( mapCapped.get( key ) );
        }

        public <T> boolean hasKey (Key<T> key){
            return mapCapped.containsKey(key);
        }

        private <T> void removeValue(Key<T> key){
            mapCapped.remove(key);
        }

        private int mapSize(Map map){
            return map.size();
        }

        public void  run(){
            while (true){
                if(mapSize(mapCapped) > cap){
                    System.out.println("error");
                }
            }

        }

    }





    private<T> long storedValueCreation(Key<T> key){
        return key.creation;
    }



}
