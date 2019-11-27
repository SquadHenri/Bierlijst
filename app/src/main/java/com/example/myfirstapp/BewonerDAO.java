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

    @Query("SELECT * FROM Bewoner WHERE isHuidigeBewoner=true")
    public abstract List<Bewoner> getAllHuidigeBewoners();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Bewoner... bewoner);

    @Update
    public abstract void update(Bewoner... bewoner);

    @Delete
    public abstract void delete(Bewoner bewoner);

    @Query("UPDATE Bewoner SET gestreeptBier = :value WHERE naam == :naam")
    public abstract void updateBier(int value, String naam);

    //
    @Query("UPDATE Bewoner SET gestreeptBier = gestreeptBier + :value WHERE naam == :naam")
    public abstract void addGestreeptBier(int value, String naam);

    @Query("UPDATE Bewoner SET gedronkenBier = gedronkenBier + :value WHERE naam == :naam")
    public abstract void addGedronkenBier(int value, String naam);

    @Query("UPDATE Bewoner SET schoonmaakbierOpJou = schoonmaakbierOpJou + :value WHERE naam == :naam")
    public abstract void addschoonmaakbierOpJou(int value, String naam);

    @Query("UPDATE Bewoner SET raakGegooid = raakGegooid + :value where naam like :naam")
    public abstract void addRaakGegooid(int value, String naam);

    // Does not include schoonmaakbier
    @Query("SELECT gestreeptBier FROM bewoner WHERE naam == :naam")
    public abstract int getGestreeptBier(String naam);


    @Query("UPDATE Bewoner SET isAdmin = 0 WHERE naam == :oldAdmin")
    public abstract void removeOldAdminStatus(String oldAdmin);

    @Query("UPDATE Bewoner SET isAdmin = 1 WHERE naam == :newAdmin")
    public abstract void setNewAdmin(String newAdmin);

    @Transaction
    public void changeAdmin(String oldAdmin, String newAdmin){
        removeOldAdminStatus(oldAdmin);
        setNewAdmin(newAdmin);
    }

}