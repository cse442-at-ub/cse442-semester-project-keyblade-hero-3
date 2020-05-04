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
import java.util.List;

public class searchAll extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper mydb;

    DatabaseConnector db;

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
        List<ArrayList<String>> d = db.httpPullMenu("Moes");
        if(d != null){
            String[] names = new String[d.get(0).size()];
            String[] prices = new String[d.get(1).size()];
            s1 = d.get(0).toArray(names);
            s2= getResources().getString(R.string.price);
            s3 = d.get(1).toArray(prices);

               /* s1=getResources().getStringArray(R.array.ChampaSushi_Item);
                s2=getResources().getStringArray(R.array.Champs_description);
                s3=getResources().getStringArray(R.array.ChampaSushi_price);*/
        }
        else{
            s1=getResources().getStringArray(R.array.ChampaSushi_Item);
            s2= getResources().getString(R.string.price);
            s3=getResources().getStringArray(R.array.ChampaSushi_price);
        }

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
