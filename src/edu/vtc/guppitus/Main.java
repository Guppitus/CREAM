package edu.vtc.guppitus;

public class Main {

    public static void main(String[] args) {

        Cash cash = new Cash();

        String hi = "hello";

        String key = "Key";

        Key<String> key1 = new Key<>(key, String.class);
        cash.storeValue(key1, hi);



    }
}
