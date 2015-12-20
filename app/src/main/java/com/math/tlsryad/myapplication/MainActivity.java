package com.math.tlsryad.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import BO.Chapitre;
import db.ChapitreDAODB;
import db.MathHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //db creation
        MathHelper dbhelper = new MathHelper(this);
        dbhelper.close();
        //dbhelper.closeDB();
        //
        //test inserting

        Chapitre chapitre3 = new Chapitre("chapitre 3");
        Chapitre chapitre4 = new Chapitre("chapitre 4");
        Chapitre chapitre5 = new Chapitre("chapitre 5");


        ChapitreDAODB chapitreDAODB = new ChapitreDAODB(this);
        chapitreDAODB.addChapitre(chapitre3);
        chapitreDAODB.addChapitre(chapitre4);
        chapitreDAODB.addChapitre(chapitre5);

        List<Chapitre> chapitreList = new ArrayList<Chapitre>();

        chapitreList = chapitreDAODB.getAllChapitre();

        for (Chapitre c : chapitreList){
            String msg = c.get_id()+" "+c.getChapitreName();
            Log.d("contetnt :  ",msg);

        }







    }
}
