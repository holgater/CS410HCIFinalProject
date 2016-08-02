package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ProductListMenu extends AppCompatActivity {
    //gridView
    GridView gridView;
    //input stream for loading products and materials
    ObjectInputStream input;
    //output stream for saving products and materials
    ObjectOutputStream out = null;
    //array of products and materials user has created
    List<Product> products;
    List<Component> materials;

    //test - hard code products
    ///*
    String[] pNameId = {
        "Let Them Eat Me",
        "Check Out My Buns!",
        "Feminist Agenda Shirt",
        "Chill Pill",
        "Mermaid One Piece",
        "Too Tall Headband",
        "Avacado Lace",
        "Consent is Sexy",
        "Roast Beef Thong",
        "Bodacious Sports Bra",
        "I Hate Pants Thong",
        "1950's Holiday Deer",
        "Fifty Shades of Gay",
        "Whale You Be Mine?",
        "Meat Head Headband"

    };

    int[] pImageId = {
        R.drawable.product01,
        R.drawable.product02,
        R.drawable.product03,
        R.drawable.product04,
        R.drawable.product05,
        R.drawable.product06,
        R.drawable.product07,
        R.drawable.product08,
        R.drawable.product09,
        R.drawable.product10,
        R.drawable.product11,
        R.drawable.product12,
        R.drawable.product13,
        R.drawable.product14,
        R.drawable.product15
    };

    int[] pItemInStockNumId = {
        12,
        2,
        6,
        15,
        4,
        3,
        22,
        7,
        14,
        24,
        2,
        1,
        0,
        5,
        9
    };

    String[] pDescription = {
            "As tasty as cake, anyways. Ever notice what you’re wearing affects your energy? Imagine yourself in a pair of these babies. Grab your booty and say thank you for being so damn adorable. Now feel free to treat that booty to a present. Give that booty the chance to shine, to feel sassy and provocative. Now is your chance to be daring and live gleefully knowing your secret: you have the greatest panties in the room. Not only do the look great but they FEEL great. This Marie Antoinette print panty is made from organic cotton lined with cotton, so it’s comfortable on your ladybits all day long.",
            "View the buns, pet the buns, love the buns. Not only do the look great but they FEEL great. This shark attack print panty is made from organic cotton lined with cotton, so it’s comfortable on your ladybits all day long.",
            "This design is inspired by the famous Pat Robertson quote regarding feminism: It is about a socialist, anti-family political movement that encourages women to leave their husbands, kill their children, practice witchcraft, destroy capitalism and become lesbians. This t shirt features a printed design on soft, luxurious, faux suede, then appliqued onto the tee.",
            "Take a Chill Pill! This all over print t-shirt is made from polyester stretch knit for a clear bright, photographic print. This design clings close to the body.",
            "Halter Swimsuits are here at last! This one piece bathing suit is printed on lycra and sewn in the USA.",
            "This ones for all the short gals out there. How dare they be taller than us >:( At last, some fun, silly, yet still wearable headbands for adults! For those of us who enjoy life with a light heart. This headband features original graphics by Textile Champion.",
            "Who doesn't love avocados? Not only do the look great but they FEEL great. This avocado print panty is made from organic cotton lined with cotton, so it’s comfortable on your ladybits all day long.",
            "This lace top thong is empowering and sends a message: Consent is Sexy! Know what you want, you have the power to say no(or yes!) Not only do the look great but they FEEL great. This black lace thong is made from organic cotton and lined with cotton, so it’s comfortable on your ladybits all day long.",
            "Take your meat curtains to the next level! Meaty, yet still vegan.",
            "You'll be the most bodacious babe around town in this sports bra. This top is made from Lycra, and works for yoga, running, sports, and various fitness activities. It can also be used as a swim top. Looking for something more casual? This top can also be made with stretch knit, for a soft bra / bralette look.",
            "The perfect pair for lounging around the house in, on those days where you just don't want to wear pants!",
            "This Thong is inspired by 1950's vintage deer motifs and features white lace with gold sparkles. If purchasing for a holiday event, please note processing time. Some backstock is available for rush orders upon request, but please message me first.",
            "Taste The Rainbow.",
            "Perfect for anyone who speaks whale, the language of love.",
            "An original pattern from Textile champion featuring meaty meat. At last, some fun, silly, yet still wearable headbands for adults! For those of us who enjoy life with a light heart. This headband features original graphics by Textile Champion. "
    };
     //*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppCompatActivity setup
        super.onCreate(savedInstanceState);
        //setup layout
        setContentView(R.layout.activity_product_list_menu);
        //load Products
        loadProducts();
        //load materials
        loadMaterials();
        //test - hard code products
        /*;

        for (int i = 0; i < 15; ++i) {
            products.add(new Product(pNameId[i], pImageId[i], pItemInStockNumId[i], pDescription[i]));
        }
        */
        //setup toolbar
        ExpandableGridView eGridView = (ExpandableGridView) findViewById(R.id.eGridView);
        eGridView.setAdapter(new GridViewAdapter(this, products));
        eGridView.setExpanded(true);

        //setup grid items with onClick listener
        eGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //create intent to start ProductEditMenu activity
                Intent intent = new Intent(ProductListMenu.this, ProductEditMenu.class);
                intent.putExtra("product", products.get(position));
                startActivity(intent);
            }
        });

        //setup "components" button in toolbar
        Button componentsButton = (Button) findViewById(R.id.compButton);
        componentsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProductListMenu.this, ComponentListMenu.class);
                startActivity(intent);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProductListMenu.this, AddProduct.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //save products and materials for next time
        saveProducts();
        saveMaterials();
    }

    //load products from serialized file
    protected void loadProducts() {
        //product serialized file
        String filename = "products.srl";
        //try to get objectInputStream, then load products
        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            products = (List<Product>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //load materials from serialize file
    protected void loadMaterials() {
        //material serialized file
        String filename = "components.srl";
        //try to get objectInputStream, then load materials
        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            materials = (List<Component>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //save products to serialized file
    protected void saveProducts() {
        //product serialized file
        String filename = "products.srl";
        //try to get the objectOutputSteam, then save products
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(products);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //save materials to serialized file
    protected void saveMaterials() {
        //material serialized file
        String filename = "components.srl";
        //try to get the objectOutputStream, then save materials
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(materials);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /* public void addProduct(View view) {
        Intent intent = new Intent(ProductListMenu.this, AddProduct.class);
        startActivity(intent);
    }*/

}
