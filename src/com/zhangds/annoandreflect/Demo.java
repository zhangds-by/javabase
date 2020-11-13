package com.zhangds.annoandreflect;

import java.util.List;
import java.util.Map;

/**
 * Create by zhangds
 * 2020-05-11 17:56
 **/
@OwnAnno(name = "demo", role = {"admin"})
public class Demo {

    @OwnFieldAnno("name")
    private String name;

    public Demo(String name) {
        this.name = name;
    }

    public Demo() {
    }

    public void test01(Map<String,User> map, List<User> list){
    }

    public Map<String, User> test02() {
        System.out.println("test02");
        return null;
    }
}
