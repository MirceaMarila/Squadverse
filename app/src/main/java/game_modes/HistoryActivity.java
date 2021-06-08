package game_modes;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.squadverse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import common.BaseActivity;
import information.HistoryInformation;
import information.MultiplayerHistoryInformation;

public class HistoryActivity extends BaseActivity {

    ListView istoric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        istoric = findViewById(R.id.list_view_history);

        String mode = getIntent().getStringExtra("mode");

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        istoric.setAdapter(adapter);

        if(mode.equals("Singleplayer")){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Single_player_history").child(reformat_user_email(get_current_logged_user_email()));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HistoryInformation info = snapshot.getValue(HistoryInformation.class);
                    if (mode.equals("Singleplayer") && info.getMode().equals("Singleplayer")) {
                        String txt = "\nRating: " + info.getRating() + "                    Chemistry: " + info.getChemistry() + "                  Score: " + info.getScore() + "\n\nMode: " + info.getMode() + "                  DateTime: " + info.getDateTime() + "\n";
                        list.add(txt);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // nothing
            }
        });
    }
        else if(mode.equals("Multiplayer")){

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Multi_player_history").child(reformat_user_email(get_current_logged_user_email()));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MultiplayerHistoryInformation info = snapshot.getValue(MultiplayerHistoryInformation.class);
                        if (mode.equals("Multiplayer") && info.getMode().equals("Multiplayer")) {
                            String txt = "\nOpponent: " + info.getOpponent_name() + "                    Winner: " + info.getWinner() + "\n\nYour score: " + info.getYour_score() + "                    Opponent score: " + info.getOpponent_score() + "\n\nDateTime: " + info.getDateTime() + "                    Mode: " + info.getMode() + "\n";
                            list.add(txt);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // nothing
                }
            });
        }

    }

}