package com.example.nutrix;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeFullDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView description,preptime,totaltime,price,steps,calories,category,ingredients;
        ImageView image,add,fav;
        setContentView(R.layout.activity_recipe_full_detail);
        add = (ImageView) findViewById(R.id.addbtn);
        fav = (ImageView) findViewById(R.id.favbtn);
        RecipeHelperClass recipeHelperClass = (RecipeHelperClass) getIntent().getSerializableExtra("selectedrecipe");

        description = findViewById(R.id.detailDescription);
        description.setText(
                recipeHelperClass.getDescription()
        );
        category = findViewById(R.id.tvCategory);
        category.setText(
                recipeHelperClass.getCategory()//fix
        );
        preptime = findViewById(R.id.detailPrepTime);
        preptime.setText(
                "Prep Time\n"+recipeHelperClass.getPreptime()
        );
        ingredients = findViewById(R.id.detailIngridients);
        String[][] ingredients2= recipeHelperClass.getIngredients();
        int i = ingredients2.length;
        int j;
        String in = "";
        for(j = 0;j<i;j++)
        {
           in+=ingredients2[j][0];
            in+="\n";
        }
        ingredients.setText(in);
        totaltime = findViewById(R.id.detailTotalTime);
        totaltime.setText(
                recipeHelperClass.getTotaltime()
        );
        price = findViewById(R.id.detailPrice);
        price.setText(
                "Price\n$"+String.valueOf(recipeHelperClass.getPrice())
        );
        steps = findViewById(R.id.detailSteps);
        steps.setText(
                recipeHelperClass.getInstructions()
        );
        image = (ImageView)findViewById(R.id.image2);
        Picasso.get().load(String.valueOf(recipeHelperClass.getImage())).into(image);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddToMealPlanActivity.class);
                intent.putExtra("recipe",recipeHelperClass);
                startActivity(intent);

            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child(recipeHelperClass.getNameid()).child("Descripton").setValue(recipeHelperClass.getDescription());
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child(recipeHelperClass.getNameid()).child("calories").setValue(recipeHelperClass.getCalories());
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child(recipeHelperClass.getNameid()).child("category").setValue(recipeHelperClass.getCategory());
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child(recipeHelperClass.getNameid()).child("image").setValue(recipeHelperClass.getImage());
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child(recipeHelperClass.getNameid()).child("itemname").setValue(recipeHelperClass.getRecipename());
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child(recipeHelperClass.getNameid()).child("preptime").setValue(recipeHelperClass.getPreptime());
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child(recipeHelperClass.getNameid()).child("price").setValue(recipeHelperClass.getPrice());
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child(recipeHelperClass.getNameid()).child("recipeid").setValue(recipeHelperClass.getNameid());
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child(recipeHelperClass.getNameid()).child("steps").setValue(recipeHelperClass.getInstructions());
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child(recipeHelperClass.getNameid()).child("totaltime").setValue(recipeHelperClass.getTotaltime());


                List<String> ingredientsx= new ArrayList<>();
                int k;
                for(String[] array:ingredients2)
                {
                    ingredientsx.addAll(Arrays.asList(array));
                }
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("favorites").child("ingredients").child(recipeHelperClass.getNameid()).setValue(ingredientsx);
                Toast.makeText(getApplicationContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }
}