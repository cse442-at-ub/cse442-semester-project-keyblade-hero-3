package com.example.ub_eats.Cart;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="mylist.db";
    public static final String TABLE_NAME="mylist_data";
  //  public static final String COL3="Resturant name";
    public static final String COL1="item";
    public static final String COL2="price";
    public static final String COL3="quantity";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME+ " (item TEXT, price TEXT, quantity TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String item, String price){
        SQLiteDatabase db= this.getWritableDatabase();
        int quantity=1;
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, item);
        contentValues.put(COL2, price);
        contentValues.put(COL3, quantity);

        if(Exists(item)){
            updateQuantity(item); //Item Already exists, update quantity

        }else{
            long result=db.insert(TABLE_NAME,null,contentValues);
           // cursor.close();
            if(result==-1){
                return false;
            }else{
                return true;

            }
        }
       // cursor.close();
        return true;

    }

    public boolean Exists(String item) {

        String[] columns = { COL1};
        String selection = COL1 + " =?";
        String[] selectionArgs = { item };
        String limit = "1";

        SQLiteDatabase db= this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
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
    public boolean updateQuantity(String item){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues val=new ContentValues();

        String getQuantity= quanRet(item);
        int update= Integer.parseInt(getQuantity);
        update++;
        val.put(COL3, update);
        //String newQuan="2";
        db.update(TABLE_NAME, val, COL1 + "= ?", new String[] {item});
        return true;
    }
    public boolean incrementQuantity(String item){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues val=new ContentValues();

        String getQuantity= quanRet(item);
        int update= Integer.parseInt(getQuantity);
        update++;
        val.put(COL3, update);
        //String newQuan="2";
        db.update(TABLE_NAME, val, COL1 + "= ?", new String[] {item});
        //quanRet(item);
        return true;
    }
    public boolean decrementQuantity(String item){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues val=new ContentValues();

        String getQuantity= quanRet(item);
        int update= Integer.parseInt(getQuantity);
        if(update>1){
            update--;
            val.put(COL3, update);
            db.update(TABLE_NAME, val, COL1 + "= ?", new String[] {item});
            //quanRet(item);
            return true;
        }else{
            return true;
        }

    }
    public String quanRet (String itemName){
        SQLiteDatabase db=this.getReadableDatabase();

        String s1,s2,s3;
        s3="1";

        Cursor cursor= db.rawQuery("select * from "+ TABLE_NAME+ " WHERE "+ COL1 +" =? ",new String[]{itemName});
        if (cursor.moveToFirst())
        {
            do
            {
                s3 = cursor.getString(cursor.getColumnIndex(COL3));


            }while (cursor.moveToNext());
        }
        return s3;
    }
    public void deleteItem(String itemName){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1 + " = ? ", new String[]{itemName});
        //db.close();

    }
    /*public void deleteItem(String col, String item){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1 + "=" + item, null) ;
        db.close();
    }*/
}
