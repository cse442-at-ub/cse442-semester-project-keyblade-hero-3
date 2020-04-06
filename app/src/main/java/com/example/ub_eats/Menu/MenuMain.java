package com.example.ub_eats.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.R;

public class MenuMain extends AppCompatActivity {
    RecyclerView recyclerView;

    String[] s1, s2;
    int[] images={R.drawable.champa_sush, R.drawable.jamba,
    R.drawable.moes_at_putnams, R.drawable.hubies, R.drawable.sizzles,
    R.drawable.perks, R.drawable.the_elli};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView=findViewById(R.id.recyclerView);

        s1=getResources().getStringArray(R.array.pro_lang);
        s2=getResources().getStringArray(R.array.description);

        MyAdapter myAdapter=new MyAdapter(this, s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void goToSubMenu(View view) {
        //openActivity();

    }

    private void openActivity() {

            Intent intent=new Intent(this, secondActivity.class);
            startActivity(intent);


    }
}
