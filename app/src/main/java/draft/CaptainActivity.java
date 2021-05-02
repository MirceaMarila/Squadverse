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

public class CaptainActivity extends BaseActivity {

    List<String> captain_pick = new ArrayList<String>();
    ImageButton captain1, captain2, captain3, captain4, captain5;
    private Class<?> next_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain);

        captain1 = findViewById(R.id.captain_1);
        captain2 = findViewById(R.id.captain_2);
        captain3 = findViewById(R.id.captain_3);
        captain4 = findViewById(R.id.captain_4);
        captain5 = findViewById(R.id.captain_5);

        // figuring out what activity to start depending on the formation chosen
        String formation = getIntent().getStringExtra("formation");
        switch (formation){
            case "f_433_attack":
                try {
                    next_activity = Class.forName("formations.F433AttackActivity");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }

        // taking 5 random players
        choose_5_random_players();

        // setting the background of the 5 imagebuttons
        set_imagebuttons_background();


        captain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaptainActivity.this, next_activity);
                intent.putExtra("captain", (String) captain_pick.toArray()[0]);
                startActivity(intent);
                finish();
            }
        });

        captain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaptainActivity.this, next_activity);
                intent.putExtra("captain", (String) captain_pick.toArray()[1]);
                startActivity(intent);
                finish();
            }
        });

        captain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaptainActivity.this, next_activity);
                intent.putExtra("captain", (String) captain_pick.toArray()[2]);
                startActivity(intent);
                finish();
            }
        });

        captain4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaptainActivity.this, next_activity);
                intent.putExtra("captain", (String) captain_pick.toArray()[3]);
                startActivity(intent);
                finish();
            }
        });

        captain5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaptainActivity.this, next_activity);
                intent.putExtra("captain", (String) captain_pick.toArray()[4]);
                startActivity(intent);
                finish();
            }
        });

    }

    public void set_imagebuttons_background() {
        int id;

        id = getId((String) captain_pick.toArray()[0], R.drawable.class);
        captain1.setBackgroundResource(id);

        id = getId((String) captain_pick.toArray()[1], R.drawable.class);
        captain2.setBackgroundResource(id);

        id = getId((String) captain_pick.toArray()[2], R.drawable.class);
        captain3.setBackgroundResource(id);

        id = getId((String) captain_pick.toArray()[3], R.drawable.class);
        captain4.setBackgroundResource(id);

        id = getId((String) captain_pick.toArray()[4], R.drawable.class);
        captain5.setBackgroundResource(id);
    }

    public void choose_5_random_players(){
        Random generator = new Random();

        int randomIndex = generator.nextInt(all_cards.length);
        int randomIndex2 = generator.nextInt(all_cards.length);
        int randomIndex3 = generator.nextInt(all_cards.length);
        int randomIndex4 = generator.nextInt(all_cards.length);
        int randomIndex5 = generator.nextInt(all_cards.length);

        while(randomIndex2 == randomIndex)
            randomIndex2 = generator.nextInt(all_cards.length);

        while(randomIndex3 == randomIndex || randomIndex3 == randomIndex2)
            randomIndex3 = generator.nextInt(all_cards.length);

        while(randomIndex4 == randomIndex || randomIndex4 == randomIndex2 || randomIndex4 == randomIndex3)
            randomIndex4 = generator.nextInt(all_cards.length);

        while(randomIndex5 == randomIndex || randomIndex5 == randomIndex2 || randomIndex5 == randomIndex3 || randomIndex5 == randomIndex4)
            randomIndex5 = generator.nextInt(all_cards.length);

        captain_pick.add(all_cards[randomIndex]);
        captain_pick.add(all_cards[randomIndex2]);
        captain_pick.add(all_cards[randomIndex3]);
        captain_pick.add(all_cards[randomIndex4]);
        captain_pick.add(all_cards[randomIndex5]);
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