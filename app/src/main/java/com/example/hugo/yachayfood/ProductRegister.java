package com.example.hugo.yachayfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodstoreexception.Exceptions;

import java.util.ArrayList;

public class ProductRegister extends AppCompatActivity {

    private Button btnRegister;
    private EditText productName;
    private EditText productDescription;
    private EditText productStock;
    private EditText productCost;
    private Exceptions exception = new Exceptions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_register);
        productName = (EditText) findViewById(R.id.productName);
        productDescription = (EditText) findViewById(R.id.productDescription);
        productStock = (EditText) findViewById(R.id.productStock);
        productCost = (EditText) findViewById(R.id.productCost);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                    exception.stinException("name",productName.getText(),30);
                                    exception.stinException("description",productDescription.getText(),240);
                                    exception.stinException("stock",productStock.getText());
                                    exception.stinException("cost",productCost.getText());
                                    startActivity(i);
                                }catch (Exceptions e){
                                    System.out.println(e.getMessage());
                                    Toast.makeText(getApplicationContext(),"Insert correct values",Toast.LENGTH_LONG).show();
                                }catch ( Exception e2){
                                    System.out.println(e2.getMessage());
                                }

                            }
                        });
                    }
                };
                tr.start();
            }
        });
    }
}
