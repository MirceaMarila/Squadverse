package draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.squadverse.MainActivity;
import com.example.squadverse.R;

import java.lang.reflect.Field;

import formations.F433AttackActivity;

public class SingleResultsActivity extends AppCompatActivity {

    TextView rating, chem, score;
    ImageView def, mid, att;
    Button back;

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

        rating.setText(received_rating);
        chem.setText(received_chemistry);
        score.setText(String.valueOf(Integer.parseInt(received_chemistry) + Integer.parseInt(received_rating)));

        def.setBackgroundResource(getId(received_defence_card, R.drawable.class));
        mid.setBackgroundResource(getId(received_midfield_card, R.drawable.class));
        att.setBackgroundResource(getId(received_attack_card, R.drawable.class));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleResultsActivity.this, MainActivity.class));
                finish();
            }
        });

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
}