package com.zhangds.java8.designpattern;

import com.zhangds.java8.designpattern.entities.Bond;
import com.zhangds.java8.designpattern.entities.Loan;
import com.zhangds.java8.designpattern.entities.Product;
import com.zhangds.java8.designpattern.entities.Stock;
import com.zhangds.java8.designpattern.factory.ProductFactory;
import com.zhangds.java8.designpattern.model.OnlineBankingLambda;
import com.zhangds.java8.designpattern.responsechain.ProcessingObject;
import com.zhangds.java8.designpattern.stategy.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Test {



    public static void main(String[] args) {

        // 策略
        Validator validator = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b1 = validator.validate("aaaa");

        // 模式
        new OnlineBankingLambda().processCustomer(1330, c -> System.out.println("hello"));

        // 责任链(一般)
        ProcessingObject<String> p1 = new ProcessingObject.HeaderTextProcessing();
        ProcessingObject<String> p2 = new ProcessingObject.SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result = p1.handle("Aren't lambdas really sexy?!!");
        System.out.println(result);

        //lambda表达式
        UnaryOperator<String> headerProcessing =
                (String text) -> "From Raoul, Mario and Alan: " + text;

        UnaryOperator<String> spellCheckerProcessing =
                (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline =
                headerProcessing.andThen(spellCheckerProcessing); // 使用andThen()进行构造来链接函数
        String result1 = pipeline.apply("Aren't lambdas really sexy?!!");

        //工厂模式
        Product p = ProductFactory.createProduct("loan");

        Supplier<Product> loanSupplier = Loan::new;
        Loan loan = (Loan) loanSupplier.get();

    }
}
