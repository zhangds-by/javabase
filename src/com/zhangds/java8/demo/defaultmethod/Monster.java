package com.zhangds.java8.demo.defaultmethod;

//分别实现可旋转、移动、缩放的接口
// 一般使用代理的方式（组合），通过该类的成员变量调用方法，
public class Monster implements Rotatable, Moveable, Resizable {

    private int x;
    private int y;
    private int width;
    private int height;
    private int rotationAngle;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getRotationAngle() {
        return rotationAngle;
    }

    @Override
    public void setRotationAngle(int rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setAbsoluteSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
