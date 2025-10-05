package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.elaboratocookaroo.Common.Methods;
import com.example.elaboratocookaroo.Object.OrderedTimes;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Request.TimesOrderedByDish;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DishesChart extends AppCompatActivity {
    private BarChart barChart;
    private ArrayList<BarEntry> entries;
    private ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_chart);
        TimesOrderedByDish r=new TimesOrderedByDish("192.168.1.9",5000);
        Methods.SJ(r);
        barChart=findViewById(R.id.barchart);
        entries=new ArrayList<BarEntry>();
        names=new ArrayList<String>();
        OrderedTimes[] orderedTimes=r.getOrderedTimes();
        BubbleSort(orderedTimes);
        for(int i=0;i<orderedTimes.length;i++){
            String dish=orderedTimes[i].getDish();
            int times=orderedTimes[i].getTimes();
            entries.add(new BarEntry(i,times));
            names.add(dish);
        }
        BarDataSet barDataSet=new BarDataSet(entries,"Volte ordinato per piatto");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description=new Description();
        description.setText("piatti");
        barChart.setDescription(description);
        BarData barData=new BarData(barDataSet);
        barChart.setData(barData);
        XAxis xAxis=barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(names));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1);
        xAxis.setLabelCount(names.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(2000);
        barChart.invalidate();
    }
    private void BubbleSort(OrderedTimes[] orderedTimes) {
        int n=orderedTimes.length;
        for(int i=0;i<n-1;i++)
            for(int j=0;j<n-i-1;j++)
                if(orderedTimes[j].getTimes()>orderedTimes[j+1].getTimes()) {
                    OrderedTimes temp=orderedTimes[j];
                    orderedTimes[j]=orderedTimes[j+1];
                    orderedTimes[j+1]=temp;
                }
    }
}