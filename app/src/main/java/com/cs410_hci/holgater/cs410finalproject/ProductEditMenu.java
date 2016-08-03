package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ProductEditMenu extends AppCompatActivity {

    static final int GET_COMPONENT = 1;

    String itemName;
    int imagePosition = 0;

    //the product
    Product product;

    TableLayout table;

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
        //set the description
        final TextView description = (TextView) findViewById(R.id.descriptionText);
        description.setText(product.getDescription());


        //set the recipe layout and resources
        table = (TableLayout) findViewById(R.id.recipeList);
        //loop through and add each part of the recipe
        for (int i = 0; i < product.getRecipe().size(); ++i) {
            //get the recipe item we're adding
            Item currItem = product.getRecipe().get(i);
            //define the icon, name, and amount of each component
            //icon
            ImageView icon = new ImageView(this);
            icon.setImageResource(currItem.getImage());
            //name
            TextView name = new TextView(this);
            name.setText(currItem.getName());
            name.setTextSize(20);
            //amount
            TextView amount = new TextView(this);
            amount.setText(String.valueOf(currItem.getInStockNum()));
            amount.setTextSize(20);
            //make a row
            TableRow row = new TableRow(this);
            //center items vertically
            row.setGravity(Gravity.FILL_HORIZONTAL);
            //add icon
            row.addView(icon);
            //edit icon size
            icon.setLayoutParams(new TableRow.LayoutParams(200, 200));
            //add name
            row.addView(name);
            //edit name size
            name.setLayoutParams(new TableRow.LayoutParams(400, 200));
            //add quantity
            row.addView(amount);
            //edit quantity size
           // amount.setLayoutParams(new TableRow.LayoutParams(200, 200));
            //add the row to the table
            table.addView(row);
        }
        //add "add component" row
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
                Intent intent = new Intent(ProductEditMenu.this, RecipeItemSelect.class);
                startActivityForResult(intent, GET_COMPONENT);
            }

        });
        //add components to row
        addRow.addView(addText);
        //add row to table
        table.addView(addRow);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (GET_COMPONENT) : {
                if (resultCode == RecipeItemSelect.RESULT_OK) {
                    Component newComponent = (Component) data.getExtras().get("ComponentReturn");
                    //icon
                    ImageView icon = new ImageView(this);
                    icon.setImageResource(newComponent.getImage());
                    //name
                    TextView name = new TextView(this);
                    name.setText(newComponent.getName());
                    name.setTextSize(20);
                    //amount
                    TextView amount = new TextView(this);
                    amount.setText(String.valueOf(newComponent.getInStockNum()));
                    amount.setTextSize(20);
                    //make a row
                    TableRow row = new TableRow(this);
                    //center items vertically
                    row.setGravity(Gravity.FILL_HORIZONTAL);
                    //add icon
                    row.addView(icon);
                    //edit icon size
                    icon.setLayoutParams(new TableRow.LayoutParams(200, 200));
                    //add name
                    row.addView(name);
                    //edit name size
                    name.setLayoutParams(new TableRow.LayoutParams(400, 200));
                    //add quantity
                    row.addView(amount);
                    //add the row to the table - second to last position
                    table.addView(row, table.getChildCount()-1);
                }
            }

        }
    }
}
