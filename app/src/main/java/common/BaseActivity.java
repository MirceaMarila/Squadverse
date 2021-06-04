package common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.JsonReader;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.squadverse.BugReportActivity;
import com.example.squadverse.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class BaseActivity extends AppCompatActivity {

    // formations database
    protected final String[] all_formations = {"f_433_attack", "f_433_defend", "f_433_false9", "f_3142", "f_523"};
    protected final String[] mid_formations = {"f_433_attack", "f_433_defend", "f_433_false9"};
    protected final String[] att_formations = {"f_3142"};
    protected final String[] def_formations = {"f_523"};

    // player cards database
    protected final String[] all_cards = {"alejandrogomez_86_cam_base", "alejandrogrimaldo_84_lb_base", "alexsandro_85_lb_base", "alextelles_84_lb_base", "alissonbecker_90_gk_base", "andrewrobertson_87_lb_base", "aubameyang_87_st_base", "azpilicueta_84_rb_base", "benzema_89_cf_base", "bernardomotasilva_87_rw_base", "brunomiguelfernandes_87_cam_base", "cronaldodossantos_92_st_base", "callejonbueno_84_rm_base", "chiellini_87_cb_base", "courtois_89_gk_base", "dacosta_78_rwb_base", "danielcarvajalramos_86_rb_base", "davidsilva_86_cam_base", "debruyne_91_cam_base", "dimaria_87_rw_base", "doherty_81_rwb_base", "douglascosta_84_lm_base", "dybala_88_cf_base", "edersonsantana_88_gk_base", "eriksen_85_cam_base", "fabiohenrique_87_cdm_base", "frenkiedejong_85_cm_base", "gerardpique_86_cb_base", "gnabry_85_rm_base", "griezmann_87_st_base", "handanovic_88_gk_base", "havertz_85_cam_base", "hazard_88_lw_base", "henderson_86_cdm_base", "henriquevenanciocasimiro_89_cdm_base", "heungminson_87_lm_base", "hummels_86_cb_base", "illems_77_lwb_base", "immobile_87_st_base", "insigne_85_lw_base", "jesuscorona_84_rm_base", "jonny_81_lwb_base", "jordialba_86_lb_base", "jorgeresurreccion_85_cm_base", "kaderabek_80_rwb_base", "kane_88_st_base", "kante_88_cdm_base", "kimmich_88_cdm_base", "kingsleycoman_84_lm_base", "koulibaly_88_cb_base", "kroos_88_cm_base", "lala_79_rwb_base", "laporte_87_cb_base", "lewandowski_91_st_base", "luisalbertoromero_85_cam_base", "luismiguelafonsofernandes_84_rm_base", "lukaku_85_st_base", "mahrez_85_rw_base", "mane_90_lw_base", "marcosaoas_85_cb_base", "mariofernandes_82_rwb_base", "matthijsdeligt_85_cb_base", "mbappe_90_st_base", "memphisdepay_85_cf_base", "mertens_85_cf_base", "messi_93_rw_base", "milinkovicsavic_85_cm_base", "modric_87_cm_base", "muller_86_cam_base", "navas_87_gk_base", "neuer_89_gk_base", "neymarjr_91_lw_base", "oblak_91_gk_base", "parejo_85_cm_base", "pjanic_85_cm_base", "pogba_86_cm_base", "raphaelvarane_86_cb_base", "rashford_85_lm_base", "reus_85_cam_base", "ricardopereira_85_rb_base", "robertofirminobarbosa_87_cf_base", "rodrigohernandez_85_cdm_base", "salah_90_rw_base", "sancho_87_rm_base", "sane_85_lm_base", "sequeira_78_lwb_base", "sergioaguero_89_st_base", "sergiobusquets_87_cdm_base", "sergioramos_89_cb_base", "silva_75_lwb_base", "sterling_88_lw_base", "stevens_78_lwb_base", "suarez_87_st_base", "szczesny_87_gk_base", "terstegen_90_gk_base", "thiago_85_cm_base", "trentalexanderarnold_87_rb_base", "vandijk_90_cb_base", "vardy_86_st_base", "veratti_86_cm_base", "walker_85_rb_base", "werner_85_st_base", "wijnaldum_85_cm_base", "ziyech_85_cam_base"};
    protected final String[] def_cards = {"alejandrogrimaldo_84_lb_base", "alexsandro_85_lb_base", "alextelles_84_lb_base", "andrewrobertson_87_lb_base", "azpilicueta_84_rb_base", "chiellini_87_cb_base", "dacosta_78_rwb_base", "danielcarvajalramos_86_rb_base", "doherty_81_rwb_base", "gerardpique_86_cb_base", "hummels_86_cb_base", "illems_77_lwb_base", "jonny_81_lwb_base", "jordialba_86_lb_base", "kaderabek_80_rwb_base", "koulibaly_88_cb_base", "lala_79_rwb_base", "laporte_87_cb_base", "marcosaoas_85_cb_base", "mariofernandes_82_rwb_base", "matthijsdeligt_85_cb_base", "raphaelvarane_86_cb_base", "ricardopereira_85_rb_base", "sequeira_78_lwb_base", "sergioramos_89_cb_base", "silva_75_lwb_base", "stevens_78_lwb_base", "trentalexanderarnold_87_rb_base", "vandijk_90_cb_base", "walker_85_rb_base"};
    protected final String[] mid_cards = {"alejandrogomez_86_cam_base", "brunomiguelfernandes_87_cam_base", "callejonbueno_84_rm_base", "davidsilva_86_cam_base", "debruyne_91_cam_base", "douglascosta_84_lm_base", "eriksen_85_cam_base", "fabiohenrique_87_cdm_base", "frenkiedejong_85_cm_base", "gnabry_85_rm_base", "havertz_85_cam_base", "henderson_86_cdm_base", "henriquevenanciocasimiro_89_cdm_base", "heungminson_87_lm_base", "jesuscorona_84_rm_base", "jorgeresurreccion_85_cm_base", "kante_88_cdm_base", "kimmich_88_cdm_base", "kingsleycoman_84_lm_base", "kroos_88_cm_base", "luisalbertoromero_85_cam_base", "luismiguelafonsofernandes_84_rm_base", "milinkovicsavic_85_cm_base", "modric_87_cm_base", "muller_86_cam_base", "parejo_85_cm_base", "pjanic_85_cm_base", "pogba_86_cm_base", "rashford_85_lm_base", "reus_85_cam_base", "rodrigohernandez_85_cdm_base", "sancho_87_rm_base", "sane_85_lm_base", "sergiobusquets_87_cdm_base", "thiago_85_cm_base", "veratti_86_cm_base", "wijnaldum_85_cm_base", "ziyech_85_cam_base"};
    protected final String[] att_cards = {"aubameyang_87_st_base", "benzema_89_cf_base", "bernardomotasilva_87_rw_base", "cronaldodossantos_92_st_base", "dimaria_87_rw_base", "dybala_88_cf_base", "griezmann_87_st_base", "hazard_88_lw_base", "immobile_87_st_base", "insigne_85_lw_base", "kane_88_st_base", "lewandowski_91_st_base", "lukaku_85_st_base", "mahrez_85_rw_base", "mane_90_lw_base", "mbappe_90_st_base", "memphisdepay_85_cf_base", "mertens_85_cf_base", "messi_93_rw_base", "neymarjr_91_lw_base", "robertofirminobarbosa_87_cf_base", "salah_90_rw_base", "sergioaguero_89_st_base", "sterling_88_lw_base", "suarez_87_st_base", "vardy_86_st_base", "werner_85_st_base"};
    protected final String[] gk_cards = {"alissonbecker_90_gk_base", "courtois_89_gk_base", "edersonsantana_88_gk_base", "handanovic_88_gk_base", "navas_87_gk_base", "neuer_89_gk_base", "oblak_91_gk_base", "szczesny_87_gk_base", "terstegen_90_gk_base"};
    protected final String[] st_cards = {"aubameyang_87_st_base", "cronaldodossantos_92_st_base", "griezmann_87_st_base", "immobile_87_st_base", "kane_88_st_base", "lewandowski_91_st_base", "lukaku_85_st_base", "mbappe_90_st_base", "sergioaguero_89_st_base", "suarez_87_st_base", "vardy_86_st_base", "werner_85_st_base"};
    protected final String[] lw_cards = {"hazard_88_lw_base", "insigne_85_lw_base", "mane_90_lw_base", "neymarjr_91_lw_base", "sterling_88_lw_base"};
    protected final String[] rw_cards = {"bernardomotasilva_87_rw_base", "dimaria_87_rw_base", "mahrez_85_rw_base", "messi_93_rw_base", "salah_90_rw_base"};
    protected final String[] cam_cards = {"alejandrogomez_86_cam_base", "brunomiguelfernandes_87_cam_base", "davidsilva_86_cam_base", "debruyne_91_cam_base", "eriksen_85_cam_base", "havertz_85_cam_base", "luisalbertoromero_85_cam_base", "muller_86_cam_base", "reus_85_cam_base", "ziyech_85_cam_base"};
    protected final String[] cm_cards = {"frenkiedejong_85_cm_base", "jorgeresurreccion_85_cm_base", "kroos_88_cm_base", "milinkovicsavic_85_cm_base", "modric_87_cm_base", "parejo_85_cm_base", "pjanic_85_cm_base", "pogba_86_cm_base", "thiago_85_cm_base", "veratti_86_cm_base", "wijnaldum_85_cm_base"};
    protected final String[] lb_cards = {"alejandrogrimaldo_84_lb_base", "alexsandro_85_lb_base", "alextelles_84_lb_base", "andrewrobertson_87_lb_base", "jordialba_86_lb_base"};
    protected final String[] rb_cards = {"azpilicueta_84_rb_base", "danielcarvajalramos_86_rb_base", "ricardopereira_85_rb_base", "trentalexanderarnold_87_rb_base", "walker_85_rb_base"};
    protected final String[] cb_cards = {"chiellini_87_cb_base", "gerardpique_86_cb_base", "hummels_86_cb_base", "koulibaly_88_cb_base", "laporte_87_cb_base", "marcosaoas_85_cb_base", "matthijsdeligt_85_cb_base", "raphaelvarane_86_cb_base", "sergioramos_89_cb_base", "vandijk_90_cb_base"};
    protected final String[] cf_cards = {"benzema_89_cf_base", "dybala_88_cf_base", "memphisdepay_85_cf_base", "mertens_85_cf_base", "robertofirminobarbosa_87_cf_base"};
    protected final String[] lf_cards = {};
    protected final String[] rf_cards = {};
    protected final String[] lm_cards = {"douglascosta_84_lm_base", "heungminson_87_lm_base", "kingsleycoman_84_lm_base", "rashford_85_lm_base", "sane_85_lm_base"};
    protected final String[] rm_cards = {"callejonbueno_84_rm_base", "gnabry_85_rm_base", "jesuscorona_84_rm_base", "luismiguelafonsofernandes_84_rm_base", "sancho_87_rm_base"};
    protected final String[] cdm_cards = {"fabiohenrique_87_cdm_base", "henderson_86_cdm_base", "henriquevenanciocasimiro_89_cdm_base", "kante_88_cdm_base", "kimmich_88_cdm_base", "rodrigohernandez_85_cdm_base", "sergiobusquets_87_cdm_base"};
    protected final String[] lwb_cards = {"illems_77_lwb_base", "jonny_81_lwb_base", "sequeira_78_lwb_base", "silva_75_lwb_base", "stevens_78_lwb_base"};
    protected final String[] rwb_cards = {"dacosta_78_rwb_base", "doherty_81_rwb_base", "kaderabek_80_rwb_base", "lala_79_rwb_base", "mariofernandes_82_rwb_base"};

    //formation positions database
    protected HashMap<String, String[]> pozitiile_formatiilor=new HashMap<>();

    //compatible positions database
    protected HashMap<String, String[]> pozitii_compatibile=new HashMap<>();

    //player details database
    protected HashMap<String, HashMap<String, String>> detalii_jucatori = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_ui);
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        generate_formation_and_compatible_pisitions_maps();
        create_hashmap_from_json_string();


    }

    @Override
    public void onResume(){
        super.onResume();
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }}

    @Override
    protected void onStart()
    {
        super.onStart();

        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {

        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);

        }
    }

    private void generate_formation_and_compatible_pisitions_maps(){

        String[] pozitii_compatibile_st = {"cf", "rf", "lf"};
        String[] pozitii_compatibile_lw = {"lf", "lm", "lwb"};
        String[] pozitii_compatibile_rw = {"rf", "rm", "rwb"};
        String[] pozitii_compatibile_cam = {"cf", "cm", "cdm"};
        String[] pozitii_compatibile_cm = {"cam", "cdm", "lm", "rm"};
        String[] pozitii_compatibile_lb = {"cb", "lm", "lwb"};
        String[] pozitii_compatibile_rb = {"cb", "rm", "rwb"};
        String[] pozitii_compatibile_cb = {"lb", "rb", "cdm"};
        String[] pozitii_compatibile_cf = {"lf", "rf", "cam", "st"};
        String[] pozitii_compatibile_lf = {"rf", "cf", "st", "lw"};
        String[] pozitii_compatibile_rf = {"lf", "cf", "st", "rw"};
        String[] pozitii_compatibile_lm = {"lb", "lw", "cm"};
        String[] pozitii_compatibile_rm = {"rb", "rw", "cm"};
        String[] pozitii_compatibile_cdm = {"cb", "cam", "cm"};
        String[] pozitii_compatibile_lwb = {"lb", "lw"};
        String[] pozitii_compatibile_rwb = {"rb", "rw"};

        pozitii_compatibile.put("st", pozitii_compatibile_st);
        pozitii_compatibile.put("lw", pozitii_compatibile_lw);
        pozitii_compatibile.put("rw", pozitii_compatibile_rw);
        pozitii_compatibile.put("cam", pozitii_compatibile_cam);
        pozitii_compatibile.put("cm", pozitii_compatibile_cm);
        pozitii_compatibile.put("lb", pozitii_compatibile_lb);
        pozitii_compatibile.put("rb", pozitii_compatibile_rb);
        pozitii_compatibile.put("cb", pozitii_compatibile_cb);
        pozitii_compatibile.put("cf", pozitii_compatibile_cf);
        pozitii_compatibile.put("lf", pozitii_compatibile_lf);
        pozitii_compatibile.put("rf", pozitii_compatibile_rf);
        pozitii_compatibile.put("lm", pozitii_compatibile_lm);
        pozitii_compatibile.put("rm", pozitii_compatibile_rm);
        pozitii_compatibile.put("cdm", pozitii_compatibile_cdm);
        pozitii_compatibile.put("lwb", pozitii_compatibile_lwb);
        pozitii_compatibile.put("rwb", pozitii_compatibile_rwb);


        String[] f_433_attack_positions = {"lw", "st", "rw", "cam", "cm", "lb", "cb", "rb", "gk"};
        String[] f_433_defend_positions = {"lw", "st", "rw", "cdm", "cm", "lb", "cb", "rb", "gk"};
        String[] f_433_false9_positions = {"lw", "cf", "rw", "cdm", "cm", "lb", "cb", "rb", "gk"};
        String[] f_3142_positions = {"lm", "st", "rm", "cdm", "cm", "cb", "gk"};
        String[] f_523_positions = {"lw", "st", "rw", "lwb", "rwb", "cm", "cb", "gk"};

        pozitiile_formatiilor.put("f_433_attack", f_433_attack_positions);
        pozitiile_formatiilor.put("f_433_defend", f_433_defend_positions);
        pozitiile_formatiilor.put("f_433_false9", f_433_false9_positions);
        pozitiile_formatiilor.put("f_3142", f_3142_positions);
        pozitiile_formatiilor.put("f_523", f_523_positions);

    }

    protected String get_player_position_from_name(String name){
        String[] arrOfStr = name.split("_", 5);
        return arrOfStr[2];
    }

    protected String get_player_name_from_name(String name){
        String[] arrOfStr = name.split("_", 5);
        return arrOfStr[0];
    }

    protected String get_player_rating_from_name(String name){
        String[] arrOfStr = name.split("_", 5);
        return arrOfStr[1];
    }

    protected String get_player_promotion_from_name(String name){
        String[] arrOfStr = name.split("_", 5);
        return arrOfStr[3];
    }

    private void create_hashmap_from_json_string(){

        String json_string = "{\"alejandrogomez\": {\"NAME\": \"A. G\\u00f3mez\", \"BIRTH_DATE\": \"2/15/1988\", \"HEIGHT\": 165.0, \"WEIGHT\": 68.0, \"NATIONALITY\": \"Argentina\", \"CLUB\": \"Atalanta\", \"VALUE\": 34000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"OTHER\"}, \"alejandrogrimaldo\": {\"NAME\": \"Grimaldo\", \"BIRTH_DATE\": \"9/20/1995\", \"HEIGHT\": 171.0, \"WEIGHT\": 69.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"SL Benfica\", \"VALUE\": 29500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"OTHER\"}, \"alexsandro\": {\"NAME\": \"A. Golovin\", \"BIRTH_DATE\": \"5/30/1996\", \"HEIGHT\": 180.0, \"WEIGHT\": 69.0, \"NATIONALITY\": \"Russia\", \"CLUB\": \"AS Monaco\", \"VALUE\": 17500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Ligue 1\"}, \"alextelles\": {\"NAME\": \"Alex Telles\", \"BIRTH_DATE\": \"12/15/1992\", \"HEIGHT\": 181.0, \"WEIGHT\": 71.0, \"NATIONALITY\": \"Brazil\", \"CLUB\": \"FC Porto\", \"VALUE\": 33000000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"OTHER\"}, \"alissonbecker\": {\"NAME\": \"Alisson\", \"BIRTH_DATE\": \"10/2/1992\", \"HEIGHT\": 191.0, \"WEIGHT\": 91.0, \"NATIONALITY\": \"Brazil\", \"CLUB\": \"Liverpool\", \"VALUE\": 58000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 1.0, \"LEAGUE\": \"Premier League\"}, \"andrewrobertson\": {\"NAME\": \"A. Robertson\", \"BIRTH_DATE\": \"3/11/1994\", \"HEIGHT\": 178.0, \"WEIGHT\": 64.0, \"NATIONALITY\": \"Scotland\", \"CLUB\": \"Liverpool\", \"VALUE\": 43500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Premier League\"}, \"aubameyang\": {\"NAME\": \"P. Aubameyang\", \"BIRTH_DATE\": \"6/18/1989\", \"HEIGHT\": 187.0, \"WEIGHT\": 80.0, \"NATIONALITY\": \"Gabon\", \"CLUB\": \"Arsenal\", \"VALUE\": 57000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"azpilicueta\": {\"NAME\": \"Azpilicueta\", \"BIRTH_DATE\": \"8/28/1989\", \"HEIGHT\": 178.0, \"WEIGHT\": 76.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"Chelsea\", \"VALUE\": 25500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Premier League\"}, \"benzema\": {\"NAME\": \"K. Benzema\", \"BIRTH_DATE\": \"12/19/1987\", \"HEIGHT\": 185.0, \"WEIGHT\": 81.0, \"NATIONALITY\": \"France\", \"CLUB\": \"Real Madrid\", \"VALUE\": 45000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"bernardomotasilva\": {\"NAME\": \"Bernardo Silva\", \"BIRTH_DATE\": \"8/10/1994\", \"HEIGHT\": 173.0, \"WEIGHT\": 64.0, \"NATIONALITY\": \"Portugal\", \"CLUB\": \"Manchester City\", \"VALUE\": 64000000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"brunomiguelfernandes\": {\"NAME\": \"Bruno Fernandes\", \"BIRTH_DATE\": \"9/8/1994\", \"HEIGHT\": 179.0, \"WEIGHT\": 69.0, \"NATIONALITY\": \"Portugal\", \"CLUB\": \"Sporting CP\", \"VALUE\": 49000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"OTHER\"}, \"callejonbueno\": {\"NAME\": \"Jos\\u00e9 Callej\\u00f3n\", \"BIRTH_DATE\": \"2/11/1987\", \"HEIGHT\": 178.0, \"WEIGHT\": 73.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"Napoli\", \"VALUE\": 24000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"OTHER\"}, \"chiellini\": {\"NAME\": \"G. Chiellini\", \"BIRTH_DATE\": \"8/14/1984\", \"HEIGHT\": 187.0, \"WEIGHT\": 85.0, \"NATIONALITY\": \"Italy\", \"CLUB\": \"Juventus\", \"VALUE\": 24500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Serie A\"}, \"courtois\": {\"NAME\": \"T. Courtois\", \"BIRTH_DATE\": \"5/11/1992\", \"HEIGHT\": 199.0, \"WEIGHT\": 96.0, \"NATIONALITY\": \"Belgium\", \"CLUB\": \"Real Madrid\", \"VALUE\": 48000000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 1.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"cronaldodossantos\": {\"NAME\": \"Cristiano Ronaldo\", \"BIRTH_DATE\": \"2/5/1985\", \"HEIGHT\": 187.0, \"WEIGHT\": 83.0, \"NATIONALITY\": \"Portugal\", \"CLUB\": \"Juventus\", \"VALUE\": 58500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Serie A\"}, \"dacosta\": {\"NAME\": \"D. da Costa\", \"BIRTH_DATE\": \"7/13/1993\", \"HEIGHT\": 187.0, \"WEIGHT\": 85.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"Eintracht Frankfurt\", \"VALUE\": 13500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Bundesliga\"}, \"danielcarvajalramos\": {\"NAME\": \"Carvajal\", \"BIRTH_DATE\": \"1/11/1992\", \"HEIGHT\": 173.0, \"WEIGHT\": 73.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"Real Madrid\", \"VALUE\": 38000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"davidsilva\": {\"NAME\": \"David L\\u00f3pez\", \"BIRTH_DATE\": \"10/9/1989\", \"HEIGHT\": 183.0, \"WEIGHT\": 81.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"RCD Espanyol\", \"VALUE\": 10000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"OTHER\"}, \"debruyne\": {\"NAME\": \"K. De Bruyne\", \"BIRTH_DATE\": \"6/28/1991\", \"HEIGHT\": 181.0, \"WEIGHT\": 70.0, \"NATIONALITY\": \"Belgium\", \"CLUB\": \"Manchester City\", \"VALUE\": 90000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 5.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"dimaria\": {\"NAME\": \"A. Di Mar\\u00eda\", \"BIRTH_DATE\": \"2/14/1988\", \"HEIGHT\": 180.0, \"WEIGHT\": 69.0, \"NATIONALITY\": \"Argentina\", \"CLUB\": \"Paris Saint-Germain\", \"VALUE\": 39000000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Ligue 1\"}, \"doherty\": {\"NAME\": \"M. Doherty\", \"BIRTH_DATE\": \"1/16/1992\", \"HEIGHT\": 182.0, \"WEIGHT\": 87.0, \"NATIONALITY\": \"Republic of Ireland\", \"CLUB\": \"Wolverhampton Wanderers\", \"VALUE\": 10500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Premier League\"}, \"douglascosta\": {\"NAME\": \"Douglas Costa\", \"BIRTH_DATE\": \"9/14/1990\", \"HEIGHT\": 172.0, \"WEIGHT\": 70.0, \"NATIONALITY\": \"Brazil\", \"CLUB\": \"Juventus\", \"VALUE\": 31500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Serie A\"}, \"dybala\": {\"NAME\": \"P. Dybala\", \"BIRTH_DATE\": \"11/15/1993\", \"HEIGHT\": 177.0, \"WEIGHT\": 75.0, \"NATIONALITY\": \"Argentina\", \"CLUB\": \"Juventus\", \"VALUE\": 76500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Serie A\"}, \"edersonsantana\": {\"NAME\": \"Ederson\", \"BIRTH_DATE\": \"8/17/1993\", \"HEIGHT\": 188.0, \"WEIGHT\": 86.0, \"NATIONALITY\": \"Brazil\", \"CLUB\": \"Manchester City\", \"VALUE\": 54500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 1.0, \"LEAGUE\": \"Premier League\"}, \"eriksen\": {\"NAME\": \"C. Eriksen\", \"BIRTH_DATE\": \"2/14/1992\", \"HEIGHT\": 181.0, \"WEIGHT\": 76.0, \"NATIONALITY\": \"Denmark\", \"CLUB\": \"Tottenham Hotspur\", \"VALUE\": 68000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 5.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"fabiohenrique\": {\"NAME\": \"Fabinho\", \"BIRTH_DATE\": \"10/23/1993\", \"HEIGHT\": 188.0, \"WEIGHT\": 78.0, \"NATIONALITY\": \"Brazil\", \"CLUB\": \"Liverpool\", \"VALUE\": 45000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Premier League\"}, \"frenkiedejong\": {\"NAME\": \"F. de Jong\", \"BIRTH_DATE\": \"5/12/1997\", \"HEIGHT\": 180.0, \"WEIGHT\": 74.0, \"NATIONALITY\": \"Netherlands\", \"CLUB\": \"FC Barcelona\", \"VALUE\": 52000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"gerardpique\": {\"NAME\": \"Piqu\\u00e9\", \"BIRTH_DATE\": \"2/2/1987\", \"HEIGHT\": 194.0, \"WEIGHT\": 85.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"FC Barcelona\", \"VALUE\": 38000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"gnabry\": {\"NAME\": \"S. Gnabry\", \"BIRTH_DATE\": \"7/14/1995\", \"HEIGHT\": 175.0, \"WEIGHT\": 75.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"FC Bayern M\\u00fcnchen\", \"VALUE\": 39500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Bundesliga\"}, \"griezmann\": {\"NAME\": \"A. Griezmann\", \"BIRTH_DATE\": \"3/21/1991\", \"HEIGHT\": 176.0, \"WEIGHT\": 73.0, \"NATIONALITY\": \"France\", \"CLUB\": \"FC Barcelona\", \"VALUE\": 69000000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"handanovic\": {\"NAME\": \"S. Handanovi\\u010d\", \"BIRTH_DATE\": \"7/14/1984\", \"HEIGHT\": 193.0, \"WEIGHT\": 92.0, \"NATIONALITY\": \"Slovenia\", \"CLUB\": \"Inter\", \"VALUE\": 26000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 1.0, \"LEAGUE\": \"OTHER\"}, \"havertz\": {\"NAME\": \"K. Havertz\", \"BIRTH_DATE\": \"6/11/1999\", \"HEIGHT\": 188.0, \"WEIGHT\": 83.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"Bayer 04 Leverkusen\", \"VALUE\": 46000000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Bundesliga\"}, \"hazard\": {\"NAME\": \"E. Hazard\", \"BIRTH_DATE\": \"1/7/1991\", \"HEIGHT\": 175.0, \"WEIGHT\": 74.0, \"NATIONALITY\": \"Belgium\", \"CLUB\": \"Real Madrid\", \"VALUE\": 90000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"henderson\": {\"NAME\": \"J. Henderson\", \"BIRTH_DATE\": \"6/17/1990\", \"HEIGHT\": 182.0, \"WEIGHT\": 80.0, \"NATIONALITY\": \"England\", \"CLUB\": \"Liverpool\", \"VALUE\": 22000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Premier League\"}, \"henriquevenanciocasimiro\": {\"NAME\": \"Casemiro\", \"BIRTH_DATE\": \"2/23/1992\", \"HEIGHT\": 185.0, \"WEIGHT\": 84.0, \"NATIONALITY\": \"Brazil\", \"CLUB\": \"Real Madrid\", \"VALUE\": 53500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"heungminson\": {\"NAME\": \"H. Son\", \"BIRTH_DATE\": \"7/8/1992\", \"HEIGHT\": 183.0, \"WEIGHT\": 78.0, \"NATIONALITY\": \"Korea Republic\", \"CLUB\": \"Tottenham Hotspur\", \"VALUE\": 60000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 5.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"hummels\": {\"NAME\": \"M. Hummels\", \"BIRTH_DATE\": \"12/16/1988\", \"HEIGHT\": 191.0, \"WEIGHT\": 94.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"Borussia Dortmund\", \"VALUE\": 41000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Bundesliga\"}, \"illems\": {\"NAME\": \"P. Jones\", \"BIRTH_DATE\": \"2/21/1992\", \"HEIGHT\": 185.0, \"WEIGHT\": 71.0, \"NATIONALITY\": \"England\", \"CLUB\": \"Manchester United\", \"VALUE\": 12500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Premier League\"}, \"immobile\": {\"NAME\": \"C. Immobile\", \"BIRTH_DATE\": \"2/20/1990\", \"HEIGHT\": 185.0, \"WEIGHT\": 85.0, \"NATIONALITY\": \"Italy\", \"CLUB\": \"Lazio\", \"VALUE\": 44500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"OTHER\"}, \"insigne\": {\"NAME\": \"L. Insigne\", \"BIRTH_DATE\": \"6/4/1991\", \"HEIGHT\": 163.0, \"WEIGHT\": 59.0, \"NATIONALITY\": \"Italy\", \"CLUB\": \"Napoli\", \"VALUE\": 52000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"OTHER\"}, \"jesuscorona\": {\"NAME\": \"J. Corona\", \"BIRTH_DATE\": \"1/6/1993\", \"HEIGHT\": 173.0, \"WEIGHT\": 62.0, \"NATIONALITY\": \"Mexico\", \"CLUB\": \"FC Porto\", \"VALUE\": 21000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 5.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"OTHER\"}, \"jonny\": {\"NAME\": \"J. Evans\", \"BIRTH_DATE\": \"1/3/1988\", \"HEIGHT\": 188.0, \"WEIGHT\": 77.0, \"NATIONALITY\": \"Northern Ireland\", \"CLUB\": \"Leicester City\", \"VALUE\": 9500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Premier League\"}, \"jordialba\": {\"NAME\": \"Jordi Alba\", \"BIRTH_DATE\": \"3/21/1989\", \"HEIGHT\": 170.0, \"WEIGHT\": 68.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"FC Barcelona\", \"VALUE\": 40000000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"jorgeresurreccion\": {\"NAME\": \"Koke\", \"BIRTH_DATE\": \"1/8/1992\", \"HEIGHT\": 176.0, \"WEIGHT\": 74.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"Atl\\u00e9tico Madrid\", \"VALUE\": 40500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"kaderabek\": {\"NAME\": \"P. Kade\\u0159\\u00e1bek\", \"BIRTH_DATE\": \"4/25/1992\", \"HEIGHT\": 182.0, \"WEIGHT\": 81.0, \"NATIONALITY\": \"Czech Republic\", \"CLUB\": \"TSG 1899 Hoffenheim\", \"VALUE\": 14500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Bundesliga\"}, \"kane\": {\"NAME\": \"H. Kane\", \"BIRTH_DATE\": \"7/28/1993\", \"HEIGHT\": 188.0, \"WEIGHT\": 89.0, \"NATIONALITY\": \"England\", \"CLUB\": \"Tottenham Hotspur\", \"VALUE\": 83000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Premier League\"}, \"kante\": {\"NAME\": \"N. Kant\\u00e9\", \"BIRTH_DATE\": \"3/29/1991\", \"HEIGHT\": 168.0, \"WEIGHT\": 72.0, \"NATIONALITY\": \"France\", \"CLUB\": \"Chelsea\", \"VALUE\": 66000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Premier League\"}, \"kimmich\": {\"NAME\": \"J. Kimmich\", \"BIRTH_DATE\": \"2/8/1995\", \"HEIGHT\": 176.0, \"WEIGHT\": 73.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"FC Bayern M\\u00fcnchen\", \"VALUE\": 48000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Bundesliga\"}, \"kingsleycoman\": {\"NAME\": \"K. Coman\", \"BIRTH_DATE\": \"6/13/1996\", \"HEIGHT\": 179.0, \"WEIGHT\": 75.0, \"NATIONALITY\": \"France\", \"CLUB\": \"FC Bayern M\\u00fcnchen\", \"VALUE\": 40500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Bundesliga\"}, \"koulibaly\": {\"NAME\": \"K. Koulibaly\", \"BIRTH_DATE\": \"6/20/1991\", \"HEIGHT\": 187.0, \"WEIGHT\": 89.0, \"NATIONALITY\": \"Senegal\", \"CLUB\": \"Napoli\", \"VALUE\": 67500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"OTHER\"}, \"kroos\": {\"NAME\": \"T. Kroos\", \"BIRTH_DATE\": \"1/4/1990\", \"HEIGHT\": 183.0, \"WEIGHT\": 76.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"Real Madrid\", \"VALUE\": 57000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 5.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"lala\": {\"NAME\": \"K. Lala\", \"BIRTH_DATE\": \"10/3/1991\", \"HEIGHT\": 178.0, \"WEIGHT\": 78.0, \"NATIONALITY\": \"France\", \"CLUB\": \"RC Strasbourg Alsace\", \"VALUE\": 12500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"OTHER\"}, \"laporte\": {\"NAME\": \"A. Laporte\", \"BIRTH_DATE\": \"5/27/1994\", \"HEIGHT\": 189.0, \"WEIGHT\": 85.0, \"NATIONALITY\": \"France\", \"CLUB\": \"Manchester City\", \"VALUE\": 56500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Premier League\"}, \"lewandowski\": {\"NAME\": \"R. Lewandowski\", \"BIRTH_DATE\": \"8/21/1988\", \"HEIGHT\": 184.0, \"WEIGHT\": 80.0, \"NATIONALITY\": \"Poland\", \"CLUB\": \"FC Bayern M\\u00fcnchen\", \"VALUE\": 64500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Bundesliga\"}, \"luisalbertoromero\": {\"NAME\": \"Luis Alberto\", \"BIRTH_DATE\": \"9/28/1992\", \"HEIGHT\": 183.0, \"WEIGHT\": 74.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"Lazio\", \"VALUE\": 37500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"OTHER\"}, \"luismiguelafonsofernandes\": {\"NAME\": \"Pizzi\", \"BIRTH_DATE\": \"10/6/1989\", \"HEIGHT\": 177.0, \"WEIGHT\": 72.0, \"NATIONALITY\": \"Portugal\", \"CLUB\": \"SL Benfica\", \"VALUE\": 30500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"OTHER\"}, \"lukaku\": {\"NAME\": \"R. Lukaku\", \"BIRTH_DATE\": \"5/13/1993\", \"HEIGHT\": 190.0, \"WEIGHT\": 94.0, \"NATIONALITY\": \"Belgium\", \"CLUB\": \"Inter\", \"VALUE\": 46000000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"OTHER\"}, \"mahrez\": {\"NAME\": \"R. Mahrez\", \"BIRTH_DATE\": \"2/21/1991\", \"HEIGHT\": 179.0, \"WEIGHT\": 67.0, \"NATIONALITY\": \"Algeria\", \"CLUB\": \"Manchester City\", \"VALUE\": 31500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Premier League\"}, \"mane\": {\"NAME\": \"S. Man\\u00e9\", \"BIRTH_DATE\": \"4/10/1992\", \"HEIGHT\": 175.0, \"WEIGHT\": 69.0, \"NATIONALITY\": \"Senegal\", \"CLUB\": \"Liverpool\", \"VALUE\": 62000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"marcosaoas\": {\"NAME\": \"M. Acu\\u00f1a\", \"BIRTH_DATE\": \"10/28/1991\", \"HEIGHT\": 172.0, \"WEIGHT\": 69.0, \"NATIONALITY\": \"Argentina\", \"CLUB\": \"Sporting CP\", \"VALUE\": 18500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"OTHER\"}, \"mariofernandes\": {\"NAME\": \"M\\u00e1rio Fernandes\", \"BIRTH_DATE\": \"9/19/1990\", \"HEIGHT\": 187.0, \"WEIGHT\": 80.0, \"NATIONALITY\": \"Russia\", \"CLUB\": \"PFC CSKA Moscow\", \"VALUE\": 19000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"OTHER\"}, \"matthijsdeligt\": {\"NAME\": \"M. de Ligt\", \"BIRTH_DATE\": \"8/12/1999\", \"HEIGHT\": 189.0, \"WEIGHT\": 89.0, \"NATIONALITY\": \"Netherlands\", \"CLUB\": \"Juventus\", \"VALUE\": 50000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Serie A\"}, \"mbappe\": {\"NAME\": \"K. Mbapp\\u00e9\", \"BIRTH_DATE\": \"12/20/1998\", \"HEIGHT\": 178.0, \"WEIGHT\": 73.0, \"NATIONALITY\": \"France\", \"CLUB\": \"Paris Saint-Germain\", \"VALUE\": 93500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Ligue 1\"}, \"memphisdepay\": {\"NAME\": \"M. Depay\", \"BIRTH_DATE\": \"2/13/1994\", \"HEIGHT\": 176.0, \"WEIGHT\": 78.0, \"NATIONALITY\": \"Netherlands\", \"CLUB\": \"Olympique Lyonnais\", \"VALUE\": 40500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Ligue 1\"}, \"mertens\": {\"NAME\": \"D. Mertens\", \"BIRTH_DATE\": \"5/6/1987\", \"HEIGHT\": 169.0, \"WEIGHT\": 61.0, \"NATIONALITY\": \"Belgium\", \"CLUB\": \"Napoli\", \"VALUE\": 40000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"OTHER\"}, \"messi\": {\"NAME\": \"L. Messi\", \"BIRTH_DATE\": \"6/24/1987\", \"HEIGHT\": 170.0, \"WEIGHT\": 72.0, \"NATIONALITY\": \"Argentina\", \"CLUB\": \"FC Barcelona\", \"VALUE\": 95500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"milinkovicsavic\": {\"NAME\": \"S. Milinkovi\\u0107-Savi\\u0107\", \"BIRTH_DATE\": \"2/27/1995\", \"HEIGHT\": 191.0, \"WEIGHT\": 76.0, \"NATIONALITY\": \"Serbia\", \"CLUB\": \"Lazio\", \"VALUE\": 50500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"OTHER\"}, \"modric\": {\"NAME\": \"L. Modri\\u0107\", \"BIRTH_DATE\": \"9/9/1985\", \"HEIGHT\": 172.0, \"WEIGHT\": 66.0, \"NATIONALITY\": \"Croatia\", \"CLUB\": \"Real Madrid\", \"VALUE\": 45000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"muller\": {\"NAME\": \"T. M\\u00fcller\", \"BIRTH_DATE\": \"9/13/1989\", \"HEIGHT\": 186.0, \"WEIGHT\": 75.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"FC Bayern M\\u00fcnchen\", \"VALUE\": 43500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Bundesliga\"}, \"navas\": {\"NAME\": \"K. Navas\", \"BIRTH_DATE\": \"12/15/1986\", \"HEIGHT\": 185.0, \"WEIGHT\": 80.0, \"NATIONALITY\": \"Costa Rica\", \"CLUB\": \"Real Madrid\", \"VALUE\": 30500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 1.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"neuer\": {\"NAME\": \"M. Neuer\", \"BIRTH_DATE\": \"3/27/1986\", \"HEIGHT\": 193.0, \"WEIGHT\": 92.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"FC Bayern M\\u00fcnchen\", \"VALUE\": 32000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 1.0, \"LEAGUE\": \"Bundesliga\"}, \"neymarjr\": {\"NAME\": \"N. Amiri\", \"BIRTH_DATE\": \"10/27/1996\", \"HEIGHT\": 180.0, \"WEIGHT\": 73.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"Bayer 04 Leverkusen\", \"VALUE\": 14500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Bundesliga\"}, \"oblak\": {\"NAME\": \"J. Oblak\", \"BIRTH_DATE\": \"1/7/1993\", \"HEIGHT\": 188.0, \"WEIGHT\": 87.0, \"NATIONALITY\": \"Slovenia\", \"CLUB\": \"Atl\\u00e9tico Madrid\", \"VALUE\": 77500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 1.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"parejo\": {\"NAME\": \"Parejo\", \"BIRTH_DATE\": \"4/16/1989\", \"HEIGHT\": 182.0, \"WEIGHT\": 74.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"Valencia CF\", \"VALUE\": 41000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"pjanic\": {\"NAME\": \"M. Pjani\\u0107\", \"BIRTH_DATE\": \"4/2/1990\", \"HEIGHT\": 178.0, \"WEIGHT\": 72.0, \"NATIONALITY\": \"Bosnia Herzegovina\", \"CLUB\": \"Juventus\", \"VALUE\": 42500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Serie A\"}, \"pogba\": {\"NAME\": \"P. Pogba\", \"BIRTH_DATE\": \"3/15/1993\", \"HEIGHT\": 191.0, \"WEIGHT\": 84.0, \"NATIONALITY\": \"France\", \"CLUB\": \"Manchester United\", \"VALUE\": 72500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Premier League\"}, \"raphaelvarane\": {\"NAME\": \"R. Varane\", \"BIRTH_DATE\": \"4/25/1993\", \"HEIGHT\": 191.0, \"WEIGHT\": 81.0, \"NATIONALITY\": \"France\", \"CLUB\": \"Real Madrid\", \"VALUE\": 45000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"rashford\": {\"NAME\": \"M. Rashford\", \"BIRTH_DATE\": \"10/31/1997\", \"HEIGHT\": 186.0, \"WEIGHT\": 70.0, \"NATIONALITY\": \"England\", \"CLUB\": \"Manchester United\", \"VALUE\": 35500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Premier League\"}, \"reus\": {\"NAME\": \"M. Reus\", \"BIRTH_DATE\": \"5/31/1989\", \"HEIGHT\": 180.0, \"WEIGHT\": 71.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"Borussia Dortmund\", \"VALUE\": 56000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Bundesliga\"}, \"ricardopereira\": {\"NAME\": \"Ricardo Pereira\", \"BIRTH_DATE\": \"10/6/1993\", \"HEIGHT\": 175.0, \"WEIGHT\": 70.0, \"NATIONALITY\": \"Portugal\", \"CLUB\": \"Leicester City\", \"VALUE\": 23500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Premier League\"}, \"robertofirminobarbosa\": {\"NAME\": \"Roberto Firmino\", \"BIRTH_DATE\": \"10/2/1991\", \"HEIGHT\": 181.0, \"WEIGHT\": 76.0, \"NATIONALITY\": \"Brazil\", \"CLUB\": \"Liverpool\", \"VALUE\": 52000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"rodrigohernandez\": {\"NAME\": \"Rodri\", \"BIRTH_DATE\": \"6/22/1996\", \"HEIGHT\": 191.0, \"WEIGHT\": 82.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"Manchester City\", \"VALUE\": 47000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Premier League\"}, \"salah\": {\"NAME\": \"M. Salah\", \"BIRTH_DATE\": \"6/15/1992\", \"HEIGHT\": 175.0, \"WEIGHT\": 71.0, \"NATIONALITY\": \"Egypt\", \"CLUB\": \"Liverpool\", \"VALUE\": 80500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"sancho\": {\"NAME\": \"J. Sancho\", \"BIRTH_DATE\": \"3/25/2000\", \"HEIGHT\": 180.0, \"WEIGHT\": 76.0, \"NATIONALITY\": \"England\", \"CLUB\": \"Borussia Dortmund\", \"VALUE\": 44500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Bundesliga\"}, \"sane\": {\"NAME\": \"S. Man\\u00e9\", \"BIRTH_DATE\": \"4/10/1992\", \"HEIGHT\": 175.0, \"WEIGHT\": 69.0, \"NATIONALITY\": \"Senegal\", \"CLUB\": \"Liverpool\", \"VALUE\": 62000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"sequeira\": {\"NAME\": \"Jo\\u00e3o F\\u00e9lix\", \"BIRTH_DATE\": \"11/10/1999\", \"HEIGHT\": 181.0, \"WEIGHT\": 70.0, \"NATIONALITY\": \"Portugal\", \"CLUB\": \"Atl\\u00e9tico Madrid\", \"VALUE\": 28000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"sergioaguero\": {\"NAME\": \"S. Ag\\u00fcero\", \"BIRTH_DATE\": \"6/2/1988\", \"HEIGHT\": 173.0, \"WEIGHT\": 70.0, \"NATIONALITY\": \"Argentina\", \"CLUB\": \"Manchester City\", \"VALUE\": 60000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"sergiobusquets\": {\"NAME\": \"Sergio Busquets\", \"BIRTH_DATE\": \"7/16/1988\", \"HEIGHT\": 189.0, \"WEIGHT\": 76.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"FC Barcelona\", \"VALUE\": 55000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"sergioramos\": {\"NAME\": \"S. Romero\", \"BIRTH_DATE\": \"2/22/1987\", \"HEIGHT\": 192.0, \"WEIGHT\": 86.0, \"NATIONALITY\": \"Argentina\", \"CLUB\": \"Manchester United\", \"VALUE\": 9000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 1.0, \"LEAGUE\": \"Premier League\"}, \"silva\": {\"NAME\": \"D. Da Silva\", \"BIRTH_DATE\": \"5/17/1988\", \"HEIGHT\": 184.0, \"WEIGHT\": 82.0, \"NATIONALITY\": \"France\", \"CLUB\": \"Stade Rennais FC\", \"VALUE\": 7500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Ligue 1\"}, \"sterling\": {\"NAME\": \"R. Sterling\", \"BIRTH_DATE\": \"12/8/1994\", \"HEIGHT\": 170.0, \"WEIGHT\": 69.0, \"NATIONALITY\": \"England\", \"CLUB\": \"Manchester City\", \"VALUE\": 73000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"stevens\": {\"NAME\": \"S. Berghuis\", \"BIRTH_DATE\": \"12/19/1991\", \"HEIGHT\": 182.0, \"WEIGHT\": 75.0, \"NATIONALITY\": \"Netherlands\", \"CLUB\": \"Feyenoord\", \"VALUE\": 15500000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"OTHER\"}, \"suarez\": {\"NAME\": \"L. Su\\u00e1rez\", \"BIRTH_DATE\": \"1/24/1987\", \"HEIGHT\": 182.0, \"WEIGHT\": 86.0, \"NATIONALITY\": \"Uruguay\", \"CLUB\": \"FC Barcelona\", \"VALUE\": 53000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"szczesny\": {\"NAME\": \"W. Szcz\\u0119sny\", \"BIRTH_DATE\": \"4/18/1990\", \"HEIGHT\": 195.0, \"WEIGHT\": 90.0, \"NATIONALITY\": \"Poland\", \"CLUB\": \"Juventus\", \"VALUE\": 37500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 1.0, \"LEAGUE\": \"Serie A\"}, \"terstegen\": {\"NAME\": \"M. ter Stegen\", \"BIRTH_DATE\": \"4/30/1992\", \"HEIGHT\": 187.0, \"WEIGHT\": 85.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"FC Barcelona\", \"VALUE\": 67500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 1.0, \"LEAGUE\": \"Primera Divisi\\u00f3n\"}, \"thiago\": {\"NAME\": \"Thiago\", \"BIRTH_DATE\": \"4/11/1991\", \"HEIGHT\": 174.0, \"WEIGHT\": 70.0, \"NATIONALITY\": \"Spain\", \"CLUB\": \"FC Bayern M\\u00fcnchen\", \"VALUE\": 50000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 5.0, \"LEAGUE\": \"Bundesliga\"}, \"trentalexanderarnold\": {\"NAME\": \"T. Alexander-Arnold\", \"BIRTH_DATE\": \"10/7/1998\", \"HEIGHT\": 180.0, \"WEIGHT\": 69.0, \"NATIONALITY\": \"England\", \"CLUB\": \"Liverpool\", \"VALUE\": 32000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Premier League\"}, \"vandijk\": {\"NAME\": \"V. van Dijk\", \"BIRTH_DATE\": \"7/8/1991\", \"HEIGHT\": 193.0, \"WEIGHT\": 92.0, \"NATIONALITY\": \"Netherlands\", \"CLUB\": \"Liverpool\", \"VALUE\": 78000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Premier League\"}, \"vardy\": {\"NAME\": \"J. Vardy\", \"BIRTH_DATE\": \"1/11/1987\", \"HEIGHT\": 179.0, \"WEIGHT\": 74.0, \"NATIONALITY\": \"England\", \"CLUB\": \"Leicester City\", \"VALUE\": 17500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Premier League\"}, \"veratti\": {\"NAME\": \"M. Verratti\", \"BIRTH_DATE\": \"11/5/1992\", \"HEIGHT\": 165.0, \"WEIGHT\": 60.0, \"NATIONALITY\": \"Italy\", \"CLUB\": \"Paris Saint-Germain\", \"VALUE\": 54500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Ligue 1\"}, \"walker\": {\"NAME\": \"K. Walker\", \"BIRTH_DATE\": \"5/28/1990\", \"HEIGHT\": 183.0, \"WEIGHT\": 70.0, \"NATIONALITY\": \"England\", \"CLUB\": \"Manchester City\", \"VALUE\": 25500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 2.0, \"LEAGUE\": \"Premier League\"}, \"werner\": {\"NAME\": \"T. Werner\", \"BIRTH_DATE\": \"3/6/1996\", \"HEIGHT\": 180.0, \"WEIGHT\": 75.0, \"NATIONALITY\": \"Germany\", \"CLUB\": \"RB Leipzig\", \"VALUE\": 34500000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 4.0, \"SKILL_MOVES\": 3.0, \"LEAGUE\": \"Bundesliga\"}, \"wijnaldum\": {\"NAME\": \"G. Wijnaldum\", \"BIRTH_DATE\": \"11/11/1990\", \"HEIGHT\": 175.0, \"WEIGHT\": 69.0, \"NATIONALITY\": \"Netherlands\", \"CLUB\": \"Liverpool\", \"VALUE\": 31000000.0, \"MAIN_FOOT\": \"Right\", \"WEAK_FOOT\": 3.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"Premier League\"}, \"ziyech\": {\"NAME\": \"H. Ziyech\", \"BIRTH_DATE\": \"3/19/1993\", \"HEIGHT\": 181.0, \"WEIGHT\": 65.0, \"NATIONALITY\": \"Morocco\", \"CLUB\": \"Ajax\", \"VALUE\": 45000000.0, \"MAIN_FOOT\": \"Left\", \"WEAK_FOOT\": 2.0, \"SKILL_MOVES\": 4.0, \"LEAGUE\": \"OTHER\"}}";

        JsonReader key_reader = new JsonReader(new StringReader(json_string));
        try {

            key_reader.beginObject();
            while(key_reader.hasNext())
            {
                String player_name = key_reader.nextName();
                key_reader.beginObject();

                HashMap<String, String> player_attributes = new HashMap<>();
                while(key_reader.hasNext())
                {
                    String atribut = key_reader.nextName();
                    switch (atribut) {
                        case "NAME":
                            player_attributes.put("NAME", key_reader.nextString());
                            break;
                        case "BIRTH_DATE":
                            player_attributes.put("BIRTH_DATE", key_reader.nextString());
                            break;
                        case "HEIGHT":
                            player_attributes.put("HEIGHT", key_reader.nextString());
                            break;
                        case "WEIGHT":
                            player_attributes.put("WEIGHT", key_reader.nextString());
                            break;
                        case "NATIONALITY":
                            player_attributes.put("NATIONALITY", key_reader.nextString());
                            break;
                        case "CLUB":
                            player_attributes.put("CLUB", key_reader.nextString());
                            break;
                        case "VALUE":
                            player_attributes.put("VALUE", key_reader.nextString());
                            break;
                        case "MAIN_FOOT":
                            player_attributes.put("MAIN_FOOT", key_reader.nextString());
                            break;
                        case "WEAK_FOOT":
                            player_attributes.put("WEAK_FOOT", key_reader.nextString());
                            break;
                        case "SKILL_MOVES":
                            player_attributes.put("SKILL_MOVES", key_reader.nextString());
                            break;
                        case "LEAGUE":
                            player_attributes.put("LEAGUE", key_reader.nextString());
                            break;
                    }
                }
                key_reader.endObject();
                detalii_jucatori.put(player_name, player_attributes);
            }
            key_reader.endObject();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}