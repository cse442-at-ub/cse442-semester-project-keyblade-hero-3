package com.example.ub_eats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class OrderConfirmation extends AppCompatActivity {
    DatabaseConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        String order_number = getIntent().getStringExtra("order_number");
        final TextView textGen=findViewById(R.id.textView14);
        textGen.setText(String.valueOf(order_number));



    }
    public void onClickk(View view) {
        openActivity();

    }

    private void openActivity() {

        Intent intent=new Intent(this, LocationActivity.class);
        startActivity(intent);


    }
}
