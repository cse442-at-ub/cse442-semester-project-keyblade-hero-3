
package com.example.ub_eats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

import com.example.ub_eats.Menu.MenuMain;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_screen);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButton:
                Intent intent = new Intent(this, MenuMain.class);
                startActivity(intent);
                break;
            case R.id.confirmButton:
                Intent intent2 = new Intent(this, OrderConfirmation.class);
                startActivity(intent2);
                break;
        }
    }
}

