package com.example.indi_pro3;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Activity activity;
    private static final String DATABASE_NAME = "my_store";

    private static final String PRODUCT_TABLE = "product";

    private static final String CREATE_PRODUCTION = "CREATE TABLE " + PRODUCT_TABLE + " (" + TAGS.TAG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TAGS.TAG_NAME + " TEXT,"
            + TAGS.TAG_DESCRIPTION + " TEXT,"
            + TAGS.TAG_DESCRIPTION_2 + " TEXT,"
            + TAGS.TAG_PRICE + " TEXT,"
            + TAGS.TAG_QTY + " TEXT,"
            + TAGS.TAG_SIZE + " TEXT,"
            + TAGS.TAG_POWER + " TEXT,"
            + TAGS.TAG_AGE + " TEXT,"
            + TAGS.TAG_RESOURCE_ID + " INTEGER )";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void initializeDataBase(Activity activity) {
        if (this.activity == null) {
            this.activity = activity;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        onCreate(db);
    }

    public void insertData(ItemMed product) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAGS.TAG_NAME, product.getName());
        values.put(TAGS.TAG_DESCRIPTION, product.getDescription1());
        values.put(TAGS.TAG_DESCRIPTION_2, product.getDescription2());
        values.put(TAGS.TAG_PRICE, product.getPrice());
        values.put(TAGS.TAG_QTY, product.getQty());
        values.put(TAGS.TAG_SIZE, product.getSize());
        values.put(TAGS.TAG_POWER, product.getPower());
        values.put(TAGS.TAG_AGE, product.getAgeGroup());
        values.put(TAGS.TAG_RESOURCE_ID, product.getImageId());
        database.insert(PRODUCT_TABLE, null, values);
        database.close();
    }


    public ArrayList<ItemMed> retrieveData() {
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<ItemMed> productList = new ArrayList<>();
        String selectQuery = "Select * from " + PRODUCT_TABLE;

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String description2 = cursor.getString(3);
                String price = cursor.getString(4);
                String qty = cursor.getString(5);
                String size = cursor.getString(6);
                String power = cursor.getString(7);
                String age = cursor.getString(8);
                int imageId = cursor.getInt(9);
                // Adding contact to list
                productList.add(new ItemMed(String.valueOf(id), name, price, qty, size, power, age, description, description2, imageId));
            } while (cursor.moveToNext());
        }

        return productList;
    }

    public int deleteData(String id) {
        SQLiteDatabase database = getWritableDatabase();
        int i = database.delete(PRODUCT_TABLE, "ID = ?", new String[]{id});
        database.close();
        return i;
    }
}
