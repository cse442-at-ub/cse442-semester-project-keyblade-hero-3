package com.example.ub_eats.Cart;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="mylist.db";
    public static final String TABLE_NAME="mylist_data";
    public static final String COL3="Resturant name";
    public static final String COL1="item";
    public static final String COL2="price";



    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME+ " (item TEXT, price TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String item, String price){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, item);
        contentValues.put(COL2, price);
        // db.insert(TABLE_NAME, null, contentValues);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;

        }

    }
    public Cursor getListContents(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data= db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
    public void deleteTable(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}
