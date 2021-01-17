package com.example.azone_android;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private TextView tv_username;
    private TextView tv_address;
    private TextView tv_postalCode;
    private TextView tv_city;
    private TextView tv_country;
    private TextView tv_total;
    private Button btn_placeOrder;
    private String total;
    private ListView lv_Products;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        setTitle(R.string.checkout_title);

        tv_username = findViewById(R.id.textViewName);
        tv_address = findViewById(R.id.textViewAdress);
        tv_postalCode = findViewById(R.id.textViewPostalCode);
        tv_city = findViewById(R.id.textViewCity);
        tv_country = findViewById(R.id.textViewCountry);
        tv_total = findViewById(R.id.textView_Total);
        btn_placeOrder = findViewById(R.id.buttonPlaceOrder);
        lv_Products = findViewById(R.id.listViewCartItems);
        mContext = getApplicationContext();
    }
}
