package com.example.squadverse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import common.BaseActivity;

public class FeedbackActivity extends BaseActivity {

    EditText feedback;
    Button send, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedback = findViewById(R.id.feedback_description);
        send = findViewById(R.id.send_feedback);
        back = findViewById(R.id.back_feedback);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( feedback.getText().toString().equals("")){
                    Toast.makeText(FeedbackActivity.this, "You can't send an empty answer!", Toast.LENGTH_SHORT).show();
                }
                else{
                HashMap<String, Object> map = new HashMap<>();
                map.put("Feedback", feedback.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Feedbacks").push().updateChildren(map);
                Toast.makeText(FeedbackActivity.this, "Feedback registered! Thank you!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FeedbackActivity.this, MainActivity.class));
                finish();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedbackActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}