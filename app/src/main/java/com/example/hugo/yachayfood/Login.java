package com.example.hugo.yachayfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToHome(View view){
        Intent intent = new Intent(this, LateralMenu.class);
        startActivity(intent);
    }

    public void goToRegister(View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

}
