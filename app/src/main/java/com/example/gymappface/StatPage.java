package com.example.gymappface;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class StatPage extends AppCompatActivity {

    BarChart bar;

    private ArrayList<String> MONTHS;
    private static int NUM_OF_MONTHS = 12;
    private static int NUM_OF_PULLUPS = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_page);
        bar = findViewById(R.id.bargraph);

        // list of months
        MONTHS = new ArrayList<>();

        MONTHS.add("January");
        MONTHS.add("February");
        MONTHS.add("March");
        MONTHS.add("April");
        MONTHS.add("May");
        MONTHS.add("June");
        MONTHS.add("July");
        MONTHS.add("August");
        MONTHS.add("September");
        MONTHS.add("October");
        MONTHS.add("November");
        MONTHS.add("December");


        /*
        Reads data from the twelve local MONTHS.txt files. Uses this data to
        create the graph.
         */
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        int i = 0;
        for(String string : MONTHS)
        {
            try {
                FileInputStream inStream = this.openFileInput(string + ".txt");
                InputStreamReader inputStreamReader = new InputStreamReader(inStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder finalString = new StringBuilder();
                String oneLine;

                while((oneLine = bufferedReader.readLine()) != null) {
                    finalString.append(oneLine);
                }
                bufferedReader.close();
                inStream.close();
                inputStreamReader.close();

                int value = Integer.parseInt(finalString.toString());

                barEntries.add(new BarEntry(value ,i));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                barEntries.add(new BarEntry( 0,i));
            } catch (IOException e) {
                e.printStackTrace();
                barEntries.add(new BarEntry( 0,i));
            }
            i++;
        }

        // Setting up the graph
        BarDataSet barDataSet = new BarDataSet(barEntries,"Pull ups");
        BarData theData = new BarData(MONTHS,barDataSet);
        bar.setData(theData);
        bar.setTouchEnabled(true);
        bar.setDragEnabled(true);
        bar.setPinchZoom(true);
        bar.setDoubleTapToZoomEnabled(true);
        bar.setHighlightPerTapEnabled(false);
        bar.setHighlightPerDragEnabled(false);

        /*
        Button for adding new pull up data
         */
        FloatingActionButton addPullup = findViewById(R.id.addPullup);
        addPullup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StatPage.this,AddPullupPage.class));
            }
        });




    }
}
