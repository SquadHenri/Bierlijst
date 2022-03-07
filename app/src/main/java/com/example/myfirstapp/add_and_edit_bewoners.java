package com.example.myfirstapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class add_and_edit_bewoners extends AppCompatActivity { //}, AdapterView.OnItemClickListener {

    private ArrayList<String> allBewoners;
    private ArrayAdapter<String> allBewonersAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit_bewoners);

        // Set BewonersList
        ListView listView = (ListView) findViewById(R.id.AllBewonersList);
        MMDatabase db = MMDatabase.getInstance(getApplicationContext());
        List<Bewoner> bewoners =  db.getBewonerDAO().getAllBewoners();
        allBewoners = new ArrayList<String>();
        {
            int i = 0;
            for (Bewoner bewoner : bewoners) {
                allBewoners.add(bewoner.getNaam());
                i++;
            }

        }

        allBewonersAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, allBewoners);

        assert listView != null;
        listView.setAdapter(allBewonersAdapter);

        // Set Spinners B1-5
        Spinner B1 = (Spinner) findViewById(R.id.spinnerB1);
        Spinner B2 = (Spinner) findViewById(R.id.spinnerB2);
        Spinner B3 = (Spinner) findViewById(R.id.spinnerB3);
        Spinner B4 = (Spinner) findViewById(R.id.spinnerB4);
        Spinner B5 = (Spinner) findViewById(R.id.spinnerB5);

        B1.setAdapter(allBewonersAdapter);
        B2.setAdapter(allBewonersAdapter);
        B3.setAdapter(allBewonersAdapter);
        B4.setAdapter(allBewonersAdapter);
        B5.setAdapter(allBewonersAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences(MMDatabase.bewonersVolgorde, Context.MODE_PRIVATE);
        setSpinnerToValue(sharedPreferences.getString("B1", ""), B1);
        setSpinnerToValue(sharedPreferences.getString("B2", ""), B2);
        setSpinnerToValue(sharedPreferences.getString("B3", ""), B3);
        setSpinnerToValue(sharedPreferences.getString("B4", ""), B4);
        setSpinnerToValue(sharedPreferences.getString("B5", ""), B5);

        // Set Spinner voor waardes aanpassen
        Spinner Waarde = (Spinner) findViewById(R.id.spinner_Voornaam_getallen);
        Waarde.setAdapter(allBewonersAdapter);

        Spinner vGVoornaam = (Spinner) findViewById(R.id.vGVoornaam);
        vGVoornaam.setAdapter(allBewonersAdapter);

        Spinner SB_Krijgt = (Spinner) findViewById(R.id.spinner_SB_Krijgt);
        SB_Krijgt.setAdapter(allBewonersAdapter);

        Spinner SB_Geeft = (Spinner) findViewById(R.id.spinner_SB_Geeft);
        SB_Geeft.setAdapter(allBewonersAdapter);

        Spinner vGNaam = (Spinner) findViewById(R.id.spinner_Voornaam_vG);
        vGNaam.setAdapter(allBewonersAdapter);

    }


    public void OnClickAddBewoner(View view)
    {
        EditText editTextVoornaam = (EditText) findViewById(R.id.editTextVoornaam);
        CheckBox isVgCheck = (CheckBox)  findViewById(R.id.isVg);
        boolean isvG = isVgCheck.isChecked();

        EditText vGBijnaam = (EditText) findViewById(R.id.editTextBijnaam);

        if(editTextVoornaam.getText().toString().equals("Voornaam"))
        {
            Toast.makeText(this, "WTF hij heet \"Voornaam\"? Geloof je het zelf?", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!vGBijnaam.getText().toString().contains("vG"))
        {
            Toast.makeText(this, "\"vG\" moet in de naam zitten. Dus het moet zijn, bijvoorbeeld: \"vG Sjonnie\" of wat dan ook. Er staat nu nergens vG dus je mag hem niet toevoegen. ", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Wil je " + editTextVoornaam.getText() + " toevoegen?");

        builder.setMessage("Weet je het zeker dat je \"" + editTextVoornaam.getText() + "\" wilt toevoegen? Check de vG bijnaam goed als je die hebt ingevuld. De bijnaam moet zijn \"vG Blabla\". vG moet dus in de naam staan. ");


        builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                Log.d("USEFUL", "Yes clicked");
                MMDatabase db = MMDatabase.getInstance(getApplicationContext());
                Bewoner newBewoner;
                if(vGBijnaam.getText().toString().equals("vG bijnaam (optioneel)")){
                    // No vG bijnaam given
                    newBewoner = db.addBewoner(
                        editTextVoornaam.getText().toString(),
                            isvG,
                            vGBijnaam.getText().toString()
                    );
                }
                else
                {
                    // No vG bijnaam given
                    newBewoner = db.addBewoner(editTextVoornaam.getText().toString(), isvG, "vG");
                }

                allBewonersAdapter.insert(newBewoner.getNaam(), allBewonersAdapter.getCount());

                allBewonersAdapter.notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Nee", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d("USEFUL", "NO CLICKED");
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void volgordeToepassenClicked(View view){
        // Checken of er geen overlap is
        Spinner B1 = (Spinner) findViewById(R.id.spinnerB1);
        Spinner B2 = (Spinner) findViewById(R.id.spinnerB2);
        Spinner B3 = (Spinner) findViewById(R.id.spinnerB3);
        Spinner B4 = (Spinner) findViewById(R.id.spinnerB4);
        Spinner B5 = (Spinner) findViewById(R.id.spinnerB5);

        String[] spinnerValues = new String[]{(String) B1.getSelectedItem(), (String) B2.getSelectedItem(), (String) B3.getSelectedItem(), (String) B4.getSelectedItem(), (String) B5.getSelectedItem()};
        Log.e("SPINNER:", Arrays.toString(spinnerValues));
        for(int i = 0; i < spinnerValues.length; i++){
            Log.d("Comparing", spinnerValues[i] + "with: ");
            for(int j = i+1; j < spinnerValues.length; j++){
                Log.e("With:", spinnerValues[j]);
                if(spinnerValues[i].equals(spinnerValues[j])) {
                    // Duplicate found
                    Toast.makeText(getApplicationContext(), "1 Persoon kan er niet twee keer in zitten. Zorg dat er geen dubbele inzitten. ", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

        // SharedPrefs aanpassen
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Wil je de volgorde aanpassen?");

        builder.setMessage("Weet je zeker dat de volgorde klopt?");


        builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                Log.d("USEFUL", "Yes clicked. Passing aan volgorde");
                SharedPreferences sharedPrefs = getSharedPreferences(MMDatabase.bewonersVolgorde, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("B1", spinnerValues[0]);
                editor.putString("B2", spinnerValues[1]);
                editor.putString("B3", spinnerValues[2]);
                editor.putString("B4", spinnerValues[3]);
                editor.putString("B5", spinnerValues[4]);

                editor.apply();

                // Update database
                MMDatabase db = MMDatabase.getInstance(getApplicationContext());
                db.getBewonerDAO().resetHuidigeBewoners();
                for(String naam : spinnerValues){
                    db.getBewonerDAO().setHuidigeBewoner(naam);
                }

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Nee", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d("USEFUL", "NO CLICKED. Not aanpassing volgorde.");
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }

    public void setSpinnerToValue(String Naam, Spinner target)
    {
        for (int position = 0; position < allBewonersAdapter.getCount(); position++)
        {
            if(allBewonersAdapter.getItem(position).equals(Naam))
            {
                target.setSelection(position);
                return;
            }
        }
    }

    public void changeBewonerValue(View view){
        Spinner s_bewoner = (Spinner) findViewById(R.id.spinner_Voornaam_getallen);
        Spinner waardeKeuze = (Spinner) findViewById(R.id.spinner_WaardeKeuze);

        String bewoner = s_bewoner.getSelectedItem().toString();
        MMDatabase db = MMDatabase.getInstance(getApplicationContext());

        EditText val = (EditText) findViewById(R.id.waarde_Keuze);
        int value = Integer.parseInt(val.getText().toString());

        int keuze = waardeKeuze.getSelectedItemPosition();
        switch(keuze){
            case 0: //gegooid all-time
                db.getBewonerDAO().setGegooid(value, bewoner);
                break;
            case 1: // gegooid deze HV
                db.getBewonerDAO().setGegooidHV(value, bewoner);
                break;
            case 2:// raak gegooid deze HV
                db.getBewonerDAO().setRaakGegooidHV(value, bewoner);
                break;
            case 4:// raak gegooid all time
                db.getBewonerDAO().setRaakGegooid(value, bewoner);
                break;
        }
    }

    public void changevG(View view){
        Spinner vGVoornaam = (Spinner) findViewById(R.id.vGVoornaam);

        String bewoner = vGVoornaam.getSelectedItem().toString();

        MMDatabase db = MMDatabase.getInstance(getApplicationContext());

        CheckBox isvG = (CheckBox) findViewById(R.id.isVgStatus);
        db.getBewonerDAO().setIsvG(bewoner, isvG.isChecked());
    }

    public void changeSB(View view){
        Spinner Spin_SB_Krijgt = (Spinner) findViewById(R.id.spinner_SB_Krijgt);
        Spinner Spin_SB_Geeft = (Spinner) findViewById(R.id.spinner_SB_Geeft);

        String bewoner_krijgt = Spin_SB_Krijgt.getSelectedItem().toString();
        String bewoner_geeft = Spin_SB_Geeft.getSelectedItem().toString();

        EditText val = (EditText) findViewById(R.id.SB_Waarde);
        int value = Integer.parseInt(val.getText().toString());

        MMDatabase db = MMDatabase.getInstance(getApplicationContext());
        db.getSchoonmaakBierDAO().setBeer(value, bewoner_krijgt, bewoner_geeft);
    }

    public void changevGNaam(View view){
        Spinner spin_voornaam = (Spinner) findViewById(R.id.spinner_Voornaam_vG);
        String bewoner = spin_voornaam.getSelectedItem().toString();

        EditText et_newNaam = (EditText) findViewById(R.id.vGNieuweNaam);
        String newNaam = et_newNaam.getText().toString();

        MMDatabase db = MMDatabase.getInstance(getApplicationContext());
        db.getBewonerDAO().setvGNaam(bewoner, newNaam);
    }

}