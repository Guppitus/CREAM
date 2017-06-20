package edu.vtc.guppitus;

import java.util.*;

/**
 * Created by Seth Lunn on 6/15/2017.
 * https://www.youtube.com/watch?v=PBwAxmrE194
 *
 * Cash is a caching mechanism that can store paramaterized keys and values.
 */
public class Cash {

    /**
     * UnboundedCash is a cache with no cap
     */
    public class UnboundedCash {
        /** hashmap taking paramterized key object and paramterized value */
        private final Map<Key<?>, Object> map = new WeakHashMap<>();
        /** synchronized map */
        private final Map mapUncapped = Collections.synchronizedMap(map);

        /**
         * Stores values into an UnboundedCash
         * @param key : a key object containing identifier, type, and creation time
         * @param value : the paramterized value being stored
         */
        public synchronized  <T>  void storeValue(Key<T> key, T value){
            mapUncapped.put( key, value);
        }

        /**
         * Gets value out of an UnboundedCash by key
         * @param key : the key object corresponding to the value being retrieved
         * @return T  : the value being retrieved.
         */
        public synchronized  <T> T getValue(Key<T> key){
            return key.type.cast( mapUncapped.get( key ) );
        }

        /**
         * Checks if UnboundedCash contains a specific Key
         * @param key the Key being searched for.
         * @return boolean : true if found, false if not
         */
        public synchronized <T> boolean hasKey (Key<T> key){
            return mapUncapped.containsKey(key);
        }

        /**
         * removes a value from UnboundedCash
         * @param key Key of entry being removed
         */
        private synchronized <T> void removeValue(Key<T> key){
            mapUncapped.remove(key);
        }
    }

    /**
     * BoundedCash is a cache with a user set cap
     * When BoundedCash exceeds its cap, values are removed based on order of creation.
     */
    public class BoundedCash extends Thread {

        /** cap is the upper boundary of the cache */
        private int cap = 0;

        /** the hashmap containing stored paramterized keys and values */
        private final Map<Key<?>, Object> mapCap = new WeakHashMap<>();

        /** synchronized map of hashmap storing keys and values */
        private final Map mapCapped = Collections.synchronizedMap(mapCap);

        /**
         * returns the cap of BoundedCash
         * @return int : the cap of BoundedCash
         */
        public synchronized int getCap() {
            return cap;
        }

        /**
         * sets the cap of a BoundedCash
         * @param cap int : the cap of BoundedCash
         */
        public synchronized void setCap(int cap) {
            this.cap = cap;
        }

        /**
         * Stores values into a BoundedCash
         * @param key : a key object containing identifier, type, and creation time
         * @param value : the paramterized value being stored
         */
        public synchronized <T> void storeValue(Key<T> key, T value){
            mapCapped.put( key, value);
        }

        /**
         * Gets value out of a BoundedCash by key
         * @param key : the key object corresponding to the value being retrieved
         * @return T  : the value being retrieved.
         */
        public synchronized <T> T getValue(Key<T> key){
            return key.type.cast( mapCapped.get( key ) );
        }

        /**
         * Checks if BoundedCash contains a specific Key
         * @param key the Key being searched for.
         * @return boolean : true if found, false if not
         */
        public synchronized <T> boolean hasKey (Key<T> key){
            return mapCapped.containsKey(key);
        }

        /**
         * removes a value from BoundedCash
         * @param key Key of entry being removed
         */
        private synchronized <T> void removeValue(Key<T> key){
            mapCapped.remove(key);
        }

        /**
         * gets size of BoundedCash
         * @param map the map containing cached items.
         * @return int : the number of entries contained in map
         */
        private synchronized int mapSize(Map map){
            return map.size();
        }

        /**
         * thread that manages the limited number of keys/values in BoundedCash
         * removing values based on creation time if # of entries exceeds user set cap.
         */
        public void  run(){
            while (true){
                if (mapSize(mapCapped) > cap) {
                    List<Key<?>> list = new ArrayList<Key<?>>(mapCapped.keySet());
                    Collections.sort(list, new CreationComparator());



                    for (Key key : list) {
                        System.out.println(key.identifier + "  " + key.getCreation());
                    }
                    removeValue(list.get(0));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Compares Cached keys creation time. Used for sorting list of keys.
     */
    public class CreationComparator implements Comparator<Key<?>> {

        @Override
        public int compare(Key<?> key1, Key<?> key2) {
            return compare(key1.getCreation(), key2.getCreation());
        }

        private int compare(long a, long b){
            return a < b ? -1
                   : a > b ? 1
                   : 0;
        }
    }
}
