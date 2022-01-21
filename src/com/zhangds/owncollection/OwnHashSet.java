package com.zhangds.owncollection;

import com.zhangds.common.model.User;

import java.util.HashMap;
import java.util.HashSet;

/**
 * HashMap实现
 * Create by zhangds
 * 2020-05-06 17:54
 **/
public class OwnHashSet<T> {

    private HashMap map;

    private static final Object PRESENT = new Object();

    public OwnHashSet() {
        map = new HashMap();
    }

    public int size(){
        return map.size();
    }

    public void put(T obj){
        map.put(obj, PRESENT);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Object object : map.keySet()){
            sb.append(object + ",");
        }
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }

    /**
     *  user重写equal()和hashcode() 修改user的值后，存储的是两个相同的对象，但两个对象的hashcode不一样
     *  否则，存储的都是同一个对象
     *  equals() 的作用是用来判断两个对象是否相等。
     *  hashCode() 的作用是获取哈希码，也称为散列码；它实际上是返回一个int整数。哈希码的作用是确定该对象在哈希表中的索引位置。
     *  1、重写equal(),必须重写hashcode()
     *  2、set存储的是不重复的对象，依据equal和hashcode判断
     *  3、set的实现使用map的键存储实现，因此使用自定义对象作为map的键时，必须重写equal和hashcode
     */
    public static void main(String[] args) {
//        User user = new User();
//        user.setId("1");
//        HashSet<User> set = new HashSet();
//        set.add(user);
//        System.out.println(user.hashCode());
//        User user2 = user;
//        user.setId("2");
//        System.out.println(user.hashCode());
//        set.add(user);
//        set.add(user2);
//        System.out.println(set);
//        System.out.println(user.equals(user2));
//
//        User tmp = null;
//        for (User t : set){
//            System.out.println(t.equals(tmp));
//            System.out.println(t.hashCode());
//            System.out.println(t.getId());
//            tmp = t;
//        }
        User user11 = new User("1", "zhang", 23);
        User user22 = new User("1", "zhang", 23); // 两个对象虽然都是new的，重写equal和hashcode
        System.out.println(user11.equals(user22)); // true
        System.out.println(user11.hashCode() == user22.hashCode()); // true
        HashSet<User> set = new HashSet();
        set.add(user11);
        set.add(user22);
        System.out.println(set);
    }
}
