package game_modes;

import androidx.appcompat.app.AppCompatActivity;

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


    }
}