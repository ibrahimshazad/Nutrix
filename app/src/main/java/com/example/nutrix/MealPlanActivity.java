package com.example.nutrix;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MealPlanActivity extends AppCompatActivity{
    RecyclerView recycleMeal;
    TextView newplan,editplan,viewplan;
    Context context;
    static List<String> days;
    static RecipeHelperClass[] recipeHelperClass;
    static RecipeHelperClass[] fullRecipeHelperClass;
    static MealPlanClassHelper mealPlanClassHelpers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);
        TextView newplan = findViewById(R.id.newplan);
        TextView editplan = findViewById(R.id.editplan);
        TextView viewplan = findViewById(R.id.viewplan);
        editplan.setVisibility(View.GONE);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Nutrix Meal Plan");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);;
        }
        recycleMeal = findViewById(R.id.listRecipes);
      // recycleMeal.setHasFixedSize(true);
        //recycleMeal.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MealPlanActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recycleMeal.setLayoutManager(horizontalLayoutManagaer);
        context = getApplicationContext();
        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.getDateInstance().format(calendar.getTime());
        TextView textViewdate = findViewById(R.id.date);
        String day = (LocalDate.now().getDayOfWeek().name()).toString().toLowerCase();

        String output = day.substring(0, 1).toUpperCase() + day.substring(1);
        textViewdate.setText((output+" "+date));
        BottomNavigationView bottomNavigationView = findViewById(R.id.navi_menu);
        bottomNavigationView.setSelectedItemId(R.id.menu_mealplan);
        DatabaseReference checkmeal = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        checkmeal.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild("mealplan")) {
                    viewplan.setVisibility(View.VISIBLE);
                    editplan.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan");
        Query ref = rootRef.child("days");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    if (key.equals(output)) {
                        int j;
                        long longnummeals = snapshot.getChildrenCount();
                        int numMealsDay = (int) longnummeals;
                        recipeHelperClass = new RecipeHelperClass[numMealsDay];
                        for (DataSnapshot daysnap : datasnapshot.child(key).getChildren()) {
                            String recipename, category, description, preptime, totaltime, instructions, priceString, calories, image, ingredientname, ingredientquantity, ingredientprice, category2, nameid;
                            String recipekey = daysnap.getKey();//index of mealplan index num
                            int price;
                            String check = daysnap.child("itemName").getValue().toString();//works

                            long temp = daysnap.child("ingredients").getChildrenCount();
                            int temp2 = (int) temp;
                            String[][] ingredients = new String[temp2][3];
                            for (j = 0; j < temp2; j++) {
                                ingredientname = daysnap.child("ingredients").child(""+j).child("name").getValue().toString();
                                ingredientprice = daysnap.child("ingredients").child(""+j).child("price").getValue().toString();
                                ingredientquantity = daysnap.child("ingredients").child(""+j).child("quantity").getValue().toString();

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
                                recipeHelperClass[numMealsDay - 1] = new RecipeHelperClass(recipename, nameid, category2, description,  ""+calories, preptime, "" + totaltime, instructions, price, image, ingredients);
                                numMealsDay -= 1;

                        }

                        MealPlanAdapterClass mealAdapter = new MealPlanAdapterClass(recipeHelperClass, MealPlanActivity.this);
                        recycleMeal.setAdapter(mealAdapter);
                    }
                    int j;

                    long longnummeals = snapshot.getChildrenCount();
                    int numMealsDay = (int) longnummeals;
                    int k = 0;
                    fullRecipeHelperClass = new RecipeHelperClass[numMealsDay];
                    for (DataSnapshot daysnap : datasnapshot.child(key).getChildren()) {
                        String recipename, category, description, preptime, totaltime, instructions, priceString, calories, image, ingredientname, ingredientquantity, ingredientprice, category2, nameid;
                        String recipekey = daysnap.getKey();//index of mealplan index num
                        int price;
                        String check = daysnap.child("itemName").getValue().toString();//works

                        long temp = daysnap.child("ingredients").getChildrenCount();
                        int temp2 = (int) temp;
                        String[][] ingredients = new String[temp2][3];
                        for (j = 0; j < temp2; j++) {
                            ingredientname = daysnap.child("ingredients").child(""+j).child("name").getValue().toString();
                            ingredientprice = daysnap.child("ingredients").child(""+j).child("price").getValue().toString();
                            ingredientquantity = daysnap.child("ingredients").child(""+j).child("quantity").getValue().toString();

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


                        fullRecipeHelperClass[numMealsDay - 1] = new RecipeHelperClass(recipename, nameid, category2, description,  ""+calories, preptime, "" + totaltime, instructions, price, image, ingredients);
                        numMealsDay -= 1;



                        //put suff here
                    }
                    editplan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openEditActivity();
                        }
                    });
                viewplan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),ViewMealPlanActivity.class);
                        intent.putExtra("recipe",fullRecipeHelperClass);
                        startActivity(intent);



                    }
                });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });





        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {//fix
                switch(item.getItemId()) {
                    case R.id.menu_mealplan:
                         return true;
                    case R.id.menu_shoppingcart:
                        startActivity(new Intent(getApplicationContext(),CartActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_account:
                        startActivity(new Intent(getApplicationContext(), ActivityAccount.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });//hello
    newplan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openNewPlanActivity();
        }
    });

}

    public void openNewPlanActivity(){
        Intent intent = new Intent(getApplicationContext(),NewMealPlanActivity.class);
        startActivity(intent);
    }
    public void openEditActivity() {
        Intent intent = new Intent(this, EditMealPlanActivity.class);
        startActivity(intent);
    }
}