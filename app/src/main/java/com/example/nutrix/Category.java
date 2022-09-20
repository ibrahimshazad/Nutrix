package com.example.nutrix;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Category extends AppCompatActivity {

    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    ArrayList<String>itemNameList;
    ArrayList<String>caloriesList;
    ArrayList<String>imageList;
    SearchAdapter searchAdapter;

    CardView chicken, beef, salad, dessert, seafood, pasta;
    ImageView favorite;
//fix
    String food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        favorite = (ImageView) findViewById(R.id.favbtn);   //Show saved/favorite recipes

        chicken = (CardView) findViewById(R.id.chickenbtn);
        beef = (CardView) findViewById(R.id.beefbtn);
        salad = (CardView) findViewById(R.id.saladbtn);
        dessert = (CardView) findViewById(R.id.dessertbtn);
        seafood = (CardView) findViewById(R.id.seafoodbtn);
        pasta = (CardView) findViewById(R.id.pastabtn);

        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food ="Chicken";
                openRecipeDetail(food); }
        });

        beef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Beef";
                openRecipeDetail(food); }
        });

        salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Salad";
                openRecipeDetail(food); }
        });

        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Dessert";
                openRecipeDetail(food); }
        });

        seafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Seafood";
                openRecipeDetail(food); }
        });

        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Pasta";
                openRecipeDetail(food); }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openFavorite(); }
        });


        //search activity
        search_edit_text = (EditText) findViewById(R.id.search_edit_text);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        itemNameList = new ArrayList<>();
        imageList = new ArrayList<>();
        caloriesList = new ArrayList<>();



        //search bar code

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    setAdapter(s.toString());
                }else{
                    itemNameList.clear();
                    caloriesList.clear();
                    imageList.clear();
                    recyclerView.removeAllViews();
                }

            }
        });

        }

    private void setAdapter(final String searchedString) {

        databaseReference.child("Search").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemNameList.clear();
                caloriesList.clear();
                imageList.clear();
                recyclerView.removeAllViews();

                for (DataSnapshot snapshot: dataSnapshot.getChildren() ){
                    int counter = 0;

                    String uid = snapshot.getKey();
                    String reference = snapshot.getKey();
                    String itemName = dataSnapshot.child(reference).child("itemName").getValue().toString();
                    //String image = dataSnapshot.child(reference).child("image").getValue().toString();
                    String calories = dataSnapshot.child(reference).child("calories").getValue().toString();


                    if (itemName.toLowerCase().contains(searchedString.toLowerCase())) {
                        itemNameList.add(itemName);
                        caloriesList.add(calories);
                        //imageList.add(image);
                        counter++;
                    }
                    //else

                    if(counter == 5)
                        break;
                }
                searchAdapter =  new SearchAdapter(Category.this, itemNameList,caloriesList,imageList);
                recyclerView.setAdapter(searchAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    public void openRecipeDetail(String food){
        Intent intent = new Intent(this, RecipeDetail.class);
        intent.putExtra("choice",food);
        startActivity(intent);
    }

    public void openFavorite(){
        Intent intent = new Intent(this, Favorite.class);
        startActivity(intent);
    }
}