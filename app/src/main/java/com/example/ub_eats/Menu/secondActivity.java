package com.example.ub_eats.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.Cart.cart;
import com.example.ub_eats.Menu.myAdapterr;
import com.example.ub_eats.PaymentActivity;
import com.example.ub_eats.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.Cart.DatabaseHelper;
import com.example.ub_eats.DatabaseConnector;
import com.example.ub_eats.Menu.myAdapterr;
import com.example.ub_eats.PaymentActivity;
import com.example.ub_eats.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.ub_eats.Menu.DatabaseHelper.TABLE_NAME;


public class secondActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper mydb;

    DatabaseConnector db;

    String s1[], s2[], s3[];
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

        db = new DatabaseConnector();
        mydb=new DatabaseHelper(this);
        mydb.deleteTable();


        int messInt=Integer.valueOf(message);
        if(messInt==0){
            List<ArrayList<String>> d = db.httpPullMenu("Champga Sushi");
            if(d != null){
                String[] names = new String[d.get(0).size()];
                String[] prices = new String[d.get(1).size()];
                s1 = d.get(0).toArray(names);
                s2=getResources().getStringArray(R.array.Champs_description);
                s3 = d.get(1).toArray(prices);

               /* s1=getResources().getStringArray(R.array.ChampaSushi_Item);
                s2=getResources().getStringArray(R.array.Champs_description);
                s3=getResources().getStringArray(R.array.ChampaSushi_price);*/
            }
            else{
                s1=getResources().getStringArray(R.array.ChampaSushi_Item);
                s2=getResources().getStringArray(R.array.Champs_description);
                s3=getResources().getStringArray(R.array.ChampaSushi_price);
            }



            //List<ArrayList<String>> menu = db.get_dining_menu("Orders");
            //ArrayList<String> items = menu.get(0);
            //ArrayList<String> prices = menu.get(1);

            //Log.d("ITEMS", items.get(0));



        }

        else if (messInt==1){
            s1=getResources().getStringArray(R.array.Jamba_Item);
            s2=getResources().getStringArray(R.array.menu_description);
            s3=getResources().getStringArray(R.array.ChampaSushi_price);
        }else if (messInt==2){//Change to Moes
            s1=getResources().getStringArray(R.array.Jamba_Item);
            s2=getResources().getStringArray(R.array.menu_description);
            s3=getResources().getStringArray(R.array.ChampaSushi_price);
        }else if (messInt==3){//Change to TimHortons item
            s1=getResources().getStringArray(R.array.Jamba_Item);
            s2=getResources().getStringArray(R.array.menu_description);
            s3=getResources().getStringArray(R.array.ChampaSushi_price);
        }else{
            s1=getResources().getStringArray(R.array.menu_Item);
            s2=getResources().getStringArray(R.array.menu_description);
            s3=getResources().getStringArray(R.array.ChampaSushi_price);
        }


        myAdapterr myAdapterr=new myAdapterr(this, s1,s2,s3,images);

        int n=myAdapterr.data1.length;
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
}
