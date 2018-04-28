package com.example.hugo.yachayfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Switch switchButton = (Switch) findViewById(R.id.switch_vendedor);
        final TextView textView = (TextView) findViewById(R.id.textSi);

        switchButton.setChecked(true);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    textView.setText("Si");
                } else {
                    textView.setText("No");
                }
            }
        });


    }
    public void goToAutentification(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        Toast toast = Toast.makeText(getApplicationContext(),"Te has registrado correctamente",Toast.LENGTH_SHORT);
        toast.show();
    }



}
