package game_modes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.squadverse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import common.BaseActivity;
import draft.FormationsActivity;
import information.MultiplayerLobbyInformation;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.squadverse.R;

import common.BaseActivity;

public class ClassicDraftActivity extends BaseActivity {

    ImageButton matchmaking, play_with_a_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_draft);

        matchmaking = findViewById(R.id.classic_draft_matchmake_btn);
        play_with_a_friend = findViewById(R.id.classic_draft_play_friend_btn);

        matchmaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassicDraftActivity.this, LobbyActivity.class);
                intent.putExtra("mode", "Multiplayer");
                startActivity(intent);
            }
        });

    }

}