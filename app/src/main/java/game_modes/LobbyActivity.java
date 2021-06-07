package game_modes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squadverse.FeedbackActivity;
import com.example.squadverse.R;
import com.example.squadverse.RegisterActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import common.BaseActivity;
import draft.FormationsActivity;
import information.MultiplayerLobbyInformation;
import information.UserInformation;

public class LobbyActivity extends BaseActivity {

    TextView title, description;
    Button cancel, start, check;
    String mode;

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

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        ArrayList<String> to_delete = new ArrayList<>();
//        ArrayList<String> to_update = new ArrayList<>();
//
//        FirebaseDatabase.getInstance().getReference().child("Matchmaking").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    MultiplayerLobbyInformation ui = snapshot.getValue(MultiplayerLobbyInformation.class);
//                    String db_host= ui.getHost();
//                    String db_guest = ui.getGuest();
//                    if(db_host.equals(get_current_logged_user_username())) {
//                        to_delete.add(snapshot.getKey());
//                        break;
//                    }
//                    if(db_guest.equals(get_current_logged_user_username())){
//                        to_update.add(snapshot.getKey());
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//
//            }
//        });
//
//        for(String key: to_delete){
//            int a = 10;
//        }
//
//    }

    private void empty_lobby(){
        FirebaseDatabase.getInstance().getReference().child("Matchmaking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MultiplayerLobbyInformation ui = snapshot.getValue(MultiplayerLobbyInformation.class);
                    String db_host= ui.getHost();
                    String db_guest = ui.getGuest();
                    if(db_host.equals(get_current_logged_user_username())) {
                        FirebaseDatabase.getInstance().getReference().child("Matchmaking").child(snapshot.getKey()).child("Host").setValue("");
                        break;
                    }
                    if(db_guest.equals(get_current_logged_user_username())){
                        FirebaseDatabase.getInstance().getReference().child("Matchmaking").child(snapshot.getKey()).child("Guest").setValue("");
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void search_for_lobby_or_create_one(){

        // search for lobby
        final boolean[] flag = {false};
        final String[] key = {null};

        FirebaseDatabase.getInstance().getReference().child("Matchmaking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MultiplayerLobbyInformation ui = snapshot.getValue(MultiplayerLobbyInformation.class);
                    String db_opponent = ui.getGuest();
                    if (db_opponent.equals("")) {
                        flag[0] = true;
                        key[0] = snapshot.getKey();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        if (flag[0]) {
            // update
            FirebaseDatabase.getInstance().getReference().child("Matchmaking").child(key[0]).child("Guest").setValue(get_current_logged_user_username());
            opponent_found_settings();

        } else
        {
            opponent_not_found_settings();
            HashMap<String, Object> map=new HashMap<>();
            map.put("Host", get_current_logged_user_username());
            map.put("Guest", "");
            map.put("Mode", mode);
            map.put("Host_rating", "");
            map.put("Host_chemistry", "");
            map.put("Guest_rating", "");
            map.put("Guest_chemistry", "");
            map.put("Host_score", "");
            map.put("Guest_score", "");
            map.put("Host_best_att", "");
            map.put("Host_best_mid", "");
            map.put("Host_best_def", "");
            map.put("Guest_best_att", "");
            map.put("Guest_best_mid", "");
            map.put("Guest_best_def", "");
            map.put("Winner", "");
            FirebaseDatabase.getInstance().getReference().child("Matchmaking").push().updateChildren(map);
            Toast.makeText(LobbyActivity.this, "Lobby created!", Toast.LENGTH_SHORT).show();

        }
    }

    private void check_if_opponent_is_found(){
        final boolean[] flag = {false};
        FirebaseDatabase.getInstance().getReference("Matchmaking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MultiplayerLobbyInformation ui = snapshot.getValue(MultiplayerLobbyInformation.class);
                    String db_host= ui.getHost();
                    String db_guest = ui.getGuest();
                    if (db_host.equals(get_current_logged_user_username()) && !db_guest.equals("")) {
                        flag[0] = true;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        if(flag[0]){
            {
                opponent_found_settings();
                Toast.makeText(LobbyActivity.this, "BINGO!", Toast.LENGTH_SHORT).show();
            }

        }
        else
            {
            opponent_not_found_settings();
            Toast.makeText(LobbyActivity.this, "No opponent found yet..", Toast.LENGTH_SHORT).show();
            }

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

    private String get_current_logged_user_username(){
        final String[] username = new String[1];
        FirebaseUser user3 = FirebaseAuth.getInstance().getCurrentUser();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(user3!=null)
        {
            String userEmail = user3.getEmail();
            FirebaseDatabase.getInstance().getReference("User_profile").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        UserInformation ui = snapshot.getValue(UserInformation.class);
                        String db_email = ui.getEmail();
                        if (db_email.equals(userEmail)) {
                            username[0] = ui.getUsername();
                            break;
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    //nothing
                }
            });
        }

        if(acct!=null){
            username[0] = acct.getDisplayName();
        }

        return username[0];
    }
}