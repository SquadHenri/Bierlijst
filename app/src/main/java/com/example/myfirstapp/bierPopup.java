package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

public class bierPopup extends AppCompatActivity {
    public int beerToThrow;
    public int hits = 0;
    public String Thrower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bierpopup);

        Intent intent = getIntent();
        Thrower = intent.getStringExtra("thrower");
        beerToThrow = intent.getIntExtra("beer",0);
        if(beerToThrow == 0) {
            Log.e("Error", "bierPopup created with no beer");
            finish();
        }

        new CountDownTimer(300000, 1000) {
            public void onTick(long millisecsleft) {

            }

            public void onFinish() {
                // Finish this activity
                if(hits > 0) {
                    handleExtraBeers();
                }
                finish();
            }
        }.start();
    }

    public void hit(View view) {
        hits++;
    }

    public void handleExtraBeers() {

        /*

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
         */

        AsyncTask.execute(new Runnable(){
            @Override
            public void run(){
                MMDatabase db = MMDatabase.getInstance(getApplicationContext());

                db.getBewonerDAO().addRaakGegooid(hits, Thrower);
                db.processBeer(Thrower, hits);
            }
        });
        // Just to make sure it does not happen twice
        hits = 0;

    }
}
