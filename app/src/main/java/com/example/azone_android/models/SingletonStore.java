package com.example.azone_android.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.azone_android.R;
import com.example.azone_android.helpers.StoreDBHelper;

import java.util.ArrayList;

public class SingletonStore {

    private static SingletonStore instance = null;
    private static String sApiUrl;
    private static Context sContext;

    private RequestQueue mRequestQueue;
    private ArrayList<Product> mProductList;
    private ArrayList<Category> mCategoryList;
    private StoreDBHelper mStoreDB;


    private SingletonStore(Context context) {
        sContext = context;
        mRequestQueue = getRequestQueue();
        mProductList = new ArrayList<>();
        mCategoryList = new ArrayList<>();
        mStoreDB = new StoreDBHelper(context);
    }

    public static synchronized SingletonStore getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonStore(context);
        }
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.app_preferences), context.MODE_PRIVATE);
        sApiUrl = preferences.getString(context.getString(R.string.app_api), "");

        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(sContext.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    public String getApiUrl() {
        return sApiUrl;
    }

    public static boolean isConnectedInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
