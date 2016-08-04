package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class ComponentEditMenu extends AppCompatActivity {

    String itemName;
    int imagePosition = 0;

    //the component
    Component component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppCompatActivity setup
        super.onCreate(savedInstanceState);
        //setup view
        setContentView(R.layout.activity_component_edit_menu);
        //setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setup intent
        Intent myIntent = getIntent();
        //get products from previous activity
        int position = (int) myIntent.getSerializableExtra("position");
        component = DataBase.components.get(position);

        //set item name in toolbar title
        TextView itemNameText = (TextView) findViewById(R.id.toolbar_title);
        itemNameText.setText(component.getName());
        //set the "in stock" amount
        final TextView itemInStockNumText = (TextView) findViewById(R.id.itemInStockNumText);
        itemInStockNumText.setText(String.valueOf(component.getInStockNum()));
        //set component unit
        final TextView compUnit = (TextView) findViewById(R.id.compUnit);
        compUnit.setText(component.getUnit());
        //set the image
        final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
        imageView.setImageBitmap(component.getImage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //set new inStock num
        TextView inStock = (TextView) findViewById(R.id.itemInStockNumText);
        int newStock = Integer.parseInt(inStock.getText().toString());
        component.setInStockNum(newStock);
        //set new component Unit
        TextView compUnit = (TextView) findViewById(R.id.compUnit);
        component.setCompUnit(compUnit.getText().toString());
    }

}
