package com.zhangds.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Hero implements Serializable {
    private Long id;

    private String name;

    private double hp;

    private double mp;

    private String location;

    private String sex;

    private BigDecimal price;

    public Hero(Long id, String name, double hp, double mp, String location, String sex, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.location = location;
        this.sex = sex;
        this.price = price;
    }

    public Hero() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getMp() {
        return mp;
    }

    public void setMp(double mp) {
        this.mp = mp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
