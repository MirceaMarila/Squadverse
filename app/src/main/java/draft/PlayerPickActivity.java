package draft;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.squadverse.FeedbackActivity;
import com.example.squadverse.R;
import com.example.squadverse.RegisterActivity;
import com.example.squadverse.SelectAvatarActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.BaseActivity;
import formations.F433AttackActivity;

public class PlayerPickActivity extends BaseActivity {

    List<String> player_pick = new ArrayList<String>();
    ImageButton player1, player2, player3, player4, player5;
    String[] picked_players, players_list;
    String players_bool, players_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_pick);
        
        player1 = findViewById(R.id.player_1);
        player2 = findViewById(R.id.player_2);
        player3 = findViewById(R.id.player_3);
        player4 = findViewById(R.id.player_4);
        player5 = findViewById(R.id.player_5);

        String picked_players_string = getIntent().getStringExtra("picked_players");
        picked_players = picked_players_string.split("@", 25);
        String pozitia = getIntent().getStringExtra("position");
        players_bool = getIntent().getStringExtra("players_bool");
        if(players_bool.equals("true"))
        {
            players_string = getIntent().getStringExtra("players_string");
            players_list = players_string.split("@", 10);
        }

        choose_player_database_from_received_position(pozitia);
        set_imagebuttons_background();


        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) player_pick.toArray()[0]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peak", "false");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        player1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) player_pick.toArray()[0]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peak", "true");
                resultIntent.putExtra("pozitia", pozitia);
                String chosen_players_string = "";
                for(String key : player_pick) {
                    chosen_players_string = chosen_players_string + key + '@';
                }

                resultIntent.putExtra("players_string", chosen_players_string);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;
            }
        });


        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) player_pick.toArray()[1]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peak", "false");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        player2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) player_pick.toArray()[1]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peak", "true");
                resultIntent.putExtra("pozitia", pozitia);
                String chosen_players_string = "";
                for(String key : player_pick) {
                    chosen_players_string = chosen_players_string + key + '@';
                }

                resultIntent.putExtra("players_string", chosen_players_string);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;
            }
        });


        player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) player_pick.toArray()[2]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peak", "false");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        player3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) player_pick.toArray()[2]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peak", "true");
                resultIntent.putExtra("pozitia", pozitia);
                String chosen_players_string = "";
                for(String key : player_pick) {
                    chosen_players_string = chosen_players_string + key + '@';
                }

                resultIntent.putExtra("players_string", chosen_players_string);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;
            }
        });


        player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) player_pick.toArray()[3]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peak", "false");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        player4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) player_pick.toArray()[3]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peak", "true");
                resultIntent.putExtra("pozitia", pozitia);
                String chosen_players_string = "";
                for(String key : player_pick) {
                    chosen_players_string = chosen_players_string + key + '@';
                }

                resultIntent.putExtra("players_string", chosen_players_string);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;
            }
        });


        player5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) player_pick.toArray()[4]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peak", "false");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        player5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("pick_result", (String) player_pick.toArray()[4]);
                resultIntent.putExtra("variabila", getIntent().getStringExtra("variabila"));
                resultIntent.putExtra("peak", "true");
                resultIntent.putExtra("pozitia", pozitia);
                String chosen_players_string = "";
                for(String key : player_pick) {
                    chosen_players_string = chosen_players_string + key + '@';
                }

                resultIntent.putExtra("players_string", chosen_players_string);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;
            }
        });
    }

    public void set_imagebuttons_background() {
        int id;

        id = getId((String) player_pick.toArray()[0], R.drawable.class);
        player1.setBackgroundResource(id);

        id = getId((String) player_pick.toArray()[1], R.drawable.class);
        player2.setBackgroundResource(id);

        id = getId((String) player_pick.toArray()[2], R.drawable.class);
        player3.setBackgroundResource(id);

        id = getId((String) player_pick.toArray()[3], R.drawable.class);
        player4.setBackgroundResource(id);

        id = getId((String) player_pick.toArray()[4], R.drawable.class);
        player5.setBackgroundResource(id);
    }

    public void choose_5_random_players(String[] cards){
        if(players_bool.equals("true")){
            player_pick.add(players_list[0]);
            player_pick.add(players_list[1]);
            player_pick.add(players_list[2]);
            player_pick.add(players_list[3]);
            player_pick.add(players_list[4]);
        }
        else {
            Random generator = new Random();

            int randomIndex = generator.nextInt(cards.length);
            int randomIndex2 = generator.nextInt(cards.length);
            int randomIndex3 = generator.nextInt(cards.length);
            int randomIndex4 = generator.nextInt(cards.length);
            int randomIndex5 = generator.nextInt(cards.length);

            while (check_if_player_is_picked(cards[randomIndex]))
                randomIndex = generator.nextInt(cards.length);

            while (randomIndex2 == randomIndex || check_if_player_is_picked(cards[randomIndex2]))
                randomIndex2 = generator.nextInt(cards.length);

            while (randomIndex3 == randomIndex || randomIndex3 == randomIndex2 || check_if_player_is_picked(cards[randomIndex3]))
                randomIndex3 = generator.nextInt(cards.length);

            while (randomIndex4 == randomIndex || randomIndex4 == randomIndex2 || randomIndex4 == randomIndex3 || check_if_player_is_picked(cards[randomIndex4]))
                randomIndex4 = generator.nextInt(cards.length);

            while (randomIndex5 == randomIndex || randomIndex5 == randomIndex2 || randomIndex5 == randomIndex3 || randomIndex5 == randomIndex4 || check_if_player_is_picked(cards[randomIndex5]))
                randomIndex5 = generator.nextInt(cards.length);

            player_pick.add(cards[randomIndex]);
            player_pick.add(cards[randomIndex2]);
            player_pick.add(cards[randomIndex3]);
            player_pick.add(cards[randomIndex4]);
            player_pick.add(cards[randomIndex5]);
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

    private boolean check_if_player_is_picked(String player){

        for(String picked_player: picked_players){
            if (picked_player.equals(player))
                return true;
        }
        return false;
    }

    private void choose_player_database_from_received_position(String position){
        switch (position){
            case "any":
                choose_5_random_players(all_cards);
                break;

            case "defence":
                choose_5_random_players(def_cards);
                break;

            case "midfield":
                choose_5_random_players(mid_cards);
                break;

            case "attack":
                choose_5_random_players(att_cards);
                break;

            case "gk":
                choose_5_random_players(gk_cards);
                break;

            case "st":
                choose_5_random_players(st_cards);
                break;

            case "lw":
                choose_5_random_players(lw_cards);
                break;

            case "rw":
                choose_5_random_players(rw_cards);
                break;

            case "cf":
                choose_5_random_players(cf_cards);
                break;

            case "lf":
                choose_5_random_players(lf_cards);
                break;

            case "rf":
                choose_5_random_players(rf_cards);
                break;

            case "lm":
                choose_5_random_players(lm_cards);
                break;

            case "cm":
                choose_5_random_players(cm_cards);
                break;

            case "rcm":
                choose_5_random_players(cm_cards);
                break;

            case "lcm":
                choose_5_random_players(cm_cards);
                break;

            case "rm":
                choose_5_random_players(rm_cards);
                break;

            case "cam":
                choose_5_random_players(cam_cards);
                break;

            case "cdm":
                choose_5_random_players(cdm_cards);
                break;

            case "lb":
                choose_5_random_players(lb_cards);
                break;

            case "rb":
                choose_5_random_players(rb_cards);
                break;

            case "cb":
                choose_5_random_players(cb_cards);
                break;

            case "rcb":
                choose_5_random_players(cb_cards);
                break;

            case "lcb":
                choose_5_random_players(cb_cards);
                break;

            case "lwb":
                choose_5_random_players(lwb_cards);
                break;

            case "rwb":
                choose_5_random_players(rwb_cards);
                break;
        }

    }
}