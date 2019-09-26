package com.example.myfirstapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//TODO: Get schoonmaakbier queries, such as all bier and per bewoner

@Dao
public interface SchoonmaakBierDAO {

    @Query("SELECT * FROM SchoonmaakBier")
    List<SchoonmaakBier> getAllSchoonmaakBier();

    @Query("SELECT * FROM SchoonmaakBier WHERE toReceive LIKE :bewonerReceive ORDER BY bier DESC")
    List<SchoonmaakBier> getAllSchoonmaakBierBewonerReceives(String bewonerReceive);

    @Insert
    void insert(SchoonmaakBier... schoonmaakBier);

    @Update
    void update(SchoonmaakBier... schoonmaakBier);

    @Delete
    void delete(SchoonmaakBier schoonmaakBier);

    @Query("UPDATE SchoonmaakBier SET bier = :beers WHERE toReceive LIKE :receiver AND toGive LIKE :giver")
    public void setBeer(int beers, String receiver, String giver);


    @Query("SELECT SUM(bier) FROM SchoonmaakBier WHERE toReceive LIKE :receiver")
    public int getSchoonmaakbierSum(String receiver);
}