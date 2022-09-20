package com.example.nutrix;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.auth.FirebaseAuth;


public class ViewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        Button home;
        TextView fname, lname, email, phno, secQ, secA;
        fname = findViewById(R.id.firstnameText);
        lname = findViewById(R.id.lastnameText);
        email = findViewById(R.id.emailText);
        phno = findViewById(R.id.phoneText);
        secQ = findViewById(R.id.securityQText);
        secA = findViewById(R.id.securityAText);

        home = (Button) findViewById(R.id.buttonHome);

        Intent intent = getIntent();
        String user_email = intent.getStringExtra("email");
        String user_firstname = intent.getStringExtra("firstname");
        String user_lastname = intent.getStringExtra("lastname");
        String user_phone = intent.getStringExtra("phone");
        String user_secA = intent.getStringExtra("secA");
        String user_secQ = intent.getStringExtra("secQ");

        fname.setText(user_firstname);
        lname.setText(user_lastname);
        phno.setText(user_phone);
        email.setText(user_email);
        secA.setText(user_secA);
        secQ.setText(user_secQ);

        home.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent downloadIntent = new Intent(v.getContext(), MealPlanActivity.class);
                v.getContext().startActivity(downloadIntent);
            }
        });

    }
}