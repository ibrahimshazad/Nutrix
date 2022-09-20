package com.example.nutrix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteMealPlanRecipe extends AppCompatActivity {
    TextView monday ,tuesday,wednesday,thursday,friday,saturday,sunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_meal_plan_recipe);
        monday = (TextView) findViewById(R.id.mon);
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Category.class);
                startActivity(intent);
            }
        });
    }

}