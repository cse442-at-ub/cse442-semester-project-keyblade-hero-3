package com.example.ub_eats.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.Menu.myAdapterr;
import com.example.ub_eats.PaymentActivity;
import com.example.ub_eats.R;

public class secondActivity extends AppCompatActivity {
    RecyclerView recyclerView;

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


        int messInt=Integer.valueOf(message);
        if(messInt==0){
            s1=getResources().getStringArray(R.array.ChampaSushi_Item);
            s2=getResources().getStringArray(R.array.Champs_description);
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
