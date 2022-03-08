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
    
    
    boolean initData = false;
    boolean initViews = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistieken);

        updateBs();
        
        updateData();

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
        
        updateData();
    }
    
    private void updateData(){
        MMDatabase db = MMDatabase.getInstance(getApplicationContext());

        /// GET ALL TEXT VIEWS
        
        // B1 geeft aan Bx
        TextView B1B2 = findViewById(R.id.B1B2);
        TextView B1B3 = findViewById(R.id.B1B3);
        TextView B1B4 = findViewById(R.id.B1B4);
        TextView B1B5 = findViewById(R.id.B1B5);
        TextView B1totaalgeven = findViewById(R.id.B1totaalgeven);
        TextView B1totaalkrijgen = findViewById(R.id.B1totaalkrijgen);

        // B2 geeft
        TextView B2B1 = findViewById(R.id.B2B1);
        TextView B2B3 = findViewById(R.id.B2B3);
        TextView B2B4 = findViewById(R.id.B2B4);
        TextView B2B5 = findViewById(R.id.B2B5);
        TextView B2totaalkrijgen = findViewById(R.id.B2totaalgeven);
        TextView B2totaalgeven = findViewById(R.id.B2totaalkrijgen);

        // B3 geeft
        TextView B3B1 = findViewById(R.id.B3B1);
        TextView B3B2 = findViewById(R.id.B3B2);
        TextView B3B4 = findViewById(R.id.B3B4);
        TextView B3B5 = findViewById(R.id.B3B5);
        TextView B3totaalkrijgen = findViewById(R.id.B3totaalgeven);
        TextView B3totaalgeven = findViewById(R.id.B3totaalkrijgen);

        // B4 geeft
        TextView B4B1 = findViewById(R.id.B4B1);
        TextView B4B2 = findViewById(R.id.B4B2);
        TextView B4B3 = findViewById(R.id.B4B3);
        TextView B4B5 = findViewById(R.id.B4B5);
        TextView B4totaalkrijgen = findViewById(R.id.B4totaalgeven);
        TextView B4totaalgeven = findViewById(R.id.B4totaalkrijgen);

        // B5 geeft
        TextView B5B1 = findViewById(R.id.B5B1);
        TextView B5B2 = findViewById(R.id.B5B2);
        TextView B5B3 = findViewById(R.id.B5B3);
        TextView B5B4 = findViewById(R.id.B5B4);
        TextView B5totaalkrijgen = findViewById(R.id.B5totaalgeven);
        TextView B5totaalgeven = findViewById(R.id.B5totaalkrijgen);

        TextView sbopB1 = findViewById(R.id.sbopB1);
        TextView biergestreeptB1 = findViewById(R.id.biergestreeptB1);
        TextView B1totaal = findViewById(R.id.B1totaal);

        TextView sbopB2 = findViewById(R.id.sbopB2);
        TextView biergestreeptB2 = findViewById(R.id.biergestreeptB2);
        TextView B2totaal = findViewById(R.id.B2totaal);

        TextView sbopB3 = findViewById(R.id.sbopB3);
        TextView biergestreeptB3 = findViewById(R.id.biergestreeptB3);
        TextView B3totaal = findViewById(R.id.B3totaal);

        TextView sbopB4 = findViewById(R.id.sbopB4);
        TextView biergestreeptB4 = findViewById(R.id.biergestreeptB4);
        TextView B4totaal = findViewById(R.id.B4totaal);

        TextView sbopB5 = findViewById(R.id.sbopB5);
        TextView biergestreeptB5 = findViewById(R.id.biergestreeptB5);
        TextView B5totaal = findViewById(R.id.B5totaal);
        
        /// FILL TEXT VIEWS
        
        // B1 gives
        B1B2.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B2,B1)));
        B1B3.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B3,B1)));
        B1B4.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B4,B1)));
        B1B5.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B5,B1)));
        B1totaalgeven.setText(String.valueOf(calculateTotalToGive(B1)));
        B1totaalkrijgen.setText(String.valueOf(calculateTotalToReceive(B1)));

        // B2 gives
        B2B1.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B1,B2)));
        B2B3.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B3,B2)));
        B2B4.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B4,B2)));
        B2B5.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B5,B2)));
        B2totaalgeven.setText(String.valueOf(calculateTotalToGive(B2)));
        B2totaalkrijgen.setText(String.valueOf(calculateTotalToReceive(B2)));

        // B3 gives
        B3B1.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B1,B3)));
        B3B2.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B2,B3)));
        B3B4.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B4,B3)));
        B3B5.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B5,B3)));
        B3totaalgeven.setText(String.valueOf(calculateTotalToGive(B3)));
        B3totaalkrijgen.setText(String.valueOf(calculateTotalToReceive(B3)));

        // B4 gives
        B4B1.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B1,B4)));
        B4B2.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B2,B4)));
        B4B3.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B3,B4)));
        B4B5.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B5,B4)));
        B4totaalgeven.setText(String.valueOf(calculateTotalToGive(B4)));
        B4totaalkrijgen.setText(String.valueOf(calculateTotalToReceive(B4)));

        // B5 gives
        B5B1.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B1,B5)));
        B5B2.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B2,B5)));
        B5B3.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B3,B5)));
        B5B4.setText(String.valueOf(db.getSchoonmaakBierDAO().getSBBetweenBewoners(B4,B5)));
        B5totaalgeven.setText(String.valueOf(calculateTotalToGive(B5)));
        B5totaalkrijgen.setText(String.valueOf(calculateTotalToReceive(B5)));
        
        sbopB1.setText(String.valueOf(db.getBewonerDAO().getSchoonmaakBierOpJou(B1)));
        biergestreeptB1.setText(String.valueOf(db.getBewonerDAO().getGestreeptBier(B1)));
        B1totaal.setText(String.valueOf(db.getBewonerDAO().getSchoonmaakBierOpJou(B1) + db.getBewonerDAO().getGestreeptBier(B1)));

        sbopB2.setText(String.valueOf(db.getBewonerDAO().getSchoonmaakBierOpJou(B2)));
        biergestreeptB2.setText(String.valueOf(db.getBewonerDAO().getGestreeptBier(B2)));
        B2totaal.setText(String.valueOf(db.getBewonerDAO().getSchoonmaakBierOpJou(B2) + db.getBewonerDAO().getGestreeptBier(B2)));

        sbopB3.setText(String.valueOf(db.getBewonerDAO().getSchoonmaakBierOpJou(B3)));
        biergestreeptB3.setText(String.valueOf(db.getBewonerDAO().getGestreeptBier(B3)));
        B3totaal.setText(String.valueOf(db.getBewonerDAO().getSchoonmaakBierOpJou(B3) + db.getBewonerDAO().getGestreeptBier(B3)));

        sbopB4.setText(String.valueOf(db.getBewonerDAO().getSchoonmaakBierOpJou(B4)));
        biergestreeptB4.setText(String.valueOf(db.getBewonerDAO().getGestreeptBier(B4)));
        B4totaal.setText(String.valueOf(db.getBewonerDAO().getSchoonmaakBierOpJou(B4) + db.getBewonerDAO().getGestreeptBier(B4)));

        sbopB5.setText(String.valueOf(db.getBewonerDAO().getSchoonmaakBierOpJou(B5)));
        biergestreeptB5.setText(String.valueOf(db.getBewonerDAO().getGestreeptBier(B5)));
        B5totaal.setText(String.valueOf(db.getBewonerDAO().getSchoonmaakBierOpJou(B5) + db.getBewonerDAO().getGestreeptBier(B5)));
    }

 
    private int calculateTotalToGive(String person){
        int res = 0;
        MMDatabase db = MMDatabase.getInstance(getApplicationContext());
        
        String[] bewoners = {B1, B2, B3, B4, B5};
        for(String bewoner : bewoners){
            if(bewoner.equals(person)){
                continue;
            }
            
            res += db.getSchoonmaakBierDAO().getSBBetweenBewoners(bewoner, person);
        }
        
        return res;
    }
    
    private int calculateTotalToReceive(String person){
        int res = 0;
        MMDatabase db = MMDatabase.getInstance(getApplicationContext());

        String[] bewoners = {B1, B2, B3, B4, B5};
        for(String bewoner : bewoners){
            if(bewoner.equals(person)){
                continue;
            }

            res += db.getSchoonmaakBierDAO().getSBBetweenBewoners(person, bewoner);
        }

        return res;
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
