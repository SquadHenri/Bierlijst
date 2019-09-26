package com.example.myfirstapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Database;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

// TODO: backup database

@Database(entities =  {Bewoner.class, SchoonmaakBier.class},
                        version = 1)
public abstract class MMDatabase extends RoomDatabase {


    // The only instance
    private static MMDatabase Database;

    private boolean wasPopulated = false;

    public static synchronized MMDatabase getInstance(Context context) {
        if (Database == null) {
            // There is no database yet
            Database = Room.databaseBuilder(context.getApplicationContext(), MMDatabase.class, "db")
                    .build();

            Database.populateDatabase();
        }
        return Database;
    }

    public abstract BewonerDAO getBewonerDAO();
    public abstract SchoonmaakBierDAO getSchoonmaakBierDAO();

    private synchronized void populateDatabase() {
        if(wasPopulated == true) {
            return;
        } else {
            wasPopulated = true;
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {


                    Log.d("IK BENEEN NIEUWE THREAD", "Ik Ga nu de database vullen met start data");


                    // Insert bewoners

                    getBewonerDAO().insert(new Bewoner("Willy", "00-00-0000", true, true));
                    getBewonerDAO().insert(new Bewoner("Thijs", "21-12-1995", false, true));
                    getBewonerDAO().insert(new Bewoner("Sven", "22-02-1996", false, true));
                    getBewonerDAO().insert(new Bewoner("vG Tinder", "10-4-1998", true, true));
                    getBewonerDAO().insert(new Bewoner("vG KVN", "20-11-1997", true, true));

                    Log.d("Bewoners", "geinstert");

                    List<Bewoner> list = getBewonerDAO().getAllBewoners();
                    Log.d("we hebben: ", "" + list.size() + " bewoners");

                    // Insert schoonmaakbier relaties

                    Log.d("SchoonmaakBier", "Inserting all relaties");

                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Willy", "Thijs", 1));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Willy", "Sven", 2));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Willy", "vG Tinder", 3));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Willy", "vG KVN", 4));

                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "Willy", 5));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "Sven", 6));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "vG Tinder", 7));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Thijs", "vG KVN", 8));

                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "Willy", 9));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "Thijs", 10));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "vG Tinder", 11));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "Sven", "vG KVN", 12));

                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "Willy", 13));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "Thijs", 14));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "Sven", 15));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG Tinder", "vG KVN", 16));

                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "Willy", 17));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "Thijs", 18));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "Sven", 19));
                    getSchoonmaakBierDAO().insert(new SchoonmaakBier(0, "vG KVN", "vG Tinder", 20));

                    Log.d("Schoonmaakbier", "All relaties inserted. ");

                    Log.d("Database", "Database populated");

                }
            });
        }
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
                    output = new OutputStreamWriter(context.openFileOutput(Calendar.getInstance().getInstance().getTime() + "", context.MODE_PRIVATE));
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
    public synchronized void processBeer(final Map<String, Integer> requestBeer) {

        AsyncTask.execute((new Runnable(){
            @Override
            public void run() {

                Map<String, Integer> orders = requestBeer;
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
                        getBewonerDAO().addBier(beers, key);
                        Log.d(beers+ " beers added as streept", "to " + key);
                        Log.d("=======================", "=========");
                    }
                    Log.d("gestreept bier voor " + key, "" + getBewonerDAO().getGestreeptBier(key));
                }
            }
        }));

    }

    public synchronized void processBeer(final String bewonerf, final int beerf) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String bewoner = bewonerf;
                int beers = beerf;
                Log.d("processing " + beers + " beer for", " "+ bewoner);

                if(beers == 0) {
                    Log.d("No beers requested", "loser");
                    return;
                }

                List<SchoonmaakBier> schoonmaakbier = getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(bewoner);


                for (SchoonmaakBier sb : schoonmaakbier) {
                    Log.d("from " + sb.getToGive() + " to " + sb.getToReceive() + " beers", "" + sb.getBeer());
                    if (sb.getBeer() == 0) {
                        // The key gets no beer from this person
                        continue;
                    } else if (sb.getBeer() >= beers) {
                        // All the requested beers can be substracted from this person
                        getSchoonmaakBierDAO().setBeer(sb.getBeer() - beers, bewoner, sb.getToGive());
                        Log.d("beer set to " + (sb.getBeer() - beers),sb.getToGive() + " -> " + bewoner);
                        beers = 0;
                    } else if (sb.getBeer() < beers) {
                        // some can be substracted from this person
                        beers = beers - sb.getBeer();
                        Log.d("beers substracted: ", "" + sb.getBeer());

                        getSchoonmaakBierDAO().setBeer(beers - sb.getBeer(), bewoner, sb.getToGive());
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
                    getBewonerDAO().addBier(beers, bewoner);
                    Log.d(beers+ " beers added as streept", "to " + bewoner);
                    Log.d("=======================", "=========");
                }
                Log.d("gestreept bier voor " + bewoner, "" + getBewonerDAO().getGestreeptBier(bewoner));
            }
        });
    }
}
