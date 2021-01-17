package com.example.azone_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    Button btn_signUp;
    EditText mUsername;
    EditText mEmail;
    EditText mPassword;
    EditText mConfirmPassword;
    EditText mFirstName;
    EditText mLastName;
    EditText mPhone;
    EditText mAddress;
    EditText mCity;
    EditText mPostalCode;
    EditText mCountry;
    EditText mNif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle(R.string.nav_signup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_signUp = findViewById(R.id.button_signup);
        mUsername = findViewById(R.id.editText_username);
        mEmail = findViewById(R.id.editText_email);
        mPassword = findViewById(R.id.editText_password);
        mConfirmPassword = findViewById(R.id.editText_confirmPassword);
        mFirstName = findViewById(R.id.editText_firstName);
        mLastName = findViewById(R.id.editText_lastName);
        mPhone = findViewById(R.id.editText_phone);
        mAddress = findViewById(R.id.editText_address);
        mCity = findViewById(R.id.editText_city);
        mPostalCode = findViewById(R.id.editText_postalCode);
        mCountry = findViewById(R.id.editText_country);
        mNif = findViewById(R.id.editText_nif);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}