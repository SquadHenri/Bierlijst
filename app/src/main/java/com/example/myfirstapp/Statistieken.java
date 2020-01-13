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
    private String etienne = "Etienne";
    private String rowin = "Rowin";
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

        schoonmaakbiertogive.put(thijs, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(sven, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(etienne, new HashMap<String, Integer>());
        schoonmaakbiertogive.put(rowin, new HashMap<String, Integer>());
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
        TextView thijsetienne = findViewById(R.id.thijsetienne);
        TextView thijsrowin = findViewById(R.id.thijsrowin);
        TextView thijslogee = findViewById(R.id.thijslogee);
        TextView thijstotaalkrijgen = findViewById(R.id.thijstotaalgeven);
        TextView thijstotaalgeven = findViewById(R.id.thijstotaalkrijgen);

        // Sven geeft
        TextView sventhijs = findViewById(R.id.sventhijs);
        TextView svenetienne = findViewById(R.id.svenetienne);
        TextView svenrowin = findViewById(R.id.svenrowin);
        TextView svenlogee = findViewById(R.id.svenlogee);
        TextView sventotaalkrijgen = findViewById(R.id.sventotaalgeven);
        TextView sventotaalgeven = findViewById(R.id.sventotaalkrijgen);

        // Tinder geeft
        TextView etiennethijs = findViewById(R.id.etiennethijs);
        TextView etiennesven = findViewById(R.id.etiennesven);
        TextView etiennerowin = findViewById(R.id.etiennerowin);
        TextView etiennelogee = findViewById(R.id.etiennelogee);
        TextView etiennetotaalkrijgen = findViewById(R.id.etiennetotaalgeven);
        TextView etiennetotaalgeven = findViewById(R.id.etiennetotaalkrijgen);

        // rowin geeft
        TextView rowinthijs = findViewById(R.id.rowinthijs);
        TextView rowinsven = findViewById(R.id.rowinsven);
        TextView rowinetienne = findViewById(R.id.rowinetienne);
        TextView rowinlogee = findViewById(R.id.rowinlogee);
        TextView rowintotaalkrijgen = findViewById(R.id.rowintotaalgeven);
        TextView rowintotaalgeven = findViewById(R.id.rowintotaalkrijgen);

        // logee geeft
        TextView logeethijs = findViewById(R.id.logeethijs);
        TextView logeesven = findViewById(R.id.logeesven);
        TextView logeeetienne = findViewById(R.id.logeeetienne);
        TextView logeerowin = findViewById(R.id.logeerowin);
        TextView logeetotaalkrijgen = findViewById(R.id.logeetotaalgeven);
        TextView logeetotaalgeven = findViewById(R.id.logeetotaalkrijgen);

        TextView sbopthijs = findViewById(R.id.sbopthijs);
        TextView biergestreeptthijs = findViewById(R.id.biergestreeptthijs);
        TextView thijstotaal = findViewById(R.id.thijstotaal);

        TextView sbopsven = findViewById(R.id.sbopsven);
        TextView biergestreeptsven = findViewById(R.id.biergestreeptsven);
        TextView sventotaal = findViewById(R.id.sventotaal);

        TextView sbopetienne = findViewById(R.id.sbopetienne);
        TextView biergestreeptetienne = findViewById(R.id.biergestreeptetienne);
        TextView etiennetotaal = findViewById(R.id.etiennetotaal);

        TextView sboprowin = findViewById(R.id.sboprowin);
        TextView biergestreeptrowin = findViewById(R.id.biergestreeptrowin);
        TextView rowintotaal = findViewById(R.id.rowintotaal);

        TextView sboplogee = findViewById(R.id.sboplogee);
        TextView biergestreeptlogee = findViewById(R.id.biergestreeptlogee);
        TextView logeetotaal = findViewById(R.id.logeetotaal);

        totaaltegeven.put(thijs, thijstotaalgeven);
        totaaltegeven.put(sven, sventotaalgeven);
        totaaltegeven.put(etienne, etiennetotaalgeven);
        totaaltegeven.put(rowin, rowintotaalgeven);
        totaaltegeven.put(steven, logeetotaalgeven);

        totaaltekrijgen.put(thijs, thijstotaalkrijgen);
        totaaltekrijgen.put(sven, sventotaalkrijgen);
        totaaltekrijgen.put(etienne, etiennetotaalkrijgen);
        totaaltekrijgen.put(rowin, rowintotaalkrijgen);
        totaaltekrijgen.put(steven, logeetotaalkrijgen);


        thijsgeven.put(sven, thijssven);
        thijsgeven.put(etienne, thijsetienne);
        thijsgeven.put(rowin, thijsrowin);
        thijsgeven.put(steven, thijslogee);

        svengeven.put(thijs, sventhijs);
        svengeven.put(etienne, svenetienne);
        svengeven.put(rowin, svenrowin);
        svengeven.put(steven, svenlogee);

        etiennegeven.put(thijs, etiennethijs);
        etiennegeven.put(sven, etiennesven);
        etiennegeven.put(rowin,etiennerowin);
        etiennegeven.put(steven, etiennelogee);

        rowingeven.put(thijs, rowinthijs);
        rowingeven.put(sven, rowinsven);
        rowingeven.put(etienne, rowinetienne);
        rowingeven.put(steven, rowinlogee);

        stevengeven.put(thijs, logeethijs);
        stevengeven.put(sven, logeesven);
        stevengeven.put(etienne, logeeetienne);
        stevengeven.put(rowin, logeerowin);

        sbonkey.put(thijs, sbopthijs);
        sbonkey.put(sven, sbopsven);
        sbonkey.put(etienne, sbopetienne);
        sbonkey.put(rowin, sboprowin);
        sbonkey.put(steven, sboplogee);

        biergestreeptonkey.put(thijs, biergestreeptthijs);
        biergestreeptonkey.put(sven, biergestreeptsven);
        biergestreeptonkey.put(etienne, biergestreeptetienne);
        biergestreeptonkey.put(rowin, biergestreeptrowin);
        biergestreeptonkey.put(steven, biergestreeptlogee);

        totaalonkey.put(thijs, thijstotaal);
        totaalonkey.put(sven, sventotaal);
        totaalonkey.put(etienne, etiennetotaal);
        totaalonkey.put(rowin, rowintotaal);
        totaalonkey.put(steven, logeetotaal);

        sbgiving.put(thijs, thijsgeven);
        sbgiving.put(sven, svengeven);
        sbgiving.put(etienne, etiennegeven);
        sbgiving.put(rowin, rowingeven);
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
