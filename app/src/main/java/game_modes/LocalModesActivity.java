package game_modes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.squadverse.GameModesActivity;
import com.example.squadverse.R;

import common.BaseActivity;

public class LocalModesActivity extends BaseActivity {

    ImageButton classic, challange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_modes);

        classic = findViewById(R.id.local_modes_start_draft_btn);
        challange = findViewById(R.id.local_modes_challange_btn);

        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalModesActivity.this, LocalInformativeActivity.class);
                startActivity(intent);
            }
        });
    }
}