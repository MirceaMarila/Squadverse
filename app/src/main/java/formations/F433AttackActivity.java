package formations;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squadverse.BugReportActivity;
import com.example.squadverse.FeedbackActivity;
import com.example.squadverse.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import static draft.FormationsActivity.getId;

import common.BaseActivity;
import draft.CaptainActivity;
import draft.ManagerPickActivity;
import draft.PlayerPickActivity;

public class F433AttackActivity extends BaseActivity {

    TextView slide_title, rating, chemistry, chm_lw, chm_st, chm_rw, chm_cam, chm_lcm, chm_rcm, chm_lb, chm_lcb, chm_rcb, chm_rb, chm_gk;
    ImageView link_lw_st, link_rw_st, link_cam_st, link_lw_lcm, link_rw_rcm, link_cam_lcm, link_cam_rcm, link_rw_rb, link_lw_lb, link_lcm_lb, link_rcm_rb, link_rcb_rb, link_lcb_lb, link_lcb_rcb, link_rcm_rcb, link_lcm_lcb, link_lcb_gk, link_rcb_gk;
    ImageButton card_lw, card_st, card_rw, card_cam, card_lcm, card_rcm, card_lb, card_lcb, card_rcb, card_rb, card_gk, sub1, sub2, sub3, sub4, sub5, sub6, sub7, res1, res2, res3, res4, res5, card_manager;
    HashMap<String, String> position_takers = new HashMap<>();
    String manager = null;
    HashMap<String, Integer> nr_of_links = new HashMap<>();
    public static final int REQUEST_CODE = 1;
    boolean swap = false;
    String swap_position_1, swap_position_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f433_attack);

        //sliding title configuration
        slide_title = findViewById(R.id.slide_title_433_att);
        findViewById(R.id.sub_res_433a).setVisibility(View.INVISIBLE);

        SlidingUpPanelLayout layout = findViewById(R.id.slidingUp);
        layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                //findViewById(R.id.slide_title).setAlpha(1-slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED){
                    slide_title.setText("\u2193 SUBSTITUTES, RESERVES AND MANAGER \u2193");

                    }

                else if(newState == SlidingUpPanelLayout.PanelState.COLLAPSED){
                    slide_title.setText("\u2191 SUBSTITUTES, RESERVES AND MANAGER \u2191");
                findViewById(R.id.sub_res_433a).setVisibility(View.INVISIBLE);
                    }
                else{
                    findViewById(R.id.sub_res_433a).setVisibility(View.VISIBLE);
                }
            }
        });

        // links
        initialize_all_elements_and_color_all_links_red();

        // place camptain in the field
        final String captain = getIntent().getStringExtra("captain");
        place_captain_in_team(captain);

        // pick a player for any position
        assign_action_to_every_image_button();

    }

    @Override
    public void onResume() {
        super.onResume();
        assign_action_to_every_image_button();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK)
            {
                String peek = data.getStringExtra("peek");
                if(peek.equals("false"))
                {
                    enable_all_buttons();
                    set_all_players_alpha_to_255();
                    String pick_Result = data.getStringExtra("pick_result");
                    String variabila = data.getStringExtra("variabila");
                    put_picked_player_in_team(variabila, pick_Result);
                }
                else{
                    disable_all_buttons();
                    String pick_Result = data.getStringExtra("pick_result");
                    String variabila = data.getStringExtra("variabila");
                    String pozitia = data.getStringExtra("pozitia");
                    String players_string;
                    if(variabila.equals("card_manager"))
                    {
                        players_string = data.getStringExtra("managers_string");
                        set_uninfluenced_players_alpha_to_100(pick_Result);
                    }

                    else
                    {
                        set_all_players_alpha_to_255();
                        players_string = data.getStringExtra("players_string");
                    }
                    put_picked_player_in_team(variabila, pick_Result);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            if(variabila.equals("card_manager"))
                            {
                                Intent intent = new Intent(F433AttackActivity.this, ManagerPickActivity.class);
                                intent.putExtra("position", pozitia);
                                intent.putExtra("variabila", variabila);
                                intent.putExtra("managers_bool", "true");
                                intent.putExtra("managers_string", players_string);
                                startActivityForResult(intent, REQUEST_CODE);
                            }
                            else
                                {
                            String picked_players_string = get_picked_players_string();
                            Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                            intent.putExtra("position", pozitia);
                            intent.putExtra("picked_players", picked_players_string);
                            intent.putExtra("variabila", variabila);
                            intent.putExtra("players_bool", "true");
                            intent.putExtra("players_string", players_string);
                            startActivityForResult(intent, REQUEST_CODE);
                            }
                        }
                    }, 3000);
                }
            }
        } catch (Exception ex) {
            //nothing
        }

    }


    private void initialize_all_elements_and_color_all_links_red(){
        link_lw_st = findViewById(R.id.link_lw_st_433_att);
        link_rw_st = findViewById(R.id.link_rw_st_433_att);
        link_cam_st = findViewById(R.id.link_cam_st_433_att);
        link_lw_lcm = findViewById(R.id.link_lw_lcm_433_att);
        link_rw_rcm = findViewById(R.id.link_rw_rcm_433_att);
        link_cam_lcm = findViewById(R.id.link_cam_lcm_433_att);
        link_cam_rcm = findViewById(R.id.link_cam_rcm_433_att);
        link_rw_rb = findViewById(R.id.link_rw_rb_433_att);
        link_lw_lb = findViewById(R.id.link_lw_lb_433_att);
        link_lcm_lb = findViewById(R.id.link_lcm_lb_433_att);
        link_rcm_rb = findViewById(R.id.link_rcm_rb_433_att);
        link_rcb_rb = findViewById(R.id.link_rb_rcb_433_att);
        link_lcb_lb = findViewById(R.id.link_lb_lcb_433_att);
        link_lcb_rcb = findViewById(R.id.link_lcb_rcb_433_att);
        link_rcm_rcb = findViewById(R.id.link_rcm_rcb_433_att);
        link_lcm_lcb = findViewById(R.id.link_lcm_lcb_433_att);
        link_lcb_gk = findViewById(R.id.link_lcb_gk_433_att);
        link_rcb_gk = findViewById(R.id.link_rcb_gk_433_att);

        link_lw_st.setColorFilter(Color.argb(200, 255, 0, 0));
        link_rw_st.setColorFilter(Color.argb(200, 255, 0, 0));
        link_cam_st.setColorFilter(Color.argb(200, 255, 0, 0));
        link_lw_lcm.setColorFilter(Color.argb(200, 255, 0, 0));
        link_rw_rcm.setColorFilter(Color.argb(200, 255, 0, 0));
        link_cam_lcm.setColorFilter(Color.argb(200, 255, 0, 0));
        link_cam_rcm.setColorFilter(Color.argb(200, 255, 0, 0));
        link_rw_rb.setColorFilter(Color.argb(200, 255, 0, 0));
        link_lw_lb.setColorFilter(Color.argb(200, 255, 0, 0));
        link_lcm_lb.setColorFilter(Color.argb(200, 255, 0, 0));
        link_rcm_rb.setColorFilter(Color.argb(200, 255, 0, 0));
        link_rcb_rb.setColorFilter(Color.argb(200, 255, 0, 0));
        link_lcb_lb.setColorFilter(Color.argb(200, 255, 0, 0));
        link_lcb_rcb.setColorFilter(Color.argb(200, 255, 0, 0));
        link_rcm_rcb.setColorFilter(Color.argb(200, 255, 0, 0));
        link_lcm_lcb.setColorFilter(Color.argb(200, 255, 0, 0));
        link_lcb_gk.setColorFilter(Color.argb(200, 255, 0, 0));
        link_rcb_gk.setColorFilter(Color.argb(200, 255, 0, 0));

        card_lw = findViewById(R.id.lw_433_att);
        card_st = findViewById(R.id.st_433_att);
        card_rw = findViewById(R.id.rw_433_att);
        card_cam = findViewById(R.id.cam_433_att);
        card_lcm = findViewById(R.id.lcm_433_att);
        card_rcm = findViewById(R.id.rcm_433_att);
        card_lb = findViewById(R.id.lb_433_att);
        card_lcb = findViewById(R.id.lcb_433_att);
        card_rcb = findViewById(R.id.rcb_433_att);
        card_rb = findViewById(R.id.rb_433_att);
        card_gk = findViewById(R.id.gk_433_att);

        rating  = findViewById(R.id.rating_433_att);
        chemistry = findViewById(R.id.chem_433_att);

        chm_lw = findViewById(R.id.chem_lw_433_att);
        chm_st = findViewById(R.id.chem_st_433_att);
        chm_rw = findViewById(R.id.chem_rw_433_att);
        chm_cam = findViewById(R.id.chem_cam_433_att);
        chm_lcm = findViewById(R.id.chem_lcm_433_att);
        chm_rcm = findViewById(R.id.chem_rcm_433_att);
        chm_lb = findViewById(R.id.chem_lb_433_att);
        chm_lcb = findViewById(R.id.chem_lcb_433_att);
        chm_rcb = findViewById(R.id.chem_rcb_433_att);
        chm_rb = findViewById(R.id.chem_rb_433_att);
        chm_gk = findViewById(R.id.chem_gk_433_att);

        sub1 = findViewById(R.id.f_433_att_sub_1);
        sub2 = findViewById(R.id.f_433_att_sub_2);
        sub3 = findViewById(R.id.f_433_att_sub_3);
        sub4 = findViewById(R.id.f_433_att_sub_4);
        sub5 = findViewById(R.id.f_433_att_sub_5);
        sub6 = findViewById(R.id.f_433_att_sub_6);
        sub7 = findViewById(R.id.f_433_att_sub_7);
        res1 = findViewById(R.id.f_433_att_res_1);
        res2 = findViewById(R.id.f_433_att_res_2);
        res3 = findViewById(R.id.f_433_att_res_3);
        res4 = findViewById(R.id.f_433_att_res_4);
        res5 = findViewById(R.id.f_433_att_res_5);
        card_manager = findViewById(R.id.f_433_att_manager);

        nr_of_links.put("lw", 3);
        nr_of_links.put("st", 3);
        nr_of_links.put("rw", 3);
        nr_of_links.put("lcm", 4);
        nr_of_links.put("cam", 3);
        nr_of_links.put("rcm", 4);
        nr_of_links.put("lb", 3);
        nr_of_links.put("lcb", 4);
        nr_of_links.put("rcb", 4);
        nr_of_links.put("rb", 3);
        nr_of_links.put("gk", 2);

    }

    private void place_captain_in_team(String captain){
        String captain_position = get_player_position_from_name(captain);
        String[] positions = pozitiile_formatiilor.get("f_433_attack");

        boolean flag = false;
        assert positions != null;
        for (String pozitie : positions){
            if(pozitie.equals(captain_position))
            {
                flag = true;
                break;
            }
        }

        if(!flag){
            String[] pozitii_compatibile2 =  pozitii_compatibile.get(captain_position);
            Random generator = new Random();
            assert pozitii_compatibile2 != null;
            boolean flag2 = false;
            while(!flag2){
                int randomIndex = generator.nextInt(pozitii_compatibile2.length);
                captain_position = pozitii_compatibile2[randomIndex];
                for (String pozitie : positions){
                    if(pozitie.equals(captain_position)) {
                        flag2 = true;
                        break;
                    }
                }
            }

        }

        if(captain_position.equals("cm"))
            captain_position = "lcm";
        else if(captain_position.equals("cb"))
            captain_position = "lcb";

        position_takers.put(captain_position, captain);
        put_position_takers_in_field();

    }

    private void put_position_takers_in_field(){
        for (String key : position_takers.keySet()) {
            int id;
            String player = position_takers.get(key);
            switch (key){
                case "st":
                    id = getId(player, R.drawable.class);
                    card_st.setBackgroundResource(id);
                    break;

                case "cam":
                    id = getId(player, R.drawable.class);
                    card_cam.setBackgroundResource(id);
                    break;

                case "lw":
                    id = getId(player, R.drawable.class);
                    card_lw.setBackgroundResource(id);
                    break;

                case "rw":
                    id = getId(player, R.drawable.class);
                    card_rw.setBackgroundResource(id);
                    break;

                case "lcm":
                    id = getId(player, R.drawable.class);
                    card_lcm.setBackgroundResource(id);
                    break;

                case "rcm":
                    id = getId(player, R.drawable.class);
                    card_rcm.setBackgroundResource(id);
                    break;

                case "lb":
                    id = getId(player, R.drawable.class);
                    card_lb.setBackgroundResource(id);
                    break;

                case "rb":
                    id = getId(player, R.drawable.class);
                    card_rb.setBackgroundResource(id);
                    break;

                case "lcb":
                    id = getId(player, R.drawable.class);
                    card_lcb.setBackgroundResource(id);
                    break;

                case "rcb":
                    id = getId(player, R.drawable.class);
                    card_rcb.setBackgroundResource(id);
                    break;

                case "gk":
                    id = getId(player, R.drawable.class);
                    card_gk.setBackgroundResource(id);
                    break;

                case "sub1":
                     id = getId(player, R.drawable.class);
                    sub1.setBackgroundResource(id);
                    break;

                case "sub2":
                     id = getId(player, R.drawable.class);
                    sub2.setBackgroundResource(id);
                    break;

                case "sub3":
                     id = getId(player, R.drawable.class);
                    sub3.setBackgroundResource(id);
                    break;

                case "sub4":
                     id = getId(player, R.drawable.class);
                    sub4.setBackgroundResource(id);
                    break;

                case "sub5":
                     id = getId(player, R.drawable.class);
                    sub5.setBackgroundResource(id);
                    break;

                case "sub6":
                     id = getId(player, R.drawable.class);
                    sub6.setBackgroundResource(id);
                    break;

                case "sub7":
                     id = getId(player, R.drawable.class);
                    sub7.setBackgroundResource(id);
                    break;

                case "res1":
                     id = getId(player, R.drawable.class);
                    res1.setBackgroundResource(id);
                    break;

                case "res2":
                     id = getId(player, R.drawable.class);
                    res2.setBackgroundResource(id);
                    break;

                case "res3":
                     id = getId(player, R.drawable.class);
                    res3.setBackgroundResource(id);
                    break;

                case "res4":
                     id = getId(player, R.drawable.class);
                    res4.setBackgroundResource(id);
                    break;

                case "res5":
                     id = getId(player, R.drawable.class);
                    res5.setBackgroundResource(id);
                    break;
            }
        }

        if(manager != null)
        {
            int id = getId(manager, R.drawable.class);
            card_manager.setBackgroundResource(id);
        }
        calculate_chemistry_of_every_player();
        calculate_rating_and_chemistry();
    }

    private void calculate_chemistry_of_every_player(){
        for (String key : position_takers.keySet()) {
            String player = position_takers.get(key);
            assert player != null;
            int new_chem;

            switch (key) {
                case "st":
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_st.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_st.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_st.setTextColor(Color.RED);
                        }
                    }
                    chm_st.setText(String.valueOf(new_chem));
                    break;


                case "cam":
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_cam.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_cam.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_cam.setTextColor(Color.RED);
                        }
                    }
                    chm_cam.setText(String.valueOf(new_chem));
                    break;


                case "lw":
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_lw.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_lw.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_lw.setTextColor(Color.RED);
                        }
                    }
                    chm_lw.setText(String.valueOf(new_chem));
                    break;


                case "rw":
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_rw.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_rw.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_rw.setTextColor(Color.RED);
                        }
                    }
                    chm_rw.setText(String.valueOf(new_chem));
                    break;


                case "lcm":
                    key = "cm";
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_lcm.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_lcm.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_lcm.setTextColor(Color.RED);
                        }
                    }
                    chm_lcm.setText(String.valueOf(new_chem));
                    break;

                case "rcm":
                    key = "cm";
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_rcm.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_rcm.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_rcm.setTextColor(Color.RED);
                        }
                    }
                    chm_rcm.setText(String.valueOf(new_chem));
                    break;

                case "lb":
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_lb.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_lb.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_lb.setTextColor(Color.RED);
                        }
                    }
                    chm_lb.setText(String.valueOf(new_chem));
                    break;

                case "rb":
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_rb.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_rb.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_rb.setTextColor(Color.RED);
                        }
                    }
                    chm_rb.setText(String.valueOf(new_chem));
                    break;

                case "lcb":
                    key = "cb";
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_lcb.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_lcb.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_lcb.setTextColor(Color.RED);
                        }
                    }
                    chm_lcb.setText(String.valueOf(new_chem));
                    break;

                case "rcb":
                    key = "cb";
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_rcb.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_rcb.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_rcb.setTextColor(Color.RED);
                        }
                    }
                    chm_rcb.setText(String.valueOf(new_chem));
                    break;

                case "gk":
                    new_chem = 0;
                    if (get_player_position_from_name(player).equals(key)) {
                            new_chem += 4;
                        chm_gk.setTextColor(Color.GREEN);
                    }

                    if (!get_player_position_from_name(player).equals(key)) {
                        if (check_if_position_is_compatible(key, get_player_position_from_name(player))) {
                                new_chem += 3;
                            chm_gk.setTextColor(Color.YELLOW);
                        } else {
                                new_chem += 1;
                            chm_gk.setTextColor(Color.RED);
                        }
                    }
                    chm_gk.setText(String.valueOf(new_chem));
                    break;

                case "sub1":
                case "sub2":
                case "sub3":
                case "sub4":
                case "sub5":
                case "sub6":
                case "sub7":
                case "res1":
                case "res2":
                case "res3":
                case "res4":
                case "res5":
                    break;
            }
        }

            String liga1, liga2, tara1, tara2, echipa1, echipa2;

            double lw_chm = Double.parseDouble(chm_lw.getText().toString());
            double st_chm = Double.parseDouble(chm_st.getText().toString());
            double rw_chm = Double.parseDouble(chm_rw.getText().toString());
            double lcm_chm = Double.parseDouble(chm_lcm.getText().toString());
            double rcm_chm = Double.parseDouble(chm_rcm.getText().toString());
            double cam_chm = Double.parseDouble(chm_cam.getText().toString());
            double lb_chm = Double.parseDouble(chm_lb.getText().toString());
            double rb_chm = Double.parseDouble(chm_rb.getText().toString());
            double lcb_chm = Double.parseDouble(chm_lcb.getText().toString());
            double rcb_chm = Double.parseDouble(chm_rcb.getText().toString());
            double gk_chm = Double.parseDouble((chm_gk.getText().toString()));


            // link_lw_st
        if(position_takers.containsKey("lw") && position_takers.containsKey("st")){
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))){
                lw_chm += (double) 2*6/nr_of_links.get("lw");
                st_chm += (double) 2*6/nr_of_links.get("st");
                link_lw_st.setColorFilter(Color.argb(200, 0, 255, 0));
            }
            else if(liga1.equals(liga2) || tara1.equals(tara2)){
                lw_chm += (double) 6/nr_of_links.get("lw");
                st_chm += (double) 6/nr_of_links.get("st");
                link_lw_st.setColorFilter(Color.argb(200, 255, 255, 0));
            }
            else{
                link_lw_st.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_rw_st
        if(position_takers.containsKey("rw") && position_takers.containsKey("st")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                rw_chm += (double) 2 * 6 / nr_of_links.get("rw");
                st_chm += (double) 2 * 6 / nr_of_links.get("st");
                link_rw_st.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                rw_chm += (double) 6 / nr_of_links.get("rw");
                st_chm += (double) 6 / nr_of_links.get("st");
                link_rw_st.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_rw_st.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }

            // link_cam_st
        if(position_takers.containsKey("cam") && position_takers.containsKey("st")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                cam_chm += (double) 2 * 6 / nr_of_links.get("cam");
                st_chm += (double) 2 * 6 / nr_of_links.get("st");
                link_cam_st.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                cam_chm += (double) 6 / nr_of_links.get("cam");
                st_chm += (double) 6 / nr_of_links.get("st");
                link_cam_st.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_cam_st.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_lw_lcm
        if(position_takers.containsKey("lw") && position_takers.containsKey("lcm")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                lw_chm += (double) 2 * 6 / nr_of_links.get("lw");
                lcm_chm += (double) 2 * 6 / nr_of_links.get("lcm");
                link_lw_lcm.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                lw_chm += (double) 6 / nr_of_links.get("lw");
                lcm_chm += (double) 6 / nr_of_links.get("lcm");
                link_lw_lcm.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_lw_lcm.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_rw_rcm
        if(position_takers.containsKey("rw") && position_takers.containsKey("rcm")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                rw_chm += (double) 2 * 6 / nr_of_links.get("rw");
                rcm_chm += (double) 2 * 6 / nr_of_links.get("rcm");
                link_rw_rcm.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                rw_chm += (double) 6 / nr_of_links.get("rw");
                rcm_chm += (double) 6 / nr_of_links.get("rcm");
                link_rw_rcm.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_rw_rcm.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_cam_lcm
        if(position_takers.containsKey("cam") && position_takers.containsKey("lcm")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                cam_chm += (double) 2 * 6 / nr_of_links.get("cam");
                lcm_chm += (double) 2 * 6 / nr_of_links.get("lcm");
                link_cam_lcm.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                cam_chm += (double) 6 / nr_of_links.get("cam");
                lcm_chm += (double) 6 / nr_of_links.get("lcm");
                link_cam_lcm.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_cam_lcm.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_cam_rcm
        if(position_takers.containsKey("cam") && position_takers.containsKey("rcm")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                cam_chm += (double) 2 * 6 / nr_of_links.get("cam");
                rcm_chm += (double) 2 * 6 / nr_of_links.get("rcm");
                link_cam_rcm.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                cam_chm += (double) 6 / nr_of_links.get("cam");
                rcm_chm += (double) 6 / nr_of_links.get("rcm");
                link_cam_rcm.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_cam_rcm.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_rw_rb
        if(position_takers.containsKey("rw") && position_takers.containsKey("rb")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                rw_chm += (double) 2 * 6 / nr_of_links.get("rw");
                rb_chm += (double) 2 * 6 / nr_of_links.get("rb");
                link_rw_rb.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                rw_chm += (double) 6 / nr_of_links.get("rw");
                rb_chm += (double) 6 / nr_of_links.get("rb");
                link_rw_rb.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_rw_rb.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }

            // link_lw_lb
        if(position_takers.containsKey("lw") && position_takers.containsKey("lb")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                lw_chm += (double) 2 * 6 / nr_of_links.get("lw");
                lb_chm += (double) 2 * 6 / nr_of_links.get("lb");
                link_lw_lb.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                lw_chm += (double) 6 / nr_of_links.get("lw");
                lb_chm += (double) 6 / nr_of_links.get("lb");
                link_lw_lb.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_lw_lb.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_lcm_lb
        if(position_takers.containsKey("lcm") && position_takers.containsKey("lb")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                lcm_chm += (double) 2 * 6 / nr_of_links.get("lcm");
                lb_chm += (double) 2 * 6 / nr_of_links.get("lb");
                link_lcm_lb.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                lcm_chm += (double) 6 / nr_of_links.get("lcm");
                lb_chm += (double) 6 / nr_of_links.get("lb");
                link_lcm_lb.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_lcm_lb.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_rcm_rb
        if(position_takers.containsKey("rcm") && position_takers.containsKey("rb")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                rcm_chm += (double) 2 * 6 / nr_of_links.get("rcm");
                rb_chm += (double) 2 * 6 / nr_of_links.get("rb");
                link_rcm_rb.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                rcm_chm += (double) 6 / nr_of_links.get("rcm");
                rb_chm += (double) 6 / nr_of_links.get("rb");
                link_rcm_rb.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_rcm_rb.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_rcb_rb
        if(position_takers.containsKey("rcb") && position_takers.containsKey("rb")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                rcb_chm += (double) 2 * 6 / nr_of_links.get("rcb");
                rb_chm += (double) 2 * 6 / nr_of_links.get("rb");
                link_rcb_rb.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                rcb_chm += (double) 6 / nr_of_links.get("rcb");
                rb_chm += (double) 6 / nr_of_links.get("rb");
                link_rcb_rb.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_rcb_rb.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_lcb_lb
        if(position_takers.containsKey("lcb") && position_takers.containsKey("lb")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                lcb_chm += (double) 2 * 6 / nr_of_links.get("lcb");
                lb_chm += (double) 2 * 6 / nr_of_links.get("lb");
                link_lcb_lb.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                lcb_chm += (double) 6 / nr_of_links.get("lcb");
                lb_chm += (double) 6 / nr_of_links.get("lb");
                link_lcb_lb.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_lcb_lb.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_lcb_rcb
        if(position_takers.containsKey("lcb") && position_takers.containsKey("rcb")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                lcb_chm += (double) 2 * 6 / nr_of_links.get("lcb");
                rcb_chm += (double) 2 * 6 / nr_of_links.get("rcb");
                link_lcb_rcb.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                lcb_chm += (double) 6 / nr_of_links.get("lcb");
                rcb_chm += (double) 6 / nr_of_links.get("rcb");
                link_lcb_rcb.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_lcb_rcb.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_rcm_rcb
        if(position_takers.containsKey("rcm") && position_takers.containsKey("rcb")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                rcm_chm += (double) 2 * 6 / nr_of_links.get("rcm");
                rcb_chm += (double) 2 * 6 / nr_of_links.get("rcb");
                link_rcm_rcb.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                rcm_chm += (double) 6 / nr_of_links.get("rcm");
                rcb_chm += (double) 6 / nr_of_links.get("rcb");
                link_rcm_rcb.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_rcm_rcb.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_lcm_lcb
        if(position_takers.containsKey("lcm") && position_takers.containsKey("lcb")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                lcm_chm += (double) 2 * 6 / nr_of_links.get("lcm");
                lcb_chm += (double) 2 * 6 / nr_of_links.get("lcb");
                link_lcm_lcb.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                lcm_chm += (double) 6 / nr_of_links.get("lcm");
                lcb_chm += (double) 6 / nr_of_links.get("lcb");
                link_lcm_lcb.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_lcm_lcb.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_lcb_gk
        if(position_takers.containsKey("lcb") && position_takers.containsKey("gk")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("gk"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("gk"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("gk"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                lcb_chm += (double) 2 * 6 / nr_of_links.get("lcb");
                gk_chm += (double) 2 * 6 / nr_of_links.get("gk");
                link_lcb_gk.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                lcb_chm += (double) 6 / nr_of_links.get("lcb");
                gk_chm += (double) 6 / nr_of_links.get("gk");
                link_lcb_gk.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_lcb_gk.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }


            // link_rcb_gk
        if(position_takers.containsKey("rcb") && position_takers.containsKey("gk")) {
            liga1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("LEAGUE");
            tara1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("NATIONALITY");
            echipa1 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("CLUB");
            liga2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("gk"))).get("LEAGUE");
            tara2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("gk"))).get("NATIONALITY");
            echipa2 = detalii_jucatori.get(get_player_name_from_name(position_takers.get("gk"))).get("CLUB");

            assert echipa1 != null;
            if (echipa1.equals(echipa2) || (liga1.equals(liga2) && tara1.equals(tara2))) {
                rcb_chm += (double) 2 * 6 / nr_of_links.get("rcb");
                gk_chm += (double) 2 * 6 / nr_of_links.get("gk");
                link_rcb_gk.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (liga1.equals(liga2) || tara1.equals(tara2)) {
                rcb_chm += (double) 6 / nr_of_links.get("rcb");
                gk_chm += (double) 6 / nr_of_links.get("gk");
                link_rcb_gk.setColorFilter(Color.argb(200, 255, 255, 0));
            } else {
                link_rcb_gk.setColorFilter(Color.argb(200, 255, 0, 0));
            }
        }

        chm_lw.setText(String.valueOf(get_int_chemistry_from_double_chemistry(lw_chm)));
        chm_st.setText(String.valueOf(get_int_chemistry_from_double_chemistry(st_chm)));
        chm_rw.setText(String.valueOf(get_int_chemistry_from_double_chemistry(rw_chm)));
        chm_cam.setText(String.valueOf(get_int_chemistry_from_double_chemistry(cam_chm)));
        chm_lcm.setText(String.valueOf(get_int_chemistry_from_double_chemistry(lcm_chm)));
        chm_rcm.setText(String.valueOf(get_int_chemistry_from_double_chemistry(rcm_chm)));
        chm_lb.setText(String.valueOf(get_int_chemistry_from_double_chemistry(lb_chm)));
        chm_lcb.setText(String.valueOf(get_int_chemistry_from_double_chemistry(lcb_chm)));
        chm_rcb.setText(String.valueOf(get_int_chemistry_from_double_chemistry(rcb_chm)));
        chm_rb.setText(String.valueOf(get_int_chemistry_from_double_chemistry(rb_chm)));
        chm_gk.setText(String.valueOf(get_int_chemistry_from_double_chemistry(gk_chm)));

        if(chm_lw.getText().toString().equals("10") && chm_lw.getCurrentTextColor() == Color.YELLOW)
            chm_lw.setText("9");
        if(chm_st.getText().toString().equals("10") && chm_st.getCurrentTextColor() == Color.YELLOW)
            chm_st.setText("9");
        if(chm_rw.getText().toString().equals("10") && chm_rw.getCurrentTextColor() == Color.YELLOW)
            chm_rw.setText("9");
        if(chm_lcm.getText().toString().equals("10") && chm_lcm.getCurrentTextColor() == Color.YELLOW)
            chm_lcm.setText("9");
        if(chm_cam.getText().toString().equals("10") && chm_cam.getCurrentTextColor() == Color.YELLOW)
            chm_cam.setText("9");
        if(chm_rcm.getText().toString().equals("10") && chm_rcm.getCurrentTextColor() == Color.YELLOW)
            chm_rcm.setText("9");
        if(chm_lb.getText().toString().equals("10") && chm_lb.getCurrentTextColor() == Color.YELLOW)
            chm_lb.setText("9");
        if(chm_rb.getText().toString().equals("10") && chm_rb.getCurrentTextColor() == Color.YELLOW)
            chm_rb.setText("9");
        if(chm_lcb.getText().toString().equals("10") && chm_lcb.getCurrentTextColor() == Color.YELLOW)
            chm_lcb.setText("9");
        if(chm_rcb.getText().toString().equals("10") && chm_rcb.getCurrentTextColor() == Color.YELLOW)
            chm_rcb.setText("9");

        if(manager != null){
            String manager_nationality = detalii_manageri.get(manager).get("NATIONALITY");
            String manager_league = detalii_manageri.get(manager).get("LEAGUE");
            ArrayList<String> chemistry_queue = new ArrayList<>();

            for (String key : position_takers.keySet()){
                switch (key){
                    case "st":
                        if(Integer.parseInt(chm_st.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("st"));
                        }
                        break;
                    case "lw":
                        if(Integer.parseInt(chm_lw.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("lw"));
                        }
                        break;
                    case "rw":
                        if(Integer.parseInt(chm_rw.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("rw"));
                        }
                        break;
                    case "lcm":
                        if(Integer.parseInt(chm_lcm.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("lcm"));
                        }
                        break;
                    case "cam":
                        if(Integer.parseInt(chm_cam.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("cam"));
                        }
                        break;
                    case "rcm":
                        if(Integer.parseInt(chm_rcm.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("rcm"));
                        }
                        break;
                    case "lb":
                        if(Integer.parseInt(chm_lb.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("lb"));
                        }
                        break;
                    case "lcb":
                        if(Integer.parseInt(chm_lcb.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("lcb"));
                        }
                        break;
                    case "rcb":
                        if(Integer.parseInt(chm_rcb.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("rcb"));
                        }
                        break;
                    case "rb":
                        if(Integer.parseInt(chm_rb.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("rb"));
                        }
                        break;
                    case "gk":
                        if(Integer.parseInt(chm_gk.getText().toString()) < 10)
                        {
                            if(detalii_jucatori.get(get_player_name_from_name(position_takers.get("gk"))).get("NATIONALITY").equals(manager_nationality) || detalii_jucatori.get(get_player_name_from_name(position_takers.get("gk"))).get("LEAGUE").equals(manager_league))
                                chemistry_queue.add(position_takers.get("gk"));
                        }
                        break;
                }
            }
            choose_5_players_and_update_their_chemistry(chemistry_queue);
        }
    }

    private int get_int_chemistry_from_double_chemistry(double chem){
        int final_chem = (int) chem;
        if(final_chem>10)
            final_chem = 10;
        return final_chem;
    }

    private boolean check_if_position_is_compatible(String main_position, String actual_position){

        String[] positions = pozitii_compatibile.get(main_position);

        boolean flag = false;
        assert positions != null;
        for (String pozitie : positions){
            if(pozitie.equals(actual_position))
            {
                flag = true;
                break;
            }
        }

        return flag;
    }

    private void calculate_rating_and_chemistry(){
        int rating_vechi = Integer.parseInt(rating.getText().toString());
        int chemistry_vechi = Integer.parseInt(chemistry.getText().toString());
        int nr_jucatori_alesi = 0;
        for (String key : position_takers.keySet()) {
            if(!key.equals("res1") && !key.equals("res2") && !key.equals("res3") && !key.equals("res4") && !key.equals("res5"))
                nr_jucatori_alesi += 1;
        }
        int rating_nou = 0;
        for (String key : position_takers.keySet()) {
            if(!key.equals("res1") && !key.equals("res2") && !key.equals("res3") && !key.equals("res4") && !key.equals("res5")){
            String player = position_takers.get(key);
            assert player != null;
            rating_nou += Integer.parseInt(get_player_rating_from_name(player));
            }
        }
        rating_nou = (int) Math.round((double)rating_nou / nr_jucatori_alesi);
        rating.setText(String.valueOf(rating_nou));

        if(rating_nou > rating_vechi)
            rating.setTextColor(Color.GREEN);
        if(rating_nou < rating_vechi)
            rating.setTextColor(Color.RED);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                rating.setTextColor(Color.WHITE);
            }
        }, 1000);


        int chem_nou = 0;
        chem_nou += Integer.parseInt((String) chm_lw.getText());
        chem_nou += Integer.parseInt((String) chm_st.getText());
        chem_nou += Integer.parseInt((String) chm_rw.getText());
        chem_nou += Integer.parseInt((String) chm_cam.getText());
        chem_nou += Integer.parseInt((String) chm_lcm.getText());
        chem_nou += Integer.parseInt((String) chm_rcm.getText());
        chem_nou += Integer.parseInt((String) chm_lb.getText());
        chem_nou += Integer.parseInt((String) chm_lcb.getText());
        chem_nou += Integer.parseInt((String) chm_rcb.getText());
        chem_nou += Integer.parseInt((String) chm_rb.getText());
        chem_nou += Integer.parseInt((String) chm_gk.getText());

        if(chem_nou > 100)
            chem_nou = 100;
        chemistry.setText(String.valueOf(chem_nou));
        if(chem_nou > chemistry_vechi)
            chemistry.setTextColor(Color.GREEN);
        if(chem_nou < chemistry_vechi)
            chemistry.setTextColor(Color.RED);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                chemistry.setTextColor(Color.WHITE);
            }
        }, 1000);
    }

    private void put_picked_player_in_team(String variabila, String pick_result){
        int id;
        switch (variabila){
            case "card_lw":
                id = getId(pick_result, R.drawable.class);
                card_lw.setBackgroundResource(id);
                position_takers.put("lw", pick_result);
                break;
            case "card_st":
                id = getId(pick_result, R.drawable.class);
                card_st.setBackgroundResource(id);
                position_takers.put("st", pick_result);
                break;
            case "card_rw":
                id = getId(pick_result, R.drawable.class);
                card_rw.setBackgroundResource(id);
                position_takers.put("rw", pick_result);
                break;
            case "card_cam":
                id = getId(pick_result, R.drawable.class);
                card_cam.setBackgroundResource(id);
                position_takers.put("cam", pick_result);
                break;
            case "card_lcm":
                id = getId(pick_result, R.drawable.class);
                card_lcm.setBackgroundResource(id);
                position_takers.put("lcm", pick_result);
                break;
            case "card_rcm":
                id = getId(pick_result, R.drawable.class);
                card_rcm.setBackgroundResource(id);
                position_takers.put("rcm", pick_result);
                break;
            case "card_lb":
                id = getId(pick_result, R.drawable.class);
                card_lb.setBackgroundResource(id);
                position_takers.put("lb", pick_result);
                break;
            case "card_lcb":
                id = getId(pick_result, R.drawable.class);
                card_lcb.setBackgroundResource(id);
                position_takers.put("lcb", pick_result);
                break;
            case "card_rcb":
                id = getId(pick_result, R.drawable.class);
                card_rcb.setBackgroundResource(id);
                position_takers.put("rcb", pick_result);
                break;
            case "card_rb":
                id = getId(pick_result, R.drawable.class);
                card_rb.setBackgroundResource(id);
                position_takers.put("rb", pick_result);
                break;
            case "card_gk":
                id = getId(pick_result, R.drawable.class);
                card_gk.setBackgroundResource(id);
                position_takers.put("gk", pick_result);
                break;
            case "sub1":
                id = getId(pick_result, R.drawable.class);
                sub1.setBackgroundResource(id);
                position_takers.put("sub1", pick_result);
                break;
            case "sub2":
                id = getId(pick_result, R.drawable.class);
                sub2.setBackgroundResource(id);
                position_takers.put("sub2", pick_result);
                break;
            case "sub3":
                id = getId(pick_result, R.drawable.class);
                sub3.setBackgroundResource(id);
                position_takers.put("sub3", pick_result);
                break;
            case "sub4":
                id = getId(pick_result, R.drawable.class);
                sub4.setBackgroundResource(id);
                position_takers.put("sub4", pick_result);
                break;
            case "sub5":
                id = getId(pick_result, R.drawable.class);
                sub5.setBackgroundResource(id);
                position_takers.put("sub5", pick_result);
                break;
            case "sub6":
                id = getId(pick_result, R.drawable.class);
                sub6.setBackgroundResource(id);
                position_takers.put("sub6", pick_result);
                break;
            case "sub7":
                id = getId(pick_result, R.drawable.class);
                sub7.setBackgroundResource(id);
                position_takers.put("sub7", pick_result);
                break;
            case "res1":
                id = getId(pick_result, R.drawable.class);
                res1.setBackgroundResource(id);
                position_takers.put("res1", pick_result);
                break;
            case "res2":
                id = getId(pick_result, R.drawable.class);
                res2.setBackgroundResource(id);
                position_takers.put("res2", pick_result);
                break;
            case "res3":
                id = getId(pick_result, R.drawable.class);
                res3.setBackgroundResource(id);
                position_takers.put("res3", pick_result);
                break;
            case "res4":
                id = getId(pick_result, R.drawable.class);
                res4.setBackgroundResource(id);
                position_takers.put("res4", pick_result);
                break;
            case "res5":
                id = getId(pick_result, R.drawable.class);
                res5.setBackgroundResource(id);
                position_takers.put("res5", pick_result);
                break;
            case "card_manager":
                id = getId(pick_result, R.drawable.class);
                card_manager.setBackgroundResource(id);
                manager = pick_result;
                break;
        }
        put_position_takers_in_field();
    }

    private String get_picked_players_string(){
        String picked_players_string = "";
        for(String key : position_takers.keySet()) {
            String player = position_takers.get(key);
            picked_players_string = picked_players_string + player + '@';
        }
        return picked_players_string;
    }

    private void assign_action_to_every_image_button(){
        card_lw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("lw"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_lw.getBackground().setAlpha(100);
                        swap_position_1 = "lw";
                    }
                    else
                        {
                        if(swap_position_1.equals("lw"))
                        {
                            card_lw.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                            {
                            swap_position_2 = "lw";
                            swap_selected_players();
                            }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                        String picked_players_string = get_picked_players_string();
                        Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                        intent.putExtra("position", "lw");
                        intent.putExtra("picked_players", picked_players_string);
                        intent.putExtra("variabila", "card_lw");
                        intent.putExtra("players_bool", "false");
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                }
            }
        });

        card_st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("st"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_st.getBackground().setAlpha(100);
                        swap_position_1 = "st";
                    }
                    else
                    {
                        if(swap_position_1.equals("st"))
                        {
                            card_st.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "st";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "st");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_st");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        card_rw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("rw"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_rw.getBackground().setAlpha(100);
                        swap_position_1 = "rw";
                    }
                    else
                    {
                        if(swap_position_1.equals("rw"))
                        {
                            card_rw.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "rw";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "rw");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_rw");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        card_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("cam"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_cam.getBackground().setAlpha(100);
                        swap_position_1 = "cam";
                    }
                    else
                    {
                        if(swap_position_1.equals("cam"))
                        {
                            card_cam.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "cam";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "cam");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_cam");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        card_lcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("lcm"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_lcm.getBackground().setAlpha(100);
                        swap_position_1 = "lcm";
                    }
                    else
                    {
                        if(swap_position_1.equals("lcm"))
                        {
                            card_lcm.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "lcm";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "lcm");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_lcm");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        card_rcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("rcm"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_rcm.getBackground().setAlpha(100);
                        swap_position_1 = "rcm";
                    }
                    else
                    {
                        if(swap_position_1.equals("rcm"))
                        {
                            card_rcm.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "rcm";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "rcm");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_rcm");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        card_lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("lb"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_lb.getBackground().setAlpha(100);
                        swap_position_1 = "lb";
                    }
                    else
                    {
                        if(swap_position_1.equals("lb"))
                        {
                            card_lb.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "lb";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "lb");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_lb");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        card_lcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("lcb"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_lcb.getBackground().setAlpha(100);
                        swap_position_1 = "lcb";
                    }
                    else
                    {
                        if(swap_position_1.equals("lcb"))
                        {
                            card_lcb.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "lcb";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "lcb");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_lcb");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        card_rcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("rcb"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_rcb.getBackground().setAlpha(100);
                        swap_position_1 = "rcb";
                    }
                    else
                    {
                        if(swap_position_1.equals("rcb"))
                        {
                            card_rcb.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "rcb";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "rcb");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_rcb");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        card_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("rb"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_rb.getBackground().setAlpha(100);
                        swap_position_1 = "rb";
                    }
                    else
                    {
                        if(swap_position_1.equals("rb"))
                        {
                            card_rb.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "rb";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "rb");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_rb");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        card_gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("gk"))
                {
                    if(!swap)
                    {
                        swap = true;
                        card_gk.getBackground().setAlpha(100);
                        swap_position_1 = "gk";
                    }
                    else
                    {
                        if(swap_position_1.equals("gk"))
                        {
                            card_gk.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "gk";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "gk");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_gk");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("sub1"))
                {
                    if(!swap)
                    {
                        swap = true;
                        sub1.getBackground().setAlpha(100);
                        swap_position_1 = "sub1";
                    }
                    else
                    {
                        if(swap_position_1.equals("sub1"))
                        {
                            sub1.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "sub1";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "gk");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "sub1");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("sub2"))
                {
                    if(!swap)
                    {
                        swap = true;
                        sub2.getBackground().setAlpha(100);
                        swap_position_1 = "sub2";
                    }
                    else
                    {
                        if(swap_position_1.equals("sub2"))
                        {
                            sub2.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "sub2";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "defence");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "sub2");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("sub3"))
                {
                    if(!swap)
                    {
                        swap = true;
                        sub3.getBackground().setAlpha(100);
                        swap_position_1 = "sub3";
                    }
                    else
                    {
                        if(swap_position_1.equals("sub3"))
                        {
                            sub3.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "sub3";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "defence");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "sub3");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        sub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("sub4"))
                {
                    if(!swap)
                    {
                        swap = true;
                        sub4.getBackground().setAlpha(100);
                        swap_position_1 = "sub4";
                    }
                    else
                    {
                        if(swap_position_1.equals("sub4"))
                        {
                            sub4.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "sub4";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "midfield");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "sub4");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        sub5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("sub5"))
                {
                    if(!swap)
                    {
                        swap = true;
                        sub5.getBackground().setAlpha(100);
                        swap_position_1 = "sub5";
                    }
                    else
                    {
                        if(swap_position_1.equals("sub5"))
                        {
                            sub5.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "sub5";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "midfield");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "sub5");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        sub6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("sub6"))
                {
                    if(!swap)
                    {
                        swap = true;
                        sub6.getBackground().setAlpha(100);
                        swap_position_1 = "sub6";
                    }
                    else
                    {
                        if(swap_position_1.equals("sub6"))
                        {
                            sub6.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "sub6";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "attack");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "sub6");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        sub7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("sub7"))
                {
                    if(!swap)
                    {
                        swap = true;
                        sub7.getBackground().setAlpha(100);
                        swap_position_1 = "sub7";
                    }
                    else
                    {
                        if(swap_position_1.equals("sub7"))
                        {
                            sub7.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "sub7";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "attack");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "sub7");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        res1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("res1"))
                {
                    if(!swap)
                    {
                        swap = true;
                        res1.getBackground().setAlpha(100);
                        swap_position_1 = "res1";
                    }
                    else
                    {
                        if(swap_position_1.equals("res1"))
                        {
                            res1.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "res1";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "any");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "res1");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        res2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("res2"))
                {
                    if(!swap)
                    {
                        swap = true;
                        res2.getBackground().setAlpha(100);
                        swap_position_1 = "res2";
                    }
                    else
                    {
                        if(swap_position_1.equals("res2"))
                        {
                            res2.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "res2";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "any");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "res2");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        res3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("res3"))
                {
                    if(!swap)
                    {
                        swap = true;
                        res3.getBackground().setAlpha(100);
                        swap_position_1 = "res3";
                    }
                    else
                    {
                        if(swap_position_1.equals("res3"))
                        {
                            res3.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "res3";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "any");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "res3");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        res4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("res4"))
                {
                    if(!swap)
                    {
                        swap = true;
                        res4.getBackground().setAlpha(100);
                        swap_position_1 = "res4";
                    }
                    else
                    {
                        if(swap_position_1.equals("res4"))
                        {
                            res4.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "res4";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "any");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "res4");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        res5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position_takers.containsKey("res5"))
                {
                    if(!swap)
                    {
                        swap = true;
                        res5.getBackground().setAlpha(100);
                        swap_position_1 = "res5";
                    }
                    else
                    {
                        if(swap_position_1.equals("res5"))
                        {
                            res5.getBackground().setAlpha(255);
                            swap = false;
                        }
                        else
                        {
                            swap_position_2 = "res5";
                            swap_selected_players();
                        }
                    }
                }
                else {
                    if (swap) {
                        Toast.makeText(F433AttackActivity.this, "You can't swap with an empty position!", Toast.LENGTH_SHORT).show();
                    } else {
                String picked_players_string = get_picked_players_string();
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "any");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "res5");
                intent.putExtra("players_bool", "false");
                startActivityForResult(intent , REQUEST_CODE);
            }}}
        });

        card_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (position_takers.size() < 23)
//                    Toast.makeText(F433AttackActivity.this, "You must choose all the players before choosing the manager!", Toast.LENGTH_SHORT).show();
//                else {

                    if (manager != null)
                        Toast.makeText(F433AttackActivity.this, "You already chose a manager!", Toast.LENGTH_SHORT).show();

                    else {
                        Intent intent = new Intent(F433AttackActivity.this, ManagerPickActivity.class);
                        intent.putExtra("position", "manager");
                        intent.putExtra("variabila", "card_manager");
                        intent.putExtra("managers_bool", "false");
                        startActivityForResult(intent, REQUEST_CODE);
                    }
//                }
            }
        });
    }

    private void swap_selected_players(){
        String aux = position_takers.get(swap_position_1);
        position_takers.put(swap_position_1, position_takers.get(swap_position_2));
        position_takers.put(swap_position_2, aux);
        swap = false;
        switch (swap_position_1){
            case "st":
                card_st.getBackground().setAlpha(255);
                break;

            case "cam":
                card_cam.getBackground().setAlpha(255);
                break;

            case "lw":
                card_lw.getBackground().setAlpha(255);
                break;

            case "rw":
                card_rw.getBackground().setAlpha(255);
                break;

            case "lcm":
                card_lcm.getBackground().setAlpha(255);
                break;

            case "rcm":
                card_rcm.getBackground().setAlpha(255);
                break;

            case "lb":
                card_lb.getBackground().setAlpha(255);
                break;

            case "rb":
                card_rb.getBackground().setAlpha(255);
                break;

            case "lcb":
                card_lcb.getBackground().setAlpha(255);
                break;

            case "rcb":
                card_rcb.getBackground().setAlpha(255);
                break;

            case "gk":
                card_gk.getBackground().setAlpha(255);
                break;

            case "sub1":
                sub1.getBackground().setAlpha(255);
                break;

            case "sub2":
                sub2.getBackground().setAlpha(255);
                break;

            case "sub3":
                sub3.getBackground().setAlpha(255);
                break;

            case "sub4":
                sub4.getBackground().setAlpha(255);
                break;

            case "sub5":
                sub5.getBackground().setAlpha(255);
                break;

            case "sub6":
                sub6.getBackground().setAlpha(255);
                break;

            case "sub7":
                sub7.getBackground().setAlpha(255);
                break;

            case "res1":
                res1.getBackground().setAlpha(255);
                break;

            case "res2":
                res2.getBackground().setAlpha(255);
                break;

            case "res3":
                res3.getBackground().setAlpha(255);
                break;

            case "res4":
                res4.getBackground().setAlpha(255);
                break;

            case "res5":
                res5.getBackground().setAlpha(255);
                break;

        }
        put_position_takers_in_field();

    }

    private void disable_all_buttons(){
        card_lw.setEnabled(false);
        card_st.setEnabled(false);
        card_rw.setEnabled(false);
        card_cam.setEnabled(false);
        card_lcm.setEnabled(false);
        card_rcm.setEnabled(false);
        card_lb.setEnabled(false);
        card_lcb.setEnabled(false);
        card_rcb.setEnabled(false);
        card_rb.setEnabled(false);
        card_gk.setEnabled(false);
        sub1.setEnabled(false);
        sub2.setEnabled(false);
        sub3.setEnabled(false);
        sub4.setEnabled(false);
        sub5.setEnabled(false);
        sub6.setEnabled(false);
        sub7.setEnabled(false);
        res1.setEnabled(false);
        res2.setEnabled(false);
        res3.setEnabled(false);
        res4.setEnabled(false);
        res5.setEnabled(false);
        card_manager.setEnabled(false);
    }

    private void enable_all_buttons(){
        card_lw.setEnabled(true);
        card_st.setEnabled(true);
        card_rw.setEnabled(true);
        card_cam.setEnabled(true);
        card_lcm.setEnabled(true);
        card_rcm.setEnabled(true);
        card_lb.setEnabled(true);
        card_lcb.setEnabled(true);
        card_rcb.setEnabled(true);
        card_rb.setEnabled(true);
        card_gk.setEnabled(true);
        sub1.setEnabled(true);
        sub2.setEnabled(true);
        sub3.setEnabled(true);
        sub4.setEnabled(true);
        sub5.setEnabled(true);
        sub6.setEnabled(true);
        sub7.setEnabled(true);
        res1.setEnabled(true);
        res2.setEnabled(true);
        res3.setEnabled(true);
        res4.setEnabled(true);
        res5.setEnabled(true);
        card_manager.setEnabled(true);
    }

    private void choose_5_players_and_update_their_chemistry(ArrayList<String> chemistry_queue){
        ArrayList<String> winners = new ArrayList<>();
        if(chemistry_queue.size() <= 5)
            winners = chemistry_queue;
        else {
            Random generator = new Random();

            int randomIndex = generator.nextInt(chemistry_queue.size());
            int randomIndex2 = generator.nextInt(chemistry_queue.size());
            int randomIndex3 = generator.nextInt(chemistry_queue.size());
            int randomIndex4 = generator.nextInt(chemistry_queue.size());
            int randomIndex5 = generator.nextInt(chemistry_queue.size());

            while (randomIndex2 == randomIndex)
                randomIndex2 = generator.nextInt(chemistry_queue.size());

            while (randomIndex3 == randomIndex || randomIndex3 == randomIndex2)
                randomIndex3 = generator.nextInt(chemistry_queue.size());

            while (randomIndex4 == randomIndex || randomIndex4 == randomIndex2 || randomIndex4 == randomIndex3)
                randomIndex4 = generator.nextInt(chemistry_queue.size());

            while (randomIndex5 == randomIndex || randomIndex5 == randomIndex2 || randomIndex5 == randomIndex3 || randomIndex5 == randomIndex4)
                randomIndex5 = generator.nextInt(chemistry_queue.size());

            winners.add(manager_cards[randomIndex]);
            winners.add(manager_cards[randomIndex2]);
            winners.add(manager_cards[randomIndex3]);
            winners.add(manager_cards[randomIndex4]);
            winners.add(manager_cards[randomIndex5]);
        }

        HashMap<String, String> reverted_position_takers = new HashMap<>();
        for(String key: position_takers.keySet())
            reverted_position_takers.put(position_takers.get(key), key);

        for(String winner: winners){
            switch (reverted_position_takers.get(winner)){
                case "st":
                    chm_st.setText(String.valueOf(Integer.parseInt(chm_st.getText().toString()) + 1));
                    break;

                case "cam":
                    chm_cam.setText(String.valueOf(Integer.parseInt(chm_cam.getText().toString()) + 1));
                    break;

                case "lw":
                    chm_lw.setText(String.valueOf(Integer.parseInt(chm_lw.getText().toString()) + 1));
                    break;

                case "rw":
                    chm_rw.setText(String.valueOf(Integer.parseInt(chm_rw.getText().toString()) + 1));
                    break;

                case "lcm":
                    chm_lcm.setText(String.valueOf(Integer.parseInt(chm_lcm.getText().toString()) + 1));
                    break;

                case "rcm":
                    chm_rcm.setText(String.valueOf(Integer.parseInt(chm_rcm.getText().toString()) + 1));
                    break;

                case "lb":
                    chm_lb.setText(String.valueOf(Integer.parseInt(chm_lb.getText().toString()) + 1));
                    break;

                case "rb":
                    chm_rb.setText(String.valueOf(Integer.parseInt(chm_rb.getText().toString()) + 1));
                    break;

                case "lcb":
                    chm_lcb.setText(String.valueOf(Integer.parseInt(chm_lcb.getText().toString()) + 1));
                    break;

                case "rcb":
                    chm_rcb.setText(String.valueOf(Integer.parseInt(chm_rcb.getText().toString()) + 1));
                    break;

                case "gk":
                    chm_gk.setText(String.valueOf(Integer.parseInt(chm_gk.getText().toString()) + 1));
                    break;
            }
        }

    }

    private void set_uninfluenced_players_alpha_to_100(String picked_manager){
        String manager_nationality = detalii_manageri.get(picked_manager).get("NATIONALITY");
        String manager_league = detalii_manageri.get(picked_manager).get("LEAGUE");
        set_all_players_alpha_to_255();

        for (String key : position_takers.keySet()){
            switch (key){
                case "st":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("st"))).get("LEAGUE").equals(manager_league))
                            card_st.getBackground().setAlpha(100);
                    break;

                case "lw":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("lw"))).get("LEAGUE").equals(manager_league))
                            card_lw.getBackground().setAlpha(100);
                    break;

                case "rw":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("rw"))).get("LEAGUE").equals(manager_league))
                            card_rw.getBackground().setAlpha(100);
                    break;

                case "lcm":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcm"))).get("LEAGUE").equals(manager_league))
                            card_lcm.getBackground().setAlpha(100);
                    break;

                case "cam":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("cam"))).get("LEAGUE").equals(manager_league))
                            card_cam.getBackground().setAlpha(100);
                    break;

                case "rcm":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcm"))).get("LEAGUE").equals(manager_league))
                            card_rcm.getBackground().setAlpha(100);
                    break;

                case "lb":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("lb"))).get("LEAGUE").equals(manager_league))
                            card_lb.getBackground().setAlpha(100);
                    break;

                case "lcb":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("lcb"))).get("LEAGUE").equals(manager_league))
                            card_lcb.getBackground().setAlpha(100);
                    break;

                case "rcb":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("rcb"))).get("LEAGUE").equals(manager_league))
                            card_rcb.getBackground().setAlpha(100);
                    break;

                case "rb":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("rb"))).get("LEAGUE").equals(manager_league))
                            card_rb.getBackground().setAlpha(100);
                    break;

                case "gk":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("gk"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("gk"))).get("LEAGUE").equals(manager_league))
                            card_gk.getBackground().setAlpha(100);
                    break;

                case "sub1":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub1"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub1"))).get("LEAGUE").equals(manager_league))
                        sub1.getBackground().setAlpha(100);
                    break;

                case "sub2":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub2"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub2"))).get("LEAGUE").equals(manager_league))
                        sub2.getBackground().setAlpha(100);
                    break;

                case "sub3":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub3"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub3"))).get("LEAGUE").equals(manager_league))
                        sub3.getBackground().setAlpha(100);
                    break;

                case "sub4":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub4"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub4"))).get("LEAGUE").equals(manager_league))
                        sub4.getBackground().setAlpha(100);
                    break;

                case "sub5":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub5"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub5"))).get("LEAGUE").equals(manager_league))
                        sub5.getBackground().setAlpha(100);
                    break;

                case "sub6":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub6"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub6"))).get("LEAGUE").equals(manager_league))
                        sub6.getBackground().setAlpha(100);
                    break;

                case "sub7":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub7"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("sub7"))).get("LEAGUE").equals(manager_league))
                        sub7.getBackground().setAlpha(100);
                    break;

                case "res1":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("res1"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("res1"))).get("LEAGUE").equals(manager_league))
                        res1.getBackground().setAlpha(100);
                    break;

                case "res2":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("res2"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("res2"))).get("LEAGUE").equals(manager_league))
                        res2.getBackground().setAlpha(100);
                    break;

                case "res3":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("res3"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("res3"))).get("LEAGUE").equals(manager_league))
                        res3.getBackground().setAlpha(100);
                    break;

                case "res4":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("res4"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("res4"))).get("LEAGUE").equals(manager_league))
                        res4.getBackground().setAlpha(100);
                    break;

                case "res5":
                    if(!detalii_jucatori.get(get_player_name_from_name(position_takers.get("res5"))).get("NATIONALITY").equals(manager_nationality) && !detalii_jucatori.get(get_player_name_from_name(position_takers.get("res5"))).get("LEAGUE").equals(manager_league))
                        res5.getBackground().setAlpha(100);
                    break;
            }
        }
    }

    private void set_all_players_alpha_to_255(){
        card_st.getBackground().setAlpha(255);
        card_lw.getBackground().setAlpha(255);
        card_rw.getBackground().setAlpha(255);
        card_lcm.getBackground().setAlpha(255);
        card_cam.getBackground().setAlpha(255);
        card_rcm.getBackground().setAlpha(255);
        card_lb.getBackground().setAlpha(255);
        card_lcb.getBackground().setAlpha(255);
        card_rcb.getBackground().setAlpha(255);
        card_rb.getBackground().setAlpha(255);
        card_gk.getBackground().setAlpha(255);
        sub1.getBackground().setAlpha(255);
        sub2.getBackground().setAlpha(255);
        sub3.getBackground().setAlpha(255);
        sub4.getBackground().setAlpha(255);
        sub5.getBackground().setAlpha(255);
        sub6.getBackground().setAlpha(255);
        sub7.getBackground().setAlpha(255);
        res1.getBackground().setAlpha(255);
        res2.getBackground().setAlpha(255);
        res3.getBackground().setAlpha(255);
        res4.getBackground().setAlpha(255);
        res5.getBackground().setAlpha(255);
    }
}