package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.internal.operators.maybe.MaybeError;

//TODO: MAKE THE TOASTS TEXT MORE RANDOM

public class bierPopup extends AppCompatActivity {
    public int beerToThrow;
    public int hits = 0;
    public int uitHetRaam = 0;
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

        setBeerToThrowText();

        new CountDownTimer(120000, 1000) {
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

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("STOPPING", "WORKS!>");
        handleExtraBeers();
    }

    public void hit(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Lekker!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        if(beerToThrow > 0) {
            hits++;
        }
    }

    public void mis(View view){
        Context context = getApplicationContext();
        CharSequence text = "Lekker man";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        if(beerToThrow > 0) {
            beerToThrow--;
            setBeerToThrowText();
        }

        if(beerToThrow == 0){
            finish();
        }
    }

    public void uithetRaam(View view){
        Context context = getApplicationContext();
        CharSequence text = "Haha drinken sukkel";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        if(beerToThrow > 0) {
            uitHetRaam++;
            setBeerToThrowText();
        }
        if(beerToThrow == 0){
            finish();
        }
    }

    public void allesMis(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Sukkel";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();
    }

    public void handleExtraBeers() {

        /*

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
         */

        if(hits + uitHetRaam > 0) {
            MMDatabase db = MMDatabase.getInstance(getApplicationContext());

            db.getBewonerDAO().addRaakGegooid(hits + uitHetRaam, Thrower);
            db.processBeer(Thrower, hits + uitHetRaam);
        }
        // Just to make sure it does not happen twice
        hits = 0;

    }

    // Simple helper function
    public void setBeerToThrowText() {
        TextView bierTeGooienText = findViewById(R.id.bierTeGooienText);
        bierTeGooienText.setText("Je hebt nog "+ beerToThrow + " bier te gooien");
    }
}
