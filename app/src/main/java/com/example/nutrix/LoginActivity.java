package com.example.nutrix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vishnusivadas.advanced_httpurlconnection.PutData;
public class LoginActivity extends AppCompatActivity {
    private EditText email,password;
    TextView signup,resetpassword,signin;
    ProgressBar progressBar;
    int counter = 0;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById((R.id.password));
        signin  = (TextView) findViewById(R.id.btnsignin);
        signup  = (TextView) findViewById(R.id.btnsignup);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        resetpassword = (TextView) findViewById(R.id.resetpassword);
        auth = FirebaseAuth.getInstance();//fix
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1,password1;
                email1 = String.valueOf(email.getText());
                password1 = String.valueOf(password.getText());
                progressBar = findViewById(R.id.progress);
                if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
                    email.setError("Invalid Email");
                    email.requestFocus();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    loginUser(email1,password1);
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationActivity();
            }
        });
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResetPasswordActivity();
            }
        });
    }
    public void loginUser(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    openMainActivity();

                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void openRegistrationActivity(){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MealPlanActivity.class);
        startActivity(intent);
    }
    public void openResetPasswordActivity(){
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }
}