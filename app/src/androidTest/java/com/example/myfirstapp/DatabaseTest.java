package com.example.myfirstapp;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

// Used to test Database, bewonerDAO and SchoonmaakBierDAO
public class DatabaseTest {
    MMDatabase db;

    // TODO: split up the tests

    @Before
    public void setupDatabase(){
        System.out.println("Before");
        //db = db.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db = Room.databaseBuilder(InstrumentationRegistry.getInstrumentation().getTargetContext(), MMDatabase.class, "db")
                .allowMainThreadQueries()
                .build();


    }

    @Test
    public void testBewonerDAO(){
        System.out.println("-----------------------------------------");
        db.populateDatabase();
        List<Bewoner> bewoners = db.getBewonerDAO().getAllBewoners();


        assertEquals(5, bewoners.size());


        for(Bewoner bewoner : bewoners){
            System.out.println("schoonmaakbieropjou");
            assertEquals(0, bewoner.getSchoonmaakbierOpJou());
            System.out.println("getgedronkenbier");
            assertEquals(0, bewoner.getGedronkenBier());
            System.out.println("getgestreeptbier");
            assertEquals(0, bewoner.getGestreeptBier());
            System.out.println("getraakgegooid");
            assertEquals(0, bewoner.getRaakGegooid());
        }

        for(Bewoner bewoner : bewoners) {
            db.getBewonerDAO().addGestreeptBier(5, bewoner.getNaam());
            assertEquals(5, db.getBewonerDAO().getGestreeptBier(bewoner.getNaam()));

            //TODO:possibly check other values but not a priority for now

        }
    }

    @Test
    public void testSchoonmaakbierDAO(){
        System.out.println("==========================================");
        int beerToAdd = 20;
        db.populateDatabase();
        List<Bewoner> bewoners = db.getBewonerDAO().getAllBewoners();

        List<SchoonmaakBier> sbbier = db.getSchoonmaakBierDAO().getAllSchoonmaakBier();

        assertEquals(20, sbbier.size());

        System.out.println("Testing sb bier is zero.");
        int total = 0;
        for(SchoonmaakBier sb : sbbier) {
            total+= sb.getBeer();
        }
        assertEquals(0,total);

        System.out.println("Testing setting beer");
        // Test set beer
        for(Bewoner bewoner : bewoners) {
            for(Bewoner bewoner1 : bewoners){
                if(bewoner.getNaam() == bewoner1.getNaam()){
                    continue;
                }

                db.getSchoonmaakBierDAO().setBeer(beerToAdd, bewoner.getNaam(),bewoner1.getNaam());
                assertEquals(beerToAdd, db.getSchoonmaakBierDAO().getSBBetweenBewoners(bewoner.getNaam(), bewoner1.getNaam()));
            }

            System.out.println("Testing sb receives and owes");
            // Test GetAllSchoonmaakBierBewonerReceives
            // Test GetAllSchoonBierBewonerOwesToOthers
            assertEquals(beerToAdd*4,db.getSchoonmaakBierDAO().getAllSchoonmaakBierBewonerReceives(bewoner.getNaam()));
            for(Bewoner bewoner1 : bewoners) {
                assertEquals(beerToAdd*4, db.getSchoonmaakBierDAO().getSchoonmaakBiertoReceiveSum(bewoner1.getNaam()));
                assertEquals(beerToAdd*4, db.getSchoonmaakBierDAO().getSchoonmaakBiertoGiveSum(bewoner1.getNaam()));
            }
        }

        //



    }
}
