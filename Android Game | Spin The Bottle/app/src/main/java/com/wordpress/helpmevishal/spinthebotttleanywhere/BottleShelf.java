package com.wordpress.helpmevishal.spinthebotttleanywhere;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BottleShelf extends AppCompatActivity implements View.OnClickListener {
    ImageView oneBottle, twoBottle, threeBottle, fourBottle, fiveBottle, sixBottle, sevenBottle, eightBottle, nineBottle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_shelf);
        oneBottle = (ImageView) findViewById(R.id.ic_bottle_one);
        oneBottle.setOnClickListener(this);
        twoBottle = (ImageView) findViewById(R.id.ic_bottle_two);
        twoBottle.setOnClickListener(this);
        threeBottle = (ImageView) findViewById(R.id.ic_bottle_three);
        threeBottle.setOnClickListener(this);
        fourBottle = (ImageView) findViewById(R.id.ic_bottle_four);
        fourBottle.setOnClickListener(this);
        fiveBottle = (ImageView) findViewById(R.id.ic_bottle_five);
        fiveBottle.setOnClickListener(this);
        sixBottle = (ImageView) findViewById(R.id.ic_bottle_six);
        sixBottle.setOnClickListener(this);
        sevenBottle = (ImageView) findViewById(R.id.ic_bottle_seven);
        sevenBottle.setOnClickListener(this);
        eightBottle = (ImageView) findViewById(R.id.ic_bottle_eight);
        eightBottle.setOnClickListener(this);
        nineBottle = (ImageView) findViewById(R.id.ic_bottle_nine);
        nineBottle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String selectedBottle = null;
        switch (id){
            case R.id.ic_bottle_one: selectedBottle = String.valueOf(R.drawable.ic_bottle_one);
                break;
            case R.id.ic_bottle_two: selectedBottle = String.valueOf(R.drawable.ic_bottle_two);
                break;
            case R.id.ic_bottle_three: selectedBottle = String.valueOf(R.drawable.ic_bottle_three);
                break;
            case R.id.ic_bottle_four: selectedBottle = String.valueOf(R.drawable.ic_bottle_four);
                break;
            case R.id.ic_bottle_five: selectedBottle = String.valueOf(R.drawable.ic_bottle_five);
                break;
            case R.id.ic_bottle_six: selectedBottle = String.valueOf(R.drawable.ic_bottle_six);
                break;
            case R.id.ic_bottle_seven: selectedBottle = String.valueOf(R.drawable.ic_bottle_seven);
                break;
            case R.id.ic_bottle_eight: selectedBottle = String.valueOf(R.drawable.ic_bottle_eight);
                break;
            case R.id.ic_bottle_nine: selectedBottle = String.valueOf(R.drawable.ic_bottle_nine);
                break;
        }
        SharedPreferences.Editor editor = getSharedPreferences("Data", MODE_PRIVATE).edit();
        editor.putString("MyBottle", selectedBottle);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), SpinTheBottleActivity.class));
        finishAffinity();
    }
}
