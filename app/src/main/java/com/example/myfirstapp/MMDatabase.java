package com.example.myfirstapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: backup database

@Database(entities =  {Bewoner.class, SchoonmaakBier.class},
                        version = 1,
                        exportSchema = false)
public abstract class MMDatabase extends RoomDatabase {


    // The only instance
    private static MMDatabase Database;

    public static synchronized MMDatabase getInstance(Context context) {
        if (Database == null) {
            // There is no database yet
            Database = Room.databaseBuilder(context.getApplicationContext(), MMDatabase.class, "db")
                    .allowMainThreadQueries()
                    .build();
                    // .allowMainThreadQueries() is a dirty way to be able to get data from my db.
                    // TODO: switch from allowMainThreadQueries to rxJava. even if its an assload of work with weird syntax

            Database.populateDatabaseRandomData();
            Database.printDB();
        }
        return Database;
    }

    public abstract BewonerDAO getBewonerDAO();
    public abstract SchoonmaakBierDAO getSchoonmaakBierDAO();

    protected synchronized void populateDatabase() {

                    // Dirty way to check if this database already has data. I could not find a good way to do this
                    List<Bewoner> listBewoners = getBewonerDAO().getAllBewoners();
                    if(!listBewoners.isEmpty()) {
                        return;
                    }


                    Log.d("IK BENEEN NIEUWE THREAD", "Ik Ga nu de database vullen met start data");


                    // Insert bewoners

                    getBewonerDAO().insert(new Bewoner("Steven", "00-00-0000", true, true));
                    getBewonerDAO().insert(new Bewoner("Thijs", "21-12-1995", false, true));
                    getBewonerDAO().insert(new Bewoner("Sven", "22-02-1996", false, true));
                    getBewonerDAO().insert(new Bewoner("vG Tinder", "10-4-1998", false, true));
                    getBewonerDAO().insert(new Bewoner("vG KVN", "20-11-1997", false, true));

                    Log.d("Bewoners", "geinstert");

                    List<Bewoner> list = getBewonerDAO().getAllBewoners();
                    Log.d("we hebben: ", "" + list.size() + " bewoners");

                    // Insert schoonmaakbier relaties

                    Log.d("SchoonmaakBier", "Inserting all relaties");


                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "Sven", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "vG Tinder", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "vG KVN", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "Steven", 0));

                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "Thijs", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "vG Tinder", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "vG KVN", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "Steven", 0));

                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "Thijs", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "Sven", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "vG KVN", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "Steven", 0));

                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "Thijs", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "Sven", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "vG Tinder", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "Steven", 0));

                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "Thijs", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "Sven", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "vG Tinder", 0));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "vG KVN", 0));

                    Log.d("Schoonmaakbier", "All relaties inserted. ");

                    Log.d("Database", "Database populated");

    }

    protected synchronized void populateDatabaseRandomData() {

        // Dirty way to check if this database already has data. I could not find a good way to do this
        List<Bewoner> listBewoners = getBewonerDAO().getAllBewoners();
        if(!listBewoners.isEmpty()) {
            return;
        }


        Log.d("IK BENEEN NIEUWE THREAD", "Ik Ga nu de database vullen met start data");


        // Insert bewoners

        getBewonerDAO().insert(new Bewoner("Steven", "00-00-0000", true, true));
        getBewonerDAO().insert(new Bewoner("Thijs", "21-12-1995", false, true));
        getBewonerDAO().insert(new Bewoner("Sven", "22-02-1996", false, true));
        getBewonerDAO().insert(new Bewoner("vG Tinder", "10-4-1998", false, true));
        getBewonerDAO().insert(new Bewoner("vG KVN", "20-11-1997", false, true));

        Log.d("Bewoners", "geinstert");

        List<Bewoner> list = getBewonerDAO().getAllBewoners();
        Log.d("we hebben: ", "" + list.size() + " bewoners");

        // Insert schoonmaakbier relaties

        Log.d("SchoonmaakBier", "Inserting all relaties");


        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "Sven", 5));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "vG Tinder", 6));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "vG KVN", 3));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "Steven", 25));

        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "Thijs", 12));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "vG Tinder", 1));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "vG KVN", 0));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "Steven", 24));

        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "Thijs", 4));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "Sven", 5));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "vG KVN", 1));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "Steven", 1));

        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "Thijs", 1));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "Sven", 3));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "vG Tinder", 2));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "Steven", 4));

        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "Thijs", 1));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "Sven", 3));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "vG Tinder", 5));
        getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Steven", "vG KVN", 9));

        Log.d("Schoonmaakbier", "All relaties inserted. ");

        Log.d("Database", "Database populated");

    }

    protected void printDB(){
        Log.d("=================","=============");
        Log.d("Printing database", "gl");

        List<Bewoner> bewoners = getBewonerDAO().getAllBewoners();

        // Total beers owed to others
        Map<String, Integer> sbBeerOwed = new HashMap<>();

        // Total beers to be received
        Map<String, Integer> sbBeerReceives = new HashMap<>();

        for(Bewoner bewoner : bewoners) {
            Log.d("Printing sbbier for", bewoner.getNaam());
            List<SchoonmaakBier> sblist = getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(bewoner.getNaam());
            for(SchoonmaakBier sb : sblist) {
                Log.d("From" + sb.getToGive(), Integer.toString(sb.getBeer()));

                if(sbBeerReceives.containsKey(bewoner.getNaam())){
                    sbBeerReceives.put(bewoner.getNaam(), sbBeerReceives.get(bewoner.getNaam())+sb.getBeer());
                } else {
                    sbBeerReceives.put(bewoner.getNaam(), sb.getBeer());
                }

                if(sbBeerOwed.containsKey(sb.getToGive())){
                    sbBeerOwed.put(sb.getToGive(), sbBeerOwed.get(sb.getToGive())+sb.getBeer());
                } else {
                   sbBeerOwed.put(sb.getToGive(), sb.getBeer());
                }

            }
        }



        for(String naam : sbBeerReceives.keySet()){
            Log.d("RECEIVE AND OWE FOR", naam);
            Log.d("receive", "ex:" + Integer.toString(sbBeerReceives.get(naam)) +
                    "ac:" + Integer.toString(getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum(naam)));
            Log.d("owe", "ex:"+ Integer.toString(sbBeerOwed.get(naam)) +
                    "ac:" + Integer.toString(getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum(naam)));
        }
        Log.d("-------------","------------");
        Log.d("=================","=============");
    }

    // TODO: Finish export to JSON
    public void exportToJSON(String filename, final Context context){
        Log.d("export to JSON", "Creating thread");
        /*        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {*/
        AsyncTask.execute(new Runnable(){
            @Override
            public void run() {
                Log.d("Exporting to JSON", " Gathering data");

                // First gather data
                List<Bewoner> bewoners = getBewonerDAO().getAllBewoners();
                List<SchoonmaakBier> sb = getSchoonmaakBierDAO().getAllSchoonmaakBier();

                // Convert to JSON
                Log.d("converting to JSON", " Gathering data");
                Gson gson = new Gson();
                Log.d("" + gson.toJson(bewoners), "");
                Log.d("" + gson.toJson(sb), "");

                String res = gson.toJson(bewoners) + gson.toJson(sb);
                // export file
                Log.d("Trying to try", "========= ");


                OutputStreamWriter output = null;
                try {
                    Log.d("trying to write to file","");
                    output = new OutputStreamWriter(context.openFileOutput(Calendar.getInstance().getInstance().getTime() + "", Context.MODE_PRIVATE));
                    output.write(gson.toJson(bewoners) + gson.toJson(sb));
                    output.close();
                } catch (FileNotFoundException e) {
                    Log.d("FileNotFoundException", " faf");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("IOEXCEPTION", "eH");
                    e.printStackTrace();
                }


                Log.d("FUCK ME THEN", "========= ");
            }
        });
    }


    // TODO: should this destroy the previous database? or override it? Delete for now
    // TODO: make sure this is safe, this function is easy to abuse
    // TODO: possibly remove this function
    public static void importJSON(String filename){

        AsyncTask.execute(new Runnable(){
            @Override
            public void run(){

            }
        });
    }

    // Process requested beers function
    public synchronized void processBeer(Map<String, Integer> orders) {
        Log.d("5555555555555", "55555555555");
        Log.d("Processing beer for","multiple people");

        // For each person that wants a beer, check where to deduct or add the beer to
        // The key here is a string of the name of the person requesting, the value is the amount of beers requested
        for (String key : orders.keySet()) {
            int beers = orders.get(key);
            Log.d(beers + " beers ordered by", key);
            if(beers == 0) {
                continue;
            }

            processBeer(key, beers);
        }

    }

    public synchronized void processBeer(String bewoner, int beersToBeAccounted){
        int beers = beersToBeAccounted;

        Log.d("0000000000000","0000000");
        Log.d("PROCESSING " + Integer.toString(beers),"beer for" + bewoner);

        getBewonerDAO().addGedronkenBier(beers, bewoner);


        List<SchoonmaakBier> sbList = getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(bewoner);

        for(SchoonmaakBier sb : sbList) {
            Log.d("beers left", Integer.toString(beers));
            if(sb.getBeer() < 0){
                Log.e(bewoner + Integer.toString(sb.getBeer()) + " from", sb.getToGive());
                continue;
            }
            if(sb.getBeer() == 0){
                Log.d(bewoner + " no sb from", sb.getToGive());
                continue;
            }

            // Bewoners receives some sb from sb.getToGive()

            if(beers <= sb.getBeer()) {
                // all beers can be substracted from sb beer
                Log.d("Subbing rest " + Integer.toString(beers) + " sb from ", sb.getToGive());
                getSchoonmaakBierDAO().setBeer(sb.getBeer() - beers, sb.getToReceive(), sb.getToGive());
                Log.d("sb.getbeer()-beers", Integer.toString(sb.getBeer()-beers));

                //there are no more beers left
                return;
            }
            else if(beers > sb.getBeer()){
                //Some can be substracted here
                Log.d("Some sb subbed", Integer.toString(sb.getBeer()) + " from " + sb.getToGive());

                getSchoonmaakBierDAO().setBeer(0, sb.getToReceive(), sb.getToGive());
                Log.d("beers-sb.getBeer", Integer.toString(beers-sb.getBeer()));
                beers -= sb.getBeer();
            }

        }

        Log.d("No sb left", "adding gestreept");

        getBewonerDAO().addGestreeptBier(beers, bewoner);

    }

    public synchronized void processBeer_deprecated(String bewoner, int beers) {
        Log.d("processing " + beers + " beer for", " "+ bewoner);
        Log.d("BEFORE PROCESS BEER",bewoner);
        printDB();

        if(beers == 0) {
            Log.d("No beers requested", "loser");
            return;
        }

        List<SchoonmaakBier> schoonmaakbier = getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(bewoner);

        // Add gedronken bier
        getBewonerDAO().addGedronkenBier(beers, bewoner);

        for (SchoonmaakBier sb : schoonmaakbier) {
            Log.d("from " + sb.getToGive() + " to " + sb.getToReceive() + " beers", "" + sb.getBeer());
            if (sb.getBeer() == 0) {
                // The key gets no beer from this person
                continue;
            } else if (sb.getBeer() >= beers) {
                // All the requested beers can be substracted from this person
                getSchoonmaakBierDAO().setBeer(sb.getBeer() - beers, bewoner, sb.getToGive());
                getBewonerDAO().addschoonmaakbierOpJou(beers, bewoner);
                Log.d("beer set to " + (sb.getBeer() - beers),sb.getToGive() + " -> " + bewoner);
                beers = 0;
            } else if (sb.getBeer() < beers) {
                // some can be substracted from this person
                beers = beers - sb.getBeer();
                Log.d("beers substracted: ", "" + sb.getBeer());

                getSchoonmaakBierDAO().setBeer(beers - sb.getBeer(), bewoner, sb.getToGive());
                getBewonerDAO().addschoonmaakbierOpJou(beers - sb.getBeer(), bewoner);
            }

            Log.d("beers to be accounted", "" + beers);

            if (beers == 0) {
                break;
            }

        }
        if (beers == 0) {
            Log.d("beers are accounted as", "schoonmaakbier");
            Log.d("=======================", "=========");
            return;
        } else {
            getBewonerDAO().addGestreeptBier(beers, bewoner);
            Log.d(beers+ " beers added as streept", "to " + bewoner);
            Log.d("=======================", "=========");
        }
        Log.d("gestreept bier voor " + bewoner, "" + getBewonerDAO().getGestreeptBier(bewoner));

        Log.d("AFTER PROCESS BEER",bewoner);
        printDB();
    }


}

/*
* // Process requested beers function
    public synchronized void processBeer(Map<String, Integer> orders) {

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
            List<SchoonmaakBier> schoonmaakbier = getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(key);

            for (SchoonmaakBier sb : schoonmaakbier) {
                Log.d("from " + sb.getToGive() + " to " + sb.getToReceive() + " beers", "" + sb.getBeer());
                if (sb.getBeer() == 0) {
                    // The key gets no beer from this person
                    continue;
                } else if (sb.getBeer() >= beers) {
                    // All the requested beers can be substracted from this person
                    getSchoonmaakBierDAO().setBeer(sb.getBeer() - beers, key, sb.getToGive());
                    Log.d("beer set to " + (sb.getBeer() - beers),sb.getToGive() + " -> " + key);
                    beers = 0;
                } else if (sb.getBeer() < beers) {
                    // some can be substracted from this person
                    beers = beers - sb.getBeer();
                    Log.d("beers substracted: ", "" + sb.getBeer());

                    getSchoonmaakBierDAO().setBeer(beers - sb.getBeer(), key, sb.getToGive());
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
                getBewonerDAO().addGestreeptBier(beers, key);
                Log.d(beers+ " beers added as streept", "to " + key);
                Log.d("=======================", "=========");
            }
            Log.d("gestreept bier voor " + key, "" + getBewonerDAO().getGestreeptBier(key));
        }
* */