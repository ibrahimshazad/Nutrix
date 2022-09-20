package com.example.nutrix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText email, firstname, lastname, phone, security, password;
    TextView login, register;
    ProgressBar progressBar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email = (EditText) findViewById(R.id.email);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById((R.id.password));
        security = (EditText) findViewById((R.id.security));
        register = (TextView) findViewById(R.id.registerbtn);//fix
        login = (TextView) findViewById(R.id.loginbtn);
        progressBar = findViewById(R.id.progress);

        Spinner spinner = findViewById(R.id.securityQ);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.security_questions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname1, lastname1, securityAnswer, email1, password1, phone1, securityQuestion;
                firstname1 = String.valueOf(firstname.getText());
                lastname1 = String.valueOf(lastname.getText());
                phone1 = String.valueOf(phone.getText());
                email1 = String.valueOf(email.getText());
                password1 = String.valueOf(password.getText());
                securityAnswer = String.valueOf(security.getText());
                securityQuestion = spinner.getSelectedItem().toString();
                auth = FirebaseAuth.getInstance();
                progressBar = findViewById(R.id.progress);

                if (firstname1.equals("")) {
                    firstname.setError("First Name Required");
                    firstname.requestFocus();
                }
                else if(lastname1.equals(""))
                {
                    lastname.setError("Last name Required");
                    lastname.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
                    email.setError("Invalid Email");
                    email.requestFocus();
                }
                else if(phone1.equals("")||phone1.length()<10){
                    phone.setError("Invalid Phone Number");
                    phone.requestFocus();
                }
                else if(password1.length()<6)
                {
                    password.setError("Password Must be at Least 6 Characters");
                    password.requestFocus();
                }
                else if(securityAnswer.equals("")){
                    security.setError("Missing Security Answer");
                    security.requestFocus();
                }
                else {
                        progressBar.setVisibility(View.VISIBLE);
                    auth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                UserHelperClass helperClass = new UserHelperClass(firstname1,lastname1,phone1,email1,password1,securityQuestion,securityAnswer);
                                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });

                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
        ((TextView) parent.getChildAt(0)).setTextSize(15);
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}