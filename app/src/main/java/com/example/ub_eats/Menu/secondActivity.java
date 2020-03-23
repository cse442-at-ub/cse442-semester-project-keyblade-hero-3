package com.example.ub_eats.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.DatabaseConnector;
import com.example.ub_eats.Menu.myAdapterr;
import com.example.ub_eats.PaymentActivity;
import com.example.ub_eats.Ping;
import com.example.ub_eats.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class secondActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseConnector db;
    String s1[], s2[];
    int images[]={R.drawable.pasta, R.drawable.pizza,
            R.drawable.wings, R.drawable.fries, R.drawable.burger,
            R.drawable.cheesecakes, R.drawable.icecream};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView=findViewById(R.id.recyclerView);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MyAdapter.EXTRA_MESSAGE);

        /*0 = Champa Sushi
        1 = Jamba Juice
        2 = Moes
        3 = Hubies
        4 = Sizzles
        5 = Perks
        6 = Elli
         */
        db = new DatabaseConnector();
        int messInt=Integer.valueOf(message);

        if(messInt==0){
            List<ArrayList<String>> d = db.get_dining_menu("Champa_Sushi");
            if(d != null){
                String[] names = new String[d.get(0).size()];
                String[] prices = new String[d.get(1).size()];
                s1 = d.get(0).toArray(names);
                s2 = d.get(1).toArray(prices);
            }
            else{
                s1=getResources().getStringArray(R.array.ChampaSushi_Item);
                s2=getResources().getStringArray(R.array.Champs_description);
            }



            //List<ArrayList<String>> menu = db.get_dining_menu("Orders");
            //ArrayList<String> items = menu.get(0);
            //ArrayList<String> prices = menu.get(1);

            //Log.d("ITEMS", items.get(0));



        }else if (messInt==1){
            s1=getResources().getStringArray(R.array.Jamba_Item);
            s2=getResources().getStringArray(R.array.menu_description);
        }else{
            s1=getResources().getStringArray(R.array.menu_Item);
            s2=getResources().getStringArray(R.array.menu_description);
        }


        myAdapterr myAdapter=new myAdapterr(this, s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    public void onClick(View view) {
        openActivity();

    }

    private void openActivity() {

        // change OrderConfirmation
        Intent intent=new Intent(this, PaymentActivity.class);
        startActivity(intent);


    }
}
