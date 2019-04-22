package com.arba.orilampung;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ProfilOmbudsman extends AppCompatActivity {

    LinearLayout layout1, layout3, layout8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_ombudsman);

        LinearLayout layout8 = (LinearLayout) findViewById(R.id.layout8);
        LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
        LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);


        layout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilOmbudsman.this, ActivityFAQ.class);
                startActivity(intent);
            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilOmbudsman.this, SekilasOmbudsmanActivity.class);
                startActivity(intent);
            }
        });

        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilOmbudsman.this, VisiMisiActivity.class);
                startActivity(intent);
            }
        });

    }
}
