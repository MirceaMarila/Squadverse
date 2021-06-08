package game_modes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squadverse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import common.BaseActivity;
import draft.FormationsActivity;
import information.MultiplayerLobbyInformation;

public class LobbyActivity extends BaseActivity {

    TextView title, description;
    Button cancel, start, check;
    String mode, lobby_key;
    boolean lobby_created, in_game, deleted_bobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        title = findViewById(R.id.lobby_title);
        description = findViewById(R.id.lobby_description);
        cancel = findViewById(R.id.cancel_lobby);
        start = findViewById(R.id.start_lobby);
        check = findViewById(R.id.check_lobby);

        mode = getIntent().getStringExtra("mode");
        lobby_created = false;
        in_game = false;
        deleted_bobby = false;

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LobbyActivity.this, FormationsActivity.class);
                intent.putExtra("mode", mode);
                startActivity(intent);
                finish();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_opponent_is_found();
            }
        });

        search_for_lobby_or_create_one();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            if(!deleted_bobby) {
                FirebaseDatabase.getInstance().getReference().child("Lobby").child(reformat_user_email(get_current_logged_user_email())).removeValue();
                deleted_bobby = true;
            }
        }
        catch (Exception e){
            //nothing
        }
    }

    private void search_for_lobby_or_create_one(){

        FirebaseDatabase.getInstance().getReference("Lobby").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean match_found = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String key = snapshot.getKey();
                        if(!key.equals(reformat_user_email(get_current_logged_user_email()))) {
                            MultiplayerLobbyInformation mli = ds.getValue(MultiplayerLobbyInformation.class);
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("Mode", mli.getMode());
                            map.put("Rating", mli.getRating());
                            map.put("Chemistry", mli.getChemistry());
                            map.put("Score", mli.getScore());
                            map.put("Winner", mli.getWinner());
                            map.put("Opponent", reformat_user_email(get_current_logged_user_email()));

                            move_lobby_to_ingame(key, map);

                            try {
                                if(!deleted_bobby) {
                                    FirebaseDatabase.getInstance().getReference().child("Lobby").child(reformat_user_email(get_current_logged_user_email())).removeValue();
                                    deleted_bobby = true;
                                }
                            }
                            catch (Exception e){
                                //nothing
                            }

                            match_found = true;
                            opponent_found_settings();
                            break;
                        }
                    }
                }
                if (!match_found && !in_game)
                    create_lobby();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    private void create_lobby(){
        if(!lobby_created) {
            opponent_not_found_settings();
            HashMap<String, Object> map = new HashMap<>();
            map.put("Mode", mode);
            map.put("Chemistry", "");
            map.put("Rating", "");
            map.put("Score", "");
            map.put("Opponent", "");
            map.put("Winner", "");
            FirebaseDatabase.getInstance().getReference().child("Lobby").child(reformat_user_email(get_current_logged_user_email())).push().updateChildren(map);
            Toast.makeText(LobbyActivity.this, "Lobby created!", Toast.LENGTH_SHORT).show();
            lobby_created = true;
        }
    }

    private void move_lobby_to_ingame(String key, HashMap<String, Object> map){
        if(!in_game && !lobby_created)
        {
            lobby_key = key+"-"+reformat_user_email(get_current_logged_user_email());
            FirebaseDatabase.getInstance().getReference().child("Currently_playing").child(lobby_key).child(key).push().updateChildren(map);
            map.put("Opponent", key);
            FirebaseDatabase.getInstance().getReference().child("Currently_playing").child(lobby_key).child(reformat_user_email(get_current_logged_user_email())).push().updateChildren(map);
            in_game = true;
        }
    }

    private void check_if_opponent_is_found(){

        FirebaseDatabase.getInstance().getReference("Currently_playing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String key = snapshot.getKey();
                        if(key.split("-", 2)[0].equals(reformat_user_email(get_current_logged_user_email())) || key.split("-", 2)[1].equals(reformat_user_email(get_current_logged_user_email())))
                            opponent_found_settings();
                            lobby_key = snapshot.getKey()+"-"+reformat_user_email(get_current_logged_user_email());

                        break;

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    private void opponent_found_settings(){
        title.setText("Opponent found!");
        description.setText("Press START to begin the draft.");
        start.setEnabled(true);
        start.setAlpha(1);
    }

    private void opponent_not_found_settings(){
        title.setText("Searching for opponent..");
        description.setText("Press CHECK to verify if you found an opponent.");
        start.setEnabled(false);
        start.setAlpha((float)0.5);
    }

}