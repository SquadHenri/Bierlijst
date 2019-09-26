package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Bierlijst extends AppCompatActivity {

    private PopupWindow pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bierlijst);
        Log.e("HEPL", "BIERLIJST CREATEDDD!!");

        EditText editTextRowin = findViewById(R.id.editTextRowin);
        editTextRowin.setText("0");

        EditText editTextThijs = findViewById(R.id.editTextThijs);
        editTextThijs.setText("0");

        EditText editTextWilly = findViewById(R.id.editTextWilly);
        editTextWilly.setText("0");

        EditText editTextSven = findViewById(R.id.editTextSven);
        editTextSven.setText("0");

        EditText editTextEtienne = findViewById(R.id.editTextEtienne);
        editTextEtienne.setText("0");

        ImageButton IB_Willy = findViewById(R.id.IG_Willy);
        IB_Willy.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                if(getTotalRequestedBeer() > 0) {
                    Intent intent = new Intent(v.getContext(), bierPopup.class);
                    intent.putExtra("beer", getTotalRequestedBeer());
                    intent.putExtra("thrower", "Willy");
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
                    startActivity(intent);
                }

                return true;

            }
        });
    }



    // Process requested bier
    public void processBier(View view) {

        EditText editTextRowin = findViewById(R.id.editTextRowin);
        EditText editTextThijs = findViewById(R.id.editTextThijs);
        EditText editTextWilly = findViewById(R.id.editTextWilly);
        EditText editTextSven = findViewById(R.id.editTextSven);
        EditText editTextEtienne = findViewById(R.id.editTextEtienne);


                // Get all orders that are more than 0
                Map<String, Integer> orders = new HashMap<String, Integer>();

                orders.put("vG KVN", Integer.valueOf(editTextRowin.getText().toString()));
                orders.put("Thijs", Integer.valueOf(editTextThijs.getText().toString()));
                orders.put("Willy", Integer.valueOf(editTextWilly.getText().toString()));
                orders.put("Sven", Integer.valueOf(editTextSven.getText().toString()));
                orders.put("vG Tinder", Integer.valueOf(editTextEtienne.getText().toString()));

                // db.exportToJSON("hello", getApplicationContext());

                Log.d("orders:", "" + orders.keySet().size());

                MMDatabase.getInstance(getApplicationContext()).processBeer(orders);

        editTextEtienne.setText("0");
        editTextRowin.setText("0");
        editTextSven.setText("0");
        editTextThijs.setText("0");
        editTextWilly.setText("0");

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

    public void willyBier(View view) {
        addWilly(view);
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

    public void addWilly(View view) {
        EditText editText = findViewById(R.id.editTextWilly);
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

        assert(value+1 == ++value);
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

    public void substractWilly(View view) {
        EditText editText = findViewById(R.id.editTextWilly);
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

        EditText editTextWilly = findViewById(R.id.editTextWilly);
        int valueWi = Integer.valueOf(editTextWilly.getText().toString());

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
        EditText editTextWilly = findViewById(R.id.editTextWilly);
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
                EditText editTextWilly = findViewById(R.id.editTextWilly);
                EditText editTextSven = findViewById(R.id.editTextSven);
                EditText editTextEtienne = findViewById(R.id.editTextEtienne);

                orders.put("vG KVN", Integer.valueOf(editTextRowin.getText().toString()));
                orders.put("Thijs", Integer.valueOf(editTextThijs.getText().toString()));
                orders.put("Willy", Integer.valueOf(editTextWilly.getText().toString()));
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
                        db.getBewonerDAO().addBier(beers, key);
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
        editTextWilly.setText("0");

    }
 */