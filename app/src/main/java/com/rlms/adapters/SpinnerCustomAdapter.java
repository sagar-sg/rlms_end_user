package com.rlms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.rlms.R;
import com.rlms.model.SpinnerModel;

import java.util.ArrayList;

// Creating an Adapter Class
public class SpinnerCustomAdapter extends ArrayAdapter {

    private final Context context;
    private ArrayList<SpinnerModel> list = new ArrayList<>();

    public SpinnerCustomAdapter(Context context, int textViewResourceId,
                                ArrayList<SpinnerModel> objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        this.list.addAll(objects);// = objects;
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {

// Inflating the layout for the custom Spinner
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.spinner_rows, parent, false);

// Declaring and Typecasting the textview in the inflated layout
        TextView label = (TextView) layout
                .findViewById(R.id.titletv);

// Declaring and Typecasting the imageView in the inflated layout
        ImageView img = (ImageView) layout.findViewById(R.id.imageiv);

//        if (position == 0) {
//
//            // Default selected Spinner item
//            label.setText("All Sensors");
//            img.setImageDrawable(null);
//
//        } else {
            // Set values for spinner each row
            label.setText(list.get(position).getTitle());

//        }


        return layout;
    }

    public View getFirstCustomView(int position, View convertView,
                              ViewGroup parent) {

// Inflating the layout for the custom Spinner
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.spinner_dropdown_view, parent, false);

// Declaring and Typecasting the textview in the inflated layout
        TextView label = (TextView) layout
                .findViewById(R.id.titletv);

// Declaring and Typecasting the imageView in the inflated layout
        ImageView img = (ImageView) layout.findViewById(R.id.imageiv);

//        if (position == 0) {
//
//            // Default selected Spinner item
//            label.setText("All Sensors");
//            img.setImageDrawable(null);
//
//        } else {
        // Set values for spinner each row
        label.setText(list.get(position).getTitle());

//        }


        return layout;
    }

    // It gets a View that displays in the drop down popup the data at the specified position
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getFirstCustomView(position, convertView, parent);
    }

    // It gets a View that displays the data at the specified position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}