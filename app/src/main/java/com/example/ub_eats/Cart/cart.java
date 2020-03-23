package com.example.ub_eats.Cart;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ub_eats.R;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ub_eats.MainActivity;
import com.example.ub_eats.MainScreen;
import com.example.ub_eats.Menu.MenuMain;
import com.example.ub_eats.Menu.MyAdapter;
import com.example.ub_eats.Menu.myAdapterr;
import com.example.ub_eats.PaymentActivity;
import com.example.ub_eats.R;

import java.util.ArrayList;
import java.util.*;

public class cart extends AppCompatActivity {
    DatabaseHelper myDB;
    /* RecyclerView recyclerView;

     String s1[], s2[];
     int images[]={R.drawable.champa_sush, R.drawable.jamba,
             R.drawable.moes_at_putnams, R.drawable.hubies, R.drawable.sizzles,
             R.drawable.perks, R.drawable.the_elli};
     @Override
     protected void onCreate(Bundle savedInstanceState) {


         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_menu);

         recyclerView = findViewById(R.id.recyclerView);

         s1 = getResources().getStringArray(R.array.pro_lang);
         s2 = getResources().getStringArray(R.array.description);

         cartAdapter myAdapter = new cartAdapter(this, s1, s2,"Price");
         String st=myAdapter.data1[0];
         System.out.println("-------------");
         System.out.println(st);
         System.out.println("-------------");
         recyclerView.setAdapter(myAdapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));

     }
 */
    RecyclerView recyclerView;

    String s1[], s2[];
    String s3;

    ArrayList<String> itemL = new ArrayList<String>();
    ArrayList<String> priceL = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuc);

        recyclerView = findViewById(R.id.recyclerView);

        String itemName;
        String price;
        //   String s3;
        Bundle resultInt = getIntent().getExtras();

        itemName = resultInt.getString("item");
        price = resultInt.getString("price");

        myDB = new DatabaseHelper(this);


        addData(itemName, price);
/*

        itemL.add(itemName);
        priceL.add(price);
        System.out.println("----------------");
        System.out.println(itemL.get(0));
        System.out.println("----------------");*/

        // String[] str=new String[itemL.size()];


        // s1=getResources().getStringArray(R.array.ChampaSushi_Item);
        // s2=getResources().getStringArray(R.array.ChampaSushi_price);

        s3 = getResources().getString(R.string.price);
       /* System.out.println("----------------");
        System.out.println(s2[0]);
        System.out.println("----------------");*/


        ArrayList<String> itemss = new ArrayList<>();
        ArrayList<String> pricess = new ArrayList<>();

        Cursor data = myDB.getListContents();

        if (data.getCount() == 0) {
            //    Toast.makeText("The database was empty", Toast.LENGTH_LONG);
        } else {
            while (data.moveToNext()) {
                itemss.add(data.getString(0));
                pricess.add(data.getString(1));
            }
        }

        s1 = itemss.toArray(new String[itemL.size()]);
        s2 = pricess.toArray(new String[priceL.size()]);

        System.out.println("----------------");
        System.out.println(s1[0]);
        System.out.println(s2[0]);
        System.out.println("----------------");

        cartAdapter cartAdapter = new cartAdapter(this, s1, s2, s3);

        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
        // finish();

    }

    public void addData(String item, String price) {
        boolean insertData = myDB.addData(item, price);
        if (insertData == true) {

        } else {

        }

    }
}
