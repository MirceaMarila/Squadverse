package game_modes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.squadverse.R;
import com.example.squadverse.RegisterActivity;
import com.example.squadverse.SelectAvatarActivity;

import common.BaseActivity;
import draft.FormationsActivity;

public class LocalInformativeActivity extends BaseActivity {

    Button start, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_informative);

        start = findViewById(R.id.start_local_draft);
        cancel = findViewById(R.id.cancel_local);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalInformativeActivity.this, FormationsActivity.class);
                intent.putExtra("mode", "Local");
                startActivity(intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}