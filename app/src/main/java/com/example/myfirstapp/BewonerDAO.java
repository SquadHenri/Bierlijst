package com.example.myfirstapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BewonerDAO {

    @Query("SELECT * FROM Bewoner")
    List<Bewoner> getAllBewoners();

    @Query("SELECT * FROM Bewoner WHERE isHuidigeBewoner=true")
    List<Bewoner> getAllHuidigeBewoners();

    @Insert
    void insert(Bewoner... bewoner);

    @Update
    void update(Bewoner... bewoner);

    @Delete
    void delete(Bewoner bewoner);

    @Query("UPDATE Bewoner SET gestreeptBier = :value WHERE naam LIKE :naam")
    void updateBier(int value, String naam);

    @Query("UPDATE Bewoner SET gestreeptBier = gestreeptBier + :value WHERE naam LIKE :naam")
    void addBier(int value, String naam);

    @Query("UPDATE Bewoner SET raakGegooid = raakGegooid + :value where naam like :naam")
    void addRaakGegooid(int value, String naam);

    // Does not include schoonmaakbier
    @Query("SELECT gestreeptBier FROM bewoner WHERE naam LIKE :naam")
    int getGestreeptBier(String naam);



}