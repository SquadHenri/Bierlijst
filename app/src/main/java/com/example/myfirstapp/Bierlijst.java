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
        Log.e("BierLijst:", "BIERLIJST CREATEDDD!!");

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
                    sendBeerToDB();
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
                    sendBeerToDB();
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
                    sendBeerToDB();
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
                    intent.putExtra("thrower", "Rowin");
                    sendBeerToDB();
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
                    intent.putExtra("thrower", "Etienne");
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

        TextView etiennegive = findViewById(R.id.etiennesbgeven);
        TextView etiennereceive = findViewById(R.id.etiennesbkrijgen);

        TextView rowingive = findViewById(R.id.rowinsbgeven);
        TextView rowinreceive = findViewById(R.id.rowinsbkrijgen);

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

        int etiennegiveamount = db.getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum("Etienne");
        int etiennetextcolour = (0xff) << 24 | (min(etiennegiveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * etiennegiveamount, 0) & 0xff) << 8 | (0 & 0xff));
        etiennegive.setText(Integer.toString(etiennegiveamount));
        etiennereceive.setText(Integer.toString(db.getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum("Etienne")));
        etiennegive.setTextColor(etiennetextcolour);

        int rowingiveamount = db.getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum("Rowin");
        int rowintextcolour = (0xff) << 24 | (min(rowingiveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * rowingiveamount, 0) & 0xff) << 8 | (0 & 0xff));
        rowingive.setText(Integer.toString(rowingiveamount));
        rowinreceive.setText(Integer.toString(db.getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum("Rowin")));
        rowingive.setTextColor(rowintextcolour);

        int stevengiveamount = db.getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum("Steven");
        int steventextcolour = (0xff) << 24 | (min(stevengiveamount*5,255) & 0xff) << 16 | ((max(255 - 5 * stevengiveamount, 0) & 0xff) << 8 | (0 & 0xff));
        stevengive.setText(Integer.toString(stevengiveamount));
        stevenreceive.setText(Integer.toString(db.getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum("Steven")));
        stevengive.setTextColor(steventextcolour);

        Log.d("AFTER SB TEXT", "UPDATE");
        db.printDB();

    }

    // Process requested bier
    public void sendBeerToDB() {

        EditText editTextRowin = findViewById(R.id.editTextRowin);
        EditText editTextThijs = findViewById(R.id.editTextThijs);
        EditText editTextsteven = findViewById(R.id.editTextsteven);
        EditText editTextSven = findViewById(R.id.editTextSven);
        EditText editTextEtienne = findViewById(R.id.editTextEtienne);




        // Get all orders that are more than 0
        Map<String, Integer> orders = new HashMap<String, Integer>();

        orders.put("Rowin", Integer.valueOf(editTextRowin.getText().toString()));
        orders.put("Thijs", Integer.valueOf(editTextThijs.getText().toString()));
        orders.put("Steven", Integer.valueOf(editTextsteven.getText().toString()));
        orders.put("Sven", Integer.valueOf(editTextSven.getText().toString()));
        orders.put("Etienne", Integer.valueOf(editTextEtienne.getText().toString()));

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
    *
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

