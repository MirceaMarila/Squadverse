package draft;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.squadverse.R;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import common.BaseActivity;

public class SingleResultsActivity extends BaseActivity {

    TextView rating, chem, score;
    ImageView def, mid, att;
    Button back;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_results);

        rating = findViewById(R.id.single_results_rating);
        chem = findViewById(R.id.single_results_chem);
        score = findViewById(R.id.single_results_score);
        def = findViewById(R.id.single_results_defence_card);
        mid = findViewById(R.id.single_results_midfield_card);
        att = findViewById(R.id.single_results_attack_card);
        back = findViewById(R.id.single_results_back_btn);

        String received_rating, received_chemistry, received_defence_card, received_midfield_card, received_attack_card;
        received_rating = getIntent().getStringExtra("rating");
        received_chemistry = getIntent().getStringExtra("chemistry");
        received_defence_card = getIntent().getStringExtra("best_defender");
        received_midfield_card = getIntent().getStringExtra("best_midfielder");
        received_attack_card = getIntent().getStringExtra("best_attacker");
        mode = getIntent().getStringExtra("mode");

        rating.setText(received_rating);
        chem.setText(received_chemistry);
        score.setText(String.valueOf(Integer.parseInt(received_chemistry) + Integer.parseInt(received_rating)));

        def.setBackgroundResource(getId(received_defence_card, R.drawable.class));
        mid.setBackgroundResource(getId(received_midfield_card, R.drawable.class));
        att.setBackgroundResource(getId(received_attack_card, R.drawable.class));
        def.getBackground().setAlpha(255);
        mid.getBackground().setAlpha(255);
        att.getBackground().setAlpha(255);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        publish_results_in_firebase_for_logged_users();

    }

    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    private void publish_results_in_firebase_for_logged_users(){
        if(!mode.equals("Trial")){

            String email = reformat_user_email(get_current_logged_user_email());

            String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            HashMap<String, Object> map=new HashMap<>();
            map.put("Mode", mode);
            map.put("Rating", rating.getText().toString());
            map.put("Chemistry", chem.getText().toString());
            map.put("Score", score.getText().toString());
            map.put("DateTime", timeStamp);

            FirebaseDatabase.getInstance().getReference().child("Single_player_history").child(email).push().updateChildren(map);
        }
    }

}