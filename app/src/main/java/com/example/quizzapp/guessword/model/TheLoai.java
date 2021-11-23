package com.example.quizzapp.guessword.model;


public class TheLoai {
    private String id;
    private String name;

    public TheLoai() {
    }

    public TheLoai(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}