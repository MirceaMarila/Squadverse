package com.example.squadverse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import common.BaseActivity;
import draft.FormationsActivity;
import game_modes.SinglePlayerActivity;

public class MainActivity extends BaseActivity {

    // variabile
    ImageButton register, login, build_a_draft, bug_reporting, feedback;
    FirebaseUser user;
    GoogleSignInAccount acct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register_btn);
        login = findViewById(R.id.login_btn);
        build_a_draft = findViewById(R.id.build_a_draft_btn);
        bug_reporting = findViewById(R.id.bug_report_btn);
        feedback = findViewById(R.id.feedback_btn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        acct = GoogleSignIn.getLastSignedInAccount(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SelectAvatarActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null || acct != null)
                    startActivity(new Intent(MainActivity.this, GameModesActivity.class));
                else
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        build_a_draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormationsActivity.class);
                intent.putExtra("mode", "Trial");
                startActivity(intent);
            }
        });

        bug_reporting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BugReportActivity.class));
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        user = FirebaseAuth.getInstance().getCurrentUser();
        acct = GoogleSignIn.getLastSignedInAccount(this);
    }
}