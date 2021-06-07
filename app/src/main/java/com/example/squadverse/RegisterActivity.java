package com.example.squadverse;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.CryptoPrimitive;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import java.util.HashMap;

import common.BaseActivity;
import game_modes.ProfileActivity;
import information.UserInformation;

import static draft.FormationsActivity.getId;

public class RegisterActivity extends BaseActivity {

    //variabile
    private EditText email, password, username;
            Button register, back;
            ImageView avatar;
    private String avatar_name;
    private final FirebaseAuth auth_register =FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        register = findViewById(R.id.register);
        back = findViewById(R.id.back);
        avatar = findViewById(R.id.avatar);

        //avatar setter
        final String echipa = getIntent().getStringExtra("avatar");
        avatar_name = echipa;
        int id;
        id = getId(echipa, R.drawable.class);
        avatar.setImageResource(id);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_username = username.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_username))
                {
                    Toast.makeText(RegisterActivity.this,"Empty credentials",Toast.LENGTH_SHORT).show();
                }
                else if (txt_password.length()<6)
                {
                    Toast.makeText(RegisterActivity.this,"Password too short",Toast.LENGTH_SHORT).show();
                }
                else check_username_already_exists_and_register_if_not(txt_username, txt_email, txt_password);

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void registerUser(String email, String password, String username) {

        auth_register.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    signOut();
                    add_user_to_database(username, email, password);
                    login_user(username, password);

                    Toast.makeText(RegisterActivity.this,"Registering user successful!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, GameModesActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Registration failed! \n There might be already an account with this email.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void add_user_to_database(String username, String email, String password){
        HashMap<String, Object> map=new HashMap<>();
        map.put("Username", username);
        map.put("Email", email);
        try {
            map.put("Password", criptare_parola(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            map.put("Password", "hashed_password");
        }
        map.put("Avatar", avatar_name);

        FirebaseDatabase.getInstance().getReference().child("User_profile").push().updateChildren(map);
    }

    private void check_username_already_exists_and_register_if_not(String username, String email, String password) {
        final boolean[] flag = {false};
        final boolean[] flag1 = {false};
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
                    registerUser(email, password, username);
                    flag1[0] = true;
                } else if (!flag1[0]) {
                    Toast.makeText(RegisterActivity.this, "There is already an account with this username. Please choose another one!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

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
                            // nothing
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

    private void login_user(String username, String password) {
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
                        FirebaseAuth auth_register = FirebaseAuth.getInstance();
                        auth_register.signInWithEmailAndPassword(db_email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                            // nothing
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

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String criptare_parola(String parola) throws NoSuchAlgorithmException {

        // salt generating
        SecureRandom random = new SecureRandom();
        byte[] biti = new byte[10];
        random.nextBytes(biti);
        Encoder encoder = Base64.getEncoder();
        String sare = encoder.encodeToString(biti);

        // transforming the password and salt in bytes
        byte[] parola_bytes = parola.getBytes(StandardCharsets.UTF_8);
        Decoder decoder = Base64.getDecoder();
        byte[] sare_bytes = decoder.decode(sare);
        byte[] sare_plus_parola_bytes = new byte[sare_bytes.length + parola_bytes.length];
        System.arraycopy(sare_bytes, 0, sare_plus_parola_bytes, 0, sare_bytes.length);
        System.arraycopy(parola_bytes, 0, sare_plus_parola_bytes, sare_bytes.length, parola_bytes.length);

        // hashing the salt+password bytes
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] sare_parola_bytes_hashed = digest.digest(sare_plus_parola_bytes);

        // returning the string result of hashed salt_password
        return encoder.encodeToString(sare_parola_bytes_hashed);


    }
}