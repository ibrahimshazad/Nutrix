package com.example.nutrix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class NewMealPlanActivity extends AppCompatActivity {

    TextView dailymeals,calories,create,cancel;
    Spinner spin;
   MealPlanClassHelper mealPlanClassHelper;
   public static List<String> daysarraylist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal_plan);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Create New Meal Plan");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);;
        }

        spin = findViewById(R.id.dayselector);
        spinner_days_adapter days_adapter = new spinner_days_adapter(getApplicationContext(), android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.dayarray));
        spin.setAdapter(days_adapter);
        dailymeals = (TextView) findViewById(R.id.dailymeals);//fix
        calories = (TextView) findViewById(R.id.calories);
        cancel = (TextView) findViewById(R.id.cancel);
        create = (TextView) findViewById(R.id.create);
        int i;
        int numdays;
        daysarraylist = new ArrayList<String>();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MealPlanActivity.class);
                startActivity(intent);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String meals, days, cals;
                meals = String.valueOf(dailymeals.getText());
                cals = String.valueOf(calories.getText());
                if(meals.length() < 1)
                {
                    dailymeals.setError("Invalid Input");
                    dailymeals.requestFocus();
                }
                else if(daysarraylist.size() < 1){
                    Toast.makeText(getApplicationContext(), "Please Select at Least One Day", Toast.LENGTH_LONG).show();
                }
                else if(cals.length() < 1){
                    calories.setError("Invalid Input");
                    calories.requestFocus();
                }
                else {
                    int size = daysarraylist.size()*Integer.valueOf(meals);
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("cart").removeValue();
                    for(int i = 0 ; i < size; i++)
                    {
                        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("cart").child(""+i).child("name").setValue("empty");
                        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("cart").child(""+i).child("price").setValue(""+0);
                        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("cart").child(""+i).child("quantity").setValue(""+0);
                    }

                    mealPlanClassHelper = new MealPlanClassHelper(daysarraylist.size(), daysarraylist, Integer.valueOf(meals), Integer.valueOf(cals));
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").setValue(mealPlanClassHelper);
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days").removeValue();
                    int k,l;
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days").removeValue();
                    for(k = 0;k<daysarraylist.size();k++)
                    {
                       DatabaseReference ref =  FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days").child(daysarraylist.get(k));
                        for(l=0;l<Integer.valueOf(meals);l++){
                            ref.child(""+l).child("Description").setValue("");
                            ref.child(""+l).child("calories").setValue(" ");
                            ref.child(""+l).child("image").setValue(" ");
                            ref.child(""+l).child("ingredients").setValue(" ");
                            ref.child(""+l).child("ingredients").child(""+0).child("name").setValue("empty");
                            ref.child(""+l).child("ingredients").child(""+0).child("price").setValue("0");
                            ref.child(""+l).child("ingredients").child(""+0).child("quantity").setValue("0");
                            ref.child(""+l).child("totaltime").setValue(" ");
                            ref.child(""+l).child("itemName").setValue("Name");
                            ref.child(""+l).child("preptime").setValue(" ");
                            ref.child(""+l).child("price").setValue(0);
                            ref.child(""+l).child("steps").setValue(" ");
                            ref.child(""+l).child("category").setValue(" ");
                            ref.child(""+l).child("recipeid").setValue(" ");
                        }
                    }

                    Toast.makeText(getApplicationContext(), "Mealplan Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MealPlanActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

}