package common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.squadverse.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseActivity extends AppCompatActivity {

    // formations database
    protected final String[] all_formations = {"f_433_attack", "f_433_defend", "f_433_false9", "f_3142", "f_523"};
    protected final String[] mid_formations = {"f_433_attack", "f_433_defend", "f_433_false9"};
    protected final String[] att_formations = {"f_3142"};
    protected final String[] def_formations = {"f_523"};

    // player cards database
    protected final String[] all_cards = {"aguero_89_st_base", "alexanderarnold_87_rb_base", "alexsandro_85_lb_base", "alisson_90_gk_base", "aubameyang_87_st_base", "azpilicueta_84_rb_base", "benzema_89_cf_base", "bernardosilva_87_rw_base", "bruno_87_cam_base", "callejon_84_rm_base", "carvajal_86_rb_base", "casemiro_89_cdm_base", "chiellini_87_cb_base", "coman_84_lm_base", "corona_84_rm_base", "costa_84_lm_base", "courtois_89_gk_base", "dacosta_78_rwb_base", "davidsilva_86_cam_base", "debruyne_91_cam_base", "dejong_85_cm_base", "deligt_85_cb_base", "depay_85_cf_base", "dimaria_87_rw_base", "doherty_81_rwb_base", "dybala_88_cf_base", "ederson_88_gk_base", "eriksen_85_cam_base", "fabinho_87_cdm_base", "fernandes_82_rwb_base", "firmino_87_cf_base", "gnabry_85_rm_base", "gomez_86_cam_base", "griezmann_87_st_base", "grimaldo_84_lb_base", "handanovic_88_gk_base", "havertz_85_cam_base", "hazard_88_lw_base", "henderson_86_cdm_base", "hummels_86_cb_base", "illems_77_lwb_base", "immobile_87_st_base", "insigne_85_lw_base", "jonny_81_lwb_base", "jordialba_86_lb_base", "kaderabek_80_rwb_base", "kane_88_st_base", "kante_88_cdm_base", "kimmich_88_cdm_base", "koke_85_cm_base", "koulibaly_88_cb_base", "kroos_88_cm_base", "lala_79_rwb_base", "laporte_87_cb_base", "lewandowski_91_st_base", "luisalberto_85_cam_base", "lukaku_85_st_base", "mahrez_85_rw_base", "mane_90_lw_base", "marquinhos_85_cb_base", "mbappe_90_st_base", "mertens_85_cf_base", "messi_93_rw_base", "milinkovicsavic_85_cm_base", "modric_87_cm_base", "muller_86_cam_base", "navas_87_gk_base", "neuer_89_gk_base", "neymar_91_lw_base", "oblak_91_gk_base", "parejo_85_cm_base", "pique_86_cb_base", "pizzi_84_rm_base", "pjanic_85_cm_base", "pogba_86_cm_base", "ramos_89_cb_base", "rashford_85_lm_base", "reus_85_cam_base", "ricardopereira_85_rb_base", "robertson_87_lb_base", "rodri_85_cdm_base", "ronaldo_92_st_base", "salah_90_rw_base", "sancho_87_rm_base", "sane_85_lm_base", "sequeira_78_lwb_base", "sergiobusquets_87_cdm_base", "silva_75_lwb_base", "son_87_lm_base", "sterling_88_lw_base", "stevens_78_lwb_base", "suarez_87_st_base", "szczesny_87_gk_base", "telles_84_lb_base", "terstegen_90_gk_base", "thiago_85_cm_base", "vandijk_90_cb_base", "varane_86_cb_base", "vardy_86_st_base", "veratti_86_cm_base", "walker_85_rb_base", "werner_85_st_base", "wijnaldum_85_cm_base", "ziyech_85_cam_base"};
    protected final String[] def_cards = {"alexanderarnold_87_rb_base", "alexsandro_85_lb_base", "azpilicueta_84_rb_base", "carvajal_86_rb_base", "chiellini_87_cb_base", "dacosta_78_rwb_base", "deligt_85_cb_base", "doherty_81_rwb_base", "fernandes_82_rwb_base", "grimaldo_84_lb_base", "hummels_86_cb_base", "illems_77_lwb_base", "jonny_81_lwb_base", "jordialba_86_lb_base", "kaderabek_80_rwb_base", "koulibaly_88_cb_base", "lala_79_rwb_base", "laporte_87_cb_base", "marquinhos_85_cb_base", "pique_86_cb_base", "ramos_89_cb_base", "ricardopereira_85_rb_base", "robertson_87_lb_base", "sequeira_78_lwb_base", "silva_75_lwb_base", "stevens_78_lwb_base", "telles_84_lb_base", "vandijk_90_cb_base", "varane_86_cb_base", "walker_85_rb_base"};
    protected final String[] mid_cards = {"bruno_87_cam_base", "callejon_84_rm_base", "casemiro_89_cdm_base", "coman_84_lm_base", "corona_84_rm_base", "costa_84_lm_base", "davidsilva_86_cam_base", "debruyne_91_cam_base", "dejong_85_cm_base", "eriksen_85_cam_base", "fabinho_87_cdm_base", "gnabry_85_rm_base", "gomez_86_cam_base", "havertz_85_cam_base", "henderson_86_cdm_base", "kante_88_cdm_base", "kimmich_88_cdm_base", "koke_85_cm_base", "kroos_88_cm_base", "luisalberto_85_cam_base", "milinkovicsavic_85_cm_base", "modric_87_cm_base", "muller_86_cam_base", "parejo_85_cm_base", "pizzi_84_rm_base", "pjanic_85_cm_base", "pogba_86_cm_base", "rashford_85_lm_base", "reus_85_cam_base", "rodri_85_cdm_base", "sancho_87_rm_base", "sane_85_lm_base", "sergiobusquets_87_cdm_base", "son_87_lm_base", "thiago_85_cm_base", "veratti_86_cm_base", "wijnaldum_85_cm_base", "ziyech_85_cam_base"};
    protected final String[] att_cards = {"aguero_89_st_base", "aubameyang_87_st_base", "benzema_89_cf_base", "bernardosilva_87_rw_base", "depay_85_cf_base", "dimaria_87_rw_base", "dybala_88_cf_base", "firmino_87_cf_base", "griezmann_87_st_base", "hazard_88_lw_base", "immobile_87_st_base", "insigne_85_lw_base", "kane_88_st_base", "lewandowski_91_st_base", "lukaku_85_st_base", "mahrez_85_rw_base", "mane_90_lw_base", "mbappe_90_st_base", "mertens_85_cf_base", "messi_93_rw_base", "neymar_91_lw_base", "ronaldo_92_st_base", "salah_90_rw_base", "sterling_88_lw_base", "suarez_87_st_base", "vardy_86_st_base", "werner_85_st_base"};
    protected final String[] gk_cards = {"alisson_90_gk_base", "courtois_89_gk_base", "ederson_88_gk_base", "handanovic_88_gk_base", "navas_87_gk_base", "neuer_89_gk_base", "oblak_91_gk_base", "szczesny_87_gk_base", "terstegen_90_gk_base"};
    protected final String[] st_cards = {"aguero_89_st_base", "aubameyang_87_st_base", "griezmann_87_st_base", "immobile_87_st_base", "kane_88_st_base", "lewandowski_91_st_base", "lukaku_85_st_base", "mbappe_90_st_base", "ronaldo_92_st_base", "suarez_87_st_base", "vardy_86_st_base", "werner_85_st_base"};
    protected final String[] lw_cards = {"hazard_88_lw_base", "insigne_85_lw_base", "mane_90_lw_base", "neymar_91_lw_base", "sterling_88_lw_base"};
    protected final String[] rw_cards = {"bernardosilva_87_rw_base", "dimaria_87_rw_base", "mahrez_85_rw_base", "messi_93_rw_base", "salah_90_rw_base"};
    protected final String[] cam_cards = {"bruno_87_cam_base", "davidsilva_86_cam_base", "debruyne_91_cam_base", "eriksen_85_cam_base", "gomez_86_cam_base", "havertz_85_cam_base", "luisalberto_85_cam_base", "muller_86_cam_base", "reus_85_cam_base", "ziyech_85_cam_base"};
    protected final String[] cm_cards = {"dejong_85_cm_base", "koke_85_cm_base", "kroos_88_cm_base", "milinkovicsavic_85_cm_base", "modric_87_cm_base", "parejo_85_cm_base", "pjanic_85_cm_base", "pogba_86_cm_base", "thiago_85_cm_base", "veratti_86_cm_base", "wijnaldum_85_cm_base"};
    protected final String[] lb_cards = {"alexsandro_85_lb_base", "grimaldo_84_lb_base", "jordialba_86_lb_base", "robertson_87_lb_base", "telles_84_lb_base"};
    protected final String[] rb_cards = {"alexanderarnold_87_rb_base", "azpilicueta_84_rb_base", "carvajal_86_rb_base", "ricardopereira_85_rb_base", "walker_85_rb_base"};
    protected final String[] cb_cards = {"chiellini_87_cb_base", "deligt_85_cb_base", "hummels_86_cb_base", "koulibaly_88_cb_base", "laporte_87_cb_base", "marquinhos_85_cb_base", "pique_86_cb_base", "ramos_89_cb_base", "vandijk_90_cb_base", "varane_86_cb_base"};
    protected final String[] cf_cards = {"benzema_89_cf_base", "depay_85_cf_base", "dybala_88_cf_base", "firmino_87_cf_base", "mertens_85_cf_base"};
    protected final String[] lf_cards = {};
    protected final String[] rf_cards = {};
    protected final String[] lm_cards = {"coman_84_lm_base", "costa_84_lm_base", "rashford_85_lm_base", "sane_85_lm_base", "son_87_lm_base"};
    protected final String[] rm_cards = {"callejon_84_rm_base", "corona_84_rm_base", "gnabry_85_rm_base", "pizzi_84_rm_base", "sancho_87_rm_base"};
    protected final String[] cdm_cards = {"casemiro_89_cdm_base", "fabinho_87_cdm_base", "henderson_86_cdm_base", "kante_88_cdm_base", "kimmich_88_cdm_base", "rodri_85_cdm_base", "sergiobusquets_87_cdm_base"};
    protected final String[] lwb_cards = {"illems_77_lwb_base", "jonny_81_lwb_base", "sequeira_78_lwb_base", "silva_75_lwb_base", "stevens_78_lwb_base"};
    protected final String[] rwb_cards = {"dacosta_78_rwb_base", "doherty_81_rwb_base", "fernandes_82_rwb_base", "kaderabek_80_rwb_base", "lala_79_rwb_base"};

    //formation positions database
    protected HashMap<String, String[]> pozitiile_formatiilor=new HashMap<>();

    //compatible positions database
    protected HashMap<String, String[]> pozitii_compatibile=new HashMap<>();

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
}