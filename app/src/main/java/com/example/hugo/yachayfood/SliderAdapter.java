package com.example.hugo.yachayfood;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    private Context context;

    SliderAdapter (Context context){
        this.context = context;
    }

    //Arrays
    private int [] slide_images = {
            R.drawable.orden,
            R.drawable.fresa,
            R.drawable.sanduche

    };
    private String [] slide_headings = {
            "ORDENA",
            "RECIBE",
            "DISFRUTA"

    };
    private String [] slide_descs = {
            "Ordena en cualquier momento y a cualquier lugar",
            "Recibe tu pedido en el lugar especificado",
            "Disfruta y califica al vendedor, animate a ser parte también de los vendedores"

    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater LayoutInflater;
        LayoutInflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.inflate(R.layout.slide_layout,container, false);

        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView slideHeading = view.findViewById(R.id.slide_heading);
        TextView  slideDescription =  view.findViewById(R.id.slide_desc);
        Button login =  view.findViewById(R.id.slide_login);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(context, Login.class);
                context.startActivity(activity);

            }});
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
