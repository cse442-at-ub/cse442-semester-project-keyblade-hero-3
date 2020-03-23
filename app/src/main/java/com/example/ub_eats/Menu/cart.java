package com.example.ub_eats.Menu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.R;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ub_eats.Menu.MyAdapter;
import com.example.ub_eats.Menu.myAdapterr;
import com.example.ub_eats.R;

import java.util.ArrayList;
import java.util.*;


public class cart extends AppCompatActivity {
    RecyclerView recyclerView;

    String s1[], s2[];
    String s3;

    ArrayList<String> itemL=new ArrayList<String>();
    ArrayList<String> priceL=new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView=findViewById(R.id.recyclerView);

        String itemName;
        String price;
        //   String s3;
        Bundle resultInt=getIntent().getExtras();

        itemName=resultInt.getString("item");
        price=resultInt.getString("price");




        itemL.add(itemName);
        priceL.add(price);
        String[] str=new String[itemL.size()];
        s1=itemL.toArray(str);
        s2=priceL.toArray(str);
        s3=getResources().getString(R.string.price);

        System.out.println("------------");
        System.out.println("whaaaa");
        System.out.println("------------");

       /* s1=getResources().getStringArray(R.array.ChampaSushi_Item);
        s2=getResources().getStringArray(R.array.ChampaSushi_price);*/

        s3=getResources().getString(R.string.price);


        cartAdapter cartAdapter=new cartAdapter(this, s1,s2,s3);

        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    /*public void onClick(View view) {
        Intent intent=new
    }*/
}
