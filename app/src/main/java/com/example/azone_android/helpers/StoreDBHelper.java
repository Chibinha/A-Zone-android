package com.example.azone_android.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StoreDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "storeDB";
    private static final int DB_VERSION = 1;

    private final SQLiteDatabase database;

    // Table Category
    private static final String CATEGORY_TABLE_NAME = "category";
    private static final String CATEGORY_ID = "id";
    private static final String CATEGORY_NAME = "name";
    private static final String CATEGORY_PARENT = "parent_id";

    // Table Products
    private static final String PRODUCT_TABLE_NAME = "product";
    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_DESCRIPTION = "description";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_IMAGE = "image";
    private static final String PRODUCT_CATEGORY = "category_id";

    public StoreDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableCategory = "CREATE TABLE " + CATEGORY_TABLE_NAME + "(" +
                CATEGORY_ID + " INTEGER PRIMARY KEY, " +
                CATEGORY_NAME + " TEXT NOT NULL, " +
                CATEGORY_PARENT + " INTEGER, " +
                "FOREIGN KEY (" + CATEGORY_PARENT + ") REFERENCES " + CATEGORY_TABLE_NAME + "(" + CATEGORY_ID + "));";

        String createTableProduct = "CREATE TABLE " + PRODUCT_TABLE_NAME + "(" +
                PRODUCT_ID + " INTEGER PRIMARY KEY, " +
                PRODUCT_NAME + " TEXT NOT NULL, " +
                PRODUCT_DESCRIPTION + " TEXT NOT NULL, " +
                PRODUCT_PRICE + " REAL NOT NULL, " +
                PRODUCT_IMAGE + " TEXT NOT NULL, " +
                PRODUCT_CATEGORY + " INTEGER, " +
                "FOREIGN KEY (" + PRODUCT_CATEGORY + ") REFERENCES " + CATEGORY_TABLE_NAME + "(" + CATEGORY_ID + "));";

        db.execSQL(createTableCategory);
        db.execSQL(createTableProduct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME);
        this.onCreate(db);
    }




}