package com.zhangds.arth.datastruction.symboltable;

import java.util.Arrays;
import java.util.List;

/**
 * Create by zhangds
 * 2020-05-12 16:02
 **/
public class Test {
    public static void main(String[] args) {
        //LinkedUnorderedST<String, String> st = new LinkedUnorderedST<>();
        //BinarySearchOrderedST<String, String> st = new BinarySearchOrderedST<>();
        LineHashST<String, String> st = new LineHashST<>();
        st.put("1","zhangsan");
        st.put("2","lisi");
        st.put("3","wangwu");
        st.put("3","wangwu2");
        st.put("2","zhangsan");

        //List<String> keys = st.keys("1", "3");
        //System.out.println(keys.size());
        //st.delete("2");
        System.out.println(st.size());
        System.out.println(st.get("2"));
        System.out.println(st.get("3"));





    }
}
