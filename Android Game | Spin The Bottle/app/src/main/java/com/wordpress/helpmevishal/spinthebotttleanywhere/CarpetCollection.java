package com.wordpress.helpmevishal.spinthebotttleanywhere;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CarpetCollection extends AppCompatActivity implements View.OnClickListener {
    ImageView oneCarpet, twoCarpet, threeCarpet, fourCarpet, fiveCarpet, sixCarpet, sevenCarpet, eightCarpet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpet_collection);
        oneCarpet = (ImageView) findViewById(R.id.ic_carpet_one);
        oneCarpet.setOnClickListener(this);
        twoCarpet = (ImageView) findViewById(R.id.ic_carpet_two);
        twoCarpet.setOnClickListener(this);
        threeCarpet = (ImageView) findViewById(R.id.ic_carpet_three);
        threeCarpet.setOnClickListener(this);
        fourCarpet = (ImageView) findViewById(R.id.ic_carpet_four);
        fourCarpet.setOnClickListener(this);
        fiveCarpet = (ImageView) findViewById(R.id.ic_carpet_five);
        fiveCarpet.setOnClickListener(this);
        sixCarpet = (ImageView) findViewById(R.id.ic_carpet_six);
        sixCarpet.setOnClickListener(this);
        sevenCarpet = (ImageView) findViewById(R.id.ic_carpet_seven);
        sevenCarpet.setOnClickListener(this);
        eightCarpet = (ImageView) findViewById(R.id.ic_carpet_eight);
        eightCarpet.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String selectedCarpet = null;
        switch (id){
            case R.id.ic_carpet_one: selectedCarpet = String.valueOf(R.drawable.ic_carpet_one);
                break;
            case R.id.ic_carpet_two: selectedCarpet = String.valueOf(R.drawable.ic_carpet_two);
                break;
            case R.id.ic_carpet_three: selectedCarpet = String.valueOf(R.drawable.ic_carpet_three);
                break;
            case R.id.ic_carpet_four: selectedCarpet = String.valueOf(R.drawable.ic_carpet_four);
                break;
            case R.id.ic_carpet_five: selectedCarpet = String.valueOf(R.drawable.ic_carpet_five);
                break;
            case R.id.ic_carpet_six: selectedCarpet = String.valueOf(R.drawable.ic_carpet_six);
                break;
            case R.id.ic_carpet_seven: selectedCarpet = String.valueOf(R.drawable.ic_carpet_seven);
                break;
            case R.id.ic_carpet_eight: selectedCarpet = String.valueOf(R.drawable.ic_carpet_eight);
                break;
        }
        SharedPreferences.Editor editor = getSharedPreferences("Data", MODE_PRIVATE).edit();
        editor.putString("MyCarpet", selectedCarpet);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), SpinTheBottleActivity.class));
        finishAffinity();
    }
}
