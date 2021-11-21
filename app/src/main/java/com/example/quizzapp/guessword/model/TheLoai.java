package com.example.quizzapp.guessword.model;


public class TheLoai {
    private String id;
    private String tenTheLoai;

    public TheLoai() {
    }

    public TheLoai(String id, String tenTheLoai) {
        this.id = id;
        this.tenTheLoai = tenTheLoai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    @Override
    public String toString() {
        return "TheLoai{" +
                "id='" + id + '\'' +
                ", tenTheLoai='" + tenTheLoai + '\'' +
                '}';
    }
}