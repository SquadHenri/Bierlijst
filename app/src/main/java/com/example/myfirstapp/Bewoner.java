package com.example.myfirstapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

//TODO: look into boolean values!
//TODO: DO WE NEED HUIDIGE BEWONER AND VG VALUES?

// DATA CLASS, NO LOGIC
@Entity(tableName = "Bewoner")
public class Bewoner {

    // Immutable
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "naam")
    private String naam;

    @ColumnInfo(name = "vGNaam")
    protected String vGNaam = "vG";

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
    private boolean isHuidigeBewoner = false;

    public void setNaam(@NonNull String naam) {
        this.naam = naam;
    }

    // There is a single person that can remove, edit and add users to the database
    // The first person to be created should become the admin
    @NonNull
    @ColumnInfo(name = "isAdmin")
    private boolean isAdmin = false;

    @NonNull
    @ColumnInfo(name = "isCorfeut")
    private boolean isCorfeut = false;

    // Mutable
    // Dit wordt gebruikt als je geen schoonmaakbier open hebt bij andere
    @NonNull
    @ColumnInfo(name = "gestreeptBier")
    private int gestreeptBier = 0;

    // All schoonmaakbier you have to pay for, this does not include beer that is still owed
    @NonNull
    @ColumnInfo(name = "schoonmaakbierOpJou")
    private int schoonmaakbierOpJou = 0;

    @NonNull
    @ColumnInfo(name = "gedronkenBier")
    private int gedronkenBier = 0;

    @NonNull
    @ColumnInfo(name = "raakGegooid")
    private int raakGegooid = 0;

    // How many times someone threw beer
    @NonNull
    @ColumnInfo(name = "Gegooid")
    private int gegooid = 0;

    @NonNull
    @ColumnInfo(name = "raakGegooidHV")
    private int raakGegooidHV = 0;

    // How many times someone threw beer this HV period
    @NonNull
    @ColumnInfo(name = "GegooidHV")
    private int gegooidHV = 0;

    // PUBLIC CONSTRUCTOR
    public Bewoner(String naam, String geboortedatum, boolean isvG, boolean isHuidigeBewoner) {
        this.naam = naam;
        this.geboortedatum = geboortedatum;
        this.isvG = isvG;
        this.isHuidigeBewoner = isHuidigeBewoner;
        this.isCorfeut = false;
        this.isAdmin = false;
        this.vGNaam = "vG"; // Default naam als er geen bijnaam is
    }

    // PUBLIC CONSTRUCTOR FOR ADMIN AND CORFEUT
    @Ignore
    public Bewoner(String naam, String geboortedatum, boolean isvG, boolean isHuidigeBewoner, boolean isAdmin, boolean isCorfeut) {
        this.naam = naam;
        this.geboortedatum = geboortedatum;
        this.isvG = isvG;
        this.isHuidigeBewoner = isHuidigeBewoner;
        this.isAdmin = isAdmin;
        this.isCorfeut = isCorfeut;
    }

    @NonNull
    public String getNaam() {
        return naam;
    }

    @NonNull
    public String getGeboortedatum() {
        return geboortedatum;
    }

    @NonNull
    public boolean isIsvG() {
        return isvG;
    }

    @NonNull
    public boolean isHuidigeBewoner() {
        return isHuidigeBewoner;
    }

    public int getGestreeptBier() {
        return gestreeptBier;
    }

    public void setGestreeptBier(int gestreeptBier) {
        this.gestreeptBier = gestreeptBier;
    }

    public int getSchoonmaakbierOpJou() {
        return schoonmaakbierOpJou;
    }

    public void setSchoonmaakbierOpJou(int schoonmaakbierOpJou) {
        this.schoonmaakbierOpJou = schoonmaakbierOpJou;
    }

    public int getRaakGegooid() {
        return raakGegooid;
    }

    public void setRaakGegooid(int raakGegooid) {
        this.raakGegooid = raakGegooid;
    }

    public boolean getisAdmin() {return isAdmin; }

    public void setAdmin(boolean admin) {isAdmin = admin;}

    public boolean getisCorfeut() {return isCorfeut;}

    public void setCorfeut(boolean corfeut) {isCorfeut = corfeut; }

    public void setGeboortedatum(@NonNull String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setIsvG(boolean isvG) {
        this.isvG = isvG;
    }

    public void setHuidigeBewoner(boolean huidigeBewoner) {
        isHuidigeBewoner = huidigeBewoner;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isCorfeut() {
        return isCorfeut;
    }

    public int getGedronkenBier() { return gedronkenBier; }

    public void setGedronkenBier(int gedronkenBier) { this.gedronkenBier = gedronkenBier; }

    public int getGegooid() {
        return gegooid;
    }

    public void setGegooid(int gegooid) {
        this.gegooid = gegooid;
    }

    public int getGegooidHV() {
        return gegooidHV;
    }

    public void setGegooidHV(int gegooidHV) {
        this.gegooidHV = gegooidHV;
    }

    public int getRaakGegooidHV() {
        return raakGegooidHV;
    }

    public void setRaakGegooidHV(int raakGegooidHV) {
        this.raakGegooidHV = raakGegooidHV;
    }

    public String getvGNaam() {
        return vGNaam;
    }

    public void setvGNaam(String vGNaam) {
        this.vGNaam = vGNaam;
    }
}
