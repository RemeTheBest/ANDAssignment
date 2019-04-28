package com.example.gymappface;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MenuItem memberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView stats = findViewById(R.id.stats);
        TextView challenges = findViewById(R.id.challenges);

        FloatingActionButton webGO = findViewById(R.id.webSiteButton);

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StatPage.class));
            }
        });

        challenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ChallengePage.class));
            }
        });

        webGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent();
                webIntent.setData(Uri.parse("https://www.nutrition.gov/")); // parameter must correspond to action name given in the intent filters for the target app.
                try {
                    startActivity(webIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Nutrition Website" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }


    public boolean onOptionsItemSelected(MenuItem item) {
      switch(item.getItemId()){
            case R.id.logOut:
                finish();
                startActivity(new Intent(MainActivity.this, LogIn.class));
                return true;
            case R.id.members:
                fetchData s = new fetchData();
                s.execute();
                startActivity(new Intent(MainActivity.this, MembershipNetworking.class));
                return true;



    }
        return super.onOptionsItemSelected(item);
    }
        protected void onStart(){
        super.onStart();
        }


    }
