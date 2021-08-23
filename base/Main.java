package base;

import hashmap.MyHashMap;

public class Main {

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(1, "rakesh");
        map.put(2, "gopal");
        map.put(3, "udhaya");
        map.put(4, "ajith");

        System.out.println(map.get(2));
        System.out.println(map.containsKey(3));

        System.out.println(map);

        map.put(1, "ramesh");
        map.remove(4);
        System.out.println(map);

    }
}
