package com.zhangds.java8.demo.optional;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class OptionalDemo {
    public static void main(String[] args) {
        // 声明空Optional对象
        Optional<Car> optCar = Optional.empty();

        //非空值创建Optional，若创建时car为null，抛出NullPointerException
        Optional<Car> optCarNotNull = Optional.of(new Car());

        //可接受null的Optional
        Car car = null;
        Optional<Car> optCarAllowNull = Optional.ofNullable(car);

        // 使用map从Optional 对象中提取和转换值
        Insurance insurance = null;
        // System.out.println(insurance.getName()); // 抛出异常
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        //Optional包含一个值，传递给map并转换
        Optional<String> name = optInsurance.map(Insurance::getName);

        //编译出错
//        Optional<Person> optPerson = Optional.of(new Person());
//        Optional<String> optName =
//                optPerson.map(Person::getCar) // Optional<Car>
//                        .map(Car::getInsurance) // Optional<Optional<Car>>
//                        .map(Insurance::getName);

    }

    /**
     * 使用flatMap 链接Optional 对象
     * 由方法生成的各个流会被合并或者扁平化为一个单一的流
     */
    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance) // 一个两层Optional<Optional<Car>>被flatMap合并为Optional<Insurance>
                .map(Insurance::getName) // 将Optional<Insurance>转化为Optional<String>对象
                .orElse(null);
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        // 对第一个Optional flatMap操作为null，不执行第二个Optional map操作，
        // 如果person、car都不为null，则调用findCheapestInsurance
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        System.out.println("findCheapestInsurance 调用");
        return new Insurance();
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public void TestStringToInt(){
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");
    }

    public int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) { }
        }
        return 0;
    }

    // 使用Optional从属性中读取duration
    public int readDurationWithOptional(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalUtils::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }
}
