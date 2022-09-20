package com.example.nutrix;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RecipeHelperClass implements Serializable {

    String recipename,description, calories,preptime, totaltime, instructions,category,image,nameid;
    String[][] ingredients;
    int price;

    public RecipeHelperClass(String recipename,String nameid,String category, String description, String calories, String preptime, String totaltime, String instructions, int price,String image,String[][] ingredients) {
        this.recipename = recipename;
        this.category = category;
        this.nameid = nameid;
        this.description = description;
        this.calories = calories;
        this.preptime = preptime;
        this.totaltime = totaltime;
        this.instructions = instructions;
        this.price = price;
        this.image = image;
        this.ingredients= ingredients;
    }

    public String getRecipename() {
        return recipename;
    }

    public void setRecipename(String recipename) {
        this.recipename = recipename;
    }
    public String getNameid() {
        return nameid;
    }

    public void setNameid(String nameid) {
        this.nameid = nameid;
    }

    public String[][] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[][] ingredients) {
        this.ingredients = ingredients;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalories() {
        return calories;
    }

    public void getCalories(String calories) { this.calories = calories; }


    public String getPreptime() {
        return preptime;
    }

    public void setPreptime(String preptime) {
        this.preptime = preptime;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;

    }
    public String getImage() {
        return image;
    }

    public void getImage(String image) {
        this.image = image;

    }

}