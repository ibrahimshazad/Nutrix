package com.example.nutrix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetail extends AppCompatActivity {
    String choice;
    RecyclerView listRecipes;
    int i = 0;
    int j = 0;
    Context context;
    private FirebaseAuth auth;
    static RecipeHelperClass[] recipeHelperClass;
    static IngredientClassHelper[] ingredientClassHelpers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        auth = FirebaseAuth.getInstance();
        Intent intent1 = getIntent();
        choice = intent1.getStringExtra("choice");
//fix
        listRecipes = findViewById(R.id.listRecipes);
        listRecipes.setHasFixedSize(true);
        listRecipes.setLayoutManager(new LinearLayoutManager(this));
        context = getApplicationContext();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Recipes");
        Query findcategory = ref.child(choice);
        findcategory.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    i++;
                    String tempName = snapshot.getKey();
                    long templong = snapshot.child(tempName).child("ingredients").getChildrenCount();
                }

                recipeHelperClass = new RecipeHelperClass[i];
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String recipename, category,description, preptime, totaltime, instructions, priceString,calories,image,ingredientname,ingredientquantity,ingredientprice;
                    int j=0;
                    int price;
                    String reference = snapshot.getKey();
                  long temp = snapshot.child("ingredients").getChildrenCount();
                  int temp2 = (int)temp;
                    String[][] ingredients = new String[temp2][3];
                    for ( j = 0;j<temp2;j++) {
                        ingredientname = dataSnapshot.child(reference).child("ingredients").child(""+String.valueOf(j)).child("name").getValue().toString();
                        ingredientprice = (dataSnapshot.child(reference).child("ingredients").child(""+String.valueOf(j)).child("price").getValue().toString());
                        ingredientquantity = (dataSnapshot.child(reference).child("ingredients").child(""+String.valueOf(j)).child("quantity").getValue().toString());

                        ingredients[j][0]=ingredientname;
                        ingredients[j][1]=ingredientprice;
                        ingredients[j][2]=ingredientquantity;
                    }


                    recipename = dataSnapshot.child(reference).child("itemName").getValue().toString();
                    description = dataSnapshot.child(reference).child("Description").getValue().toString();
                    preptime = dataSnapshot.child(reference).child("preptime").getValue().toString();
                    calories = dataSnapshot.child(reference).child("calories").getValue().toString();
                    totaltime = dataSnapshot.child(reference).child("totaltime").getValue().toString();
                    instructions = dataSnapshot.child(reference).child("steps").getValue().toString();
                    priceString = dataSnapshot.child(reference).child("price").getValue().toString();
                    price = Integer.valueOf(priceString);
                    image = dataSnapshot.child(reference).child("image").getValue().toString();


                   recipeHelperClass[i-1] = new  RecipeHelperClass(recipename,reference,choice, description,"Calories\n"+calories, preptime,"Cook Time\n"+ totaltime, instructions, price,image,ingredients);
                    i-=1;
                }
                RecipeBrowserAdapter recipeAdapter = new RecipeBrowserAdapter(recipeHelperClass,RecipeDetail.this);
                listRecipes.setAdapter(recipeAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                /*FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(recipeHelperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });*/
            }
        });
    }
    public void openRecipeFullDetail() {
        Intent intent = new Intent(this, RecipeFullDetail.class);
        startActivity(intent);
    }
}


