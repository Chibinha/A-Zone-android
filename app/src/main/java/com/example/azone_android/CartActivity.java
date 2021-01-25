package com.example.azone_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.azone_android.adapters.CartListAdapter;
import com.example.azone_android.models.CartItem;
import com.example.azone_android.models.Product;
import com.example.azone_android.models.SingletonStore;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements CartListAdapter.OnCartItemClickListener  {

    private ListView lv_Products;
    private TextView tv_total;
    private Button btn_checkout;
    private String total;
    private ArrayList<CartItem> cart;
    private CartListAdapter mCartListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle(R.string.Cart_title);
        cart = SingletonStore.getInstance(this).getCart();

        lv_Products = findViewById(R.id.listCartProducts);
        tv_total = findViewById(R.id.textView_Total);
        btn_checkout = findViewById(R.id.button_Checkout);

        mCartListAdapter = new CartListAdapter(this, cart, this);
        lv_Products.setAdapter(mCartListAdapter);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_checkout.setEnabled(true);
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

        lv_Products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                intent.putExtra("ID", product.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCartItemClick(int position, int id) {
        Product product = SingletonStore.getInstance(this).getProduct(id);
        Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
        intent.putExtra("ID", product.getId());
        startActivity(intent);
    }
}
