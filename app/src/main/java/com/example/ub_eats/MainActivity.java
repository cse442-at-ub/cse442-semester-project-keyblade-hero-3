package com.example.ub_eats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ub_eats.Menu.MenuMain;

import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) throws NoSuchAlgorithmException {
        switch (v.getId()){
            case R.id.signupButton:
                Intent intent=new Intent(this, Registration.class);
                startActivity(intent);
                break;
            case R.id.loginButton:
                EditText user_field= findViewById(R.id.user_name_field);
                EditText pass_field = findViewById(R.id.password_field);
                Encoder q = new Encoder();
                String salt = "2AxGUi/qgnHOZRuG2RaUMtLhe+Q=";

                String encoded_pass = q.decodePassword(pass_field.getText().toString(), salt);

                DatabaseConnector connector = new DatabaseConnector();
                String result = connector.httpLoginUser(user_field.getText().toString(), encoded_pass);


                if(result.equals("Passed")){
                    Intent intent2=new Intent(this, MenuMain.class);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(this, "Incorrect password or username", Toast.LENGTH_LONG).show();
                }
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
