package com.zhangds.owncollection;

/**
 * Create by zhangds
 * 2020-05-13 14:02
 **/
public class Test {
    public static void main(String[] args) {
        //OwnArrayList<Integer> collection = new OwnArrayList<>();
//        OwnHashSet<Integer> collection = new OwnHashSet<>();
        OwnLinkedList<Integer> collection = new OwnLinkedList<>();
//        OwnHashMap<Integer, String> collection = new OwnHashMap<>();
//        collection.put(1);
//        collection.put(2);
//        collection.put(2);

//        collection.add(1);
//        collection.add(2);

        collection.add(0, 0);
        collection.add(1, 1);
        collection.add(2, 2);
        collection.add(3, 3);

//        collection.put(1, "zhangds");
//        collection.put(2, "lisi");

//        System.out.println(collection.size());

//        collection.remove(collection.get(1));

//        System.out.println(collection.size());

        System.out.println(collection.get(1));

        collection.remove(0);

        System.out.println(collection.toString());

    }
}
