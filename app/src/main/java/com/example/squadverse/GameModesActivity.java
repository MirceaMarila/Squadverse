package com.example.squadverse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import common.BaseActivity;
import game_modes.HistoryActivity;
import game_modes.MultiPlayerActivity;
import game_modes.ProfileActivity;
import game_modes.SinglePlayerActivity;

public class GameModesActivity extends BaseActivity {

    ImageButton singleplayer, multiplayer, local, profile, friends_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_modes);

        singleplayer = findViewById(R.id.singleplayer_btn);
        multiplayer = findViewById(R.id.multiplayer_btn);
        local = findViewById(R.id.local_btn);
        profile = findViewById(R.id.profile_btn);
        friends_list = findViewById(R.id.friends_list_btn);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameModesActivity.this, ProfileActivity.class));
            }
        });

        singleplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameModesActivity.this, SinglePlayerActivity.class));
            }
        });

        multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameModesActivity.this, MultiPlayerActivity.class));
            }
        });

        friends_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameModesActivity.this, HistoryActivity.class);
                intent.putExtra("mode", "Friends");
                startActivity(intent);
            }
        });


    }
}