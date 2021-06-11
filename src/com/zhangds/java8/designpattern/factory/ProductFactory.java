package com.zhangds.java8.designpattern.factory;

import com.zhangds.java8.designpattern.entities.Bond;
import com.zhangds.java8.designpattern.entities.Loan;
import com.zhangds.java8.designpattern.entities.Product;
import com.zhangds.java8.designpattern.entities.Stock;

public class ProductFactory {

    public static Product createProduct(String name){
        switch(name){
            case "loan": return new Loan();
            case "stock": return new Stock();
            case "bond": return new Bond();
            default: throw new RuntimeException("No such product " + name);
        }
    }
}
