package com.example.ub_eats.Menu;
import com.example.ub_eats.Cart.DatabaseHelper;
import com.example.ub_eats.DatabaseConnector;
import com.example.ub_eats.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.io.*;

public class searchAll extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper mydb;

    DatabaseConnector db;


    String Chmp[], Moe[], Tim[];
    String Chmpp[], Moep[], Timp[];
    String s1[], s3[];
    String s2;
    myAdapterr myAdapterr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.srchall);

        recyclerView=findViewById(R.id.recyclerView);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MyAdapter.EXTRA_MESSAGE);

        db = new DatabaseConnector();
        mydb=new DatabaseHelper(this);
        List<String> All = new ArrayList<String>();
        List<ArrayList<String>> Champa = db.httpPullMenu("Champa_Sushi");
        List<ArrayList<String>> Timm = db.httpPullMenu("Tim_Hortons");

        List<ArrayList<String>> d = db.httpPullMenu("Moes");

        int sz=0;


        if(Champa != null){

            String[] names = new String[Champa.get(0).size()];
            sz+=Champa.get(0).size();
            String[] prices = new String[Champa.get(1).size()];
            Chmp = Champa.get(0).toArray(names);
            s2= getResources().getString(R.string.price);
            Chmpp= Champa.get(1).toArray(prices);
        }
        else{
            s1=getResources().getStringArray(R.array.ChampaSushi_Item);
            s2= getResources().getString(R.string.price);
            s3=getResources().getStringArray(R.array.ChampaSushi_price);
        }
        if(Timm != null){
            String[] names = new String[Timm.get(0).size()];
            sz+=Timm.get(0).size();
            String[] prices = new String[Timm.get(1).size()];
            Tim = Timm.get(0).toArray(names);
            s2= getResources().getString(R.string.price);
            Timp= Timm.get(1).toArray(prices);

        }
        else{
            s1=getResources().getStringArray(R.array.ChampaSushi_Item);
            s2= getResources().getString(R.string.price);
            s3=getResources().getStringArray(R.array.ChampaSushi_price);
        }
        if(d != null){
            String[] names = new String[d.get(0).size()];
            String[] prices = new String[d.get(1).size()];
            sz+=d.get(0).size();
            Moe = d.get(0).toArray(names);
            s2= getResources().getString(R.string.price);
            Moep= d.get(1).toArray(prices);
            System.out.println("=============");

            System.out.println("Tim array  : " + Arrays.toString(Tim));

        }
        else{
            s1=getResources().getStringArray(R.array.ChampaSushi_Item);
            s2= getResources().getString(R.string.price);
            s3=getResources().getStringArray(R.array.ChampaSushi_price);
        }


        int first = Champa.get(0).size();
        int second = d.get(0).size();
        int third = Timm.get(0).size();

        s1=new String [sz];
        s3= new String [sz];
        // System.out.println("size s1" +sz);


       /* System.arraycopy(Chmp, 0, s1, 0, first);
        System.arraycopy(Moe, 0, s1, first, second);
        System.arraycopy(Tim, 0, s1, second, third);*/

        System.arraycopy(Chmpp, 0, s3, 0, first);
        System.arraycopy(Moep, 0, s3, first, second);
        System.arraycopy(Timp, 0, s3, second, third);

        List<String> list1 = new ArrayList<String>();
        Collections.addAll(list1, Chmp);
        Collections.addAll(list1, Moe);
        Collections.addAll(list1, Tim);

        s1=list1.toArray(s1);

        List<String> list2 = new ArrayList<String>();
        Collections.addAll(list2, Chmpp);
        Collections.addAll(list2, Moep);
        Collections.addAll(list2, Timp);

        s3=list2.toArray(s3);
        System.out.println("-----------------");
        System.out.println(Timp);


        System.out.println(list1.size());
        System.out.println(list1);

        myAdapterr=new myAdapterr(this, s1,s2,s3);


        // int n=myAdapterr.data1.length;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myAdapterr);

    }
    public void onClick(View view) {
        openActivity();

    }

    private void openActivity() {

        // change OrderConfirmation
        Intent intent=new Intent(this, com.example.ub_eats.Menu.cart.class );
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item= menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapterr.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
