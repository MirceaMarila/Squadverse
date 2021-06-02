package formations;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squadverse.FeedbackActivity;
import com.example.squadverse.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.HashMap;
import java.util.Random;
import static draft.FormationsActivity.getId;

import common.BaseActivity;
import draft.CaptainActivity;
import draft.PlayerPickActivity;

public class F433AttackActivity extends BaseActivity {

    TextView slide_title, rating, chemistry, chm_lw, chm_st, chm_rw, chm_cam, chm_lcm, chm_rcm, chm_lb, chm_lcb, chm_rcb, chm_rb, chm_gk;
    ImageView link_lw_st, link_rw_st, link_cam_st, link_lw_lcm, link_rw_rcm, link_cam_lcm, link_cam_rcm, link_rw_rb, link_lw_lb, link_lcm_lb, link_rcm_rb, link_rcb_rb, link_lcb_lb, link_lcb_rcb, link_rcm_rcb, link_lcm_lcb, link_lcb_gk, link_rcb_gk;
    ImageButton card_lw, card_st, card_rw, card_cam, card_lcm, card_rcm, card_lb, card_lcb, card_rcb, card_rb, card_gk, sub1, sub2, sub3, sub4, sub5, sub6, sub7, res1, res2, res3, res4, res5;
    HashMap<String, String> position_takers=new HashMap<>();
    public static final int REQUEST_CODE = 1;


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
                    slide_title.setText("\u2193 SUBSTITUTES AND RESERVES \u2193");

                    }

                else if(newState == SlidingUpPanelLayout.PanelState.COLLAPSED){
                    slide_title.setText("\u2191 SUBSTITUTES AND RESERVES \u2191");
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
        card_st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String picked_players_string = "";
                for(String key : position_takers.keySet()) {
                    String player = position_takers.get(key);
                    picked_players_string = picked_players_string + player + '@';
                }

//                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
//                intent.putExtra("position", "st");
//                intent.putExtra("picked_players", picked_players_string);
//                intent.putExtra("variabila", "card_st");
//                startActivity(intent);
                Intent intent = new Intent(F433AttackActivity.this, PlayerPickActivity.class);
                intent.putExtra("position", "st");
                intent.putExtra("picked_players", picked_players_string);
                intent.putExtra("variabila", "card_st");
                startActivityForResult(intent , REQUEST_CODE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {
                String pick_Result = data.getStringExtra("pick_result");
                String variabila = data.getStringExtra("variabila");
                put_picked_player_in_team(variabila, pick_Result);
            }
        } catch (Exception ex) {
            Toast.makeText(F433AttackActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
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
            }
        }
        calculate_chemistry_of_every_player();
        calculate_rating_and_chemistry();
    }

    private void calculate_chemistry_of_every_player(){
        for (String key : position_takers.keySet()) {
            String player = position_takers.get(key);
            assert player != null;
            int chm_actual;
            int new_chem;

            switch (key){
                case "st":
                    chm_actual = Integer.parseInt((String) chm_st.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_st.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_st.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_st.setTextColor(Color.RED);
                        }
                    }
                    chm_st.setText(String.valueOf(new_chem));
                    break;


                case "cam":
                    chm_actual = Integer.parseInt((String) chm_cam.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_cam.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_cam.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_cam.setTextColor(Color.RED);
                        }
                    }
                    chm_cam.setText(String.valueOf(new_chem));
                    break;


                case "lw":
                    chm_actual = Integer.parseInt((String) chm_lw.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_lw.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_lw.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_lw.setTextColor(Color.RED);
                        }
                    }
                    chm_lw.setText(String.valueOf(new_chem));
                    break;


                case "rw":
                    chm_actual = Integer.parseInt((String) chm_rw.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_rw.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_rw.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_rw.setTextColor(Color.RED);
                        }
                    }
                    chm_rw.setText(String.valueOf(new_chem));
                    break;


                case "lcm":
                    key = "cm";
                    chm_actual = Integer.parseInt((String) chm_lcm.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_lcm.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_lcm.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_lcm.setTextColor(Color.RED);
                        }
                    }
                    chm_lcm.setText(String.valueOf(new_chem));
                    break;

                case "rcm":
                    key = "cm";
                    chm_actual = Integer.parseInt((String) chm_rcm.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_rcm.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_rcm.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_rcm.setTextColor(Color.RED);
                        }
                    }
                    chm_rcm.setText(String.valueOf(new_chem));
                    break;

                case "lb":
                    chm_actual = Integer.parseInt((String) chm_lb.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_lb.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_lb.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_lb.setTextColor(Color.RED);
                        }
                    }
                    chm_lb.setText(String.valueOf(new_chem));
                    break;

                case "rb":
                    chm_actual = Integer.parseInt((String) chm_rb.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_rb.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_rb.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_rb.setTextColor(Color.RED);
                        }
                    }
                    chm_rb.setText(String.valueOf(new_chem));
                    break;

                case "lcb":
                    key = "cb";
                    chm_actual = Integer.parseInt((String) chm_lcb.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_lcb.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_lcb.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_lcb.setTextColor(Color.RED);
                        }
                    }
                    chm_lcb.setText(String.valueOf(new_chem));
                    break;

                case "rcb":
                    key = "cb";
                    chm_actual = Integer.parseInt((String) chm_rcb.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_rcb.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_rcb.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_rcb.setTextColor(Color.RED);
                        }
                    }
                    chm_rcb.setText(String.valueOf(new_chem));
                    break;

                case "gk":
                    chm_actual = Integer.parseInt((String) chm_gk.getText());
                    new_chem = chm_actual;
                    if(get_player_position_from_name(player).equals(key)){
                        if(chm_actual == 0)
                            new_chem += 4;
                        chm_gk.setTextColor(Color.GREEN);
                    }

                    if(!get_player_position_from_name(player).equals(key)){
                        if(check_if_position_is_compatible(key, get_player_position_from_name(player))){
                            if(chm_actual == 0)
                                new_chem += 3;
                            chm_gk.setTextColor(Color.YELLOW);
                        }
                        else{
                            if(chm_actual == 0)
                                new_chem += 1;
                            chm_gk.setTextColor(Color.RED);
                        }
                    }
                    chm_gk.setText(String.valueOf(new_chem));
                    break;
            }
        }
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
        int rating_nou = 0;
        for (String key : position_takers.keySet()) {
            String player = position_takers.get(key);
            assert player != null;
            rating_nou += Integer.parseInt(get_player_rating_from_name(player))/18;
        }
        rating.setText(String.valueOf(rating_nou));

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
                // TODO: adauga picked players si pt sub si res
            case "sub1":
                id = getId(pick_result, R.drawable.class);
                sub1.setBackgroundResource(id);
                break;
            case "sub2":
                id = getId(pick_result, R.drawable.class);
                sub2.setBackgroundResource(id);
                break;
            case "sub3":
                id = getId(pick_result, R.drawable.class);
                sub3.setBackgroundResource(id);
                break;
            case "sub4":
                id = getId(pick_result, R.drawable.class);
                sub4.setBackgroundResource(id);
                break;
            case "sub5":
                id = getId(pick_result, R.drawable.class);
                sub5.setBackgroundResource(id);
                break;
            case "sub6":
                id = getId(pick_result, R.drawable.class);
                sub6.setBackgroundResource(id);
                break;
            case "sub7":
                id = getId(pick_result, R.drawable.class);
                sub7.setBackgroundResource(id);
                break;
            case "res1":
                id = getId(pick_result, R.drawable.class);
                res1.setBackgroundResource(id);
                break;
            case "res2":
                id = getId(pick_result, R.drawable.class);
                res2.setBackgroundResource(id);
                break;
            case "res3":
                id = getId(pick_result, R.drawable.class);
                res3.setBackgroundResource(id);
                break;
            case "res4":
                id = getId(pick_result, R.drawable.class);
                res4.setBackgroundResource(id);
                break;
            case "res5":
                id = getId(pick_result, R.drawable.class);
                res5.setBackgroundResource(id);
                break;
        }
        put_position_takers_in_field();
    }

}