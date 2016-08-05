package com.cs410_hci.holgater.cs410finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RecipeItemSelect extends AppCompatActivity {
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_item_select);

        GridViewAdapter adapter = new GridViewAdapter(this, DataBase.components, "");
        ExpandableGridView eGridView = (ExpandableGridView) findViewById(R.id.gridViewCompChooser);
        eGridView.setAdapter(adapter);
        eGridView.setExpanded(true);

        eGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {

                final Intent intent = new Intent();
                //get prompts.xml view
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View promptView = layoutInflater.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set text
                TextView promptText = (TextView) promptView.findViewById(R.id.promptMessage);
                promptText.setText("How many items add to Recipe");

                // set prompts.xml to be the layout file of the alertdialog builder
                alertDialogBuilder.setView(promptView);

                final EditText input = (EditText) promptView.findViewById(R.id.userInput);

                // setup a dialog window
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                //int prevNum = Integer.parseInt(inStockNum.getText().toString());
                                int addNum = Integer.parseInt(input.getText().toString());
                                intent.putExtra("count", addNum);
                                intent.putExtra("position", position);
                                setResult(RESULT_OK, intent);
                                finish();
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

    }

}
