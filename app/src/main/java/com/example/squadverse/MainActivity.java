package com.example.squadverse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import common.HideUiActivity;
import draft.FormationsActivity;

public class MainActivity extends HideUiActivity {

    // variabile
    ImageButton register, login, build_a_draft, bug_reporting, feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register_btn);
        login = findViewById(R.id.login_btn);
        build_a_draft = findViewById(R.id.build_a_draft_btn);
        bug_reporting = findViewById(R.id.bug_report_btn);
        feedback = findViewById(R.id.feedback_btn);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SelectAvatarActivity.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        build_a_draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FormationsActivity.class));
                finish();
            }
        });

        bug_reporting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BugReportActivity.class));
                finish();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
                finish();
            }
        });
    }
}