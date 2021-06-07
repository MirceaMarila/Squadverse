package game_modes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.squadverse.MainActivity;
import com.example.squadverse.R;
import com.example.squadverse.RegisterActivity;
import com.example.squadverse.SelectAvatarActivity;

import common.BaseActivity;
import draft.FormationsActivity;
import draft.SingleResultsActivity;

public class SinglePlayerActivity extends BaseActivity {

    ImageButton start_draft, challanges, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        start_draft = findViewById(R.id.start_draft_single_btn);
        challanges = findViewById(R.id.challanges_single_btn);
        history = findViewById(R.id.history_single_btn);

        start_draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePlayerActivity.this, FormationsActivity.class);
                intent.putExtra("mode", "Singleplayer");
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePlayerActivity.this, HistoryActivity.class);
                intent.putExtra("mode", "Singleplayer");
                startActivity(intent);
            }
        });
    }
}