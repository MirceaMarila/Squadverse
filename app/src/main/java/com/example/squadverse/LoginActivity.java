package com.example.squadverse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import common.BaseActivity;
import information.UserInformation;

public class LoginActivity extends BaseActivity {

    //Firebase acc variables
    private EditText username, password;
    Button login, back;
    private FirebaseAuth auth_login;

    //Google acc variables
    SignInButton signin;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        login = findViewById(R.id.login_button);
        back = findViewById(R.id.back_login);
        signin = findViewById(R.id.sign_in_button);

        auth_login = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username=username.getText().toString();
                String txt_password=password.getText().toString();
                loginUser(txt_username,txt_password);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

        // GOOGLE SIGNIN
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });

        // GOOGLE SIGN IN
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    // Firebase account SIGNIN methods
    private void loginUser(String username, String password) {
        final boolean[] acc_found = {false};

        //get the email from database using username to identify account and log in the user
        FirebaseDatabase.getInstance().getReference("User_profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    UserInformation ui = snapshot.getValue(UserInformation.class);
                    String db_username = ui.getUsername();
                    String db_email = ui.getEmail();

                    if (db_username.equals(username)) {
                        acc_found[0] = true;
                        auth_login.signInWithEmailAndPassword(db_email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, GameModesActivity.class));
                                finish();
                            }
                        });
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(!acc_found[0])
                    Toast.makeText(LoginActivity.this, "There is no account with this username! Try again.", Toast.LENGTH_SHORT).show();
            }
        }, 3000);



    }

    //GOOGLE SIGNIN methods
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            String google_username = acct.getDisplayName();
            String google_email = acct.getEmail();

            check_username_already_exists_and_register_if_not(google_username, google_email);

            startActivity(new Intent(LoginActivity.this, GameModesActivity.class));
            finish();
        } catch (ApiException e) {

            Log.w("error:::::::", "signInResult:failed code=" + e.getStatusCode());

        }
    }


    private void add_user_to_database(String username, String email){
        HashMap<String, Object> map=new HashMap<>();
        map.put("Username", username);
        map.put("Email", email);
        map.put("Password", "google_account");
        map.put("Avatar", "google_account_icon");

        FirebaseDatabase.getInstance().getReference().child("User_profile").push().updateChildren(map);
    }

    private void check_username_already_exists_and_register_if_not(String username, String email) {
        final boolean[] flag = {false};
        FirebaseDatabase.getInstance().getReference("User_profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserInformation ui = snapshot.getValue(UserInformation.class);
                    String db_username = ui.getUsername();
                    if (db_username.equals(username)) {
                        flag[0] = true;
                        break;
                    }
                }
                if (!flag[0]) {
                    add_user_to_database(username, email);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }




}