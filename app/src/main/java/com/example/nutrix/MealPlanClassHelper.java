package com.example.nutrix;

import java.io.Serializable;
import java.util.List;

public class MealPlanClassHelper implements Serializable {
    int numdays;
    List<String> days;
    int nummeals;
    int calories;

    public MealPlanClassHelper(int numdays, List<String> days, int nummeals, int calories) {
        this.numdays = numdays;
        this.nummeals = nummeals;
        this.days = days;
        this.calories = calories;

    }
    public MealPlanClassHelper() {
    }

    public int getNumdays() {
        return numdays;
    }

    public void setNumdays(int numdays) {
        this.numdays = numdays;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public int getNummeals() {
        return nummeals;
    }

    public void setNummeals(int nummeals) {
        this.nummeals = nummeals;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }


}

