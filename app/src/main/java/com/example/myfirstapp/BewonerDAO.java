package com.example.myfirstapp;

import android.util.Log;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public abstract class BewonerDAO {

    @Query("SELECT * FROM Bewoner")
    public abstract List<Bewoner> getAllBewoners();

    // 1 == True
    @Query("SELECT * FROM Bewoner WHERE isHuidigeBewoner=1")
    public abstract List<Bewoner> getAllHuidigeBewoners();

    @Query("UPDATE Bewoner SET isHuidigeBewoner=1")
    public abstract void resetHuidigeBewoners();

    @Query("Update Bewoner SET isHuidigeBewoner=1 WHERE naam == :naam")
    public abstract void setHuidigeBewoner(String naam);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Bewoner... bewoner);

    @Update
    public abstract void update(Bewoner... bewoner);

    @Delete
    public abstract void delete(Bewoner bewoner);

    // GESTREEPT BIER

    @Query("UPDATE Bewoner SET gestreeptBier = :value WHERE naam == :naam")
    public abstract void updateBier(int value, String naam);

    @Query("UPDATE Bewoner SET gestreeptBier = gestreeptBier + :value WHERE naam == :naam")
    public abstract void addGestreeptBier(int value, String naam);

    // Does not include schoonmaakbier
    @Query("SELECT gestreeptBier FROM bewoner WHERE naam == :naam")
    public abstract int getGestreeptBier(String naam);

    //
    @Query("UPDATE bewoner SET gestreeptBier = :value WHERE naam == :naam")
    public abstract int setGestreeptBier(String naam, int value);

    @Query("UPDATE Bewoner SET gestreeptBier = 0")
    public abstract void resetGestreeptBier();

    // GEDRONKEN BIER

    @Query("UPDATE Bewoner SET GedronkenBier = :value WHERE naam == :naam")
    public abstract void setGedronkenBier(int value, String naam);

    @Query("UPDATE Bewoner SET GedronkenBier = 0")
    public abstract void resetGedronkenBier();

    @Query("UPDATE Bewoner SET gedronkenBier = gedronkenBier + :value WHERE naam == :naam")
    public abstract void addGedronkenBier(int value, String naam);

    @Query("SELECT gedronkenBier FROM bewoner WHERE naam == :naam")
    public abstract int getGedronkenBier(String naam);

    // Schoonmaakbier gedronken op jou

    @Query("UPDATE Bewoner SET schoonmaakbierOpJou = schoonmaakbierOpJou + :value WHERE naam == :naam")
    public abstract void addschoonmaakbierOpJou(int value, String naam);

    @Query("UPDATE Bewoner SET schoonmaakbierOpJou = 0")
    public abstract void resetSchoonmaakbierOpBewoner();

    @Query("SELECT schoonmaakbierOpJou FROM Bewoner where naam = :naam")
    public abstract int getSchoonmaakBierOpJou(String naam);

    // Raak Gegooid & Raak Gegooid HV

    @Query("UPDATE Bewoner SET raakGegooid = raakGegooid + :value where naam == :naam")
    public abstract void addRaakGegooid(int value, String naam);

    @Query("SELECT raakGegooid FROM bewoner where naam == :naam")
    public abstract int getRaakGegooid(String naam);

    @Query("UPDATE Bewoner SET raakGegooidHV = raakGegooidHV + :value where naam == :naam")
    public abstract void addRaakGegooidHV(int value, String naam);

    @Query("SELECT raakGegooidHV FROM bewoner where naam == :naam")
    public abstract int getRaakGegooidHV(String naam);

    @Query("UPDATE Bewoner SET raakGegooidHV = :value WHERE naam == :naam")
    public abstract void setRaakGegooidHV(int value, String naam);

    @Query("UPDATE Bewoner SET raakGegooid = :value WHERE naam == :naam")
    public abstract void setRaakGegooid(int value, String naam);

    @Query("UPDATE Bewoner SET Gegooid = :value WHERE naam == :naam")
    public abstract void setGegooid(int value, String naam);

    @Query("UPDATE Bewoner SET raakGegooidHV = :value WHERE naam == :naam")
    public abstract void setGegooidHV(int value, String naam);

    // RaakGegooid en RaakGegooidHV voor het huis

    @Query("SELECT sum(raakGegooid) from Bewoner WHERE isHuidigeBewoner = 1")
    public abstract int getRaakGegooid();

    @Query("SELECT sum(raakGegooidHV) from Bewoner")
    public abstract int getRaakGegooidHV();

    // Gegooid & GegooidHV

    @Query("UPDATE Bewoner SET gegooid = gegooid + :value where naam == :naam")
    public abstract void addGegooid(int value, String naam);

    @Query("SELECT gegooid FROM bewoner where naam == :naam")
    public abstract int getGegooid(String naam);

    @Query("UPDATE Bewoner SET gegooidHV = gegooidHV + :value where naam == :naam")
    public abstract void addGegooidHV(int value, String naam);

    @Query("SELECT gegooidHV FROM bewoner where naam == :naam")
    public abstract int getGegooidHV(String naam);

    @Query("UPDATE Bewoner SET raakGegooidHV = 0, gegooidHV = 0")
    public abstract void resetGooienHV();

    // Gegooid en GegooidHV voor het huis

    @Query("SELECT sum(Gegooid) from Bewoner")
    public abstract int getGegooid();

    @Query("SELECT sum(GegooidHV) from Bewoner")
    public abstract int getGegooidHV();

    // vGNaam

    @Query("UPDATE Bewoner SET vGNaam = :vGNaam WHERE naam == :naam")
    public abstract void setvGNaam(String naam, String vGNaam);

    @Query("Select vGNaam FROM Bewoner WHERE naam == :naam")
    public abstract String getvGNaam(String naam);

    // vG
    @Query("SELECT vG FROM BEWONER WHERE naam == :naam")
    public abstract boolean getIsvG(String naam);

    @Query("Update Bewoner SET vG = :isVg WHERE naam == :naam")
    public abstract void setIsvG(String naam, boolean isVg);

    // ADMIN

    @Query("UPDATE Bewoner SET isAdmin = 0 WHERE naam == :oldAdmin")
    public abstract void removeOldAdminStatus(String oldAdmin);

    @Query("UPDATE Bewoner SET isAdmin = 1 WHERE naam == :newAdmin")
    public abstract void setNewAdmin(String newAdmin);

    //

    @Transaction
    public void changeAdmin(String oldAdmin, String newAdmin){
        removeOldAdminStatus(oldAdmin);
        setNewAdmin(newAdmin);
    }

    @Transaction
    public void UpdateRaakGegooidAndGegooid(String naam, int gegooid, int raakGegooid){
        Log.d("UpdateRaakGegooid", "AndGegooid");
        String gegooidTotaal = Integer.toString(gegooid);
        String raakGegooidTotaal = Integer.toString(raakGegooid);
        Log.d("Gegooid", gegooidTotaal);
        Log.d("raakGegooid", raakGegooidTotaal);
        if(raakGegooid > 0){
            addGegooid(gegooid, naam);
            addRaakGegooid(raakGegooid, naam);

            addGegooidHV(gegooid, naam);
            addRaakGegooidHV(raakGegooid, naam);

            gegooidTotaal = Integer.toString(getGegooid(naam));
            raakGegooidTotaal = Integer.toString(getRaakGegooid(naam));
            Log.d("New Gegooid", gegooidTotaal);
            Log.d("New raakGegooid", raakGegooidTotaal);

        } else {
            addGegooid(gegooid, naam);
            addGegooidHV(gegooid, naam);
        }
    }

}