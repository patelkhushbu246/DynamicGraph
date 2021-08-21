package com.example.dynamicgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText x_value,y_value;
    Button btn;
    DBHelper db;


    ListView lv;
    ArrayList<Value> arr;
    //create ListAdapter class reference
    ListAdapter listAdapter;

    //floating button
    FloatingActionButton mAddFab, mPiechart, mBarchart,mLinechart;
    TextView pie, bar,line;
    Boolean isAllFabsVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize add value
        x_value=findViewById(R.id.xvalue_edt);
        y_value=findViewById(R.id.yvalue_edt);
        btn=findViewById(R.id.add_btn);

        db=new DBHelper(this);
        //initialize display data
        lv=(ListView)findViewById(R.id.listview);
        arr=new ArrayList<>();
        arr=db.retrive();
        listAdapter=new ListAdapter(this,arr);
        lv.setAdapter(listAdapter);

        //initialize all floating
        mAddFab=findViewById(R.id.add_fab);
        mPiechart=findViewById(R.id.piechart_btn);
        mBarchart=findViewById(R.id.barchart_btn);
        mLinechart=findViewById(R.id.linechart_btn);

        pie=findViewById(R.id.piechart_txt);
        bar=findViewById(R.id.barchart_txt);
        line=findViewById(R.id.linechart_txt);

        mPiechart.setVisibility(View.GONE);
        mBarchart.setVisibility(View.GONE);
        mLinechart.setVisibility(View.GONE);

        pie.setVisibility(View.GONE);
        bar.setVisibility(View.GONE);
        line.setVisibility(View.GONE);

        isAllFabsVisible = false;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });

        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAllFabsVisible) {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs VISIBLE.
                    mPiechart.show();
                    mBarchart.show();
                    mLinechart.show();
                    pie.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.VISIBLE);
                    line.setVisibility(View.VISIBLE);

                    // make the boolean variable true as
                    // we have set the sub FABs
                    // visibility to GONE
                    isAllFabsVisible = true;
                } else {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs GONE.
                    mPiechart.hide();
                    mBarchart.hide();
                    mLinechart.hide();
                    pie.setVisibility(View.GONE);
                    bar.setVisibility(View.GONE);
                    line.setVisibility(View.GONE);

                    // make the boolean variable false
                    // as we have set the sub FABs
                    // visibility to GONE
                    isAllFabsVisible = false;
                }
            }
        });

        mPiechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,PieChartActivity.class);
                startActivity(i);
            }
        });
        mBarchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,BarChartActivity.class);
                startActivity(i);
            }
        });
        mLinechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LineChartActivity.class);
                startActivity(i);
            }
        });
    }
    private void insert() {

        String xvalue=x_value.getText().toString().trim();
        String yvalue=y_value.getText().toString().trim();
        db.addvalue(xvalue, yvalue);
        Toast.makeText(MainActivity.this, "Added...", Toast.LENGTH_SHORT).show();

    }

}