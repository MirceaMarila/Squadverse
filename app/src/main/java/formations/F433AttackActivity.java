package formations;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squadverse.MainActivity;
import com.example.squadverse.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import common.BaseActivity;

import static java.lang.Character.UnicodeBlock.ARROWS;

public class F433AttackActivity extends BaseActivity {

    TextView slide_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f433_attack);

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


    }
}