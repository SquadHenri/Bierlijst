package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Bierlijst extends AppCompatActivity {


    // when you have 51 sb owed to others, the colour is completely red
    int SBMaxRange = 51;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bierlijst);
        Log.e("BierLijst:", "BIERLIJST CREATEDDD!!");

        EditText eTB4 = findViewById(R.id.editTextB4);
        eTB4.setText("0");

        EditText eTB1 = findViewById(R.id.editTextB1);
        eTB1.setText("0");

        EditText eTB5 = findViewById(R.id.editTextB5);
        eTB5.setText("0");

        EditText eTB2 = findViewById(R.id.editTextB2);
        eTB2.setText("0");

        EditText eTB3 = findViewById(R.id.editTextB3);
        eTB3.setText("0");

        SharedPreferences sharedPrefs = getSharedPreferences(MMDatabase.bewonersVolgorde, MODE_PRIVATE);

        // update Schoonmaakbier values and set colour
        updateSBText();

        ImageButton IB_B5 = findViewById(R.id.IG_B5);
        IB_B5.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                if(getTotalRequestedBeer() > 0) {

                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", sharedPrefs.getString("B5",""));
                    sendBeerToDB();
                    startActivity(intent);


                }
                return true;
            }
        });

        ImageButton IB_B2 = findViewById(R.id.IG_B2);
        IB_B2.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(getTotalRequestedBeer() > 0) {


                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", sharedPrefs.getString("B2",""));
                    sendBeerToDB();
                    startActivity(intent);

                }
                return true;
            }
        });

        ImageButton IB_B1 = findViewById(R.id.IG_B1);
        IB_B1.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(getTotalRequestedBeer() > 0) {

                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", sharedPrefs.getString("B1",""));
                    sendBeerToDB();
                    startActivity(intent);


                }
                return true;
            }
        });

        ImageButton IB_B4 = findViewById(R.id.IG_B4);
        IB_B4.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(getTotalRequestedBeer() > 0) {

                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", sharedPrefs.getString("B4",""));
                    sendBeerToDB();
                    startActivity(intent);


                }
                return true;
            }
        });

        ImageButton IB_B3 = findViewById(R.id.IG_B3);
        IB_B3.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                if (getTotalRequestedBeer() > 0) {

                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", sharedPrefs.getString("B3",""));
                    sendBeerToDB();
                    startActivity(intent);
                }

                return true;

            }
        });


        ImageButton IB_nonMM = findViewById(R.id.IG_nonMM);
        IB_nonMM.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(getTotalRequestedBeer() > 0) {
                    sendBeerToDB();
                    updateSBText();
                    Context context = getApplicationContext();
                    CharSequence text = "Je hebt bier besteld! Lekker hoor";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return true;
                }

                Context context = getApplicationContext();
                CharSequence text = "Je moet wel bier strepen op iemand sjonnie";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            }
        });

        // Set images

    }

    @Override
    protected  void onResume() {
        super.onResume();

        updateSBText();

        updateImages();
    }

    //TODO: totaal to give and to receive is WRONG

    private void updateImages(){
        SharedPreferences sp = getSharedPreferences(MMDatabase.bewonersVolgorde,MODE_PRIVATE);

        String[] Bs = {sp.getString("B1", ""),sp.getString("B2", ""),
                sp.getString("B3", ""),sp.getString("B4", ""),sp.getString("B5", "")};

        ImageButton[] Btns = {
                (ImageButton) findViewById(R.id.IG_B1),
                (ImageButton) findViewById(R.id.IG_B2),
                (ImageButton) findViewById(R.id.IG_B3),
                (ImageButton) findViewById(R.id.IG_B4),
                (ImageButton) findViewById(R.id.IG_B5)
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


    private int calculateTotalToGive(String person){
        int res = 0;
        MMDatabase db = MMDatabase.getInstance(getApplicationContext());
        SharedPreferences sp = getSharedPreferences(MMDatabase.bewonersVolgorde, MODE_PRIVATE);

        String[] bewoners = {sp.getString("B1",""), sp.getString("B2",""), sp.getString("B3",""), sp.getString("B4",""), sp.getString("B5","")};
        for(String bewoner : bewoners){
            if(bewoner.equals(person)){
                continue;
            }

            res += db.getSchoonmaakBierDAO().getSBBetweenBewoners(bewoner, person);
        }

        return res;
    }

    private int calculateTotalToReceive(String person){
        int res = 0;
        MMDatabase db = MMDatabase.getInstance(getApplicationContext());
        SharedPreferences sp = getSharedPreferences(MMDatabase.bewonersVolgorde, MODE_PRIVATE);
        String[] bewoners = {sp.getString("B1",""), sp.getString("B2",""), sp.getString("B3",""), sp.getString("B4",""), sp.getString("B5","")};
        for(String bewoner : bewoners){
            if(bewoner.equals(person)){
                continue;
            }

            res += db.getSchoonmaakBierDAO().getSBBetweenBewoners(person, bewoner);
        }

        return res;
    }

    // Gets new SB values and sets the proper colour
    @SuppressLint("SetTextI18n")
    public void updateSBText() {
        TextView B1give = findViewById(R.id.B1sbgeven);
        TextView B1receive = findViewById(R.id.B1sbkrijgen);

        TextView B2give = findViewById(R.id.B2sbgeven);
        TextView B2receive = findViewById(R.id.B2sbkrijgen);

        TextView B3give = findViewById(R.id.B3sbgeven);
        TextView B3receive = findViewById(R.id.B3sbkrijgen);

        TextView B4give = findViewById(R.id.B4sbgeven);
        TextView B4receive = findViewById(R.id.B4sbkrijgen);

        TextView B5give = findViewById(R.id.B5sbgeven);
        TextView B5receive = findViewById(R.id.B5sbkrijgen);

        MMDatabase db = MMDatabase.getInstance(this.getApplicationContext());

        SharedPreferences sharedPrefs = getSharedPreferences(MMDatabase.bewonersVolgorde, MODE_PRIVATE);

        int B1giveamount = calculateTotalToGive(sharedPrefs.getString("B1", ""));
        int B1textcolour = (0xff) << 24 | (min(B1giveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * B1giveamount, 0) & 0xff) << 8 | (0 & 0xff));
        B1give.setText(Integer.toString(B1giveamount));
        B1receive.setText(Integer.toString(calculateTotalToReceive(sharedPrefs.getString("B1",""))));
        B1give.setTextColor(B1textcolour);

        int B2giveamount = calculateTotalToGive(sharedPrefs.getString("B2", ""));
        int B2textcolour = (0xff) << 24 | (min(B2giveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * B2giveamount, 0) & 0xff) << 8 | (0 & 0xff));
        B2give.setText(Integer.toString(B2giveamount));
        B2receive.setText(Integer.toString(calculateTotalToReceive(sharedPrefs.getString("B2",""))));
        B2give.setTextColor(B2textcolour);

        int B3giveamount = calculateTotalToGive(sharedPrefs.getString("B3", ""));
        int B3textcolour = (0xff) << 24 | (min(B3giveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * B3giveamount, 0) & 0xff) << 8 | (0 & 0xff));
        B3give.setText(Integer.toString(B3giveamount));
        B3receive.setText(Integer.toString(calculateTotalToReceive(sharedPrefs.getString("B3",""))));
        B3give.setTextColor(B3textcolour);

        int B4giveamount = calculateTotalToGive(sharedPrefs.getString("B4", ""));
        int B4textcolour = (0xff) << 24 | (min(B4giveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * B4giveamount, 0) & 0xff) << 8 | (0 & 0xff));
        B4give.setText(Integer.toString(B4giveamount));
        B4receive.setText(Integer.toString(calculateTotalToReceive(sharedPrefs.getString("B4",""))));
        B4give.setTextColor(B4textcolour);

        int B5giveamount = calculateTotalToGive(sharedPrefs.getString("B5", ""));
        int B5textcolour = (0xff) << 24 | (min(B5giveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * B5giveamount, 0) & 0xff) << 8 | (0 & 0xff));
        B5give.setText(Integer.toString(B5giveamount));
        B5receive.setText(Integer.toString(calculateTotalToReceive(sharedPrefs.getString("B5",""))));
        B5give.setTextColor(B5textcolour);

        Log.d("AFTER SB TEXT", "UPDATE");
        db.printDB();

    }

    // Process requested bier
    public void sendBeerToDB() {

        EditText eTB4 = findViewById(R.id.editTextB4);
        EditText eTB1 = findViewById(R.id.editTextB1);
        EditText eTB5 = findViewById(R.id.editTextB5);
        EditText eTB2 = findViewById(R.id.editTextB2);
        EditText eTB3 = findViewById(R.id.editTextB3);

        SharedPreferences sharedPrefs = getSharedPreferences(MMDatabase.bewonersVolgorde, MODE_PRIVATE);

        // Get all orders that are more than 0
        Map<String, Integer> orders = new HashMap<String, Integer>();

        orders.put(sharedPrefs.getString("B4",""), Integer.valueOf(eTB4.getText().toString()));
        orders.put(sharedPrefs.getString("B1",""), Integer.valueOf(eTB1.getText().toString()));
        orders.put(sharedPrefs.getString("B5",""), Integer.valueOf(eTB5.getText().toString()));
        orders.put(sharedPrefs.getString("B2",""), Integer.valueOf(eTB2.getText().toString()));
        orders.put(sharedPrefs.getString("B3",""), Integer.valueOf(eTB3.getText().toString()));

                // db.exportToJSON("hello", getApplicationContext());

        Log.d("orders:", "" + orders.keySet().size());


        eTB3.setText("0");
        eTB4.setText("0");
        eTB2.setText("0");
        eTB1.setText("0");
        eTB5.setText("0");


        MMDatabase.getInstance(getApplicationContext()).processBeer(orders);
        //TODO: make sure sb also keeps track of total beer

    }


    /*
    *                   ALL IMAGES BUTTONS
    *                   For now these just call the respective add functions
    * */
    public void rowinBier(View view) {
        addRowin(view);

    }

    public void thijsBier(View view) {
        addThijs(view);
    }

    public void stevenBier(View view) {
        addsteven(view);
    }

    public void svenBier(View view) {
        addSven(view);
    }

    public void etienneBier(View view) {
        addEtienne(view);
    }

    /*
    *                   ALL ADD BUTTONS
    *
    * */

    public void addRowin(View view) {
        EditText editText = findViewById(R.id.editTextB4);
        int value = Integer.parseInt(editText.getText().toString());


        editText.setText("" + ++value);

        assert(value+1 == ++value);
    }

    public void addThijs(View view) {
        EditText editText = findViewById(R.id.editTextB1);
        int value = Integer.parseInt(editText.getText().toString());

        editText.setText("" + ++value);

        assert(value+1 == ++value);
    }

    public void addsteven(View view) {
        EditText editText = findViewById(R.id.editTextB5);
        int value = Integer.parseInt(editText.getText().toString());

        editText.setText("" + ++value);

        assert(value+1 == ++value);
    }

    public void addSven(View view) {
        EditText editText = findViewById(R.id.editTextB2);
        int value = Integer.parseInt(editText.getText().toString());

        editText.setText("" + ++value);

        assert(value+1 == ++value);
    }

    public void addEtienne(View view) {
        EditText editText = findViewById(R.id.editTextB3);
        int value = Integer.parseInt(editText.getText().toString());

        editText.setText("" + ++value);

    }


    /*
    *                   ALL SUBSTRACT BUTTONS
    * */

    public void substractRowin(View view) {
        EditText editText = findViewById(R.id.editTextB4);
        int value = Integer.parseInt(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractThijs(View view) {
        EditText editText = findViewById(R.id.editTextB1);
        int value = Integer.parseInt(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractsteven(View view) {
        EditText editText = findViewById(R.id.editTextB5);
        int value = Integer.parseInt(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractSven(View view) {
        EditText editText = findViewById(R.id.editTextB2);
        int value = Integer.parseInt(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractEtienne(View view) {
        EditText editText = findViewById(R.id.editTextB3);
        int value = Integer.parseInt(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }


    public int getTotalRequestedBeer(){
        EditText editTextRowin = findViewById(R.id.editTextB4);
        int valueRo = Integer.parseInt(editTextRowin.getText().toString());

        EditText editTextThijs = findViewById(R.id.editTextB1);
        int valueTh = Integer.parseInt(editTextThijs.getText().toString());

        EditText editTextsteven = findViewById(R.id.editTextB5);
        int valueWi = Integer.parseInt(editTextsteven.getText().toString());

        EditText editTextSven = findViewById(R.id.editTextB2);
        int valueSv = Integer.parseInt(editTextSven.getText().toString());

        EditText editTextEtienne = findViewById(R.id.editTextB3);
        int valueEt = Integer.parseInt(editTextEtienne.getText().toString());

        return valueRo + valueTh + valueWi + valueSv + valueEt;
    }
}

