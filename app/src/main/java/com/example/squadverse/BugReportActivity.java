package com.example.squadverse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import common.HideUiActivity;

public class BugReportActivity extends HideUiActivity {

    EditText bug_description;
    Button send, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_report);

        bug_description = findViewById(R.id.bug_description);
        send = findViewById(R.id.send_bug);
        back = findViewById(R.id.back_bug);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( bug_description.getText().toString().equals("")){
                    Toast.makeText(BugReportActivity.this, "You can't send an empty answer!", Toast.LENGTH_SHORT).show();
                }
                else{
                HashMap<String, Object> map = new HashMap<>();
                map.put("Bug", bug_description.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Bugs").push().updateChildren(map);
                Toast.makeText(BugReportActivity.this, "Bug registered! Thank you!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BugReportActivity.this, MainActivity.class));
                finish();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BugReportActivity.this, MainActivity.class));
                finish();
            }
        });

    }

}