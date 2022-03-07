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

    @Query("SELECT * FROM SchoonmaakBier WHERE toReceive == :bewonerReceive ORDER BY bier DESC")
    List<SchoonmaakBier> getAllSchoonmaakBierBewonerReceives(String bewonerReceive);

    @Query("SELECT * FROM SchoonmaakBier WHERE toGive == :bewonerToGive ORDER BY bier DESC")
    List<SchoonmaakBier> getAllSchoonmaakBierBewonerOwesToOthers(String bewonerToGive);

    @Insert
    void insert(SchoonmaakBier... schoonmaakBier);

    @Update
    void update(SchoonmaakBier... schoonmaakBier);

    @Delete
    void delete(SchoonmaakBier schoonmaakBier);

    @Query("UPDATE SchoonmaakBier SET bier = :beers WHERE toReceive == :receiver AND toGive == :giver")
    void setBeer(int beers, String receiver, String giver);

    @Query("SELECT bier FROM SchoonmaakBier WHERE toReceive == :receiver AND toGive == :giver")
    int getSBBetweenBewoners(String receiver, String giver);


    @Query("SELECT SUM(bier) FROM SchoonmaakBier WHERE toReceive == :receiver")
    int getSchoonmaakBiertoReceiveSum(String receiver);

    @Query("SELECT SUM(bier) FROM SchoonmaakBier WHERE toGive == :giver")
    int getSchoonmaakBiertoGiveSum(String giver);

    @Query("UPDATE SchoonmaakBier SET bier = bier + 1 WHERE toGive == :giver")
    void addSchoonmaakbier(String giver);

    @Query("UPDATE SchoonmaakBier SET bier = bier + :value WHERE toGive == :giver")
    void addSchoonmaakbier(String giver, int value);
}

/*
*
* BEER STATS WE KEEP TRACK OF:
* - Currently open schoonmaakbier between all bewoners - This is done by the Schoonmaakbier class
*
* This is kept track of by the Bewoners class
* - All time schoonmaakbier on a single bewoners
* - All time gestreept bier on a single bewoner(this is when the bewoners has no schoonmaakbier it receives from others)
* - All gedronken bier, this is all beer you have drank, but not necessarily have to pay for
* */