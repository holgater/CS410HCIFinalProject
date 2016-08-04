package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductEditMenu extends AppCompatActivity {

    static final int GET_COMPONENT = 1;
    //the product
    Product product;
    ExpandableGridView eGridView;

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
        int position = (int) myIntent.getSerializableExtra("position");
        product = DataBase.products.get(position);

        //set item name in toolbar title
        TextView itemNameText = (TextView) findViewById(R.id.toolbar_title);
        itemNameText.setText(product.getName());
        //set the "in stock" amount
        final TextView itemInStockNumText = (TextView) findViewById(R.id.itemInStockNumText);
        itemInStockNumText.setText(String.valueOf(product.getInStockNum()));
        //set the image
        final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
        imageView.setImageBitmap(product.getImage());
        //set the description
        final TextView description = (TextView) findViewById(R.id.descriptionText);
        description.setText(product.getDescription());

        //Process button
        Button process = (Button) findViewById(R.id.processButton);
        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText inStockNum = (EditText) findViewById(R.id.inStockNumInput);

                //set focus and bring up keyboard for invisble editText field
                //inStockNum.setEnabled(true);
                //inStockNum.requestFocus();
                inStockNum.setFocusableInTouchMode(true);
            }
        });

        //Recipe grid
        eGridView = (ExpandableGridView) findViewById(R.id.gridViewCompShow);
        GridViewAdapter adapter = new GridViewAdapter(this, product.getRecipe());
        eGridView.setAdapter(adapter);
        eGridView.setExpanded(true);

        //make row
        Button addButton = (Button) findViewById(R.id.add_to_receipt);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductEditMenu.this, RecipeItemSelect.class);
                startActivityForResult(intent, GET_COMPONENT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (GET_COMPONENT) : {
                if (resultCode == RecipeItemSelect.RESULT_OK) {
                    int position = (int) data.getExtras().get("position");
                    //Add to list inside product for father use
                    product.addToRecipe(DataBase.components.get(position));
                    //Show product list in
                    GridViewAdapter adapter = new GridViewAdapter(this, product.getRecipe());
                    eGridView.setAdapter(adapter);
                }
            }

        }
    }
}
