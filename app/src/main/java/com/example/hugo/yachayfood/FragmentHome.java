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
import android.widget.ListView;
import android.widget.Toast;

import com.example.foodstorewebservice.OpenConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class FragmentHome extends Fragment {

    //Creation of connection
    private OpenConnection openConnection = new OpenConnection();
    //Here I create an array list of string which will store the names of the products readed from the GET Method
    private ArrayList<String> product = new ArrayList<>();
    //This variable will allow me to add the products to the ListView in activity Perfil Vendedor
    private ListView list;


    //To save the list of products that can be deleted
    String[] listOfProductsThatCanBeDeleted;
    //Here we declare an array that will tell me which products are going to be deleted
    boolean[] checkedItems;
    //Here we save the position of the selected items for deletion
    ArrayList<Integer> positionOfProductsForElimination = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, null);
        //Let connection in main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //This is the list view in the activity_home that will show us all the products as a list
        list = (ListView) view.findViewById(R.id.listView2);
        //Insertion of URL "https://api.myjson.com/bins/t48pz"
        openConnection.setUrl("http://192.168.43.168:8000/rest/productos");
        try {
            //Obtain Json array of products
            String result = openConnection.obtenerDatos();
            //Creation of array list of products
            product = openConnection.json_array(result,"name");
            //Here we adapt our list of products to an ArrayAdapter in order to could carry them to our listview in our activity_home
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, product);
            //Here we carry our products to our List View in our activity_home
            list.setAdapter(arrayAdapter);
        } catch (MalformedURLException e) {
            Log.d("error numero 1", "problema numero 1 no se pudo conectar");
            e.printStackTrace();
            Toast.makeText(getContext(),"Connection problems",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.d("error numero 2", "problema numero 2 no se pudo conectar");
            Toast.makeText(getContext(),"Connection problems",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Food Store Home");
    }
}
