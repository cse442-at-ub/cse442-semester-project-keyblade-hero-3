
package com.example.ub_eats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.example.ub_eats.Cart.DatabaseHelper;
import com.example.ub_eats.Menu.MenuMain;
import com.example.ub_eats.Menu.MyAdapter;

import java.util.ArrayList;
import java.util.Random;

public class PaymentActivity extends AppCompatActivity {
    ArrayList<String> order;
    DatabaseConnector db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_screen);

        order = getIntent().getStringArrayListExtra("order");
        AutoCompleteTextView price = findViewById(R.id.price_total);
        String price_to_display = String.format("%.2f",Float.valueOf(order.get(order.size()-1)));
        price.setText("Total: $" + price_to_display);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButton:
               DatabaseHelper mydb=new DatabaseHelper(this);
                mydb.deleteTable();
                Intent intent = new Intent(this, MenuMain.class);
                startActivity(intent);
                break;
            case R.id.confirmButton:
                //Push order to database here

                String concatenated_order = "";

                for(int i = 0; i < order.size()-1; i+=1){
                    concatenated_order = concatenated_order + order.get(i);
                    if(order.size() >= 2 && i < order.size()-2){
                        concatenated_order += ",";
                    }
                }


                final Random rdnum=new Random();

                String user_id = "0";
                String deliverer_id = "1";
                String status = "pending";
                AutoCompleteTextView price = findViewById(R.id.price_total);
                String price_to_display = String.format("%.2f",Float.valueOf(order.get(order.size()-1)));
                String items = concatenated_order.replace("  ", ",");


                db = new DatabaseConnector();
                String order_number = db.httpPushOrder(user_id, deliverer_id, status, price_to_display, items);

                Intent intent2 = new Intent(this, OrderConfirmation.class);
                intent2.putExtra("order_number", order_number);
                startActivity(intent2);
                break;
        }
    }
}

