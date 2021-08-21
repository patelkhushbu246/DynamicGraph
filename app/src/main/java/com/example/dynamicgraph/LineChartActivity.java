package com.example.dynamicgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity {
    TextView textView;

    LineDataSet lineDataSet=new LineDataSet(null,null);
    LineChart lineChart;
    DBHelper db;
    ArrayList<ILineDataSet> dataSets=new ArrayList<>();
    LineData lineData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        textView=findViewById(R.id.linechart_txt);

        db=new DBHelper(this);
        lineChart=findViewById(R.id.linechart);

        lineDataSet.setLineWidth(4);

        lineDataSet.setValues(db.getDataValues());
        lineDataSet.setLabel("Dataset 1");
        dataSets.clear();
        dataSets.add(lineDataSet);
        lineData =new LineData(dataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();
    }


}