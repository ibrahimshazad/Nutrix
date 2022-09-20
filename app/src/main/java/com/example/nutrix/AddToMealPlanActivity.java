package com.example.nutrix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddToMealPlanActivity extends AppCompatActivity {
    TextView monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_meal_plan);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView monday = (TextView) findViewById(R.id.addmonday);
        TextView tuesday = (TextView) findViewById(R.id.addtuesday);//
        TextView wednesday = (TextView) findViewById(R.id.addwednesday);
        TextView thursday = (TextView) findViewById(R.id.addthursday);
        TextView friday = (TextView) findViewById(R.id.addfriday);
        TextView saturday = (TextView) findViewById(R.id.addsaturday);
        TextView sunday = (TextView) findViewById(R.id.addsunday);
        RecipeHelperClass recipeHelperClass = (RecipeHelperClass) getIntent().getSerializableExtra("recipe");
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Select Day");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);;
        }
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference checkday = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days");
                checkday.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("Monday")) {
                            Intent intent = new Intent(getApplicationContext(), AddLocation.class);
                            intent.putExtra("weekday","Monday");
                            intent.putExtra("recipe",recipeHelperClass);
                            startActivity(intent);
                        }
                        else
                        {
                            String test = snapshot.getKey();
                            Toast.makeText(getApplicationContext(), "No Recipes For" +test+ " This Day in Meal Plan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference checkday = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days");
                checkday.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("Tuesday")) {
                            Intent intent = new Intent(getApplicationContext(), AddLocation.class);
                            intent.putExtra("weekday","Tuesday");
                            intent.putExtra("recipe",recipeHelperClass);
                            startActivity(intent);
                        }
                        else
                        {
                            String test = snapshot.getKey();
                            Toast.makeText(getApplicationContext(), "No Recipes For" +test+ " This Day in Meal Plan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference checkday = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days");
                checkday.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("Wednesday")) {
                            Intent intent = new Intent(getApplicationContext(), AddLocation.class);
                            intent.putExtra("weekday","Wednesday");
                            intent.putExtra("recipe",recipeHelperClass);
                            startActivity(intent);
                        }
                        else
                        {
                            String test = snapshot.getKey();
                            Toast.makeText(getApplicationContext(), "No Recipes For" +test+ " This Day in Meal Plan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference checkday = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days");
                checkday.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("Thursday")) {
                            Intent intent = new Intent(getApplicationContext(), AddLocation.class);
                            intent.putExtra("weekday","Thursday");
                            intent.putExtra("recipe",recipeHelperClass);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "No Recipes For This Day in Meal Plan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference checkday = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days");
                checkday.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("Friday")) {
                            Intent intent = new Intent(getApplicationContext(), AddLocation.class);
                            intent.putExtra("weekday","Friday");
                            intent.putExtra("recipe",recipeHelperClass);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "No Recipes For This Day in Meal Plan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference checkday = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days");
                checkday.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("Saturday")) {
                            Intent intent = new Intent(getApplicationContext(), AddLocation.class);
                            intent.putExtra("weekday","Saturday");
                            intent.putExtra("recipe",recipeHelperClass);
                            startActivity(intent);
                        }
                        else
                        {
                            String test = snapshot.getKey();
                            Toast.makeText(getApplicationContext(), "No Recipes For This Day in Meal Plan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference checkday = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days");
                checkday.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("Sunday")) {
                            Intent intent = new Intent(getApplicationContext(), AddLocation.class);
                            intent.putExtra("weekday","Sunday");
                            intent.putExtra("recipe",recipeHelperClass);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "No Recipes For This Day in Meal Plan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}