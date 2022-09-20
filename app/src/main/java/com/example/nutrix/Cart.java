package com.example.nutrix;

import java.io.Serializable;

public class Cart implements Serializable
{
    String  name;
    int price, quantity,totalprice;

    public Cart(String name,int price, int quantity,int totalprice)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalprice = totalprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

}
