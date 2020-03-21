package com.example.ub_eats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

import com.example.ub_eats.Menu.MenuMain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.signupButton:
                Intent intent=new Intent(this, Registration.class);
                startActivity(intent);
                break;
            case R.id.loginButton:
                Intent intent2=new Intent(this, MenuMain.class);
                startActivity(intent2);
        }
    }
/*
    public void onClick(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    public void onClickMain(View view){
        Intent intent = new Intent(this, MenuMain.class);
        startActivity(intent);
    }*/
}
