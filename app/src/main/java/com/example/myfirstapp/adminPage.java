package com.example.myfirstapp;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;


public class adminPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_page);
    }

    public void snapshotBtn(View view) {
        MMDatabase db = MMDatabase.getInstance(this.getApplicationContext());

        db.makeSnapshot(false);
    }

    public void verwijderDataBtn(View view) {
        MMDatabase db = MMDatabase.getInstance(this.getApplicationContext());
        db.makeSnapshot(true);
        db.clearOrderedBeer();
        db.getBewonerDAO().resetGooienHV();
    }

}
