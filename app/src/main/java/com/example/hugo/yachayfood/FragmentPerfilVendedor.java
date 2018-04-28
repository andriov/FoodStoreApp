package com.example.hugo.yachayfood;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.foodstoreexception.Exceptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class FragmentPerfilVendedor extends Fragment {

    //Here I create an array list of string which will store the names of the products readed from the GET Method
    ArrayList<String> productos = new ArrayList<>();
    //This variable will allow me to add the products to the ListView in activity Perfil Vendedor
    ListView list;


    //The button that will allow us to select the products we want to delete
    Button insertProductPrincipalButton;
    //To save the list of products that can be deleted
    String[] listOfProductsThatCanBeDeleted;
    //Here we declare an array that will tell me which products are going to be deleted
    boolean[] checkedItems;
    //Here we save the position of the selected items for deletion
    ArrayList<Integer> positionOfProductsForElimination = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_perfil_vendedor, null);

        //This is the list view in the activity_home that will show us all the products as a list
        list = (ListView) view.findViewById(R.id.listView);
        //Here we adapt our list of products to an ArrayAdapter in order to could carry them to our listview in our activity_home
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,productos);
        //Here we carry our products to our List View in our activity_home
        list.setAdapter(arrayAdapter);
        //We make a request to the server and present the data in the list view
        getData();

        //Button to insert new product that open Product Register
        insertProductPrincipalButton = (Button) view.findViewById(R.id.buttonNewProduct);
        insertProductPrincipalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),ProductRegister.class);
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Perfil Vendedor");
    }

    //This method allow me to read the products from the server
    public void getData(){
        //Here I give the direction from where we going to read the products data
        //String linkToJson = "https://api.myjson.com/bins/t48pz";
        String linkToJson = "http://192.168.1.3:8000/productj";
        //These 2 lines are necessary in order to allow us work for some smartphones
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Url variable to allow us to manage an internet url where is stored our json file
        //URL url=null;
        //This variable will help us to make the reading or the connection to the server
        HttpURLConnection conn;

        try {
            //We create our link with the string passed before
            URL url = new URL(linkToJson);
            //we open a connection to the url of our json file
            conn = (HttpURLConnection) url.openConnection();
            //We made a request to get all in the file
            conn.setRequestMethod("GET");
            //We connect us to the url
            conn.connect();
            //The next five lines are to be sure that the connecton was made successfully
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                //throw new IOException("HTTP error code: " + responseCode);
                Exceptions mensajeDeError = new Exceptions("Error al conectar con el servidor, intente entrar nuevamente al perfil del vendedor");
            }
            //We safe the readed json
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //This variable will help us to save line by line of the json file
            String inputLine;
            //to safe our lines that are stored in a BufferReader variable
            StringBuffer response = new StringBuffer();
            //While to read all lines in the BufferReader
            while ((inputLine = in.readLine()) != null){
                //We save all in our variable
                response.append(inputLine);
            }
            //In order to store all the Buffer as a string
            String json = "";
            //We parse our response to a String
            json = response.toString();
            //Toast.makeText(getActivity(), json, Toast.LENGTH_SHORT).show();
            //To store the string json
            JSONArray jsonArr = null;
            //We create a JsonArray from the string that contains Json Object from all the response inside the GET response
            jsonArr = new JSONArray(json);
            //To store a message that we will present in our ListView
            String mensage = "";
            //Where we will store our Json Objects which are in the JSon Array
            JSONObject jsonObject;
            //For each Json Object in the JsonArray that we created
            for(int i=0; i<jsonArr.length(); i++){
                //Take each Json Object inside the JsonArray
                jsonObject = jsonArr.getJSONObject(i);
                //Print the key of the name field of the Json object in the LogCat, this is just for testing the response
                Log.d("Salida", jsonObject.optString("name"));
                //the next line is just because we want to print the number of the product, but we want to begin in 1, not in zero
                int numero = i+1;
                //Save the result of the key name of our json Object in a customized message
                mensage = /*"Producto " + numero + ": " + */ jsonObject.optString("name") + "\n";
                //Add the customized Message to the Array list of strings that will be printed at the end in the ListView
                productos.add(mensage);
            }

        } catch (MalformedURLException e) {
            Log.d("error numero 1", "problema numero 1 no se pudo conectar");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("error numero 2", "problema numero 2 no se pudo conectar");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("error numero 3", "problema numero 3 no se pudo conectar");
            e.printStackTrace();
        }
    }
}
