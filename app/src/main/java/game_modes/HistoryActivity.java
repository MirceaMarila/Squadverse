package game_modes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.squadverse.R;
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

import common.BaseActivity;
import information.HistoryInformation;
import information.UserInformation;

public class HistoryActivity extends BaseActivity {

    ListView istoric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        istoric = findViewById(R.id.list_view_history);

        String mode = getIntent().getStringExtra("mode");

        final ArrayList<String> list=new ArrayList<>();
        final ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.list_item,list);
        istoric.setAdapter(adapter);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Single_player_history").child(get_current_logged_user_username());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    HistoryInformation info=snapshot.getValue(HistoryInformation.class);
                    if(mode.equals("Singleplayer") && info.getMode().equals("Singleplayer")) {
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

    private String get_current_logged_user_username(){
        final String[] username = new String[1];
        FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(user2!=null)
        {
            String userEmail = user2.getEmail();
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