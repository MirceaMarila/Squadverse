package draft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squadverse.BugReportActivity;
import com.example.squadverse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import common.BaseActivity;
import information.MultiplayerLobbyInformation;

public class MultiResultsActivity extends BaseActivity {

    TextView winner, yscore, yrating, ychem, oscore, orating, ochem, op_name;
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
        op_name = findViewById(R.id.opponent_name);

        received_chemistry = getIntent().getStringExtra("chemistry");
        received_rating = getIntent().getStringExtra("rating");
        mode = getIntent().getStringExtra("mode");

        yscore.setText(String.valueOf(Integer.parseInt(received_chemistry) + Integer.parseInt(received_rating)));
        ychem.setText(received_chemistry);
        yrating.setText(received_rating);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publish_results_in_firebase_for_logged_users();
                delete_your_lobby_branch();
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
                    String key = snapshot.getKey();
                    if(key.split("-",2)[0].equals(reformat_user_email(get_current_logged_user_email())) || key.split("-",2)[1].equals(reformat_user_email(get_current_logged_user_email())))
                    {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String key2 = ds.getKey();
                        if (!key2.equals(reformat_user_email(get_current_logged_user_email()))) {
                            op_name.setText(key2);
                            for (DataSnapshot ds2 : ds.getChildren())
                            {
                                String key3 = ds2.getKey();
                            MultiplayerLobbyInformation mli = ds2.getValue(MultiplayerLobbyInformation.class);

                            if(!mli.getScore().equals(""))
                            {
                                oscore.setText(mli.getScore());
                                orating.setText(mli.getRating());
                                ochem.setText(mli.getChemistry());

                                if (Integer.parseInt(yscore.getText().toString()) > Integer.parseInt(oscore.getText().toString())) {
                                    winner.setText("YOU");
                                    FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(key2).child(key3).child("Winner").setValue(reformat_user_email(get_current_logged_user_email()));
                                }
                                else if (Integer.parseInt(yscore.getText().toString()) < Integer.parseInt(oscore.getText().toString())){
                                    winner.setText("OPPONENT");
                                    FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(key2).child(key3).child("Winner").setValue(key2);
                                }
                                else{
                                    winner.setText("DRAW");
                                    FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(key2).child(key3).child("Winner").setValue("DRAW");
                                }
                            }
                            break;
                            }
                        }
                        else {
                            for (DataSnapshot ds2 : ds.getChildren())
                            { String key3 = ds2.getKey();
                            FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(key2).child(key3).child("Score").setValue(String.valueOf(Integer.parseInt(received_chemistry) + Integer.parseInt(received_rating)));
                            FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(key2).child(key3).child("Chemistry").setValue(received_chemistry);
                            FirebaseDatabase.getInstance().getReference("Currently_playing").child(key).child(key2).child(key3).child("Rating").setValue(received_rating);
                            break;
                        } }

                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void publish_results_in_firebase_for_logged_users(){

            String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            HashMap<String, Object> map=new HashMap<>();
            map.put("Mode", mode);
            map.put("Your_score", yscore.getText().toString());
            map.put("Opponent_score", oscore.getText().toString());
            map.put("Winner", winner.getText().toString());
            map.put("DateTime", timeStamp);
            map.put("Opponent_name", op_name.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("Multi_player_history").child(reformat_user_email(get_current_logged_user_email())).push().updateChildren(map);
            if(mode.equals("Friendly")) {
                HashMap<String, Object> map2=new HashMap<>();
                map2.put("friend", op_name.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Friends").child(reformat_user_email(get_current_logged_user_email())).child(op_name.getText().toString()).push().updateChildren(map2);
            }
    }

    private void delete_your_lobby_branch()
    {
        String your_name = reformat_user_email(get_current_logged_user_email());
        String opponent_name = op_name.getText().toString();

        try {
                FirebaseDatabase.getInstance().getReference().child("Currently_playing").child(your_name + "-" + opponent_name).child(your_name).removeValue();
            }

        catch (Exception e){
            //nothing
        }

        try {
            FirebaseDatabase.getInstance().getReference().child("Currently_playing").child(opponent_name + "-" + your_name).child(your_name).removeValue();
        }

        catch (Exception e){
            //nothing
        }
    }
}