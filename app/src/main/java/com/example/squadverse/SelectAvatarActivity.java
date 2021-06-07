package com.example.squadverse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import common.BaseActivity;

public class SelectAvatarActivity extends BaseActivity {

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
            }
        });

        barcelona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "barcelona");
                startActivity(intent);
                
            }
        });

        atletico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "atletico");
                startActivity(intent);
                
            }
        });

        sevilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "sevilla");
                startActivity(intent);
                
            }
        });

        valencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "valencia");
                startActivity(intent);
                
            }
        });

        united.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "united");
                startActivity(intent);
                
            }
        });

        chelsea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "chelsea");
                startActivity(intent);
                
            }
        });

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "city");
                startActivity(intent);
                
            }
        });

        spurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "spurs");
                startActivity(intent);
                
            }
        });

        liverpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "liverpool");
                startActivity(intent);
                
            }
        });

        bayern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "bayern");
                startActivity(intent);
                
            }
        });

        dortmund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "dortmund");
                startActivity(intent);
                
            }
        });

        shalke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "shalke");
                startActivity(intent);
                
            }
        });

        leipzig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "leipzig");
                startActivity(intent);
                
            }
        });

        monchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "monchen");
                startActivity(intent);
                
            }
        });

        juventus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "juventus");
                startActivity(intent);
                
            }
        });

        milan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "milan");
                startActivity(intent);
                
            }
        });

        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "inter");
                startActivity(intent);
                
            }
        });

        lazio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "lazio");
                startActivity(intent);
                
            }
        });

        roma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "roma");
                startActivity(intent);
                
            }
        });

        psg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "psg");
                startActivity(intent);
                
            }
        });

        marseille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "marseille");
                startActivity(intent);
                
            }
        });

        lyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "lyon");
                startActivity(intent);
                
            }
        });

        monaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "monaco");
                startActivity(intent);
                
            }
        });

        nice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "nice");
                startActivity(intent);
                
            }
        });

        brasov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "brasov");
                startActivity(intent);
                
            }
        });

        steaua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "steaua");
                startActivity(intent);
                
            }
        });

        cfr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "cfr");
                startActivity(intent);
                
            }
        });

        craiova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "craiova");
                startActivity(intent);
                
            }
        });

        dinamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAvatarActivity.this, RegisterActivity.class);
                intent.putExtra("avatar", "dinamo");
                startActivity(intent);
                
            }
        });

    }
}