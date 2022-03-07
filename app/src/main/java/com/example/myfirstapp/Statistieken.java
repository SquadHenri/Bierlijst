package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// TODO: possibly use db queries instead of weirdly complex algorithms
public class Statistieken extends AppCompatActivity {

    /*
    *               DATA
    * */

    // Thijs, Sven, Tinder, KVN, Steven
    Map<String, Map<String, Integer>> schoonmaakbiertogive = new HashMap<String, Map<String, Integer>>();
    Map<String, Integer> gestreeptbier = new HashMap<String, Integer>();
    Map<String, Integer> schoonmaakbiergestreept = new HashMap<String, Integer>();
    // Bit annoying to calculate so we do it like this
    Map<String, Integer> totaaltekrijgendata = new HashMap<>();
    Map<String, Integer> totaaltegevendata= new HashMap<>();


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

    /*
    *               TEXTVIEWS
    * */

    // The string is who it applies to
    Map<String, TextView>  totaaltegeven = new HashMap<String, TextView>();
    Map<String, TextView> totaaltekrijgen = new HashMap<String, TextView>();

    // The String is the person who will receive this
    // TODO: make a list
    Map<String, TextView> thijsgeven = new HashMap<String, TextView>();
    Map<String, TextView> svengeven = new HashMap<String, TextView>();
    Map<String, TextView> etiennegeven = new HashMap<String, TextView>();
    Map<String, TextView> rowingeven = new HashMap<String, TextView>();
    Map<String, TextView> stevengeven = new HashMap<String, TextView>();
    Map<String, Map<String,TextView>> sbgiving = new HashMap<String, Map<String, TextView>>();

    // SB on the String key
    Map<String, TextView> sbonkey = new HashMap<String, TextView>();

    //
    Map<String, TextView> biergestreeptonkey = new HashMap<String, TextView>();

    //
    Map<String, TextView> totaalonkey = new HashMap<String, TextView>();





    boolean initData = false;
    boolean initViews = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistieken);

        updateBs();

        schoonmaakbiertogive.put(B1, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(B2, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(B3, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(B4, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(B5, new HashMap<String, Integer>());



        // Gather data
        Log.d("updating data", "yessir");
        updateData();
        Log.d("set text views", "yessir");
        setTextViews();
        Log.d("setting all text views", "empty yessir");
        setAllTextViews(" ");
        Log.d("Updating text views", "yessir");
        updateText();
        Log.d("Text views set", "yessir");

        TextView SecretAdminPage = findViewById(R.id.textView60);
        SecretAdminPage.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Log.d("CLICKED", "REEEEEEE");
                Intent intent = new Intent(v.getContext(), adminPage.class);
                startActivity(intent);
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateBs();

        schoonmaakbiertogive.put(B1, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(B2, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(B3, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(B4, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(B5, new HashMap<String, Integer>());



        // Gather data
        Log.d("updating data", "yessir");
        updateData();
        Log.d("set text views", "yessir");
        setTextViews();
        Log.d("setting all text views", "empty yessir");
        setAllTextViews(" ");
        Log.d("Updating text views", "yessir");
        updateText();
        Log.d("Text views set", "yessir");
    }

    private void setTextViews(){


        // Fills the TextView maps and set all values to " "
        // Thijs geeft
        TextView thijssven = findViewById(R.id.B1B2);
        TextView thijsetienne = findViewById(R.id.B1B3);
        TextView thijsrowin = findViewById(R.id.B1B4);
        TextView thijslogee = findViewById(R.id.B1B5);
        TextView thijstotaalkrijgen = findViewById(R.id.B1totaalgeven);
        TextView thijstotaalgeven = findViewById(R.id.B1totaalkrijgen);

        // Sven geeft
        TextView sventhijs = findViewById(R.id.B2B1);
        TextView svenetienne = findViewById(R.id.B2B3);
        TextView svenrowin = findViewById(R.id.B2B4);
        TextView svenlogee = findViewById(R.id.B2B5);
        TextView sventotaalkrijgen = findViewById(R.id.B2totaalgeven);
        TextView sventotaalgeven = findViewById(R.id.B2totaalkrijgen);

        // Etienne geeft
        TextView etiennethijs = findViewById(R.id.B3B1);
        TextView etiennesven = findViewById(R.id.B3B2);
        TextView etiennerowin = findViewById(R.id.B3B4);
        TextView etiennelogee = findViewById(R.id.B3B5);
        TextView etiennetotaalkrijgen = findViewById(R.id.B3totaalgeven);
        TextView etiennetotaalgeven = findViewById(R.id.B3totaalkrijgen);

        // rowin geeft
        TextView rowinthijs = findViewById(R.id.B4B1);
        TextView rowinsven = findViewById(R.id.B4B2);
        TextView rowinetienne = findViewById(R.id.B4B3);
        TextView rowinlogee = findViewById(R.id.B4B5);
        TextView rowintotaalkrijgen = findViewById(R.id.B4totaalgeven);
        TextView rowintotaalgeven = findViewById(R.id.B4totaalkrijgen);

        // logee geeft
        TextView logeethijs = findViewById(R.id.B5B1);
        TextView logeesven = findViewById(R.id.B5B2);
        TextView logeeetienne = findViewById(R.id.B5B3);
        TextView logeerowin = findViewById(R.id.B5B4);
        TextView logeetotaalkrijgen = findViewById(R.id.B5totaalgeven);
        TextView logeetotaalgeven = findViewById(R.id.B5totaalkrijgen);

        TextView sbopthijs = findViewById(R.id.sbopB1);
        TextView biergestreeptthijs = findViewById(R.id.biergestreeptB1);
        TextView thijstotaal = findViewById(R.id.B1totaal);

        TextView sbopsven = findViewById(R.id.sbopB2);
        TextView biergestreeptsven = findViewById(R.id.biergestreeptB2);
        TextView sventotaal = findViewById(R.id.B2totaal);

        TextView sbopetienne = findViewById(R.id.sbopB3);
        TextView biergestreeptetienne = findViewById(R.id.biergestreeptB3);
        TextView etiennetotaal = findViewById(R.id.B3totaal);

        TextView sboprowin = findViewById(R.id.sbopB4);
        TextView biergestreeptrowin = findViewById(R.id.biergestreeptB4);
        TextView rowintotaal = findViewById(R.id.B4totaal);

        TextView sboplogee = findViewById(R.id.sbopB5);
        TextView biergestreeptlogee = findViewById(R.id.biergestreeptB5);
        TextView logeetotaal = findViewById(R.id.B5totaal);

        totaaltegeven.put(B1, thijstotaalgeven);
        totaaltegeven.put(B2, sventotaalgeven);
        totaaltegeven.put(B3, etiennetotaalgeven);
        totaaltegeven.put(B4, rowintotaalgeven);
        totaaltegeven.put(B5, logeetotaalgeven);

        totaaltekrijgen.put(B1, thijstotaalkrijgen);
        totaaltekrijgen.put(B2, sventotaalkrijgen);
        totaaltekrijgen.put(B3, etiennetotaalkrijgen);
        totaaltekrijgen.put(B4, rowintotaalkrijgen);
        totaaltekrijgen.put(B5, logeetotaalkrijgen);


        thijsgeven.put(B2, thijssven);
        thijsgeven.put(B3, thijsetienne);
        thijsgeven.put(B4, thijsrowin);
        thijsgeven.put(B5, thijslogee);

        svengeven.put(B1, sventhijs);
        svengeven.put(B3, svenetienne);
        svengeven.put(B4, svenrowin);
        svengeven.put(B5, svenlogee);

        etiennegeven.put(B1, etiennethijs);
        etiennegeven.put(B2, etiennesven);
        etiennegeven.put(B4,etiennerowin);
        etiennegeven.put(B5, etiennelogee);

        rowingeven.put(B1, rowinthijs);
        rowingeven.put(B2, rowinsven);
        rowingeven.put(B3, rowinetienne);
        rowingeven.put(B5, rowinlogee);

        stevengeven.put(B1, logeethijs);
        stevengeven.put(B2, logeesven);
        stevengeven.put(B3, logeeetienne);
        stevengeven.put(B4, logeerowin);

        sbonkey.put(B1, sbopthijs);
        sbonkey.put(B2, sbopsven);
        sbonkey.put(B3, sbopetienne);
        sbonkey.put(B4, sboprowin);
        sbonkey.put(B5, sboplogee);

        biergestreeptonkey.put(B1, biergestreeptthijs);
        biergestreeptonkey.put(B2, biergestreeptsven);
        biergestreeptonkey.put(B3, biergestreeptetienne);
        biergestreeptonkey.put(B4, biergestreeptrowin);
        biergestreeptonkey.put(B5, biergestreeptlogee);

        totaalonkey.put(B1, thijstotaal);
        totaalonkey.put(B2, sventotaal);
        totaalonkey.put(B3, etiennetotaal);
        totaalonkey.put(B4, rowintotaal);
        totaalonkey.put(B5, logeetotaal);

        sbgiving.put(B1, thijsgeven);
        sbgiving.put(B2, svengeven);
        sbgiving.put(B3, etiennegeven);
        sbgiving.put(B4, rowingeven);
        sbgiving.put(B5, stevengeven);

        initViews = true;

    }

    public void setAllTextViews(String text){
        if(!initViews) {
            Log.e("ERR", "trying to set text, not init");
        }

        // The string is who it applies to
        for(String key : totaaltegeven.keySet()) {
            TextView textview = totaaltegeven.get(key);
            textview.setText(text);

        }

        for(String key : totaaltekrijgen.keySet()) {
            TextView textview = totaaltekrijgen.get(key);
            textview.setText(text);

        }

        for(String key : thijsgeven.keySet()) {
            TextView textview = thijsgeven.get(key);
            textview.setText(text);

        }

        for(String key : svengeven.keySet()) {
            TextView textview = svengeven.get(key);
            textview.setText(text);

        }

        for(String key : etiennegeven.keySet()) {
            TextView textview = etiennegeven.get(key);
            textview.setText(text);

        }

        for(String key : rowingeven.keySet()) {
            TextView textview = rowingeven.get(key);
            textview.setText(text);

        }

        for(String key : stevengeven.keySet()) {
            TextView textview = stevengeven.get(key);
            textview.setText(text);

        }

        for(String key : sbonkey.keySet()) {
            TextView textview = sbonkey.get(key);
            textview.setText(text);

        }

        for(String key : biergestreeptonkey.keySet()) {
            TextView textview = biergestreeptonkey.get(key);
            textview.setText(text);

        }

        for(String key : totaalonkey.keySet()) {
            TextView textview = totaalonkey.get(key);
            textview.setText(text);

        }

    }

    private void updateData(){
        MMDatabase db = MMDatabase.getInstance(this.getApplicationContext());

        // gestreeptbier and  schoonmaakbiergestreept
        List<Bewoner> bewoners = db.getBewonerDAO().getAllHuidigeBewoners();

        for(Bewoner bewoner : bewoners) {
            // Schoonmaakbier
            List<SchoonmaakBier> sbforbewoner = db.getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(bewoner.getNaam());

            for(SchoonmaakBier sb : sbforbewoner) {
                Log.d(bewoner.getNaam(), sb.getToGive());
                schoonmaakbiertogive.get(bewoner.getNaam()).put(sb.getToGive(), sb.getBeer());
            }


            // Gestreeptbier
            gestreeptbier.put(bewoner.getNaam(), bewoner.getGestreeptBier());

            // schoonmaakbiergestreept
            schoonmaakbiergestreept.put(bewoner.getNaam(), bewoner.getSchoonmaakbierOpJou());

            totaaltekrijgendata.put(bewoner.getNaam(), db.getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum(bewoner.getNaam()));


            totaaltegevendata.put(bewoner.getNaam(), db.getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum(bewoner.getNaam()));
        }

        initData = true;


    }

    private void updateText(){
        if(!initData){
            Log.e("ERR", "DATA NOT INITIALIZED");
            return;
        }

        if(!initViews){
            Log.e("ERR", "VIEWS NOT INITIALIZED");
            return;
        }

        for(String persongiving : sbgiving.keySet()) {
            Map<String, TextView> persongivingtextviews = sbgiving.get(persongiving);
            Log.e("persongiving: ", persongiving);
            for(String personreceiving : persongivingtextviews.keySet()) {
                Log.e("Receiving: " + personreceiving, "Giving: " + persongiving);
                TextView text = persongivingtextviews.get(personreceiving);

                Log.e(".get(persongiving):", schoonmaakbiertogive.get(persongiving).keySet().toString());

                Log.e(".get.get:", "gg: " + schoonmaakbiertogive.get(persongiving).get(personreceiving).toString());

                text.setText(
                        Integer.toString(
                                schoonmaakbiertogive.get(persongiving).get(personreceiving)
                        )
                );


            }
        }

        for(String personreceiving : totaaltekrijgen.keySet()){
            totaaltekrijgen.get(personreceiving).setText(Integer.toString(totaaltekrijgendata.get(personreceiving)));

            totaaltegeven.get(personreceiving).setText(Integer.toString(totaaltegevendata.get(personreceiving)));
        }


        for(String naam : totaalonkey.keySet()){
            sbonkey.get(naam).setText(Integer.toString(schoonmaakbiergestreept.get(naam)));

            biergestreeptonkey.get(naam).setText(Integer.toString(gestreeptbier.get(naam)));

            totaalonkey.get(naam).setText(Integer.toString(schoonmaakbiergestreept.get(naam) + gestreeptbier.get(naam)));
        }



    }

    private void updateBs(){
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

        TextView LS_SB_B1 = findViewById(R.id.LS_SB_B1);
        TextView LS_SB_B2 = findViewById(R.id.LS_SB_B2);
        TextView LS_SB_B3 = findViewById(R.id.LS_SB_B3);
        TextView LS_SB_B4 = findViewById(R.id.LS_SB_B4);
        TextView LS_SB_B5 = findViewById(R.id.LS_SB_B5);

        TextView TS_SB_B1 = findViewById(R.id.TS_SB_B1);
        TextView TS_SB_B2 = findViewById(R.id.TS_SB_B2);
        TextView TS_SB_B3 = findViewById(R.id.TS_SB_B3);
        TextView TS_SB_B4 = findViewById(R.id.TS_SB_B4);
        TextView TS_SB_B5 = findViewById(R.id.TS_SB_B5);

        TextView LS_B_B1 = findViewById(R.id.LS_B_B1);
        TextView LS_B_B2 = findViewById(R.id.LS_B_B2);
        TextView LS_B_B3 = findViewById(R.id.LS_B_B3);
        TextView LS_B_B4 = findViewById(R.id.LS_B_B4);
        TextView LS_B_B5 = findViewById(R.id.LS_B_B5);

        LS_SB_B1.setText(B1_Display);
        LS_SB_B2.setText(B2_Display);
        LS_SB_B3.setText(B3_Display);
        LS_SB_B4.setText(B4_Display);
        LS_SB_B5.setText(B5_Display);

        TS_SB_B1.setText(B1_Display);
        TS_SB_B2.setText(B2_Display);
        TS_SB_B3.setText(B3_Display);
        TS_SB_B4.setText(B4_Display);
        TS_SB_B5.setText(B5_Display);

        LS_B_B1.setText(B1_Display);
        LS_B_B2.setText(B2_Display);
        LS_B_B3.setText(B3_Display);
        LS_B_B4.setText(B4_Display);
        LS_B_B5.setText(B5_Display);
    }

}
