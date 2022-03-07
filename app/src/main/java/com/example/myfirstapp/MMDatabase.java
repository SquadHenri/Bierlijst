package com.example.myfirstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

// TODO: backup database

@Database(entities = {Bewoner.class, SchoonmaakBier.class},
        version = 1,
        exportSchema = false)
public abstract class MMDatabase extends RoomDatabase {


    // The only instance
    private static MMDatabase Database;

    private String beer_data_filename = "BierGebruik.txt";
    private String sb_data_filename = "sbData.txt";
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MM/DATA/";

    public static final String bewonersVolgorde = "BewonersVolgorde";

    public static synchronized MMDatabase getInstance(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(bewonersVolgorde, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        if(!sharedPrefs.contains("initialized"))
        {
            // It has not been initialized yet
            editor.putString("B1", "Etienne");
            editor.putString("B2", "Steven");
            editor.putString("B3", "Daniël");
            editor.putString("B4", "Twan");
            editor.putString("B5", "Roel");


            editor.putBoolean("initialized", true);
            editor.commit();
        }


        if (Database == null) {
            Log.d("Database is null.", "Building database:");
            // There is no database yet
            Database = Room.databaseBuilder(context.getApplicationContext(), MMDatabase.class, "db")
                    .allowMainThreadQueries()
                    .build();
            // .allowMainThreadQueries() is a dirty way to be able to get data from my db.
            // TODO: switch from allowMainThreadQueries to rxJava. even if its an assload of work with weird syntax

            Log.d("PopulateDatabase", "InitialData:");
            Database.populateDatabase();
            Database.printDB();
        }
        return Database;
    }

    public static String getDateAndTime() {
        return Calendar.getInstance().getTime().toString();
    }

    public abstract BewonerDAO getBewonerDAO();

    public abstract SchoonmaakBierDAO getSchoonmaakBierDAO();

    protected synchronized void populateDatabase() {

        // Dirty way to check if this database already has data. I could not find a good way to do this
        List<Bewoner> listBewoners = getBewonerDAO().getAllBewoners();
        if (!listBewoners.isEmpty()) {
            return;
        }

        // Insert bewoners


        getBewonerDAO().insert(new Bewoner("Etienne", "00-00-0000", false, true));
        getBewonerDAO().insert(new Bewoner("Steven", "00-00-0000", false, true));
        getBewonerDAO().insert(new Bewoner("Daniël", "00-00-0000", true, true));
        getBewonerDAO().insert(new Bewoner("Twan", "00-00-0000", true, true));
        getBewonerDAO().insert(new Bewoner("Roel", "00-00-0000", true, true));

        getBewonerDAO().setvGNaam("Daniël", "vG Slap");
        getBewonerDAO().setvGNaam("Twan", "vG Malibu");
        getBewonerDAO().setvGNaam("Roel", "vG Badmuts");


        Log.d("Bewoners", "geinstert");

        List<Bewoner> list = getBewonerDAO().getAllBewoners();
        Log.d("we hebben: ", "" + list.size() + " bewoners");

        // Insert Gooi statistieken
        getBewonerDAO().setRaakGegooid(45, "Etienne");
        getBewonerDAO().setGegooid(394, "Etienne");
        getBewonerDAO().setRaakGegooid(54, "Steven");
        getBewonerDAO().setGegooid(685, "Steven");

        Log.d("Statistieken", "Gooi statiestieken geinstert.");
        // Insert schoonmaakbier relaties

        Log.d("SchoonmaakBier", "Inserting all relaties");

        String et = "Etienne";
        String st = "Steven";
        String da = "Daniël";
        String tw = "Twan";
        String ro = "Roel";

        for(Bewoner bewoner : listBewoners)
        {
            for(Bewoner bewoner1 : listBewoners)
            {
                if(bewoner.getNaam().equals(bewoner1.getNaam()))
                {
                    continue;
                }

                getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, bewoner.getNaam(), bewoner1.getNaam(),0));
                getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, bewoner1.getNaam(), bewoner.getNaam(), 0));
            }


        }

//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Etienne", "Steven", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Etienne", "Daniël", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Etienne", "Twan", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Etienne", "Roel", 0));
//
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "Etienne", 10));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "Daniël", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "Twan", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "Roel", 0));
//
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Daniël", "Etienne", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Daniël", "Steven", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Daniël", "Twan", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Daniël", "Roel", 0));
//
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Twan", "Etienne", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Twan", "Steven", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Twan", "Daniël", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Twan", "Roel", 0));
//
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Roel", "Etienne", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Roel", "Steven", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Roel", "Daniël", 0));
//        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Roel", "Twan", 0));

        Log.d("Schoonmaakbier", "All relaties inserted. ");

        Log.d("Database", "Database populated");

    }

    protected synchronized Bewoner addBewoner(String naam, boolean isVg)
    {
        // Create new Bewoner
        Bewoner newBewoner = new Bewoner(naam, "00-00-0000", isVg, false);
        getBewonerDAO().insert(newBewoner);

        // Add schoonmaakbier relations
        List<Bewoner> listBewoners = getBewonerDAO().getAllBewoners();
        for(Bewoner bewoner : listBewoners)
        {
            if(bewoner.equals(newBewoner))
            {
                continue;
            }

            getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, bewoner.getNaam(), newBewoner.getNaam(),0));
            getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, newBewoner.getNaam(), bewoner.getNaam(), 0));

        }

        return newBewoner;
    }

    protected synchronized Bewoner addBewoner(String naam, boolean isVg, String vgNaam)
    {
        Bewoner newBewoner = addBewoner(naam, isVg);
        getBewonerDAO().setvGNaam(naam, vgNaam);
        return newBewoner;
    }

    protected void printDB() {
        Log.d("Printing database", "gl");

        List<Bewoner> bewoners = getBewonerDAO().getAllBewoners();

        // Total beers owed to others
        Map<String, Integer> sbBeerOwed = new HashMap<>();

        // Total beers to be received
        Map<String, Integer> sbBeerReceives = new HashMap<>();

        for (Bewoner bewoner : bewoners) {
            Log.d("Printing sbbier for", bewoner.getNaam());
            List<SchoonmaakBier> sblist = getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(bewoner.getNaam());
            for (SchoonmaakBier sb : sblist) {
                Log.d("From" + sb.getToGive(), Integer.toString(sb.getBeer()));

                if (sbBeerReceives.containsKey(bewoner.getNaam())) {
                    sbBeerReceives.put(bewoner.getNaam(), sbBeerReceives.get(bewoner.getNaam()) + sb.getBeer());
                } else {
                    sbBeerReceives.put(bewoner.getNaam(), sb.getBeer());
                }

                if (sbBeerOwed.containsKey(sb.getToGive())) {
                    sbBeerOwed.put(sb.getToGive(), sbBeerOwed.get(sb.getToGive()) + sb.getBeer());
                } else {
                    sbBeerOwed.put(sb.getToGive(), sb.getBeer());
                }

            }
        }


        for (String naam : sbBeerReceives.keySet()) {
            Log.d("RECEIVE AND OWE FOR", naam);
            Log.d("receive", "ex:" + sbBeerReceives.get(naam) +
                    "ac:" + getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum(naam));

            Log.d("owe", "ex:" + sbBeerOwed.get(naam) +
                    "ac:" + getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum(naam));

        }
    }

    // Process requested beers function
    public synchronized void processBeer(Map<String, Integer> orders) {
        Log.d("Processing beer for", "multiple people");

        Map<String, Integer> orders_out = new HashMap<String, Integer>(orders);
        // For each person that wants a beer, check where to deduct or add the beer to
        // The key here is a string of the name of the person requesting, the value is the amount of beers requested
        for (String key : orders.keySet()) {
            int beers = orders.get(key);
            Log.d(beers + " beers ordered by", key);
            if (beers == 0) {
                orders_out.remove(key);
                continue;
            }

            // do not write process beer because this happens below this line
            processBeer(key, beers, false);
        }

        // Write orders to file
        beerDataToFile(orders_out);

    }

    public synchronized void processBeer(String bewoner, int beersToBeAccounted, boolean writeToFile) {
        if (writeToFile) {
            beerDataToFile(bewoner, beersToBeAccounted);
        }

        int beers = beersToBeAccounted;

        Log.d("PROCESSING " + beers, "beer for" + bewoner);

        getBewonerDAO().addGedronkenBier(beers, bewoner);


        List<SchoonmaakBier> sbList = getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(bewoner);

        for (SchoonmaakBier sb : sbList) {
            Log.d("beers left", Integer.toString(beers));
            if (sb.getBeer() < 0) {
                Log.e(bewoner + sb.getBeer() + " from", sb.getToGive());
                continue;
            }
            if (sb.getBeer() == 0) {
                Log.d(bewoner + " no sb from", sb.getToGive());
                continue;
            }

            // Bewoners receives some sb from sb.getToGive()

            if (beers <= sb.getBeer()) {
                // all beers can be substracted from sb beer
                Log.d("Subbing rest " + beers + " sb from ", sb.getToGive());
                getSchoonmaakBierDAO().setBeer(sb.getBeer() - beers, sb.getToReceive(), sb.getToGive());
                getBewonerDAO().addschoonmaakbierOpJou(beers, sb.getToGive());
                Log.d("sb.getbeer()-beers", Integer.toString(sb.getBeer() - beers));

                //there are no more beers left
                return;
            } else if (beers > sb.getBeer()) {
                //Some can be substracted here
                Log.d("Some sb subbed", sb.getBeer() + " from " + sb.getToGive());

                getSchoonmaakBierDAO().setBeer(0, sb.getToReceive(), sb.getToGive());
                getBewonerDAO().addschoonmaakbierOpJou(beers - sb.getBeer(), sb.getToGive());
                Log.d("beers-sb.getBeer", Integer.toString(beers - sb.getBeer()));
                beers -= sb.getBeer();
            }

        }

        Log.d("No sb left", "adding gestreept");

        getBewonerDAO().addGestreeptBier(beers, bewoner);

    }

    // Write sb beer data to file
    protected void SbDataToFile(Map<String, Integer> sbData) {
        Log.d("Writing String to file", "WOOT");
        try {
            // Create directory
            File dir = new File(path);
            if (dir.mkdirs()) {
                Log.d("Directory creatededs:", "YEBABE");
            }


            File file = new File(path + sb_data_filename);
            if (!file.exists()) {
                if (file.createNewFile()) {
                    Log.d("FILE SUCCESFULLY CREATE", "GODDOMN");
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(("Op " + getDateAndTime() + ", is er schoonmaakbier toegevoegd:\n").getBytes());

            for (String key : sbData.keySet()) {
                int sb = sbData.get(key);
                fileOutputStream.write(("\t\t" + sb + " op: " + key + "\n").getBytes());
            }

            fileOutputStream.close();

        } catch (IOException e) {
            Log.e("IO", e.getMessage());
        }

    }

    // Write ordered beer data to file
    protected void beerDataToFile(Map<String, Integer> orders) {
        Log.d("Writing Data to file", "WOOT");
        try {
            // Create directory
            File dir = new File(path);
            if (dir.mkdirs()) {
                Log.d("Directory creatededs:", "YEBABE");
            }


            File file = new File(path + beer_data_filename);
            if (!file.exists()) {
                if (file.createNewFile()) {
                    Log.d("FILE SUCCESFULLY CREATE", "GODDOMN");
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(("Op " + getDateAndTime() + ", werd er bier besteld door:\n").getBytes());

            for (String key : orders.keySet()) {
                int beers = orders.get(key);
                fileOutputStream.write(("\t\t" + beers + " door: " + key + "\n").getBytes());
            }

            fileOutputStream.close();

        } catch (IOException e) {
            Log.e("IO", e.getMessage());
        }

    }

    // Write ordered beer data to file
    protected void beerDataToFile(String bewoner, int beer) {
        Log.d("Writing Data to file", "WOOT");
        try {
            // Create directory
            File dir = new File(path);
            if (dir.mkdirs()) {
                Log.d("Directory creatededs:", "YEBABE");
            }


            File file = new File(path + beer_data_filename);
            if (!file.exists()) {
                if (file.createNewFile()) {
                    Log.d("FILE SUCCESFULLY CREATE", "GODDOMN");
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(("Op " + getDateAndTime() + ", werd er bier besteld door:\n").getBytes());

            fileOutputStream.write(("\t\t" + beer + " door: " + bewoner + "\n").getBytes());

            fileOutputStream.close();

        } catch (IOException e) {
            Log.e("IO", e.getMessage());
        }

    }

    protected void makeSnapshot(boolean OrderedBeerDataDeleted) {
        try {
            // Create directory
            File dir = new File(path);
            if (dir.mkdirs()) {
                Log.d("Directory creatededs:", "YEBABE");
            }

            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

            int currentyear = calendar.get(Calendar.YEAR);
            int currentmonth = calendar.get(Calendar.MONTH) + 1;
            int currentday = calendar.get(Calendar.DAY_OF_MONTH);

            String filename = currentday + "-" + currentmonth + "-" + currentyear;
            if (OrderedBeerDataDeleted) {
                filename += "D";
            }
            File file = new File(path + filename + ".txt");
            if (!file.exists()) {
                if (file.createNewFile()) {
                    Log.d("FILE SUCCESFULLY CREATE", "GODDOMN");
                }
            } else {
                // File exists
                file = new File(path + filename + "(1).txt");
                //TODO: This limits it to only 2 snapshots per day. Except when the data is deleted
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        Log.d("Succesfully created", "second file");
                    }
                }
            }

            List<Bewoner> bewoners = getBewonerDAO().getAllBewoners();

            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(("Op " + getDateAndTime() + ", is deze snapshot gemaakt.\n").getBytes());
            if(OrderedBeerDataDeleted){
                fileOutputStream.write(("Hierbij is ook het gestreepte bier verwijderd.\n").getBytes());
            }

            // This loop writes Schoonmaakbier to file
            for(Bewoner bewoner : bewoners){
                // Schoonmaakbier:
                fileOutputStream.write((bewoner.getNaam() + "Krijgt schoonmaakbier van:\n").getBytes());
                List<SchoonmaakBier> sblist = getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(bewoner.getNaam());

                for(SchoonmaakBier sb : sblist){
                    fileOutputStream.write(("\t\tVan " + sb.getToGive() + ":" + Integer.toString(sb.getBeer()) +"\n").getBytes());
                }
            }

            // Split up the for loops so there are sections in the file
            for(Bewoner bewoner : bewoners){
                fileOutputStream.write(("Hier de belangrijke gegevens per bewoner:\n").getBytes());
                int GedronkenBier = getBewonerDAO().getGedronkenBier(bewoner.getNaam());
                int GestreeptBier = getBewonerDAO().getGestreeptBier(bewoner.getNaam());
                int SchoonmaakBierOpJoU = getBewonerDAO().getSchoonmaakBierOpJou(bewoner.getNaam());
                int RaakGegooid = getBewonerDAO().getRaakGegooid(bewoner.getNaam());

                fileOutputStream.write(("\t" + bewoner.getNaam() + ":\n").getBytes());
                fileOutputStream.write(("\t\t" + "Gedronken Bier = " + Integer.toString(GedronkenBier)+"\n").getBytes());
                fileOutputStream.write(("\t\t" + "Gestreept Bier = " + Integer.toString(GestreeptBier)+"\n").getBytes());
                fileOutputStream.write(("\t\t" + "Schoonmaakbier op jou gestreept = " + Integer.toString(SchoonmaakBierOpJoU)+"\n").getBytes());
                fileOutputStream.write(("\t\t" + "Raak gegooid = " + Integer.toString(RaakGegooid)+"\n").getBytes());
            }

            // Another Split: now for beer throwing stats:
            for(Bewoner bewoner : bewoners){
                fileOutputStream.write(("Hier de bier-gooi gegevens per bewoner:\n").getBytes());

                int gegooidHV = getBewonerDAO().getGegooidHV(bewoner.getNaam());
                int raakgegooidHV = getBewonerDAO().getRaakGegooidHV(bewoner.getNaam());
                int gegooid = getBewonerDAO().getGegooid(bewoner.getNaam());
                int raakgegooid = getBewonerDAO().getRaakGegooid(bewoner.getNaam());

                fileOutputStream.write(("\t" + bewoner.getNaam() + ":\n").getBytes());
                fileOutputStream.write(("\t\t" + "Gegooid deze HV = " + Integer.toString(gegooidHV)+"\n").getBytes());
                fileOutputStream.write(("\t\t" + "Raak gegooid deze HV = " + Integer.toString(raakgegooidHV)+"\n").getBytes());
                fileOutputStream.write(("\t\t" + "Gegooid all-time: = " + Integer.toString(gegooid)+"\n").getBytes());
                fileOutputStream.write(("\t\t" + "Raak gegooid all-time = " + Integer.toString(raakgegooid)+"\n").getBytes());
            }

            fileOutputStream.close();

        } catch (IOException e) {
            Log.e("IO", e.getMessage());
        }
    }

    // Clears all beer data except for hits and sb beer
    protected void clearOrderedBeer() {
        Log.d("CLEAR ORDERED", "BEER");

        getBewonerDAO().resetSchoonmaakbierOpBewoner();
        getBewonerDAO().resetGestreeptBier();
    }

}

