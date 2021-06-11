package com.zhangds.java8.designpattern.model;

import com.zhangds.java8.designpattern.entities.Customer;

import java.util.function.Consumer;

/**
 * lambda的实现
 */
public class OnlineBankingLambda {

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
//        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(new Customer());
    }
}
