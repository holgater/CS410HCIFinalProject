package com.cs410_hci.holgater.cs410finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddComponent extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component);

        imageView = (ImageView) findViewById(R.id.ImageViewComp);
        imageView.setImageResource(R.drawable.noimagefound);

        //Use camera to get thumbnail
        RadioButton productsButton = (RadioButton) findViewById(R.id.Camera_Com);
        productsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1888);
            }
        });

        //Use gallery to create thumbnail
        RadioButton galeryButton = (RadioButton) findViewById(R.id.Gallery_Com);
        galeryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 18);
            }
        });

        //Use gallery to create thumbnail
        RadioButton cloudButton = (RadioButton) findViewById(R.id.Cloud_Com);
        cloudButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddComponent.this);
                builder.setTitle("Sorry! This button not available now!")
                        .setMessage("Wait for update before it will be available!")
                        .setCancelable(true)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        Button create = (Button) findViewById(R.id.button_Create_Com);
        final EditText desc = (EditText) findViewById(R.id.editText_com);
        final EditText name = (EditText) findViewById(R.id.componentNameInput);
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataBase.loadProducts();
                DataBase.components.add(new Component(name.getText().toString(),((BitmapDrawable)imageView.getDrawable()).getBitmap(),0, desc.getText().toString()));
                setResult(RESULT_OK, new Intent());
                finish();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1888 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        } else if (requestCode == 18 && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
