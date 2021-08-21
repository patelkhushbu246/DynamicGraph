package com.example.dynamicgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {
    TextView textView;
    BarChart barChart;
    DBHelper db;


    BarData barData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        textView=findViewById(R.id.barchart_txt);
        db=new DBHelper(this);
        barChart=findViewById(R.id.barchart);


        ArrayList<BarEntry> barEntries=db.getBarDataValues();
        BarDataSet barDataSet=new BarDataSet(barEntries,"Dataset");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);
        barChart.setData(barData);
    }
}