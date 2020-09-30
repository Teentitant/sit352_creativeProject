package com.example.storage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.storage.database.model.Item;
import com.example.storage.database.model.UserModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserDBHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "user.db";
    public final static int VERSION = 3;

    //user table
    public final static String USER_TABLE = "user_table";
    public final static String USER_COL_1 = "email";
    public final static String USER_COL_2 = "password";

    //item table
    public final static String ITEM_TABLE = "item_table";
    public final static String ITEM_COL_1 = "title";
    public final static String ITEM_COL_2 = "email";
    public final static String ITEM_COL_3 = "password";
    public final static String ITEM_COL_4 = "description";


    public UserDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");
        db.execSQL("create table " + ITEM_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, email TEXT, password TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
        onCreate(db);
    }

    public boolean insertUserModel(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_1, userModel.getEmail());
        contentValues.put(USER_COL_2, userModel.getPassword());
        return db.insert(USER_TABLE, null, contentValues) != -1;
    }

    public boolean checkUserModel(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where email = ? and password = ? ", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertItemModel(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_COL_1, item.getTitle());
        contentValues.put(ITEM_COL_2, item.getEmail());
        contentValues.put(ITEM_COL_3, item.getPassword());
        contentValues.put(ITEM_COL_4, item.getDescription());
        return db.insert(ITEM_TABLE, null, contentValues) != -1;
    }

    public List<Item> getAllItemModel() {
        List<Item> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from item_table", null);
        if (cursor.moveToFirst()) {
            do {
                returnList.add(new Item(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }
}
