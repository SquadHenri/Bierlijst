package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class EditBewoners extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoonmaakbier_toevoegen);

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

        updateImages();

        //TODO WORKING ON EDIT USERS AND Quality of life improvements for DATABASE, such as admin removing and adding users;


    }

    @Override
    protected void onResume(){
        super.onResume();

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

        updateImages();
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

        SharedPreferences sharedPrefs = getSharedPreferences(MMDatabase.bewonersVolgorde, MODE_PRIVATE);


        Map<String, Integer> sbBeerToAdd = new HashMap<>();
        sbBeerToAdd.put(sharedPrefs.getString("B1", ""), valueThijs);
        sbBeerToAdd.put(sharedPrefs.getString("B2", ""), valueSven);
        sbBeerToAdd.put(sharedPrefs.getString("B3", ""), valueEtienne);
        sbBeerToAdd.put(sharedPrefs.getString("B4", ""), valueRowin);
        sbBeerToAdd.put(sharedPrefs.getString("B5", ""), valueSteven);

        MMDatabase db = MMDatabase.getInstance(getApplicationContext());
        for(String giver : sbBeerToAdd.keySet()) {
            int value = sbBeerToAdd.get(giver);

            db.getSchoonmaakBierDAO().addSchoonmaakbier(giver, value);
        }

        db.SbDataToFile(sbBeerToAdd);

        Context context = getApplicationContext();
        CharSequence text = "Je hebt schoonmaakbier toegevoegd.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        editTextThijs.setText("0");
        editTextSven.setText("0");
        editTextEtienne.setText("0");
        editTextRowin.setText("0");
        editTextSteven.setText("0");

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

    private void updateImages(){
        SharedPreferences sp = getSharedPreferences(MMDatabase.bewonersVolgorde,MODE_PRIVATE);

        String[] Bs = {sp.getString("B1", ""),sp.getString("B2", ""),
                sp.getString("B3", ""),sp.getString("B4", ""),sp.getString("B5", "")};

        ImageButton[] Btns = {
                (ImageButton) findViewById(R.id.IG_SB_B1),
                (ImageButton) findViewById(R.id.IG_SB_B2),
                (ImageButton) findViewById(R.id.IG_SB_B3),
                (ImageButton) findViewById(R.id.IG_SB_B4),
                (ImageButton) findViewById(R.id.IG_SB_B5)
        };

        for(int i = 0; i < Bs.length; i++) {
            Log.d("UPDATE IMAGE", "FOR " + Bs[i]);
            // Try and get image and then set image
            try {
                Log.d("URI For " + Bs[i], "Is present!");
                Uri uri = Uri.parse(sp.getString(Bs[i], ""));

                InputStream is;
                try{
                    Log.e("SETTING", "BUTTON!");
                    is = this.getContentResolver().openInputStream(uri);
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize = 10;
                    Bitmap preview_bitmap=BitmapFactory.decodeStream(is,null,options);

                    Drawable icon = new BitmapDrawable(getResources(),preview_bitmap);
                    Btns[i].setImageDrawable(icon);
                } catch(FileNotFoundException e){
                    continue;
                }

            } catch(NullPointerException e){
                // there is no string to parse
                continue;
            }
        }
    }

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
