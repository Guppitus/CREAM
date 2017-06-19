package edu.vtc.guppitus;



public class Main {

    public static void main(String[] args) {


        Cash.BoundedCash cash = new Cash().new BoundedCash();
        cash.setCap(0);

         cash.start();

        String hi = "hello";



        Key<String> key1 = new Key<>("key1", String.class);
        cash.storeValue(key1, hi);

        System.out.println(key1.creation);



    }
}
