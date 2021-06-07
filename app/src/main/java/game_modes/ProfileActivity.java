package game_modes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.squadverse.BugReportActivity;
import com.example.squadverse.GameModesActivity;
import com.example.squadverse.MainActivity;
import com.example.squadverse.R;
import com.example.squadverse.RegisterActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static draft.FormationsActivity.getId;

import common.BaseActivity;
import information.UserInformation;

public class ProfileActivity extends BaseActivity {

    ImageView profile_pic;
    TextView username, type;
    Button sign_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_pic = findViewById(R.id.profile_picture);
        username = findViewById(R.id.profile_username);
        type = findViewById(R.id.profile_type);
        sign_out = findViewById(R.id.sign_out_btn);

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();

            }
        });

        set_username_and_type_and_picture();


    }

    private void signOut() {
        try{
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ProfileActivity.this, "Signed out!", Toast.LENGTH_SHORT).show();

                        }
                    });


        }catch(Exception error1) {
        // nothing
        }

        try{
            FirebaseAuth.getInstance().signOut();

        }catch(Exception error1) {
        // nothing
        }

    }

    private void set_username_and_type_and_picture(){
        // getting user's name from email or google acc
        FirebaseUser user4 = FirebaseAuth.getInstance().getCurrentUser();

        if (user4 != null) {
            String userEmail = user4.getEmail();

            FirebaseDatabase.getInstance().getReference("User_profile").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        UserInformation ui = snapshot.getValue(UserInformation.class);
                        String db_email = ui.getEmail();
                        if (db_email.equals(userEmail)) {
                            username.setText(ui.getUsername());
                            type.setText("Squadverse account");
                            profile_pic.setBackgroundResource(getId(ui.getAvatar(), R.drawable.class));
                            break;
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {

                }
            });


        } else {
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                username.setText(acct.getDisplayName());
                type.setText("Google account");
                Glide.with(this).load(String.valueOf(acct.getPhotoUrl())).into(profile_pic);
            }
        }
    }
}