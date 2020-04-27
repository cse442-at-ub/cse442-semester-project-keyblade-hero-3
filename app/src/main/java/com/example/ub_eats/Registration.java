package com.example.ub_eats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void onClickCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickRegister(View view){
        EditText user_field = findViewById(R.id.RegisterUserName);
        EditText pass_field = findViewById(R.id.RegisterPassword);
        EditText phone_field = findViewById(R.id.RegisterPhone);
        EditText email_field = findViewById(R.id.RegisterEmail);
        DatabaseConnector connector = new DatabaseConnector();
        String result = connector.httpRegisterUser(user_field.getText().toString(), pass_field.getText().toString(), email_field.getText().toString(),phone_field.getText().toString());

        if (result.equals("Passed")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Register has failed", Toast.LENGTH_LONG).show();
        }
    }

}
