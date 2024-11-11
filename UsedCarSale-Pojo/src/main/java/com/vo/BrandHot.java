package com.vo;

import java.io.Serializable;


public class BrandHot implements Serializable {
    private String name;
    private int num;

    public BrandHot() {
    }

    public BrandHot(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "BrandHot{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
