package com.example.nutrix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

public class ViewMealPlanActivity extends AppCompatActivity {
    RecyclerView listRecipes;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meal_plan);
        listRecipes = findViewById(R.id.mealplans);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(ViewMealPlanActivity.this, LinearLayoutManager.HORIZONTAL, false);
        listRecipes.setLayoutManager(horizontalLayoutManagaer);
        context = getApplicationContext();
        listRecipes = findViewById(R.id.mealplans);
        RecipeHelperClass[] recipeHelperClass = (RecipeHelperClass[]) getIntent().getSerializableExtra("recipe");

        ViewMealPlanAdapter mealAdapter = new ViewMealPlanAdapter(recipeHelperClass, ViewMealPlanActivity.this);
        listRecipes.setAdapter(mealAdapter);
    }
}