package edu.vtc.guppitus;



public class Main {

    public static void main(String[] args) {


        Cash.BoundedCash cash = new Cash().new BoundedCash();
        cash.setCap(0);

         cash.start();

        String hi = "hello";



        Key<String> key1 = new Key<>("key1", String.class);
        Key<String> key2 = new Key<>("key2", String.class);
        Key<String> key3 = new Key<>("key3", String.class);
        Key<String> key4 = new Key<>("key4", String.class);
        cash.storeValue(key1, hi);
        cash.storeValue(key2, hi);
        cash.storeValue(key3, hi);
        cash.storeValue(key4, hi);


        String value = cash.getValue(key4);
        System.out.println(value);

    }
}
