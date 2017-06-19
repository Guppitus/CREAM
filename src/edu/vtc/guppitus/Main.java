package edu.vtc.guppitus;



public class Main {

    public static void main(String[] args) {

        Cash cash = new Cash();

        String hi = "hello";

        String key = "hi";

        Key<String> key1 = new Key<>("key", String.class);
       Cash.unboundedCash.storeValue(key1, hi);

        System.out.println(key1.creation);


        Cash.boundedCash.setCap(1);

        Cash.boundedCash.storeValue(key1, hi);






    }
}
