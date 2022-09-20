package com.example.nutrix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountUpdate extends AppCompatActivity {
    Button home, update, reset;
    EditText  phno,fname,lname,email,secQ, secA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_update);


        fname =(EditText) findViewById(R.id.firstnameText);
        lname = (EditText) findViewById(R.id.lastnameText);
        email = findViewById(R.id.emailText);
        phno = findViewById(R.id.phoneText);
        secQ = findViewById(R.id.securityQText);
        secA = findViewById(R.id.securityAText);

        home = (Button) findViewById(R.id.buttonHome);
        update = (Button) findViewById(R.id.buttonUpdate);
        reset = (Button) findViewById(R.id.buttonReset);

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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("users");

                if (fname.getText().toString().equals("")) {
                    fname.setError("First Name Required");
                    fname.requestFocus();
                }
                else if(lname.getText().toString().equals(""))
                {
                    lname.setError("Last name Required");
                    lname.requestFocus();
                }
                else if(phno.getText().toString().equals("")||phno.getText().length()<10){
                    phno.setError("Invalid Phone Number");
                    phno.requestFocus();
                }
                else if(secA.equals("")){
                    secA.setError("Missing Security Answer");
                    secA.requestFocus();
                }
                else {
                    if (!user_firstname.equals(fname.getText().toString())) {
                        currentUser.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("firstname").setValue(fname.getText().toString());
                    }

                    if (!user_lastname.equals(lname.getText().toString())) {
                        currentUser.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lastname").setValue(lname.getText().toString());
                    }

                    if (!user_phone.equals(phno.getText().toString())) {
                        currentUser.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("phone").setValue(phno.getText().toString());
                    }

                    if (!user_secA.equals(secA.getText().toString())) {
                        currentUser.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("securityAnswer").setValue(secA.getText().toString());
                    }

                    Toast.makeText(getApplicationContext(), "Data Updated!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(v.getContext(), ActivityAccount.class);
                    v.getContext().startActivity(intent);
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname.setText(user_firstname);
                lname.setText(user_lastname);
                phno.setText(user_phone);
                email.setText(user_email);
                secA.setText(user_secA);
                secQ.setText(user_secQ);

            }
        });



    }
}