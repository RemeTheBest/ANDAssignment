package com.example.gymappface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MembershipNetworking extends AppCompatActivity {

    public static TextView memberView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership_networking);
        memberView = (TextView) findViewById(R.id.memberView);
    }
    }
