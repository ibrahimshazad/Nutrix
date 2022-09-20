package com.example.nutrix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class EditMealPlanActivity extends AppCompatActivity {
    TextView add,delete;
    static int nummeals,numdays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal_plan);

        MealPlanClassHelper mealPlanClassHelper = (MealPlanClassHelper) getIntent().getSerializableExtra("mealPlan");
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView add = (TextView) findViewById(R.id.add);
        TextView delete = (TextView) findViewById(R.id.delete);

        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Edit Meal Plan");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);;
        }
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Query ref = rootRef.child("mealplan");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                List<String> days;
                for(DataSnapshot snapshot: datasnapshot.getChildren())
                {
                    long longnummeals = snapshot.getChildrenCount();
                    int numMealsDay = (int) longnummeals;
                    String key = snapshot.getKey();
                    //numdays = Integer.valueOf(snapshot.child("numdays").getValue().toString());
                   // nummeals = Integer.valueOf(snapshot.child("nummeals").getValue().toString());
                }
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(getApplicationContext(),Category.class);
                   startActivity(intent);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),DeleteMealPlanRecipe.class);
                    startActivity(intent);
                }
            });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });




    }
//fix
}