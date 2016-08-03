package com.cs410_hci.holgater.cs410finalproject;

import android.graphics.BitmapFactory;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by mtsvietukhin on 8/2/2016.
 */
public class Test {
    //test - hard code products
    ///*
    static String[] cNameId = {
            "Black Lace",
            "White Lace",
            "Purple Lace",
            "Red Lace",
            "Blue Fabric",
            "Red Fabric",
            "Plaid Fabric",
            "White Elastic",
            "Green Elastic",
            "Red Button",
            "Blue Button",
            "Yellow Button",
            "Green Button",
            "Patterned Fabric 1",
            "Patterned Fabric 2"

    };

    static int[] cImageId = {
            R.drawable.component01,
            R.drawable.component02,
            R.drawable.component03,
            R.drawable.component04,
            R.drawable.component05,
            R.drawable.component06,
            R.drawable.component07,
            R.drawable.component08,
            R.drawable.component09,
            R.drawable.component10,
            R.drawable.component11,
            R.drawable.component12,
            R.drawable.component13,
            R.drawable.component14,
            R.drawable.component15
    };

    static int[] cItemInStockNumId = {
            12,
            2,
            6,
            15,
            4,
            1,
            0,
            13,
            19,
            2,
            1,
            0,
            25,
            21,
            4
    };

    static String[] cDescription = {
            "Some black lace - coolio",
            "Some white lace - coolio",
            "Some purple lace - coolio",
            "Some red lace - coolio",
            "Some blue fabric - coolio",
            "Some red fabric - coolio",
            "Some plaid fabric - coolio",
            "Some white elastic - coolio",
            "Some green elastic - coolio",
            "A red button - coolio",
            "A blue button - coolio",
            "A yellow button - coolio",
            "A green button - coolio",
            "Some patterned fabric - coolio",
            "Some patterned fabric - coolio"

    };

    static Component[] cComponents = {
            new Component(cNameId[0], cImageId[0], cItemInStockNumId[0], cDescription[0]),
            new Component(cNameId[1], cImageId[1], cItemInStockNumId[1], cDescription[1]),
            new Component(cNameId[2], cImageId[2], cItemInStockNumId[2], cDescription[2]),
            new Component(cNameId[3], cImageId[3], cItemInStockNumId[3], cDescription[3]),
            new Component(cNameId[4], cImageId[4], cItemInStockNumId[4], cDescription[4]),
            new Component(cNameId[5], cImageId[5], cItemInStockNumId[5], cDescription[5]),
            new Component(cNameId[6], cImageId[6], cItemInStockNumId[6], cDescription[6]),
            new Component(cNameId[7], cImageId[7], cItemInStockNumId[7], cDescription[7]),
            new Component(cNameId[8], cImageId[8], cItemInStockNumId[8], cDescription[8]),
            new Component(cNameId[9], cImageId[9], cItemInStockNumId[9], cDescription[9]),
            new Component(cNameId[10], cImageId[10], cItemInStockNumId[10], cDescription[10]),
            new Component(cNameId[11], cImageId[11], cItemInStockNumId[11], cDescription[11]),
            new Component(cNameId[12], cImageId[12], cItemInStockNumId[12], cDescription[12]),
            new Component(cNameId[13], cImageId[13], cItemInStockNumId[13], cDescription[13]),
            new Component(cNameId[14], cImageId[14], cItemInStockNumId[14], cDescription[14]),
    };

    //test - hard code products
    ///*
    static String[] pNameId = {
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

    static int[] pImageId = {
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

    static int[] pItemInStockNumId = {
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

    static String[] pDescription = {
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
}
