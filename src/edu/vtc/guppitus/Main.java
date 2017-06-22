package edu.vtc.guppitus;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * examples for how to use CREAM
 *
 * @uthor Seth Lunn
 */

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {

        /* Initialize a BoundedCash cache  */
        Cash.BoundedCash cash = new Cash().new BoundedCash();
        /* set cache cap, 0 by default */
        cash.setCap(3);
        /* start cache cap maintain thread */


        /* example string object */
        String hi = "hello";

        /* example test object containing a string */
        class TestValue{
            String testValue = "this is a test value Object!";
        }
        TestValue testValue = new TestValue();

        class TestKey{
            String testKey = "this is a test key Object!";
        }
        TestKey testKey = new TestKey();

        /* lets get weird and meta and initialize another cache */
        Cash.UnboundedCash metaCash = new Cash().new UnboundedCash();
        Key<String,String> uKeyExample = new Key<>("uKey", String.class);
        metaCash.storeValue(uKeyExample,"so freaking meta!");


        /* make a key containing an identifier, and the type
        *  Key<T> ( String identifier, Class<T> type )
        *  Keys also hold the creation time in which they are stored into the cache in nanoseconds
        */
        // example key for a String
        Key<String,String> key1 = new Key<>("key1", String.class);
        // example key for an object
        Key<TestKey,TestValue> key2 = new Key<>(testKey, TestValue.class);
        // example key for a key cause sh*ts cray
        Key<String,Key> key3 = new Key<>("key3", Key.class);
        // example key for another cache stored within a cache
        Key<String,Cash.UnboundedCash> key4 = new Key<>("key4", Cash.UnboundedCash.class);

        /*  Lets throw this junk in the cache!
         *  Insertion order and time matters for
         *  maintaining the cache.
         *  FIFO when removing objects stored
         *  when cap is exceeded
         *  */
        cash.storeValue(key1, hi);
        cash.storeValue(key2, testValue);
        cash.storeValue(key3, key1);


        /* Has Key example */
        boolean doWeGotTheKey = cash.iGotTheKeys(key1);
        System.out.println(doWeGotTheKey);

        /* Get value of key1 */
        System.out.println(cash.getValue(key1));

        /* retrieving object */
        TestValue retrievedTest = cash.getValue(key2);
        System.out.println(retrievedTest.testValue);


        /* retrieving a key stored within the cache        */
        String value = cash.getValue(cash.getValue(key3));
        System.out.println(value);


        /* Since the cap is set to 3, this will remove key1 and its value from the cache */
        cash.storeValue(key4, metaCash );

        /* retrieving a meta cache */
        Cash.UnboundedCash retrievedMetaCash = cash.getValue(key4);
        System.out.println(retrievedMetaCash.getValue(uKeyExample));





        /* clears whole cache  */
        cash.mAAdCity();

        if(Desktop.isDesktopSupported())
        {
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=PBwAxmrE194"));
        }

    }
}
