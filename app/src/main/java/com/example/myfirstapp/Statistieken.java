package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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


    private String thijs = "Thijs";
    private String sven = "Sven";
    private String tinder = "vG Tinder";
    private String kvn = "vG KVN";
    private String steven = "Steven";

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
    Map<String, TextView> tindergeven = new HashMap<String, TextView>();
    Map<String, TextView> kvngeven = new HashMap<String, TextView>();
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

        schoonmaakbiertogive.put(thijs, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(sven, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(tinder, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(kvn, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(steven, new HashMap<String, Integer>());



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
        TextView thijssven = findViewById(R.id.thijssven);
        TextView thijstinder = findViewById(R.id.thijstinder);
        TextView thijskvn = findViewById(R.id.thijskvn);
        TextView thijslogee = findViewById(R.id.thijslogee);
        TextView thijstotaalkrijgen = findViewById(R.id.thijstotaalgeven);
        TextView thijstotaalgeven = findViewById(R.id.thijstotaalkrijgen);

        // Sven geeft
        TextView sventhijs = findViewById(R.id.sventhijs);
        TextView sventinder = findViewById(R.id.sventinder);
        TextView svenkvn = findViewById(R.id.svenkvn);
        TextView svenlogee = findViewById(R.id.svenlogee);
        TextView sventotaalkrijgen = findViewById(R.id.sventotaalgeven);
        TextView sventotaalgeven = findViewById(R.id.sventotaalkrijgen);

        // Tinder geeft
        TextView tinderthijs = findViewById(R.id.tinderthijs);
        TextView tindersven = findViewById(R.id.tindersven);
        TextView tinderkvn = findViewById(R.id.tinderkvn);
        TextView tinderlogee = findViewById(R.id.tinderlogee);
        TextView tindertotaalkrijgen = findViewById(R.id.tindertotaalgeven);
        TextView tindertotaalgeven = findViewById(R.id.tindertotaalkrijgen);

        // kvn geeft
        TextView kvnthijs = findViewById(R.id.kvnthijs);
        TextView kvnsven = findViewById(R.id.kvnsven);
        TextView kvntinder = findViewById(R.id.kvntinder);
        TextView kvnlogee = findViewById(R.id.kvnlogee);
        TextView kvntotaalkrijgen = findViewById(R.id.kvntotaalgeven);
        TextView kvntotaalgeven = findViewById(R.id.kvntotaalkrijgen);

        // logee geeft
        TextView logeethijs = findViewById(R.id.logeethijs);
        TextView logeesven = findViewById(R.id.logeesven);
        TextView logeetinder = findViewById(R.id.logeetinder);
        TextView logeekvn = findViewById(R.id.logeekvn);
        TextView logeetotaalkrijgen = findViewById(R.id.logeetotaalgeven);
        TextView logeetotaalgeven = findViewById(R.id.logeetotaalkrijgen);

        TextView sbopthijs = findViewById(R.id.sbopthijs);
        TextView biergestreeptthijs = findViewById(R.id.biergestreeptthijs);
        TextView thijstotaal = findViewById(R.id.thijstotaal);

        TextView sbopsven = findViewById(R.id.sbopsven);
        TextView biergestreeptsven = findViewById(R.id.biergestreeptsven);
        TextView sventotaal = findViewById(R.id.sventotaal);

        TextView sboptinder = findViewById(R.id.sboptinder);
        TextView biergestreepttinder = findViewById(R.id.biergestreepttinder);
        TextView tindertotaal = findViewById(R.id.tindertotaal);

        TextView sbopkvn = findViewById(R.id.sbopkvn);
        TextView biergestreeptkvn = findViewById(R.id.biergestreeptkvn);
        TextView kvntotaal = findViewById(R.id.kvntotaal);

        TextView sboplogee = findViewById(R.id.sboplogee);
        TextView biergestreeptlogee = findViewById(R.id.biergestreeptlogee);
        TextView logeetotaal = findViewById(R.id.logeetotaal);

        totaaltegeven.put(thijs, thijstotaalgeven);
        totaaltegeven.put(sven, sventotaalgeven);
        totaaltegeven.put(tinder, tindertotaalgeven);
        totaaltegeven.put(kvn, kvntotaalgeven);
        totaaltegeven.put(steven, logeetotaalgeven);

        totaaltekrijgen.put(thijs, thijstotaalkrijgen);
        totaaltekrijgen.put(sven, sventotaalkrijgen);
        totaaltekrijgen.put(tinder, tindertotaalkrijgen);
        totaaltekrijgen.put(kvn, kvntotaalkrijgen);
        totaaltekrijgen.put(steven, logeetotaalkrijgen);


        thijsgeven.put(sven, thijssven);
        thijsgeven.put(tinder, thijstinder);
        thijsgeven.put(kvn, thijskvn);
        thijsgeven.put(steven, thijslogee);

        svengeven.put(thijs, sventhijs);
        svengeven.put(tinder, sventinder);
        svengeven.put(kvn, svenkvn);
        svengeven.put(steven, svenlogee);

        tindergeven.put(thijs, tinderthijs);
        tindergeven.put(sven, tindersven);
        tindergeven.put(kvn,tinderkvn);
        tindergeven.put(steven, tinderlogee);

        kvngeven.put(thijs, kvnthijs);
        kvngeven.put(sven, kvnsven);
        kvngeven.put(tinder, kvntinder);
        kvngeven.put(steven, kvnlogee);

        stevengeven.put(thijs, logeethijs);
        stevengeven.put(sven, logeesven);
        stevengeven.put(tinder, logeetinder);
        stevengeven.put(kvn, logeekvn);

        sbonkey.put(thijs, sbopthijs);
        sbonkey.put(sven, sbopsven);
        sbonkey.put(tinder, sboptinder);
        sbonkey.put(kvn, sbopkvn);
        sbonkey.put(steven, sboplogee);

        biergestreeptonkey.put(thijs, biergestreeptthijs);
        biergestreeptonkey.put(sven, biergestreeptsven);
        biergestreeptonkey.put(tinder, biergestreepttinder);
        biergestreeptonkey.put(kvn, biergestreeptkvn);
        biergestreeptonkey.put(steven, biergestreeptlogee);

        totaalonkey.put(thijs, thijstotaal);
        totaalonkey.put(sven, sventotaal);
        totaalonkey.put(tinder, tindertotaal);
        totaalonkey.put(kvn, kvntotaal);
        totaalonkey.put(steven, logeetotaal);

        sbgiving.put(thijs, thijsgeven);
        sbgiving.put(sven, svengeven);
        sbgiving.put(tinder, tindergeven);
        sbgiving.put(kvn, kvngeven);
        sbgiving.put(steven, stevengeven);

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

        for(String key : tindergeven.keySet()) {
            TextView textview = tindergeven.get(key);
            textview.setText(text);

        }

        for(String key : kvngeven.keySet()) {
            TextView textview = kvngeven.get(key);
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
        /*
        *     // Thijs, Sven, Tinder, KVN, Steven
    Map<String, Map<String, Integer>> schoonmaakbiertogive;
    Map<String, Integer> gestreeptbier = new HashMap<String, Integer>();
    Map<String, Integer> schoonmaakbiergestreept = new HashMap<String, Integer>();
        * */

        MMDatabase db = MMDatabase.getInstance(this.getApplicationContext());



        // gestreeptbier and  schoonmaakbiergestreept
        List<Bewoner> bewoners = db.getBewonerDAO().getAllBewoners();

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

            for(String personreceiving : persongivingtextviews.keySet()) {
                TextView text = persongivingtextviews.get(personreceiving);
                text.setText(Integer.toString(schoonmaakbiertogive.get(persongiving).get(personreceiving)));


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
}
