package com.example.nutrix;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ActivityAccount extends AppCompatActivity {
    Toolbar toolbar;
    TextView buttonUpdate,securitybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Account");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);;
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.navi_menu);
        bottomNavigationView.setSelectedItemId(R.id.menu_shoppingcart);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.menu_mealplan:
                        startActivity(new Intent(getApplicationContext(),MealPlanActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_shoppingcart:
                        return true;
                    case R.id.menu_account:
                        startActivity(new Intent(getApplicationContext(), ActivityAccount.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        buttonUpdate = (TextView) findViewById(R.id.buttonUpdate);
        securitybtn = (TextView) findViewById(R.id.security);
        // Code to go to another activity -

        buttonUpdate.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("users");
                Query ifexists = currentUser.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                ifexists.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            String emailFromDB = snapshot.child("email").getValue(String.class);
                            String firstFromDB = snapshot.child("firstname").getValue(String.class);
                            String lastFromDB = snapshot.child("lastname").getValue(String.class);
                            String phoneFromDB = snapshot.child("phone").getValue(String.class);
                            String secAFromDB = snapshot.child("securityAnswer").getValue(String.class);
                            String secQFromDB = snapshot.child("securityQuestion").getValue(String.class);


                            Intent intent = new Intent(getApplicationContext(), AccountUpdate.class);


                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("firstname", firstFromDB);
                            intent.putExtra("lastname", lastFromDB);
                            intent.putExtra("phone", phoneFromDB);
                            intent.putExtra("secA", secAFromDB);
                            intent.putExtra("secQ", secQFromDB);

                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "DataSnapshot Error", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Database Connection Error", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });


    }
}