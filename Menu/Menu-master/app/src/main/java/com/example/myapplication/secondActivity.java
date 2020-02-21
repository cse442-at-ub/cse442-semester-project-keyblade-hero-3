package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class secondActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    String s1[], s2[];
    int images[]={R.drawable.pasta, R.drawable.pizza,
            R.drawable.wings, R.drawable.fries, R.drawable.burger,
            R.drawable.cheesecakes, R.drawable.icecream};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        s1=getResources().getStringArray(R.array.menu_Item);
        s2=getResources().getStringArray(R.array.menu_description);

        myAdapterr myAdapter=new myAdapterr(this, s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    public void onClick(View view) {
        openActivity();

    }

    private void openActivity() {

        Intent intent=new Intent(this, OrderConfirmation.class);
        startActivity(intent);


    }
}
