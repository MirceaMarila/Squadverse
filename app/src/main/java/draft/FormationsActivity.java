package draft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.squadverse.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.BaseActivity;

public class FormationsActivity extends BaseActivity {

    List<String> random_formations = new ArrayList<String>();
    ImageButton formation1, formation2, formation3, formation4, formation5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations);

        formation1 = findViewById(R.id.formation_1);
        formation2 = findViewById(R.id.formation_2);
        formation3 = findViewById(R.id.formation_3);
        formation4 = findViewById(R.id.formation_4);
        formation5 = findViewById(R.id.formation_5);

        // taking 5 random formations - 3 balanced formations, 1 attacking formation and 1 defensive formation
        choose_5_random_formations();

        // setting the background of the 5 imagebuttons
        set_imagebuttons_background();

        formation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormationsActivity.this, CaptainActivity.class);
                intent.putExtra("formation", (String) random_formations.toArray()[0]);
                intent.putExtra("mode", getIntent().getStringExtra("mode"));
                startActivity(intent);
                finish();
            }
        });

        formation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormationsActivity.this, CaptainActivity.class);
                intent.putExtra("formation", (String) random_formations.toArray()[3]);
                intent.putExtra("mode", getIntent().getStringExtra("mode"));
                startActivity(intent);
                finish();
            }
        });

        formation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormationsActivity.this, CaptainActivity.class);
                intent.putExtra("formation", (String) random_formations.toArray()[1]);
                intent.putExtra("mode", getIntent().getStringExtra("mode"));
                startActivity(intent);
                finish();
            }
        });

        formation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormationsActivity.this, CaptainActivity.class);
                intent.putExtra("formation", (String) random_formations.toArray()[4]);
                intent.putExtra("mode", getIntent().getStringExtra("mode"));
                startActivity(intent);
                finish();
            }
        });

        formation5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormationsActivity.this, CaptainActivity.class);
                intent.putExtra("formation", (String) random_formations.toArray()[2]);
                intent.putExtra("mode", getIntent().getStringExtra("mode"));
                startActivity(intent);
                finish();
            }
        });

    }

    public void set_imagebuttons_background() {
        int id;

        id = getId((String) random_formations.toArray()[0], R.drawable.class);
        formation1.setBackgroundResource(id);

        id = getId((String) random_formations.toArray()[3], R.drawable.class);
        formation2.setBackgroundResource(id);

        id = getId((String) random_formations.toArray()[1], R.drawable.class);
        formation3.setBackgroundResource(id);

        id = getId((String) random_formations.toArray()[4], R.drawable.class);
        formation4.setBackgroundResource(id);

        id = getId((String) random_formations.toArray()[2], R.drawable.class);
        formation5.setBackgroundResource(id);
    }

    public void choose_5_random_formations(){
        Random generator = new Random();

        int randomIndex = generator.nextInt(all_formations.length);
        int randomIndex2 = generator.nextInt(all_formations.length);
        int randomIndex3 = generator.nextInt(all_formations.length);
        int randomIndex4 = generator.nextInt(all_formations.length);
        int randomIndex5 = generator.nextInt(all_formations.length);

        while(randomIndex2 == randomIndex)
            randomIndex2 = generator.nextInt(all_formations.length);

        while(randomIndex3 == randomIndex || randomIndex3 == randomIndex2)
            randomIndex3 = generator.nextInt(all_formations.length);

        while(randomIndex4 == randomIndex || randomIndex4 == randomIndex2 || randomIndex4 == randomIndex3)
            randomIndex4 = generator.nextInt(all_formations.length);

        while(randomIndex5 == randomIndex || randomIndex5 == randomIndex2 || randomIndex5 == randomIndex3 || randomIndex5 == randomIndex4)
            randomIndex5 = generator.nextInt(all_formations.length);

        random_formations.add(all_formations[randomIndex]);
        random_formations.add(all_formations[randomIndex2]);
        random_formations.add(all_formations[randomIndex3]);
        random_formations.add(all_formations[randomIndex4]);
        random_formations.add(all_formations[randomIndex5]);
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