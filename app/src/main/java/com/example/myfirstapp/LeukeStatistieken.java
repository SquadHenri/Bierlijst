package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class LeukeStatistieken extends AppCompatActivity {

    private String B1;
    private String B1_Display;
    private String B2;
    private String B2_Display;
    private String B3;
    private String B3_Display;
    private String B4;
    private String B4_Display;
    private String B5;
    private String B5_Display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leuke_statistieken);

        UpdateBs();

        updateGooiTable();
    }

    @Override
    protected void onResume() {
        super.onResume();

        UpdateBs();
        updateGooiTable();
    }

    private void UpdateBs()
    {
        SharedPreferences sharedPrefs = getSharedPreferences(MMDatabase.bewonersVolgorde, MODE_PRIVATE);
        B1 = sharedPrefs.getString("B1", "");
        B2 = sharedPrefs.getString("B2", "");
        B3 = sharedPrefs.getString("B3", "");
        B4 = sharedPrefs.getString("B4", "");
        B5 = sharedPrefs.getString("B5", "");

        MMDatabase db = MMDatabase.getInstance(getApplicationContext());

        B1_Display = db.getBewonerDAO().getIsvG(B1) ? db.getBewonerDAO().getvGNaam(B1) : B1;
        B2_Display = db.getBewonerDAO().getIsvG(B2) ? db.getBewonerDAO().getvGNaam(B2) : B2;
        B3_Display = db.getBewonerDAO().getIsvG(B3) ? db.getBewonerDAO().getvGNaam(B3) : B3;
        B4_Display = db.getBewonerDAO().getIsvG(B4) ? db.getBewonerDAO().getvGNaam(B4) : B4;
        B5_Display = db.getBewonerDAO().getIsvG(B5) ? db.getBewonerDAO().getvGNaam(B5) : B5;

        // Set TextView
        TextView LS_B1 = findViewById(R.id.LS_B1);
        TextView LS_B2 = findViewById(R.id.LS_B2);
        TextView LS_B3 = findViewById(R.id.LS_B3);
        TextView LS_B4 = findViewById(R.id.LS_B4);
        TextView LS_B5 = findViewById(R.id.LS_B5);

        LS_B1.setText(B1_Display);
        LS_B2.setText(B2_Display);
        LS_B3.setText(B3_Display);
        LS_B4.setText(B4_Display);
        LS_B5.setText(B5_Display);
    }

    // This is hardcoded to the max
    // But fixing this would require a rewrite of the whole program
    @SuppressLint("SetTextI18n")
    public void updateGooiTable(){
        MMDatabase db = MMDatabase.getInstance(getApplicationContext());

        UpdateBs();

        // Thijs
        TextView thijs_raakHV = findViewById(R.id.B1_raakHV);
        TextView thijs_raak = findViewById(R.id.B1_raak);
        TextView thijs_gooiHV = findViewById(R.id.B1_gooiHV);
        TextView thijs_gooi = findViewById(R.id.B1_gooi);

        thijs_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV(B1)));
        thijs_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid(B1)));

        thijs_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooidHV(B1)));
        thijs_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooid(B1)));

        // Sven
        TextView sven_raakHV = findViewById(R.id.B2_raakHV);
        TextView sven_raak = findViewById(R.id.B2_raak);
        TextView sven_gooiHV = findViewById(R.id.B2_gooiHV);
        TextView sven_gooi = findViewById(R.id.B2_gooi);

        sven_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV(B2)));
        sven_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid(B2)));

        sven_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooidHV(B2)));
        sven_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooid(B2)));

        // Rowin
        TextView rowin_raakHV = findViewById(R.id.B3_raakHV);
        TextView rowin_raak = findViewById(R.id.B3_raak);
        TextView rowin_gooiHV = findViewById(R.id.B3_gooiHV);
        TextView rowin_gooi = findViewById(R.id.B3_gooi);

        rowin_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV(B4)));
        rowin_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid(B4)));

        rowin_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooidHV(B4)));
        rowin_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooid(B4)));

        // Etienne
        TextView etienne_raakHV = findViewById(R.id.B4_raakHV);
        TextView etienne_raak = findViewById(R.id.B4_raak);
        TextView etienne_gooiHV = findViewById(R.id.B4_gooiHV);
        TextView etienne_gooi = findViewById(R.id.B4_gooi);

        etienne_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV(B3)));
        etienne_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid(B3)));

        etienne_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooidHV(B3)));
        etienne_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooid(B3)));

        // Steven / vG
        TextView steven_raakHV = findViewById(R.id.B5_raakHV);
        TextView steven_raak = findViewById(R.id.B5_raak);
        TextView steven_gooiHV = findViewById(R.id.B5_gooiHV);
        TextView steven_gooi = findViewById(R.id.B5_gooi);

        steven_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV(B5)));
        steven_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid(B5)));

        steven_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooidHV(B5)));
        steven_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooid(B5)));

        // Huis
        TextView huis_raakHV = findViewById(R.id.huis_raakHV);
        TextView huis_raak = findViewById(R.id.huis_raak);
        TextView huis_gooiHV = findViewById(R.id.huis_gooiHV);
        TextView huis_gooi = findViewById(R.id.huis_gooi);

        huis_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV()));
        huis_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid()));

        huis_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooidHV()));
        huis_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooid()));


    }
}