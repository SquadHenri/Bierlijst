package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.Math;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Bierlijst extends AppCompatActivity {


    // when you have 51 sb owed to others, the colour is completely red
    int SBMaxRange = 51;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bierlijst);
        Log.e("HEPL", "BIERLIJST CREATEDDD!!");

        EditText editTextRowin = findViewById(R.id.editTextRowin);
        editTextRowin.setText("0");

        EditText editTextThijs = findViewById(R.id.editTextThijs);
        editTextThijs.setText("0");

        EditText editTextsteven = findViewById(R.id.editTextsteven);
        editTextsteven.setText("0");

        EditText editTextSven = findViewById(R.id.editTextSven);
        editTextSven.setText("0");

        EditText editTextEtienne = findViewById(R.id.editTextEtienne);
        editTextEtienne.setText("0");


        // update Schoonmaakbier values and set colour
        updateSBText();

        ImageButton IB_steven = findViewById(R.id.IG_steven);
        IB_steven.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                if(getTotalRequestedBeer() > 0) {

                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", "Steven");
                    processBier();
                    startActivity(intent);


                }
                return true;
            }
        });

        ImageButton IB_Sven = findViewById(R.id.IG_Sven);
        IB_Sven.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(getTotalRequestedBeer() > 0) {


                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", "Sven");
                    processBier();
                    startActivity(intent);

                }
                return true;
            }
        });

        ImageButton IB_Thijs = findViewById(R.id.IG_Thijs);
        IB_Thijs.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(getTotalRequestedBeer() > 0) {


                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", "Thijs");
                    processBier();
                    startActivity(intent);


                }
                return true;
            }
        });

        ImageButton IB_Rowin = findViewById(R.id.IG_Rowin);
        IB_Rowin.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(getTotalRequestedBeer() > 0) {

                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", "vG KVN");
                    processBier();
                    startActivity(intent);


                }
                return true;
            }
        });

        ImageButton IB_Etienne = findViewById(R.id.IG_Etienne);
        IB_Etienne.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                if (getTotalRequestedBeer() > 0) {

                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", "vG Tinder");
                    processBier();
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
                    processBier();
                    updateSBText();
                    Context context = getApplicationContext();
                    CharSequence text = "Je hebt bier besteld! Lekker hoor";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

                Context context = getApplicationContext();
                CharSequence text = "Je moet wel bier strepen op iemand sjonnie";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            }
        });
    }

    @Override
    protected  void onResume() {
        super.onResume();

        updateSBText();
    }

    //TODO: totaal to give and to receive is WRONG


    // Gets new SB values and sets the proper colour
    public void updateSBText() {
        TextView thijsgive = findViewById(R.id.thijssbgeven);
        TextView thijsreceive = findViewById(R.id.thijssbkrijgen);

        TextView svengive  = findViewById(R.id.svensbgeven);
        TextView svenreceive = findViewById(R.id.svensbkrijgen);

        TextView tindergive = findViewById(R.id.tindersbgeven);
        TextView tinderreceive = findViewById(R.id.tindersbkrijgen);

        TextView kvngive = findViewById(R.id.kvnsbgeven);
        TextView kvnreceive = findViewById(R.id.kvnsbkrijgen);

        TextView stevengive = findViewById(R.id.stevensbgeven);
        TextView stevenreceive = findViewById(R.id.stevensbkrijgen);

        MMDatabase db = MMDatabase.getInstance(this.getApplicationContext());

        int thijsgiveamount = db.getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum("Thijs");
        int thijstextcolour = (0xff) << 24 | (min(thijsgiveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * thijsgiveamount, 0) & 0xff) << 8 | (0 & 0xff));
        thijsgive.setText(Integer.toString(thijsgiveamount));
        thijsreceive.setText(Integer.toString(db.getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum("Thijs")));
        thijsgive.setTextColor(thijstextcolour);

        int svengiveamount = db.getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum("Sven");
        int sventextcolour = (0xff) << 24 | (min(svengiveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * svengiveamount, 0) & 0xff) << 8 | (0 & 0xff));
        svengive.setText(Integer.toString(svengiveamount));
        svenreceive.setText(Integer.toString(db.getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum("Sven")));
        svengive.setTextColor(sventextcolour);

        int tindergiveamount = db.getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum("vG Tinder");
        int tindertextcolour = (0xff) << 24 | (min(tindergiveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * tindergiveamount, 0) & 0xff) << 8 | (0 & 0xff));
        tindergive.setText(Integer.toString(tindergiveamount));
        tinderreceive.setText(Integer.toString(db.getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum("vG Tinder")));
        tindergive.setTextColor(tindertextcolour);

        int kvngiveamount = db.getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum("vG KVN");
        int kvntextcolour = (0xff) << 24 | (min(kvngiveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * kvngiveamount, 0) & 0xff) << 8 | (0 & 0xff));
        kvngive.setText(Integer.toString(kvngiveamount));
        kvnreceive.setText(Integer.toString(db.getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum("vG KVN")));
        kvngive.setTextColor(kvntextcolour);

        int stevengiveamount = db.getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum("Steven");
        int steventextcolour = (0xff) << 24 | (min(stevengiveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * stevengiveamount, 0) & 0xff) << 8 | (0 & 0xff));
        stevengive.setText(Integer.toString(stevengiveamount));
        stevenreceive.setText(Integer.toString(db.getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum("Steven")));
        stevengive.setTextColor(steventextcolour);

        Log.d("AFTER SB TEXT", "UPDATE");
        db.printDB();

    }

    // Process requested bier
    public void processBier() {

        EditText editTextRowin = findViewById(R.id.editTextRowin);
        EditText editTextThijs = findViewById(R.id.editTextThijs);
        EditText editTextsteven = findViewById(R.id.editTextsteven);
        EditText editTextSven = findViewById(R.id.editTextSven);
        EditText editTextEtienne = findViewById(R.id.editTextEtienne);




        // Get all orders that are more than 0
                Map<String, Integer> orders = new HashMap<String, Integer>();

                orders.put("vG KVN", Integer.valueOf(editTextRowin.getText().toString()));
                orders.put("Thijs", Integer.valueOf(editTextThijs.getText().toString()));
                orders.put("Steven", Integer.valueOf(editTextsteven.getText().toString()));
                orders.put("Sven", Integer.valueOf(editTextSven.getText().toString()));
                orders.put("vG Tinder", Integer.valueOf(editTextEtienne.getText().toString()));

                // db.exportToJSON("hello", getApplicationContext());

                Log.d("orders:", "" + orders.keySet().size());


        editTextEtienne.setText("0");
        editTextRowin.setText("0");
        editTextSven.setText("0");
        editTextThijs.setText("0");
        editTextsteven.setText("0");


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
    *                   // TODO: check if value is actually has a value
    * */

    public void addRowin(View view) {
        EditText editText = findViewById(R.id.editTextRowin);
        int value = Integer.valueOf(editText.getText().toString());


        editText.setText("" + ++value);

        assert(value+1 == ++value);
    }

    public void addThijs(View view) {
        EditText editText = findViewById(R.id.editTextThijs);
        int value = Integer.valueOf(editText.getText().toString());

        editText.setText("" + ++value);

        assert(value+1 == ++value);
    }

    public void addsteven(View view) {
        EditText editText = findViewById(R.id.editTextsteven);
        int value = Integer.valueOf(editText.getText().toString());

        editText.setText("" + ++value);

        assert(value+1 == ++value);
    }

    public void addSven(View view) {
        EditText editText = findViewById(R.id.editTextSven);
        int value = Integer.valueOf(editText.getText().toString());

        editText.setText("" + ++value);

        assert(value+1 == ++value);
    }

    public void addEtienne(View view) {
        EditText editText = findViewById(R.id.editTextEtienne);
        int value = Integer.valueOf(editText.getText().toString());

        editText.setText("" + ++value);

    }


    /*
    *                   ALL SUBSTRACT BUTTONS
    * */

    public void substractRowin(View view) {
        EditText editText = findViewById(R.id.editTextRowin);
        int value = Integer.valueOf(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractThijs(View view) {
        EditText editText = findViewById(R.id.editTextThijs);
        int value = Integer.valueOf(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractsteven(View view) {
        EditText editText = findViewById(R.id.editTextsteven);
        int value = Integer.valueOf(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractSven(View view) {
        EditText editText = findViewById(R.id.editTextSven);
        int value = Integer.valueOf(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }

    public void substractEtienne(View view) {
        EditText editText = findViewById(R.id.editTextEtienne);
        int value = Integer.valueOf(editText.getText().toString());

        if(value == 0) {
            return;
        } else {
            editText.setText("" + --value);

            assert(value-1 == --value);
        }
    }


    public int getTotalRequestedBeer(){
        EditText editTextRowin = findViewById(R.id.editTextRowin);
        int valueRo = Integer.valueOf(editTextRowin.getText().toString());

        EditText editTextThijs = findViewById(R.id.editTextThijs);
        int valueTh = Integer.valueOf(editTextThijs.getText().toString());

        EditText editTextsteven = findViewById(R.id.editTextsteven);
        int valueWi = Integer.valueOf(editTextsteven.getText().toString());

        EditText editTextSven = findViewById(R.id.editTextSven);
        int valueSv = Integer.valueOf(editTextSven.getText().toString());

        EditText editTextEtienne = findViewById(R.id.editTextEtienne);
        int valueEt = Integer.valueOf(editTextEtienne.getText().toString());

        return valueRo + valueTh + valueWi + valueSv + valueEt;
    }
}

/*

// Process requested bier
    public synchronized void processBier(View view) {

        EditText editTextRowin = findViewById(R.id.editTextRowin);
        EditText editTextThijs = findViewById(R.id.editTextThijs);
        EditText editTextsteven = findViewById(R.id.editTextsteven);
        EditText editTextSven = findViewById(R.id.editTextSven);
        EditText editTextEtienne = findViewById(R.id.editTextEtienne);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                // Get all orders that are more than 0
                Map<String, Integer> orders = new HashMap<String, Integer>();


                // TODO: possibly make this a bit more efficient. its also ugly as fuck
                EditText editTextRowin = findViewById(R.id.editTextRowin);
                EditText editTextThijs = findViewById(R.id.editTextThijs);
                EditText editTextsteven = findViewById(R.id.editTextsteven);
                EditText editTextSven = findViewById(R.id.editTextSven);
                EditText editTextEtienne = findViewById(R.id.editTextEtienne);

                orders.put("vG KVN", Integer.valueOf(editTextRowin.getText().toString()));
                orders.put("Thijs", Integer.valueOf(editTextThijs.getText().toString()));
                orders.put("Steven", Integer.valueOf(editTextsteven.getText().toString()));
                orders.put("Sven", Integer.valueOf(editTextSven.getText().toString()));
                orders.put("vG Tinder", Integer.valueOf(editTextEtienne.getText().toString()));

                MMDatabase db = MMDatabase.getInstance(getApplicationContext());

                // db.exportToJSON("hello", getApplicationContext());

                Log.d("orders:", "" + orders.keySet().size());

                // For each person that want a beer, check where to deduct or add the beer to
                // The key here is a string of the name of the person requesting, the value is the amount of beers requested
                for (String key : orders.keySet()) {
                    int beers = orders.get(key);

                    if(beers == 0) {
                        Log.d("no beers ordered by", key);
                        Log.d("=======================", "=========");
                        continue;
                    }

                    Log.d("processing beers for", key);
                    Log.d("Beers requested", "" + beers);

                    assert (beers > 0);

                    // First check if this person still has to receive beer from other people

                    // A list of all relations with other bewoners. So any beer the key should receive is in this list
                    List<SchoonmaakBier> schoonmaakbier = db.getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(key);

                    for (SchoonmaakBier sb : schoonmaakbier) {
                        Log.d("from " + sb.getToGive() + " to " + sb.getToReceive() + " beers", "" + sb.getBeer());
                        if (sb.getBeer() == 0) {
                            // The key gets no beer from this person
                            continue;
                        } else if (sb.getBeer() >= beers) {
                            // All the requested beers can be substracted from this person
                            db.getSchoonmaakBierDAO().setBeer(sb.getBeer() - beers, key, sb.getToGive());
                            Log.d("beer set to " + (sb.getBeer() - beers),sb.getToGive() + " -> " + key);
                            beers = 0;
                        } else if (sb.getBeer() < beers) {
                            // some can be substracted from this person
                            beers = beers - sb.getBeer();
                            Log.d("beers substracted: ", "" + sb.getBeer());

                            db.getSchoonmaakBierDAO().setBeer(beers - sb.getBeer(), key, sb.getToGive());
                        }

                        Log.d("beers to be accounted", "" + beers);

                        if (beers == 0) {
                            break;
                        }

                    }
                    if (beers == 0) {
                        Log.d("beers are accounted as", "schoonmaakbier");
                        Log.d("=======================", "=========");
                        continue;
                    } else {
                        db.getBewonerDAO().addGestreeptBier(beers, key);
                        Log.d(beers+ " beers added as streept", "to " + key);
                        Log.d("=======================", "=========");
                    }
                    Log.d("gestreept bier voor " + key, "" + db.getBewonerDAO().getGestreeptBier(key));
                }
            }
        });

        editTextEtienne.setText("0");
        editTextRowin.setText("0");
        editTextSven.setText("0");
        editTextThijs.setText("0");
        editTextsteven.setText("0");

    }
 */