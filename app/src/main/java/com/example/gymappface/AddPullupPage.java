package com.example.gymappface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddPullupPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pullup_page);

        final String[] filename = new String[1];

        // finding the buttons
        final Spinner monthSpinner = findViewById(R.id.monthSpinner);
        final EditText pullupField = findViewById(R.id.pullupField);
        FloatingActionButton addInput = findViewById(R.id.addInput);

        // setting up the possibilities for the spinner drop down.
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.monthArray, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        // Determines what file (month) is written to determined by the drop down spinner
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filename[0] = String.valueOf(parent.getItemAtPosition(position))+".txt";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filename[0] = "January.txt";
            }
        });

        // the input is added to the files when the add button is clicked.
        addInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataIn = pullupField.getText().toString();

                // Checks if data was actually given.
                if (dataIn.equals("")) {
                    dataIn = 0+"";
                }

                // Turns string into integer for adding
                int value = Integer.valueOf(dataIn);

                // Reads the corrosponding file and adds the integer value to the one given by the input.
                try {
                    FileInputStream inStream = getApplicationContext().openFileInput(filename[0]);
                    InputStreamReader inputStreamReader = new InputStreamReader(inStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder finalString = new StringBuilder();
                    String oneLine;

                    while ((oneLine = bufferedReader.readLine()) != null) {
                        finalString.append(oneLine);
                    }
                    bufferedReader.close();
                    inStream.close();
                    inputStreamReader.close();

                    value = value + Integer.parseInt(finalString.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Turns combined value back into a String
                String fileContents = String.valueOf(value);

                // Saves the String in the corosponding file name
                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput(filename[0], Context.MODE_PRIVATE);
                    outputStream.write(fileContents.getBytes());
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Returns to the Statistics Page (Maybe this isn't the right way to do it?)
                startActivity(new Intent(AddPullupPage.this,StatPage.class));

            }
        });
    }
}
