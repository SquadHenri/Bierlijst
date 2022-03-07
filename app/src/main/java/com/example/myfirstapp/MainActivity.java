package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Database", "Fetching database to init it");
        MMDatabase.getInstance(getApplicationContext());

    }

    // called by bierlijstBtn
    public void startBierlijst(View view) {
        Log.d("HEPL", "STARTBIERLIJST CALLED!");
        Intent intent = new Intent(this, Bierlijst.class);
        startActivity(intent);
    }

    public void startStatistieken(View view) {
        Log.d("HEPL", "START KEUZE STATISTIEKEN!");
        Intent intent = new Intent(this, StatistiekenKeuze.class);
        startActivity(intent);
    }

    public void startSchoonmaakbier(View view)
    {
        Log.d("Info", "Schoonmaakbier started");
        Intent intent = new Intent(this, EditBewoners.class);
        startActivity(intent);
    }

    public void startBewonersBewerken(View view)
    {
        Log.d("Info", "Going to Bewoners Bewerken");
        Intent intent = new Intent(this, add_and_edit_bewoners.class);
        startActivity(intent);
    }


}
