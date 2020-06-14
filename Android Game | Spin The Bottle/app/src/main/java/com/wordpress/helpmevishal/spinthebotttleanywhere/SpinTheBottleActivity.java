package com.wordpress.helpmevishal.spinthebotttleanywhere;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;
import java.util.Random;

public class SpinTheBottleActivity extends AppCompatActivity {
    ImageView Bottle, selectBottle, selectCarpet, selectTable, Table, Spin, share, about;
    Boolean Restart = false;
    int Angle;
    Random random;
    RelativeLayout Carpet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_the_bottle);

        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);


        about =(ImageView) findViewById(R.id.iv_about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), About.class));
            }
        });
        random = new Random();
        selectBottle = (ImageView) findViewById(R.id.select_bottle);
        selectBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BottleShelf.class));
            }
        });
        selectCarpet = (ImageView) findViewById(R.id.select_carpet);
        selectCarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CarpetCollection.class));
            }
        });
        selectTable = (ImageView) findViewById(R.id.select_table);
        selectTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SpinTable.class));
            }
        });
        Table = (ImageView) findViewById(R.id.iv_Table);
        Bottle = (ImageView) findViewById(R.id.iv_bottle);
        Spin = (ImageView) findViewById(R.id.bt_spin);
        Carpet = (RelativeLayout) findViewById(R.id.activity_spin_the_bottle);
        final SharedPreferences prefs = getSharedPreferences("Data", MODE_PRIVATE);

        String selectedBottle = prefs.getString("MyBottle", "Not_Found");
        if(!selectedBottle.equals("Not_Found")) Bottle.setImageResource(Integer.parseInt(selectedBottle));
        String selectedTable = prefs.getString("MyTable", "Not_Found");
        if(!selectedTable.equals("Not_Found")) Table.setImageResource(Integer.parseInt(selectedTable));
        String selectedCarpet = prefs.getString("MyCarpet", "Not_Found");
        if(!selectedCarpet.equals("Not_Found")) Carpet.setBackgroundResource(Integer.parseInt(selectedCarpet));

        Spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Restart){
                    Angle = Angle % 360;
                    RotateAnimation rotate = new RotateAnimation(Angle, 360,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setFillAfter(true);
                    rotate.setDuration(1000);
                    rotate.setInterpolator(new AccelerateDecelerateInterpolator());

                    Bottle.startAnimation(rotate);
                    Spin.setImageResource(R.drawable.ic_spin_bottle);
                    Restart = false;
                } else {
                    Angle = random.nextInt(3600) + 360;
                    RotateAnimation rotate = new RotateAnimation(0, Angle,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setFillAfter(true);
                    rotate.setDuration(3600);
                    rotate.setInterpolator(new AccelerateDecelerateInterpolator());

                    Bottle.startAnimation(rotate);
                    Spin.setImageResource(R.drawable.ic_reset);
                    Restart = true;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        deleteCache(this);
        clearApplicationData();
        SharedPreferences preferences = getSharedPreferences("Data", MODE_PRIVATE);
        preferences.edit().clear().apply();
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }
    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    try{
                        deleteDir(new File(appDir, s));
                    } catch (Exception e){
                    }
                }
            }
        }
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

}
