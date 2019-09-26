package com.example.myfirstapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

//TODO: look into boolean values!
//TODO: DO WE NEED HUIDIGE BEWONER AND VG VALUES?

// DATA CLASS, NO LOGIC
@Entity(tableName = "Bewoner")
public class Bewoner {

    @NonNull
    public String getNaam() {
        return naam;
    }

    @NonNull
    public String getGeboortedatum() {
        return geboortedatum;
    }

    public boolean isIsvG() {
        return isvG;
    }

    public boolean isHuidigeBewoner() {
        return isHuidigeBewoner;
    }

    public int getGestreeptBier() {
        return gestreeptBier;
    }

    public void setGestreeptBier(int gestreeptBier) {
        this.gestreeptBier = gestreeptBier;
    }

    public int getRaakGegooid() {
        return raakGegooid;
    }

    public void setRaakGegooid(int raakGegooid) {
        this.raakGegooid = raakGegooid;
    }

    // PUBLIC CONSTRUCTOR
    public Bewoner(String naam, String geboortedatum, boolean isvG, boolean isHuidigeBewoner) {
        this.naam = naam;
        this.geboortedatum = geboortedatum;
        this.isvG = isvG;
        this.isHuidigeBewoner = isHuidigeBewoner;
    }


    // Immutable
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "naam")
    private String naam;

    // Immutable
    // Save this as a string because DateTime is a bitch here
    // format: dd-mm-yyyy
    @NonNull
    @ColumnInfo(name = "geboortedatum")
    private String geboortedatum;

    // Mutable
    @NonNull
    @ColumnInfo(name = "vG")
    private boolean isvG;

    // Mutable, maybe unnecessary
    @NonNull
    @ColumnInfo(name = "isHuidigeBewoner")
    private boolean isHuidigeBewoner;

    // Mutable
    // Hier moet voor betaald worden
    @NonNull
    @ColumnInfo(name = "gestreeptBier")
    private int gestreeptBier = 0;

    @NonNull
    @ColumnInfo(name = "raakGegooid")
    private int raakGegooid = 0;
}
