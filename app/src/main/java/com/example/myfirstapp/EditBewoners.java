package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class EditBewoners extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bewoners);

        EditText editTextRowin = findViewById(R.id.editTextSBRowin);
        editTextRowin.setText("0");

        EditText editTextThijs = findViewById(R.id.editTextSBThijs);
        editTextThijs.setText("0");

        EditText editTextsteven = findViewById(R.id.editTextSBSteven);
        editTextsteven.setText("0");

        EditText editTextSven = findViewById(R.id.editTextSBSven);
        editTextSven.setText("0");

        EditText editTextEtienne = findViewById(R.id.editTextSBEtienne);
        editTextEtienne.setText("0");


        //TODO WORKING ON EDIT USERS AND Quality of life improvements for DATABASE, such as admin removing and adding users;


    }

    public void processSB(View view){
        EditText editTextThijs = findViewById(R.id.editTextSBThijs);
        int valueThijs = Integer.valueOf(editTextThijs.getText().toString());

        EditText editTextSven = findViewById(R.id.editTextSBSven);
        int valueSven = Integer.valueOf(editTextSven.getText().toString());

        EditText editTextEtienne = findViewById(R.id.editTextSBEtienne);
        int valueEtienne = Integer.valueOf(editTextEtienne.getText().toString());

        EditText editTextRowin = findViewById(R.id.editTextSBRowin);
        int valueRowin = Integer.valueOf(editTextRowin.getText().toString());

        EditText editTextSteven = findViewById(R.id.editTextSBSteven);
        int valueSteven = Integer.valueOf(editTextSteven.getText().toString());

        if(valueEtienne == 0 && valueRowin == 0 && valueSteven == 0 && valueSven == 0  && valueThijs == 0) {
            return;
        }

        Map<String, Integer> sbBeerToAdd = new HashMap<>();
        sbBeerToAdd.put("Thijs", valueThijs);
        sbBeerToAdd.put("Sven", valueSven);
        sbBeerToAdd.put("vG Tinder", valueEtienne);
        sbBeerToAdd.put("vG KVN", valueRowin);
        sbBeerToAdd.put("Steven", valueSteven);

        MMDatabase db = MMDatabase.getInstance(getApplicationContext());
        for(String giver : sbBeerToAdd.keySet()) {
            int value = sbBeerToAdd.get(giver);

            db.getSchoonmaakBierDAO().addSchoonmaakbier(giver, value);
        }

        Context context = getApplicationContext();
        CharSequence text = "Je hebt schoonmaakbier toegevoegd.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /*
    *           ADD SB FUNCTIONS
    * */

    public void addSBThijs(View view){
        EditText editText = findViewById(R.id.editTextSBThijs);
        int value = Integer.valueOf(editText.getText().toString());


        editText.setText("" + ++value);
    }

    public void addSBSven(View view){
        EditText editText = findViewById(R.id.editTextSBSven);
        int value = Integer.valueOf(editText.getText().toString());

        editText.setText("" + ++value);
    }


    public void addSBEtienne(View view){
        EditText editText = findViewById(R.id.editTextSBEtienne);
        int value = Integer.valueOf(editText.getText().toString());

        editText.setText("" + ++value);
    }


    public void addSBRowin(View view){
        EditText editText = findViewById(R.id.editTextSBRowin);
        int value = Integer.valueOf(editText.getText().toString());

        editText.setText("" + ++value);
    }

    public void addSBSteven(View view) {
        EditText editText = findViewById(R.id.editTextSBSteven);
        int value = Integer.valueOf(editText.getText().toString());

        editText.setText("" + ++value);
    }

    /*
    *               SUBSTRACT SB FUNCTIONS
    * */


    public void substractSBThijs(View view) {
        EditText editText = findViewById(R.id.editTextSBThijs);
        int value = Integer.valueOf(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractSBSven(View view){
        EditText editText = findViewById(R.id.editTextSBSven);
        int value = Integer.valueOf(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractSBEtienne(View view){
        EditText editText = findViewById(R.id.editTextSBEtienne);
        int value = Integer.valueOf(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractSBRowin(View view){
        EditText editText = findViewById(R.id.editTextSBRowin);
        int value = Integer.valueOf(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractSBSteven(View view){
        EditText editText = findViewById(R.id.editTextSBSteven);
        int value = Integer.valueOf(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }
}
