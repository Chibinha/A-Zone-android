package com.example.azone_android.listeners;

import com.example.azone_android.models.Product;

import java.util.ArrayList;

public interface ProductListener {
    void onRefreshProductList(ArrayList<Product> productArrayList);
}

