package draft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.squadverse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import common.BaseActivity;
import information.MultiplayerLobbyInformation;

public class MultiResultsActivity extends BaseActivity {

    TextView winner, yscore, yrating, ychem, oscore, orating, ochem;
    String received_rating, received_chemistry, mode;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_results);

        winner = findViewById(R.id.winner_multi);
        yscore = findViewById(R.id.your_score);
        yrating = findViewById(R.id.your_rating);
        ychem = findViewById(R.id.your_chemistry);
        oscore = findViewById(R.id.oponent_score);
        orating = findViewById(R.id.oponent_rating);
        ochem = findViewById(R.id.oponent_chemistry);
        exit = findViewById(R.id.exit_multi_lobby);

        received_chemistry = getIntent().getStringExtra("chemistry");
        received_rating = getIntent().getStringExtra("rating");
        mode = getIntent().getStringExtra("mode");

        yscore.setText(String.valueOf(Integer.parseInt(received_chemistry) + Integer.parseInt(received_rating)));
        ychem.setText(received_chemistry);
        yrating.setText(received_rating);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        wait_for_opponent_and_update_textviews();

    }

    private void wait_for_opponent_and_update_textviews(){

        FirebaseDatabase.getInstance().getReference("Currently_playing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String key = snapshot.getKey();
                        if(key.equals(mode.split("@",2)[1])){
                            MultiplayerLobbyInformation mli = ds.getValue(MultiplayerLobbyInformation.class);
                            if(mli.getHost().equals(reformat_user_email(get_current_logged_user_email())))
                            {
                                FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(ds.getKey()).child("Host_score").setValue(String.valueOf(Integer.parseInt(received_chemistry) + Integer.parseInt(received_rating)));
                                FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(ds.getKey()).child("Host_chemistry").setValue(received_chemistry);
                                FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(ds.getKey()).child("Host_rating").setValue(received_rating);

                                if(!mli.getGuest().equals("") && !mli.getGuest_score().equals("")){
                                    MultiplayerLobbyInformation mli2 = ds.getValue(MultiplayerLobbyInformation.class);

                                    oscore.setText(mli2.getGuest_score());
                                    orating.setText(mli2.getGuest_rating());
                                    ochem.setText(mli2.getGuest_chemistry());

                                    if(Integer.parseInt(yscore.getText().toString()) > Integer.parseInt(oscore.getText().toString()))
                                        winner.setText("YOU");
                                    else
                                        winner.setText("OPPONENT");
                                }
                                }

                            if(mli.getGuest().equals(reformat_user_email(get_current_logged_user_email())))
                            {
                                FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(ds.getKey()).child("Guest_score").setValue(String.valueOf(Integer.parseInt(received_chemistry) + Integer.parseInt(received_rating)));
                                FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(ds.getKey()).child("Guest_chemistry").setValue(received_chemistry);
                                FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(ds.getKey()).child("Guest_rating").setValue(received_rating);

                                if(!mli.getGuest().equals("") && !mli.getGuest_score().equals("")){
                                    MultiplayerLobbyInformation mli2 = ds.getValue(MultiplayerLobbyInformation.class);

                                    oscore.setText(mli2.getHost_score());
                                    orating.setText(mli2.getHost_rating());
                                    ochem.setText(mli2.getHost_chemistry());

                                    if(Integer.parseInt(yscore.getText().toString()) > Integer.parseInt(oscore.getText().toString()))
                                        winner.setText("YOU");
                                    else
                                        winner.setText("OPPONENT");
                                    }
                                }
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}