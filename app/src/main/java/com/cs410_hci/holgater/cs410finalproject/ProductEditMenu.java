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

import java.util.List;

public class ProductEditMenu extends AppCompatActivity{

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

        //calculate and set potential stock number
        TextView potential = (TextView) findViewById(R.id.potentialStock);
        potential.setText(calculatePotential());

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
                                //prev number in stock and input
                                int prevNum = Integer.parseInt(inStockNum.getText().toString());
                                int inputNum = Integer.parseInt(input.getText().toString());
                                //final number to set in stock to
                                int finalNum;
                                //overflow in case number too large
                                int overflow = 0;
                                //if number too large
                                if(prevNum - inputNum < 0) {
                                    //calculate overflow, display number, set final to 0
                                    overflow = inputNum - prevNum;
                                    displayError("ProcessOverflow", overflow, prevNum);
                                    finalNum = 0;
                                } else {
                                    //otherwise deduct input amount normally
                                    finalNum = prevNum - inputNum;
                                }

                                //re-calculate and set in-stock number
                                product.setInStockNum(finalNum);
                                //reset inStock num
                                final TextView itemInStockNumText = (TextView) findViewById(R.id.itemInStockNumText);
                                itemInStockNumText.setText(String.valueOf(product.getInStockNum()));
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
        final Button restock = (Button) findViewById(R.id.restockButton);
        restock.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Popup dialog box to get number
                final TextView inStockNum = (TextView) findViewById(R.id.itemInStockNumText);
                final TextView potentialNum = (TextView) findViewById(R.id.potentialStock);

                if(calculatePotential().equals("No Recipe")) {

                    displayError("No Recipe", 0, 0);

                } else {


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
                                //deduct from components in stock --
                                //get recipe for product
                                List<Item> recipe = product.getRecipe();
                                Component currItem;
                                int currInStockNum;

                                // get current values on inStock and potentialStock
                                int prevInNum = Integer.parseInt(inStockNum.getText().toString());
                                int prevPotNum = Integer.parseInt(potentialNum.getText().toString());
                                //get user input num
                                int inputNum = Integer.parseInt(input.getText().toString());
                                //the actual amount that will be restocked
                                int restockNum = inputNum;
                                int overflow = 0;
                                //loop through each component in the recipe and see if any deductions go beyond the componenets in stock
                                for(int i =  0; i < recipe.size(); ++i) {
                                    currItem = (Component) recipe.get(i);
                                    currInStockNum = currItem.getInStockNum();
                                    //the amount of the current component to deduct
                                    int deductNum = currItem.getCount()*inputNum;
                                    //check to see if full amount can be deducted
                                    if(currInStockNum - deductNum < 0) {
                                        //we only want to stock the minimum possible
                                        restockNum = Math.min(restockNum, (currInStockNum/currItem.getCount()));
                                        //record what the overflow was
                                        overflow = deductNum - currInStockNum;
                                    }
                                }

                                //if there was overflow, we want to display an error saying how many couldn't be made
                                if(overflow > 0) {
                                    displayError("RestockOverflow", overflow, restockNum);
                                }

                                //restock the max number possible (may be below input num if limited by stock)
                                //loop through each component in the recipe and deduct
                                for(int i =  0; i < recipe.size(); ++i) {
                                    currItem = (Component) recipe.get(i);
                                    currInStockNum = currItem.getInStockNum();
                                    //get the actually component from the database
                                    DataBase.NameToItem(currItem.getName()).setInStockNum(currInStockNum - (restockNum*currItem.getCount()));
                                    currItem.setInStockNum(currInStockNum - (restockNum*currItem.getCount()));
                                }

                                //add the restock num to the new in stock num
                                product.setInStockNum(prevInNum + restockNum);
                                final TextView itemInStockNumText = (TextView) findViewById(R.id.itemInStockNumText);
                                itemInStockNumText.setText(String.valueOf(product.getInStockNum()));

                                //re-calculate and set potential stock number
                                TextView potential = (TextView) findViewById(R.id.potentialStock);
                                potential.setText(calculatePotential());
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
            }
        });

        //Recipe grid
        eGridView = (ExpandableGridView) findViewById(R.id.gridViewCompShow);
        final GridViewAdapter adapter = new GridViewAdapter(this, product.getRecipe(), "withCount");
        eGridView.setAdapter(adapter);
        eGridView.setExpanded(true);

        eGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {

                //eGridView.removeViewAt();

                product.deleteRecipe(position);
                //calculate and set potential stock number
                TextView potential = (TextView) findViewById(R.id.potentialStock);
                potential.setText(calculatePotential());
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

    private void displayError(String error, int valueInOne, int valueInTwo) {
        //get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.error, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);

        if(error.equals("RestockOverflow")) {
            //set text
            TextView promptText = (TextView) promptView.findViewById(R.id.promptMessage);
            promptText.setText("Could not restock all items - " + valueInOne + " items not restocked." + "\n" + valueInTwo + " items succesfully restocked");
        } else if (error.equals("ProcessOverflow")) {
            TextView promptText = (TextView) promptView.findViewById(R.id.promptMessage);
            promptText.setText("Could not process all items - " + valueInOne + " items not processed." + "\n" + valueInTwo + " items succesfully processed");

        } else if (error.equals("No Recipe")) {
            TextView promptText = (TextView) promptView.findViewById(R.id.promptMessage);
            promptText.setText("The product must have a recipe before it can be restocked");

        }

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();

    }

    private String calculatePotential() {
        //get recipe for product
        List<Item> recipe = product.getRecipe();
        int potentialStock = 100;
        Component currItem;
        //if there's no recipe yet - return default string
        if(recipe.size() == 0)
            return "No Recipe";

        //loop through each component in the recipe and get the min of all potential stocks
        for(int i =  0; i < recipe.size(); ++i) {
            currItem = (Component) recipe.get(i);
            potentialStock = Math.min(potentialStock, (currItem.getInStockNum()/currItem.getCount()));
        }
        return String.valueOf(potentialStock);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (GET_COMPONENT) : {
                if (resultCode == RecipeItemSelect.RESULT_OK) {
                    int position = (int) data.getExtras().get("position");
                    int count = (int) data.getExtras().get("count");
                    //Add to list inside product for father use
                    Item item = DataBase.components.get(position).clone();
                    ((Component)item).setCount(count);
                    product.addToRecipe(item);
                    //Show product list in

                    GridViewAdapter adapter = new GridViewAdapter(this, product.getRecipe(), "withCount");
                    eGridView.setAdapter(adapter);
                }
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onResume() {
        super.onResume();
        //re-calculate and set potential stock number
        TextView potential = (TextView) findViewById(R.id.potentialStock);
        potential.setText(calculatePotential());
        //reset inStock num
        final TextView itemInStockNumText = (TextView) findViewById(R.id.itemInStockNumText);
        itemInStockNumText.setText(String.valueOf(product.getInStockNum()));
    }
}
