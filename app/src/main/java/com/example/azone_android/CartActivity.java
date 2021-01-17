package com.example.azone_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private ListView lv_Products;
    private TextView tv_total;
    private Button btn_checkout;
    private String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle(R.string.Cart_title);

        lv_Products = findViewById(R.id.listCartProducts);
        tv_total = findViewById(R.id.textView_Total);
        btn_checkout = findViewById(R.id.button_Checkout);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_checkout.setEnabled(true);
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });
    }

}
