package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: possibly move this
        // Init Database by fetching an instance and not doing anything
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
        Log.d("HEPL", "STARTSTATISTIEKEN CALLED!");
        Intent intent = new Intent(this, Statistieken.class);
        startActivity(intent);
    }
}
