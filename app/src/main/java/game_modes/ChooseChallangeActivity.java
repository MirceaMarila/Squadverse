package game_modes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.squadverse.GameModesActivity;
import com.example.squadverse.R;

import common.BaseActivity;
import draft.FormationsActivity;

public class ChooseChallangeActivity extends BaseActivity {

    Button right_main_foot;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_challange);

        right_main_foot = findViewById(R.id.challange_right_main_foot);
        mode = getIntent().getStringExtra("mode");

        right_main_foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseChallangeActivity.this, FormationsActivity.class);
                intent.putExtra("mode", mode + " RightMainFoot Challange");
                startActivity(intent);
                finish();
            }
        });
    }
}