package com.example.myfirstapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.annotation.NonNull;
import androidx.room.Query;

//TODO: Remove ID somehow, right now the table needs a primary key, but it is never used as such.
// TODO: add function to add schoonmaakbier
// DATA CLASS, NO LOGIC
// Entity to keep track of schoonmaakbier between each bewoner
// toReceive is the person that has the free beer
// ToGive is the person who fucked up and has to give free beer
@Entity(tableName = "SchoonmaakBier",
        foreignKeys = {
                @ForeignKey(
                        entity = Bewoner.class,
                        parentColumns = "naam",
                        childColumns = "toReceive"),
                @ForeignKey(
                        entity = Bewoner.class,
                        parentColumns = "naam",
                        childColumns = "toGive")
        }
)
public class SchoonmaakBier {

    @PrimaryKey(autoGenerate = true)
    int id;

    public SchoonmaakBier(int id, String toReceive, String toGive, int beer) {
        this.id = id;
        this.toReceive = toReceive;
        this.toGive = toGive;
        this.beer = beer;
    }

    @NonNull
    public String getToReceive() {
        return toReceive;
    }

    public void setToReceive(@NonNull String toReceive) {
        this.toReceive = toReceive;
    }

    public void setToGive(@NonNull String toGive) {
        this.toGive = toGive;
    }

    public void setBeer(int beer) {
        this.beer = beer;
    }

    @NonNull
    public String getToGive() {
        return toGive;
    }

    public int getBeer() {
        return beer;
    }

    // Person who gets free beer!
    @NonNull
    @ColumnInfo(name = "toReceive")
    private String toReceive;

    // Person who fucked up task
    @NonNull
    @ColumnInfo(name = "toGive")
    private String toGive;

    @NonNull
    @ColumnInfo(name = "bier")
    private int beer;



}
