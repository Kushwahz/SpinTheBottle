package com.wordpress.helpmevishal.spinthebotttleanywhere;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SpinTable extends AppCompatActivity implements View.OnClickListener {

    ImageView oneTable, twoTable, threeTable, fourTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_table);

        oneTable = (ImageView) findViewById(R.id.ic_table_one);
        oneTable.setOnClickListener(this);
        twoTable = (ImageView) findViewById(R.id.ic_table_two);
        twoTable.setOnClickListener(this);
        threeTable = (ImageView) findViewById(R.id.ic_table_three);
        threeTable.setOnClickListener(this);
        fourTable = (ImageView) findViewById(R.id.ic_table_four);
        fourTable.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String selectedTable = null;
        switch (id){
            case R.id.ic_table_one : selectedTable = String.valueOf(R.drawable.ic_table_one);
                break;
            case R.id.ic_table_two : selectedTable = String.valueOf(R.drawable.ic_table_two);
                break;
            case R.id.ic_table_three : selectedTable = String.valueOf(R.drawable.ic_table_three);
                break;
            case R.id.ic_table_four : selectedTable = String.valueOf(R.drawable.ic_table_four);
                break;
        }
        SharedPreferences.Editor editor = getSharedPreferences("Data", MODE_PRIVATE).edit();
        editor.putString("MyTable", selectedTable);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), SpinTheBottleActivity.class));
        finishAffinity();
    }
}
