package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class LeukeStatistieken extends AppCompatActivity {

    private String thijs = "Thijs";
    private String sven = "Sven";
    private String etienne = "Etienne";
    private String rowin = "Rowin";
    private String steven = "Steven";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leuke_statistieken);

        updateGooiTable();
    }

    // This is hardcoded to the max
    // But fixing this would require a rewrite of the whole program
    public void updateGooiTable(){
        MMDatabase db = MMDatabase.getInstance(getApplicationContext());

        // Thijs
        TextView thijs_raakHV = findViewById(R.id.thijs_raakHV);
        TextView thijs_raak = findViewById(R.id.thijs_raak);
        TextView thijs_gooiHV = findViewById(R.id.thijs_gooiHV);
        TextView thijs_gooi = findViewById(R.id.thijs_gooi);

        thijs_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV(thijs)));
        thijs_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid(thijs)));
        thijs_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooid(thijs)));
        thijs_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooidHV(thijs)));

        // Sven
        TextView sven_raakHV = findViewById(R.id.sven_raakHV);
        TextView sven_raak = findViewById(R.id.sven_raak);
        TextView sven_gooiHV = findViewById(R.id.sven_gooiHV);
        TextView sven_gooi = findViewById(R.id.sven_gooi);

        sven_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV(sven)));
        sven_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid(sven)));
        sven_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooid(sven)));
        sven_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooidHV(sven)));

        // Rowin
        TextView rowin_raakHV = findViewById(R.id.rowin_raakHV);
        TextView rowin_raak = findViewById(R.id.rowin_raak);
        TextView rowin_gooiHV = findViewById(R.id.rowin_gooiHV);
        TextView rowin_gooi = findViewById(R.id.rowin_gooi);

        rowin_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV(rowin)));
        rowin_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid(rowin)));
        rowin_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooid(rowin)));
        rowin_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooidHV(rowin)));

        // Etienne
        TextView etienne_raakHV = findViewById(R.id.etienne_raakHV);
        TextView etienne_raak = findViewById(R.id.etienne_raak);
        TextView etienne_gooiHV = findViewById(R.id.etienne_gooiHV);
        TextView etienne_gooi = findViewById(R.id.etienne_gooi);

        etienne_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV(etienne)));
        etienne_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid(etienne)));
        etienne_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooid(etienne)));
        etienne_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooidHV(etienne)));

        // Steven / vG
        TextView steven_raakHV = findViewById(R.id.steven_raakHV);
        TextView steven_raak = findViewById(R.id.steven_raak);
        TextView steven_gooiHV = findViewById(R.id.steven_gooiHV);
        TextView steven_gooi = findViewById(R.id.steven_gooi);

        steven_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV(steven)));
        steven_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid(steven)));
        steven_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooid(steven)));
        steven_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooidHV(steven)));

        // Huis
        TextView huis_raakHV = findViewById(R.id.huis_raakHV);
        TextView huis_raak = findViewById(R.id.huis_raak);
        TextView huis_gooiHV = findViewById(R.id.huis_gooiHV);
        TextView huis_gooi = findViewById(R.id.huis_gooi);

        huis_raakHV.setText(Integer.toString(db.getBewonerDAO().getRaakGegooidHV()));
        huis_raak.setText(Integer.toString(db.getBewonerDAO().getRaakGegooid()));
        huis_gooiHV.setText(Integer.toString(db.getBewonerDAO().getGegooid()));
        huis_gooi.setText(Integer.toString(db.getBewonerDAO().getGegooidHV()));


    }
}