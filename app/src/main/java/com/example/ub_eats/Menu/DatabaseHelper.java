package com.example.ub_eats.Menu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Resturant.dp";
    public static final String TABLE_NAME="RstNm";
    public static final String TABLE_2NAME="MenuNm";
    public static final String Col_1="ResName";
    public static final String Col_2="items";
    String [] test1={"hello", "hi", "bye"};

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable1="CREATE TABLE Rst(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR)";
        String createTable2="CREATE TABLE Menu(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, artist_id INTEGER, FOREIGN KEY(artist_id) REFERENCES artists(id))";
        sqLiteDatabase.execSQL(createTable1);
        sqLiteDatabase.execSQL(createTable2);

    }

    public boolean addResturants(String name){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name", name);
        db.insert("Rst", null, contentValues);
        db.close();
        return true;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String createTable1="DROP TABLE IF EXISTS Rst";
        String createTable2="DROP TABLE IF EXISTS Menu";

        onCreate(sqLiteDatabase);

    }
}
