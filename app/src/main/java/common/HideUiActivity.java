package common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.squadverse.R;

public class HideUiActivity extends AppCompatActivity {

    // formations database
    protected final String[] mid_formations = {"f_433_attack", "f_433_defend", "f_433_false9"};
    protected final String[] att_formations = {"f_3142"};
    protected final String[] def_formations = {"f_523"};

    // player cards database
    protected final String[] all_cards = {"alexanderarnold_87_rb_base", "alexsandro_85_lb_base", "alisson_90_gk_base", "benzema_89_cf_base", "bruno_87_cam_base", "carvajal_86_rb_base", "casemiro_89_cdm_base", "debruyne_91_cam_base", "dimaria_87_rw_base", "dybala_88_cf_base", "firmino_87_cf_base", "gnabry_85_rm_base", "hazard_88_lw_base", "jordialba_86_lb_base", "kante_88_cdm_base", "kimmich_88_cdm_base", "koulibaly_88_cb_base", "kroos_88_cm_base", "laporte_87_cb_base", "lewandowski_91_st_base", "mane_90_lw_base", "mbappe_90_st_base", "messi_93_rw_base", "modric_87_cm_base", "muller_86_cam_base", "neymar_91_lw_base", "oblak_91_gk_base", "pogba_86_cm_base", "ramos_89_cb_base", "rashford_85_lm_base", "ricardopereira_85_rb_base", "robertson_87_lb_base", "ronaldo_92_st_base", "salah_90_rw_base", "sancho_87_rm_base", "sane_85_lm_base", "son_87_lm_base", "terstegen_90_gk_base", "vandijk_90_cb_base"};
    protected final String[] def_cards = {"alexanderarnold_87_rb_base", "alexsandro_85_lb_base", "carvajal_86_rb_base", "jordialba_86_lb_base", "koulibaly_88_cb_base", "laporte_87_cb_base", "ramos_89_cb_base", "ricardopereira_85_rb_base", "robertson_87_lb_base", "vandijk_90_cb_base"};
    protected final String[] mid_cards = {"bruno_87_cam_base", "casemiro_89_cdm_base", "debruyne_91_cam_base", "gnabry_85_rm_base", "kante_88_cdm_base", "kimmich_88_cdm_base", "kroos_88_cm_base", "modric_87_cm_base", "muller_86_cam_base", "pogba_86_cm_base", "rashford_85_lm_base", "sancho_87_rm_base", "sane_85_lm_base", "son_87_lm_base"};
    protected final String[] att_cards = {"benzema_89_cf_base", "dimaria_87_rw_base", "dybala_88_cf_base", "firmino_87_cf_base", "hazard_88_lw_base", "lewandowski_91_st_base", "mane_90_lw_base", "mbappe_90_st_base", "messi_93_rw_base", "neymar_91_lw_base", "ronaldo_92_st_base", "salah_90_rw_base"};

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
}