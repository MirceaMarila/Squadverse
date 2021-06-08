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
               // exit_or_delete_lobby();
                Intent intent = new Intent(ClassicDraftActivity.this, LobbyActivity.class);
                intent.putExtra("mode", "Multiplayer");
                startActivity(intent);
            }
        });

    }

    private void exit_or_delete_lobby(){

        FirebaseDatabase.getInstance().getReference().child("Matchmaking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MultiplayerLobbyInformation ui = snapshot.getValue(MultiplayerLobbyInformation.class);
                    String db_host= ui.getHost();
                    String db_guest = ui.getGuest();
                    if(db_host.equals("") && db_guest.equals("")) {
                        FirebaseDatabase.getInstance().getReference().child("Matchmaking").child(snapshot.getKey()).removeValue();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    private void delete_lobby(String lobby_name){
        FirebaseDatabase.getInstance().getReference().child("Matchmaking").child(lobby_name).removeValue();
    }

    private void exit_from_lobby(String lobby_name){
        FirebaseDatabase.getInstance().getReference().child("Matchmaking").child(lobby_name).child("Guest").setValue("");
    }

}