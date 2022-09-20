package com.example.nutrix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmFinalOrderActivity extends AppCompatActivity
{
    private EditText nameEditText , PhoneEditText, AddressEditText, CityEditText,CardEditText;
    private Button confirmOrderBtn;
    private String totalAmount = "";
//fix
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);


        confirmOrderBtn = (Button) findViewById(R.id.Confirm_Final_order);
        nameEditText = (EditText) findViewById(R.id.shipment_name);
        PhoneEditText= (EditText) findViewById(R.id.shipment_phone_number);
        AddressEditText = (EditText) findViewById(R.id.shipment_address);
        CityEditText = (EditText) findViewById(R.id.shipment_city);
        CardEditText = (EditText) findViewById(R.id.card_details);

        confirmOrderBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Check();
            }
        });



    }

    private void Check()
    {
        int length = CardEditText.getText().length();
        if(TextUtils.isEmpty(nameEditText.getText().toString()))
        {
            Toast.makeText(this,"Please provide your full name.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(PhoneEditText.getText().toString()))
        {
            Toast.makeText(this,"Please provide your phone number.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(AddressEditText.getText().toString()))
        {
            Toast.makeText(this,"Please provide your full address.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(CityEditText.getText().toString()))
        {
            Toast.makeText(this,"Please provide your City name.",Toast.LENGTH_SHORT).show();
        }
        else if(length != 16)
        {
            Toast.makeText(this,"Please provide valid payment details",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Your order has been successfully placed.",Toast.LENGTH_SHORT).show();
            startActivity((new Intent(getApplicationContext(),MealPlanActivity.class)));
        }
    }
}
