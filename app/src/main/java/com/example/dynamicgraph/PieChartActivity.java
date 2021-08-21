package com.example.dynamicgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {
    PieChart pieChart;
    DBHelper db;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        textView=findViewById(R.id.piechart_txt);

        db=new DBHelper(this);
        pieChart=findViewById(R.id.piechart);

        ArrayList<PieEntry> pieEntries=db.getPieDataValues();
        PieDataSet piedataSet=new PieDataSet(pieEntries,"Dataset");
        piedataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData pieData=new PieData(piedataSet);
        pieChart.setData(pieData);
    }
}