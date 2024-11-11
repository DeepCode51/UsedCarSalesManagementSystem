package com.pojo;


import java.io.Serializable;

public class Evaluation implements Serializable {
    private int id;
    private String name;
    private int value;
    private int score;

    public Evaluation() {
    }

    public Evaluation(int id, String name, int value, int score) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", score=" + score +
                '}';
    }
}
