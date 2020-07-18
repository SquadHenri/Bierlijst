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

    // The value that beerToThrow is initially set to
    public int ThrownBeer;
    public int hits = 0;
    public int uitHetRaam = 0;
    public String Thrower;
    protected CountDownTimer timer = null;
    protected long secsLeft = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bierpopup);


        // TODO: make sure the person throwing actually opens a beer, possible discussion
        Intent intent = getIntent();
        Thrower = intent.getStringExtra("thrower");
        beerToThrow = intent.getIntExtra("beer",0);
        ThrownBeer = beerToThrow;

        if(beerToThrow == 0) {
            Log.e("Error", "bierPopup created with no beer");
            finish();
        }

        setBeerToThrowText();

        timer = newTimer(120);
        timer.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("STOPPING", "WORKS!>");
        handleExtraBeers();
    }

    public void hit(View view) {
        showToast("Lekker!");


        if(beerToThrow > 0) {
            addToTimer(15);
            hits++;
        }
    }

    public void mis(View view){
        showToast("Lekker man");

        if(beerToThrow > 0) {
            beerToThrow--;
            setBeerToThrowText();
        }

        if(beerToThrow == 0){
            finish();
        }
    }

    public void uithetRaam(View view){
        showToast("Haha drinken sukkel");

        if(beerToThrow > 0) {
            uitHetRaam++;

            addToTimer(15);

            setBeerToThrowText();
        }
        if(beerToThrow == 0){
            finish();
        }
    }

    public void allesMis(View view) {
        showToast("Sukkel");

        finish();
    }

    public void handleExtraBeers() {
        MMDatabase db = MMDatabase.getInstance(getApplicationContext());

        // add gegooid:

        db.getBewonerDAO().UpdateRaakGegooidAndGegooid(Thrower, hits + uitHetRaam + ThrownBeer, hits);

        if(hits + uitHetRaam > 0) {
            db.processBeer(Thrower, hits + uitHetRaam, true);
        }
        // Just to make sure it does not happen twice
        hits = 0;
        uitHetRaam = 0;

    }

    // Simple helper function
    public void setBeerToThrowText() {
        TextView bierTeGooienText = findViewById(R.id.bierTeGooienText);
        bierTeGooienText.setText("Je hebt nog "+ beerToThrow + " bier te gooien");
    }

    public void showToast(String text){
        Context context = getApplicationContext();
        CharSequence toast_text = text;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, toast_text, duration);
        toast.show();
    }

    public void addToTimer(int seconds) {
        if(timer == null) {
            Log.e("ERROR", "There is no timer");
        }

        timer.cancel();
        timer = newTimer(15 + secsLeft);
        timer.start();
    }

    public CountDownTimer newTimer(long seconds){
        return new CountDownTimer(seconds * 1000, 1000) {
            public void onTick(long millisecsleft) {
                secsLeft = millisecsleft / 1000;

                TextView bierTeGooienTijd = findViewById(R.id.bierTeGooienTijd);
                bierTeGooienTijd.setText("Je hebt nog "+ millisecsleft / 1000 + "s om te gooien");
            }

            public void onFinish() {
                // Finish this activity

                // Finish should call handleExtraBeers by onDestroy()
//                if(hits > 0 || uitHetRaam > 0) {
//                    handleExtraBeers();
//                }
                finish();
            }
        };
    }
}
