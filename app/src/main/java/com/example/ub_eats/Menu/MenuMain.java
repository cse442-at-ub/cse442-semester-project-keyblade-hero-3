package com.example.ub_eats.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.Cart.DatabaseHelper;
import com.example.ub_eats.DatabaseConnector;
import com.example.ub_eats.R;

import java.util.ArrayList;
import java.util.List;

public class MenuMain extends AppCompatActivity {
    RecyclerView recyclerView1;
    RecyclerView recyclerView;

    String[] s1, s2;
    int[] images={R.drawable.champa_sush, R.drawable.jamba,
    R.drawable.moes_at_putnams, R.drawable.hubies, R.drawable.sizzles,
    R.drawable.perks, R.drawable.the_elli};

    DatabaseHelper mydb;

    DatabaseConnector db;

    String s11[], s33[];
    String s22;
    myAdapterr myAdapterr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView=findViewById(R.id.recyclerView);
         recyclerView1=findViewById(R.id.recyclerView);


        s1=getResources().getStringArray(R.array.pro_lang);
        s2=getResources().getStringArray(R.array.description);

        MyAdapter myAdapter=new MyAdapter(this, s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        /*
         search feature
         */

        db = new DatabaseConnector();
        mydb=new DatabaseHelper(this);
        mydb.deleteTable();
        List<ArrayList<String>> d = db.httpPullMenu(".Moes");
        String[] names = new String[d.get(0).size()];
        String[] prices = new String[d.get(1).size()];
        s11 = d.get(0).toArray(names);
        s22= getResources().getString(R.string.price);
        s33 = d.get(1).toArray(prices);
        myAdapterr=new myAdapterr(this, s11,s22,s33);
       // recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView1.setAdapter(myAdapterr);
        System.out.println(" search menu main");


    }

    public void goToSubMenu(View view) {
        //openActivity();

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

    private void openActivity() {

            Intent intent=new Intent(this, secondActivity.class);
            startActivity(intent);


    }

    public void srhBut(View view) {
        Intent intent=new Intent(this, searchAll.class);
        startActivity(intent);
    }
}
