package com.example.myapplication;

public class listviewbean {
    int image;
    String name;
    String money;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public listviewbean(int image, String name, String money) {
        this.image = image;
        this.name = name;
        this.money = money;

    }
}
