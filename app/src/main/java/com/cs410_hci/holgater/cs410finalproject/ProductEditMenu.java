package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ProductEditMenu extends AppCompatActivity {

    String itemName;
    int imagePosition = 0;
    ///*test - hard coded products
    String[] pNameId = {
            "name1",
            "name2",
            "name3",
            "name4",
            "name5"
    };

    int[] pImageId = {
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5
    };

    int[] pInStockNum = {
            12,
            2,
            6,
            15,
            4
    };
    //*/

    //the product
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppCompatActivity setup
        super.onCreate(savedInstanceState);
        //setup view
        setContentView(R.layout.activity_product_edit_menu);
        //setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setup intent
        Intent myIntent = getIntent();
        //get products from previous activity
        product = (Product) myIntent.getSerializableExtra("product");

        //set item name in toolbar title
        TextView itemNameText = (TextView) findViewById(R.id.toolbar_title);
        itemNameText.setText(product.getName());
        //set the "in stock" amount
        final TextView itemInStockNumText = (TextView) findViewById(R.id.itemInStockNumText);
        itemInStockNumText.setText(String.valueOf(product.getInStockNum()));
        //set the image
        final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
        imageView.setImageResource(product.getImage());
        //set the recipe layout and resources
        TableLayout table = (TableLayout) findViewById(R.id.recipeList);
        //loop through and add each component
        //TODO - right now it just adds all products as a test
        for (int i = 0; i < pNameId.length; ++i) {
            //define the icon, name, and amount of each component
            //icon
            ImageView icon = new ImageView(this);
            icon.setImageResource(pImageId[i]);
            //name
            TextView name = new TextView(this);
            name.setText(pNameId[i]);
            name.setTextSize(20);
            //amount
            TextView amount = new TextView(this);
            amount.setText(String.valueOf(pInStockNum[i]));
            amount.setTextSize(20);
            //make a row
            TableRow row = new TableRow(this);
            //set row's total weight, center items vertically, and add margin
            row.setWeightSum(6f);
            row.setGravity(Gravity.CENTER_VERTICAL);
            //add icon
            row.addView(icon);
            //edit icon size
            icon.setLayoutParams(new TableRow.LayoutParams(200, 200, 1f));
            //add name
            row.addView(name);
            //edit name size
            name.setLayoutParams(new TableRow.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT, 6f));
            //add quantity
            row.addView(amount);
            //edit quantity size
            amount.setLayoutParams(new TableRow.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            //add the row to the table
            table.addView(row);
        }
        //add "add component" row
        //make add icon
        ImageView plusIcon = new ImageView(this);
        plusIcon.setImageResource(R.drawable.plus_button);
        plusIcon.setId(plusIcon.generateViewId()); //
        //make text
        TextView addText = new TextView(this);
        addText.setText("Add new component");
        addText.setTextSize(20);
        //make row
        TableRow addRow = new TableRow(this);
        addRow.setWeightSum(6f);
        addRow.setGravity(Gravity.CENTER_VERTICAL);
        //make the row clickable
        addRow.setClickable(true);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }

        });
        //add components to row
        addRow.addView(plusIcon);
        plusIcon.setLayoutParams(new TableRow.LayoutParams(200, 200, 1f));
        addRow.addView(addText);
        //add row to table
        table.addView(addRow);
    }



}
