package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class StatistiekenKeuze extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statiestieken_keuze);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    // called by bierlijstBtn
    public void startStatistieken(View view) {
        Log.d("HEPL", "STARTSTATISTIEKEN CALLED!");
        Intent intent = new Intent(this, Statistieken.class);
        startActivity(intent);
    }

    // called by bierlijstBtn
    public void startLeukeStatistieken(View view) {
        Log.d("HEPL", "START LEUKE STATISTIEKEN CALLED");
        Intent intent = new Intent(this, LeukeStatistieken.class);
        startActivity(intent);
    }
}