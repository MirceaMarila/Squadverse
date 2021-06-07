package game_modes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.squadverse.R;

import common.BaseActivity;

public class MultiPlayerActivity extends BaseActivity {

    ImageButton classic_draft, challanges, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

        classic_draft = findViewById(R.id.classic_draft_multi_btn);
        challanges = findViewById(R.id.challanges_multi_btn);
        history = findViewById(R.id.history_multi_btn);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiPlayerActivity.this, HistoryActivity.class);
                intent.putExtra("mode", "Multiplayer");
                startActivity(intent);
            }
        });

        classic_draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiPlayerActivity.this, ClassicDraftActivity.class);
                intent.putExtra("mode", "Multiplayer");
                startActivity(intent);
            }
        });
    }
}