package com.zhangds.java8.designpattern.model;

import com.zhangds.java8.designpattern.entities.Customer;

/**
 * 一般使用继承的方式来完成具体的实现
 */
public abstract class OnlineBanking {

    public void processCustomer(int id){
//        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(new Customer());
    }

    abstract void makeCustomerHappy(Customer c);
}
