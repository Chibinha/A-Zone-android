package com.example.azone_android.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.azone_android.models.Category;
import com.example.azone_android.models.Product;

import java.util.ArrayList;

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

    /* CRUD Product */

    public void insertProductDB(Product product) {
        ContentValues values = new ContentValues();
        values.put(PRODUCT_ID, product.getId());
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_DESCRIPTION, product.getDescription());
        values.put(PRODUCT_PRICE, product.getPrice());
        values.put(PRODUCT_IMAGE, product.getImage());
        values.put(PRODUCT_CATEGORY, product.getIdCategory());

        this.database.insert(PRODUCT_TABLE_NAME, null, values);
    }

    public void deleteAllProductDB() {
        this.database.delete(PRODUCT_TABLE_NAME, null, null);
    }

    public ArrayList<Product> getAllProductsDB() {
        ArrayList<Product> products = new ArrayList<>();
        Cursor cursor = this.database.query(PRODUCT_TABLE_NAME,
                new String[]{PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_IMAGE, PRODUCT_CATEGORY},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Product auxProduct = new Product(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getFloat(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5));
                auxProduct.setId(cursor.getInt(0));
                products.add(auxProduct);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return products;
    }

    public ArrayList<Product> getAllProductsByCategoryDB(int id_category) {
        ArrayList<Category> mAllCategories = new ArrayList<>();
        mAllCategories = getAllCategoriesDB();

        ArrayList<Category> mCategories = new ArrayList<>();
        for (Category category: mAllCategories) {
            if (category.getParent_id() == id_category || category.getId() == id_category){
                mCategories.add(category);
            }
        }

        ArrayList<Product> mAllProducts = new ArrayList<>();
        mAllProducts = getAllProductsDB();

        ArrayList<Product> CategoryProducts = new ArrayList<>();
        for (Category category: mCategories) {
            for (Product product : mAllProducts) {
                if (product.getIdCategory() == category.getId()) {
                    CategoryProducts.add(product);
                }
            }
        }
        return CategoryProducts;
    }

    /* CRUD Category */

    public ArrayList<Category> getAllCategoriesDB() {
        ArrayList<Category> categories = new ArrayList<>();
        Cursor cursor = this.database.query(CATEGORY_TABLE_NAME,
                new String[]{CATEGORY_ID, CATEGORY_NAME, CATEGORY_PARENT},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Category auxCategory = new Category(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2)
                );
                auxCategory.setId(cursor.getInt(0));
                categories.add(auxCategory);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return categories;
    }

    public void insertCategoryDB(Category category) {
        ContentValues values = new ContentValues();
        values.put(CATEGORY_ID, category.getId());
        values.put(CATEGORY_NAME, category.getName());
        values.put(CATEGORY_PARENT, category.getParent_id());

        this.database.insert(CATEGORY_TABLE_NAME, null, values);
    }

    public void deleteAllCategoriesDB() {
        this.database.delete(CATEGORY_TABLE_NAME, null, null);
    }

}
