package com.example.hugo.yachayfood;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.foodstoreexception.Exceptions;
import com.example.foodstorewebservice.OpenConnection;

import java.io.IOException;

public class ProductRegister extends AppCompatActivity {

    //Creation of connection
    private OpenConnection openConnection = new OpenConnection();
    //Button to registe the product
    private Button btnRegister;
    //EditText to insert the name of the product
    private EditText productName;
    //EditText to registe the description of the product
    private EditText productDescription;
    //EditText to registe the stock of the product
    private EditText productStock;
    //EditText to registe the cost of the product
    private EditText productCost;
    //Object that work with the exceptions of productName, productDescription, productStock and productCost
    private RadioButton rbtBreakFast, rbtnLunch, rbtnFastFoot;
    //Integer of found button
    private int rbtnanswer = 1;
    //Excepcions obtained by imported module created by us
    private Exceptions exception = new Exceptions();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_register);
        //Let connection in main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Insertion of the references(activity_product_register) of the button and EditText created as global variables
        productName = (EditText) findViewById(R.id.productName);
        productDescription = (EditText) findViewById(R.id.productDescription);
        productStock = (EditText) findViewById(R.id.productStock);
        productCost = (EditText) findViewById(R.id.productCost);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        //Insertion of the references to radio buttons
        rbtBreakFast = (RadioButton) findViewById(R.id.rbtnDesayuno);
        rbtnLunch = (RadioButton) findViewById(R.id.rbtnAlmuerzo);
        rbtnFastFoot = (RadioButton) findViewById(R.id.rbtnComidaRapida);
        //Insertion of the product if all the values are inserted correctly
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
                                    //After the insertion the app go to seller profile
                                    Intent i=new Intent(getApplicationContext(),LateralMenu.class);
                                    //If the name is greater than 30 charcters or is a number we obtain a exception
                                    exception.stinException("name",productName.getText(),30);
                                    //If the name is greater than 240 charcters or is a number we obtain a exception
                                    exception.stinException("description",productDescription.getText(),240);
                                    //If the stock is not an integer we obtain a exception
                                    exception.stinException("stock",productStock.getText());
                                    //If the cost is not a double number we obtain a exception
                                    exception.stinException("cost",productCost.getText());
                                    validation();
                                    openConnection.insertarDatos("{\"category\":\""+rbtnanswer+"\",\"name\":\""+productName.getText().toString()
                                            +"\",\"description\":\""+productDescription.getText().toString()+"\",\"stock\":"+productStock.getText().toString()
                                            +",\"price\":\""+productCost.getText().toString()+"\"}");
                                    startActivity(i);
                                }catch (Exceptions e){
                                    //Print the exactly error for developer
                                    System.out.println(e.getMessage());
                                    //Message that insert correct values to user
                                    Toast.makeText(getApplicationContext(),"Insert correct values",Toast.LENGTH_LONG).show();
                                }catch ( Exception e2){
                                    //If exit other error that not has Exceptions package, obtain it for the developer
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

    private void validation(){
        if(rbtBreakFast.isChecked()){
            rbtnanswer=1;
        }
        if(rbtnLunch.isChecked()){
            rbtnanswer=2;
        }
        if(rbtnFastFoot.isChecked()){
            rbtnanswer=3;
        }
    }

}
