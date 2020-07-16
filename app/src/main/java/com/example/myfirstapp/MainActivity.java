package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: possibly move this
        // Init Database by fetching an instance and not doing anything
        Log.d("Database", "Fetching database to init it");
        MMDatabase.getInstance(getApplicationContext());


        ImageButton editBewonersBtn = findViewById(R.id.editBewonersBtn);
        editBewonersBtn.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                Log.d("HEPL", "EDITBEWONERS CALLED!");
                Intent intent = new Intent(v.getContext(), EditBewoners.class);
                startActivity(intent);

                return true;

            }
        });
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


}
