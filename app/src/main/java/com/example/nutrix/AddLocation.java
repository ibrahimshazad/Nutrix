package com.example.nutrix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddLocation extends AppCompatActivity {
    RecyclerView recycleMeal;
    Context context;
    static RecipeHelperClass[] recipeHelperClassFull;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Place Recipe");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);;
        }
        recycleMeal = findViewById(R.id.recycleadd);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(AddLocation.this, LinearLayoutManager.HORIZONTAL, false);
        recycleMeal.setLayoutManager(horizontalLayoutManagaer);
        context = getApplicationContext();

        RecipeHelperClass recipeHelperClass = (RecipeHelperClass) getIntent().getSerializableExtra("recipe");
        String day = getIntent().getSerializableExtra("weekday").toString();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days");
        Query ref = rootRef.child(day);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String key = datasnapshot.getKey();
                long longnummeals = datasnapshot.getChildrenCount();
                int numMealsDay = (int) longnummeals;
                recipeHelperClassFull = new RecipeHelperClass[numMealsDay];
                int j;
                for (DataSnapshot daysnap : datasnapshot.getChildren()) {
                    String recipename, category, description, preptime, totaltime, instructions, priceString, calories, image, ingredientname, ingredientquantity, ingredientprice, category2, nameid;
                    String recipekey = daysnap.getKey();//index of mealplan index num
                    int price;
                    String check = daysnap.child("itemName").getValue().toString();//works

                    long temp = daysnap.child("ingredients").getChildrenCount();
                    int temp2 = (int) temp;
                    String[][] ingredients = new String[temp2][3];
                    for (j = 0; j < temp2; j++) {
                        ingredientname = daysnap.child("itemName").getValue().toString();
                        ingredientprice = daysnap.child("itemName").getValue().toString();
                        ingredientquantity = daysnap.child("itemName").getValue().toString();

                        ingredients[j][0] = ingredientname;
                        ingredients[j][1] = ingredientprice;
                        ingredients[j][2] = ingredientquantity;
                    }
                    recipename = daysnap.child("itemName").getValue().toString();
                    description = daysnap.child("Description").getValue().toString();
                    preptime = daysnap.child("preptime").getValue().toString();
                    calories = daysnap.child("calories").getValue().toString();
                    totaltime = daysnap.child("totaltime").getValue().toString();
                    priceString = daysnap.child("price").getValue().toString();
                    instructions = daysnap.child("steps").getValue().toString();
                    category2 = daysnap.child("category").getValue().toString();
                    nameid = daysnap.child("recipeid").getValue().toString();
                    price = Integer.valueOf(priceString);
                    image = daysnap.child("image").getValue().toString();


                    recipeHelperClassFull[numMealsDay - 1] = new RecipeHelperClass(recipename, nameid, category2, description, "Calories\n" + calories, preptime, "Cook Time\n" + totaltime, instructions, price, image, ingredients);
                    numMealsDay -= 1;

                }

                LocationAdapter mealAdapter = new LocationAdapter(day,recipeHelperClassFull , recipeHelperClass , AddLocation.this);
                recycleMeal.setAdapter(mealAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}