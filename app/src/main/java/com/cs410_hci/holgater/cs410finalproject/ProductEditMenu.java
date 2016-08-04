package com.cs410_hci.holgater.cs410finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductEditMenu extends AppCompatActivity {

    static final int GET_COMPONENT = 1;
    static final int GET_PROCESS_NUM = 2;
    //the product
    Product product;
    ExpandableGridView eGridView;

    final Context context = this;

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
        process.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Popup dialog box to get number
                final TextView inStockNum = (TextView) findViewById(R.id.itemInStockNumText);

                //get prompts.xml view
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View promptView = layoutInflater.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set text
                TextView promptText = (TextView) promptView.findViewById(R.id.promptMessage);
                promptText.setText("How many items to process?");

                // set prompts.xml to be the layout file of the alertdialog builder
                alertDialogBuilder.setView(promptView);

                final EditText input = (EditText) promptView.findViewById(R.id.userInput);

                // setup a dialog window
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                int prevNum = Integer.parseInt(inStockNum.getText().toString());
                                int subtractNum = Integer.parseInt(input.getText().toString());
                                int finalNum;
                                if(prevNum - subtractNum < 0) {
                                    finalNum = 0;
                                } else {
                                    finalNum = prevNum - subtractNum;
                                }
                                inStockNum.setText(String.valueOf(finalNum));
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,	int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alertD = alertDialogBuilder.create();

                alertD.show();

            }
        });

        //Restock button
        Button restock = (Button) findViewById(R.id.restockButton);
        restock.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Popup dialog box to get number
                final TextView inStockNum = (TextView) findViewById(R.id.itemInStockNumText);
                final TextView potentialNum = (TextView) findViewById(R.id.potentialStock);

                //get prompts.xml view
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View promptView = layoutInflater.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set text
                TextView promptText = (TextView) promptView.findViewById(R.id.promptMessage);
                promptText.setText("How many items to restock?");

                // set prompts.xml to be the layout file of the alertdialog builder
                alertDialogBuilder.setView(promptView);

                final EditText input = (EditText) promptView.findViewById(R.id.userInput);

                // setup a dialog window
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                int prevNum = Integer.parseInt(inStockNum.getText().toString());
                                int addNum = Integer.parseInt(input.getText().toString());
                                int finalNum = prevNum + addNum;

                                inStockNum.setText(String.valueOf(finalNum));
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,	int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alertD = alertDialogBuilder.create();

                alertD.show();

            }
        });

        //Recipe grid
        eGridView = (ExpandableGridView) findViewById(R.id.gridViewCompShow);
        final GridViewAdapter adapter = new GridViewAdapter(this, product.getRecipe());
        eGridView.setAdapter(adapter);
        eGridView.setExpanded(true);

        eGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {

                //eGridView.removeViewAt();

                product.deleteRecipe(position);
                //adapter.notifyDataSetChanged();
                eGridView.setAdapter(adapter);
                //set the image as wallpaper
                return true;
            }
        });

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
