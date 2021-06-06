package draft;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class ManagerPickActivity extends BaseActivity {

    List<String> manager_pick = new ArrayList<String>();
    ImageButton manager1, manager2, manager3, manager4, manager5;
    String[] managers_list;
    String managers_bool, managers_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_pick);

        manager1 = findViewById(R.id.manager_1);
        manager2 = findViewById(R.id.manager_2);
        manager3 = findViewById(R.id.manager_3);
        manager4 = findViewById(R.id.manager_4);
        manager5 = findViewById(R.id.manager_5);

        String pozitia = getIntent().getStringExtra("position");
        managers_bool = getIntent().getStringExtra("managers_bool");
        if(managers_bool.equals("true"))
        {
            managers_string = getIntent().getStringExtra("managers_string");
            managers_list = managers_string.split("@", 10);
        }

        choose_5_random_managers();
        set_imagebuttons_background();


        manager1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) manager_pick.toArray()[0]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peek", "false");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        manager1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) manager_pick.toArray()[0]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peek", "true");
                resultIntent.putExtra("pozitia", pozitia);
                String chosen_managers_string = "";
                for(String key : manager_pick) {
                    chosen_managers_string = chosen_managers_string + key + '@';
                }

                resultIntent.putExtra("managers_string", chosen_managers_string);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;
            }
        });


        manager2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) manager_pick.toArray()[1]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peek", "false");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        manager2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) manager_pick.toArray()[1]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peek", "true");
                resultIntent.putExtra("pozitia", pozitia);
                String chosen_managers_string = "";
                for(String key : manager_pick) {
                    chosen_managers_string = chosen_managers_string + key + '@';
                }

                resultIntent.putExtra("managers_string", chosen_managers_string);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;
            }
        });


        manager3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) manager_pick.toArray()[2]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peek", "false");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        manager3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) manager_pick.toArray()[2]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peek", "true");
                resultIntent.putExtra("pozitia", pozitia);
                String chosen_managers_string = "";
                for(String key : manager_pick) {
                    chosen_managers_string = chosen_managers_string + key + '@';
                }

                resultIntent.putExtra("managers_string", chosen_managers_string);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;
            }
        });


        manager4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) manager_pick.toArray()[3]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peek", "false");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        manager4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) manager_pick.toArray()[3]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peek", "true");
                resultIntent.putExtra("pozitia", pozitia);
                String chosen_managers_string = "";
                for(String key : manager_pick) {
                    chosen_managers_string = chosen_managers_string + key + '@';
                }

                resultIntent.putExtra("managers_string", chosen_managers_string);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;
            }
        });


        manager5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) manager_pick.toArray()[4]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peek", "false");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        manager5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) manager_pick.toArray()[4]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peek", "true");
                resultIntent.putExtra("pozitia", pozitia);
                String chosen_managers_string = "";
                for(String key : manager_pick) {
                    chosen_managers_string = chosen_managers_string + key + '@';
                }

                resultIntent.putExtra("managers_string", chosen_managers_string);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;
            }
        });
    }

    public void set_imagebuttons_background() {
        int id;

        id = getId((String) manager_pick.toArray()[0], R.drawable.class);
        manager1.setBackgroundResource(id);

        id = getId((String) manager_pick.toArray()[1], R.drawable.class);
        manager2.setBackgroundResource(id);

        id = getId((String) manager_pick.toArray()[2], R.drawable.class);
        manager3.setBackgroundResource(id);

        id = getId((String) manager_pick.toArray()[3], R.drawable.class);
        manager4.setBackgroundResource(id);

        id = getId((String) manager_pick.toArray()[4], R.drawable.class);
        manager5.setBackgroundResource(id);
    }

    public void choose_5_random_managers(){
        if(managers_bool.equals("true")){
            manager_pick.add(managers_list[0]);
            manager_pick.add(managers_list[1]);
            manager_pick.add(managers_list[2]);
            manager_pick.add(managers_list[3]);
            manager_pick.add(managers_list[4]);
        }
        else {
            Random generator = new Random();

            int randomIndex = generator.nextInt(manager_cards.length);
            int randomIndex2 = generator.nextInt(manager_cards.length);
            int randomIndex3 = generator.nextInt(manager_cards.length);
            int randomIndex4 = generator.nextInt(manager_cards.length);
            int randomIndex5 = generator.nextInt(manager_cards.length);

            while (randomIndex2 == randomIndex)
                randomIndex2 = generator.nextInt(manager_cards.length);

            while (randomIndex3 == randomIndex || randomIndex3 == randomIndex2)
                randomIndex3 = generator.nextInt(manager_cards.length);

            while (randomIndex4 == randomIndex || randomIndex4 == randomIndex2 || randomIndex4 == randomIndex3)
                randomIndex4 = generator.nextInt(manager_cards.length);

            while (randomIndex5 == randomIndex || randomIndex5 == randomIndex2 || randomIndex5 == randomIndex3 || randomIndex5 == randomIndex4)
                randomIndex5 = generator.nextInt(manager_cards.length);

            manager_pick.add(manager_cards[randomIndex]);
            manager_pick.add(manager_cards[randomIndex2]);
            manager_pick.add(manager_cards[randomIndex3]);
            manager_pick.add(manager_cards[randomIndex4]);
            manager_pick.add(manager_cards[randomIndex5]);
        }
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