package com.example.ub_eats;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ViewSwitcher;


public class MainScreen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}
