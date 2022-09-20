package com.example.nutrix;

import java.io.Serializable;

public class IngredientClassHelper implements Serializable {
    String name;
    double price;
    int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int  quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public IngredientClassHelper(String name, double price,int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
