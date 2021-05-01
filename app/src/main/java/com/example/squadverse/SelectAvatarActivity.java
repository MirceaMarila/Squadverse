package com.example.squadverse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import common.HideUiActivity;

public class SelectAvatarActivity extends HideUiActivity {

    //variabile
    ImageButton real, barcelona, atletico, sevilla, valencia, united, chelsea, city, spurs, liverpool, bayern, dortmund, shalke, leipzig, monchen;
    ImageButton juventus, milan, inter, lazio, roma, psg, marseille, lyon, monaco, nice, brasov, steaua, cfr, craiova, dinamo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);

        real = findViewById(R.id.real);
        barcelona = findViewById(R.id.barcelona);
        atletico = findViewById(R.id.atletico);
        sevilla = findViewById(R.id.sevilla);
        valencia = findViewById(R.id.valencia);

        united = findViewById(R.id.united);
        chelsea = findViewById(R.id.chelsea);
        city  = findViewById(R.id.city);
        spurs = findViewById(R.id.spurs);
        liverpool = findViewById(R.id.liverpool);

        bayern = findViewById(R.id.bayern);
        dortmund = findViewById(R.id.dortmund);
        shalke = findViewById(R.id.shalke);
        leipzig = findViewById(R.id.leipzig);
        monchen = findViewById(R.id.monchen);

        juventus = findViewById(R.id.juventus);
        milan = findViewById(R.id.milan);
        inter = findViewById(R.id.inter);
        lazio = findViewById(R.id.lazio);
        roma = findViewById(R.id.roma);

        psg = findViewById(R.id.psg);
        marseille = findViewById(R.id.marseille);
        lyon = findViewById(R.id.lyon);
        monaco = findViewById(R.id.monaco);
        nice = findViewById(R.id.nice);

        brasov = findViewById(R.id.brasov);
        steaua = findViewById(R.id.steaua);
        cfr = findViewById(R.id.cfr);
        craiova = findViewById(R.id.craiova);
        dinamo = findViewById(R.id.dinamo);

        real.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "real");
                startActivity(intent);
                finish();

            }
        });

        barcelona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "barcelona");
                startActivity(intent);
                finish();
            }
        });

        atletico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "atletico");
                startActivity(intent);
                finish();
            }
        });

        sevilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "sevilla");
                startActivity(intent);
                finish();
            }
        });

        valencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "valencia");
                startActivity(intent);
                finish();
            }
        });

        united.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "united");
                startActivity(intent);
                finish();
            }
        });

        chelsea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "chelsea");
                startActivity(intent);
                finish();
            }
        });

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "city");
                startActivity(intent);
                finish();
            }
        });

        spurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "spurs");
                startActivity(intent);
                finish();
            }
        });

        liverpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "liverpool");
                startActivity(intent);
                finish();
            }
        });

        bayern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "bayern");
                startActivity(intent);
                finish();
            }
        });

        dortmund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "dortmund");
                startActivity(intent);
                finish();
            }
        });

        shalke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "shalke");
                startActivity(intent);
                finish();
            }
        });

        leipzig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "leipzig");
                startActivity(intent);
                finish();
            }
        });

        monchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "monchen");
                startActivity(intent);
                finish();
            }
        });

        juventus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "juventus");
                startActivity(intent);
                finish();
            }
        });

        milan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "milan");
                startActivity(intent);
                finish();
            }
        });

        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "inter");
                startActivity(intent);
                finish();
            }
        });

        lazio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "lazio");
                startActivity(intent);
                finish();
            }
        });

        roma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "roma");
                startActivity(intent);
                finish();
            }
        });

        psg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "psg");
                startActivity(intent);
                finish();
            }
        });

        marseille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "marseille");
                startActivity(intent);
                finish();
            }
        });

        lyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "lyon");
                startActivity(intent);
                finish();
            }
        });

        monaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "monaco");
                startActivity(intent);
                finish();
            }
        });

        nice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "nice");
                startActivity(intent);
                finish();
            }
        });

        brasov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "brasov");
                startActivity(intent);
                finish();
            }
        });

        steaua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "steaua");
                startActivity(intent);
                finish();
            }
        });

        cfr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "cfr");
                startActivity(intent);
                finish();
            }
        });

        craiova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "craiova");
                startActivity(intent);
                finish();
            }
        });

        dinamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "dinamo");
                startActivity(intent);
                finish();
            }
        });

    }
}