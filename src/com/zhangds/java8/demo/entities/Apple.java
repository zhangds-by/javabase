package com.zhangds.java8.demo.entities;

public class Apple {

    private String name;

    private String color;

    private double weight;

    public Apple(String name, String color, double weight) {
        this.name = name;
        this.color = color;
        this.weight = weight;
    }

    public Apple() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
