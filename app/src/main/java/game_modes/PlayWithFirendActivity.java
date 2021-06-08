package game_modes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.squadverse.BugReportActivity;
import com.example.squadverse.R;
import com.example.squadverse.RegisterActivity;
import com.example.squadverse.SelectAvatarActivity;

import common.BaseActivity;

public class PlayWithFirendActivity extends BaseActivity {

    EditText email;
    Button create_lobby, search_lobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_with_firend);

        email = findViewById(R.id.friends_email);
        create_lobby = findViewById(R.id.create_lobby_friend);
        search_lobby = findViewById(R.id.search_lobby_friend);

        String mode = getIntent().getStringExtra("mode");

        create_lobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals(""))
                    Toast.makeText(PlayWithFirendActivity.this, "You forgot to write the email!", Toast.LENGTH_SHORT).show();
                else{
                    Intent intent = new Intent(PlayWithFirendActivity.this, LobbyActivity.class);
                    intent.putExtra("friend", reformat_user_email(email.getText().toString()));
                    intent.putExtra("mode", mode);
                    startActivity(intent);
                    finish();
                }

            }
        });

        search_lobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayWithFirendActivity.this, LobbyActivity.class);
                intent.putExtra("friend", "");
                intent.putExtra("mode", mode);
                startActivity(intent);
                finish();
            }
        });


    }
}