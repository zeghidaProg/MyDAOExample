package com.math.tlsryad.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import BO.Chapitre;
import db.ChapitreDAODB;
import db.MathCotracts;
import db.MathHelper;


/**
 * Created by MOI on 26/11/2015.
 */
public class TestDBChapitreOP extends AndroidTestCase {




    public void testTestDBClass() throws Exception {

        int res = 5+2;
        assertEquals(res, 7);


    }

    public void testCReateDB() throws Exception{


        int dbversion ;
        mContext.deleteDatabase(MathHelper.DB_NAME);
        SQLiteDatabase db = MathHelper.getInstance(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        dbversion= db.getVersion();
        db.close();
        assertEquals(dbversion , 1);

    }

    public void testInsertReadDB() throws Exception {

        String LOG_TAG="Meth testInsertReadDB ";

        testCReateDB();

        long resRowID;

        Chapitre chapitre = new Chapitre("chap1");


        ChapitreDAODB chapitreDAODB = new ChapitreDAODB(mContext);
        resRowID= chapitreDAODB.addChapitre(chapitre);


        //test first id created id
        assertTrue(resRowID != -1);
        Log.d(LOG_TAG, " Result ROW ID: " + resRowID);


        chapitre.set_id(1);
        testAddedChapitre(String.valueOf(chapitre.get_id()), chapitre.getChapitreName(), chapitre);

        //test values of first id
        //specify wich column i want
        String[] columns = {
                MathCotracts.ChapitreEntry._ID,
                MathCotracts.ChapitreEntry.KEY_CHAPITRE_NAME
        };

        // a cursor to query
        MathHelper.db =  MathHelper.getInstance(mContext).getMyWDB();
        Cursor cursor = MathHelper.db.query(MathCotracts.ChapitreEntry.TABLE_CHAPITRE , columns ,
                null , //columns for where close
                null , //Values for  where close
                null , //columns to groupe by
                null ,  //columns to filtre by groupe
                null);  //sort order



        if (cursor.moveToFirst()){

            int idIndex = cursor.getColumnIndex(MathCotracts.ChapitreEntry._ID);
            String chapitreId = cursor.getString(idIndex);

            int chapitreNameIndex = cursor.getColumnIndex(MathCotracts.ChapitreEntry.KEY_CHAPITRE_NAME);
            String chapitreName = cursor.getString(chapitreNameIndex);

            //asert on column index
            assertEquals(idIndex, 0);
            assertEquals(chapitreNameIndex, 1);
            Log.d(LOG_TAG, " id Column index  " + idIndex);
            Log.d(LOG_TAG," chapitre name Column index "+chapitreNameIndex);


            //assert on column value
            assertEquals(chapitreId, "1");
            assertEquals(chapitreName , chapitre.getChapitreName());
            Log.d(LOG_TAG," chapitre row id "+chapitreId);
            Log.d(LOG_TAG, " chapitre name  "+chapitreName);


        }else {
            fail("no value returned");
        }

    }

    public void testUpdateReadDB() throws Exception {

        String LOG_TAG=" Meth testUpdateReadDB ";

        testCReateDB();

        long expectedUpdatedRecord = 1;
        String expectedChapitreId = "2";
        String expectedChapitreName = "chap2";


        long result;
        int id=2;
        String lastValue;
        //create first chapitre obj
        Chapitre chapitre1 = new Chapitre("chap1");
        chapitre1.set_id(1);
        //create second chap obj
        Chapitre chapitre = new Chapitre(id,"Chap 3");
        //add chapitre obj on database
        ChapitreDAODB chapitreDAODB = new ChapitreDAODB(mContext);
        chapitreDAODB.addChapitre(chapitre1);//row id 1
        chapitreDAODB.addChapitre(chapitre);//row id 2
        //test data inserted
        String LOG_STAR="*******************************************";
        //Log.d(LOG_STAR,LOG_STAR);

        Log.d(LOG_TAG, " test data inserted");
        testAddedChapitre(String.valueOf(chapitre1.get_id()), chapitre1.getChapitreName(), chapitre1);
        testAddedChapitre(String.valueOf(chapitre.get_id()), chapitre.getChapitreName(), chapitre);


        chapitre.setChapitreName("chap2");
        result=  chapitreDAODB.updateChapitre(chapitre);

        //test number of updated record
        assertEquals(expectedUpdatedRecord, result);
        testAddedChapitre(expectedChapitreId, expectedChapitreName, chapitre);


    }

    public void testAddedChapitre(String expectedChapitreId, String expectedChapitreName, Chapitre chapitre) {

        testFoundChapitre(chapitre);
    }

    public void testNotFountChapitre(Chapitre chapitre){

        String expectedChapitreId = String.valueOf(chapitre.get_id()); String expectedChapitreName =chapitre.getChapitreName() ;
        String LOG_TAG="Methode testAddedChapitre ";
        //*******************
        //test values updated
        //*******************

        //select updated data
        // a cursor to query
        //specify wich column i want
        String[] columns = {
                MathCotracts.ChapitreEntry._ID,
                MathCotracts.ChapitreEntry.KEY_CHAPITRE_NAME
        };
        MathHelper.db =  MathHelper.getInstance(mContext).getMyWDB();
        Cursor cursor = MathHelper.db.query(MathCotracts.ChapitreEntry.TABLE_CHAPITRE , columns ,
                MathCotracts.ChapitreEntry._ID+"=?", //columns for where close
                new String[]{String.valueOf(chapitre.get_id())} , //Values for  where close
                null , //columns to groupe by
                null ,  //columns to filtre by groupe
                null);  //sort order
        //assert updated data
        assertTrue(!cursor.moveToFirst());


    }

    public void testFoundChapitre(Chapitre chapitre) {
        String expectedChapitreId = String.valueOf(chapitre.get_id()); String expectedChapitreName =chapitre.getChapitreName() ;
        String LOG_TAG="Methode testAddedChapitre ";
        //*******************
        //test values updated
        //*******************

        //select updated data
        // a cursor to query
        //specify wich column i want
        String[] columns = {
                MathCotracts.ChapitreEntry._ID,
                MathCotracts.ChapitreEntry.KEY_CHAPITRE_NAME
        };
        MathHelper.db =  MathHelper.getInstance(mContext).getMyWDB();
        Cursor cursor = MathHelper.db.query(MathCotracts.ChapitreEntry.TABLE_CHAPITRE , columns ,
                MathCotracts.ChapitreEntry._ID+"=?", //columns for where close
                new String[]{String.valueOf(chapitre.get_id())} , //Values for  where close
                null , //columns to groupe by
                null ,  //columns to filtre by groupe
                null);  //sort order
        //assert updated data
        if (cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(MathCotracts.ChapitreEntry._ID);
            String chapitreId = cursor.getString(idIndex);

            int chapitreNameIndex = cursor.getColumnIndex(MathCotracts.ChapitreEntry.KEY_CHAPITRE_NAME);
            String chapitreName = cursor.getString(chapitreNameIndex);

            assertEquals(expectedChapitreId, chapitreId);
            Log.d(LOG_TAG, "id of  chapitre obj is: " + chapitreId);
            assertEquals(expectedChapitreName, chapitreName);
            Log.d(LOG_TAG, " name of chapitre obj is: " + chapitreName);

        }else {fail("no value returned");}
    }


    public void testDeleteReadDB() throws Exception {

        int expectedCount = 3;
        //

        testCReateDB();
        Chapitre chapitre1 = new Chapitre("chap1");
        Chapitre chapitre2 = new Chapitre("chap2");
        Chapitre chapitre3 = new Chapitre("chap3");
        Chapitre chapitre4 = new Chapitre("chap4");

        ChapitreDAODB chapitreDAODB = new ChapitreDAODB(mContext);
        chapitreDAODB.addChapitre(chapitre1);
        chapitreDAODB.addChapitre(chapitre2);
        chapitreDAODB.addChapitre(chapitre3);
        chapitreDAODB.addChapitre(chapitre4);

        chapitre2.set_id(2);
        testAddedChapitre(String.valueOf(chapitre2.get_id()), chapitre2.getChapitreName(), chapitre2);
        chapitreDAODB.deleteChapitre(chapitre2);
        chapitre2.set_id(2);
        //testAddedChapitre(String.valueOf(chapitre2.get_id()) , chapitre2.getChapitreName(),chapitre2);

        testNotFountChapitre(chapitre2);
        assertEquals(testGetChapitreCount(), expectedCount);

    }

    private int testGetChapitreCount() {

        MathHelper.db = MathHelper.getInstance(mContext).getMyWDB();

       Cursor cursor = MathHelper.db.rawQuery("SELECT * FROM "+ MathCotracts.ChapitreEntry.TABLE_CHAPITRE, null);

        return cursor.getCount();

    }

    public void testSelectAllReadDB() throws Exception {

        int expectedCount = 3;
        //
        testCReateDB();
        Chapitre chapitre1 = new Chapitre("chap1");
        Chapitre chapitre2 = new Chapitre("chap2");
        Chapitre chapitre3 = new Chapitre("chap3");

        List<Chapitre> expectedChapitreList = new ArrayList<Chapitre>();
        expectedChapitreList.add(chapitre1);
        expectedChapitreList.add(chapitre2);
        expectedChapitreList.add(chapitre3);

        ChapitreDAODB chapitreDAODB = new ChapitreDAODB(mContext);
        chapitreDAODB.addChapitre(chapitre1);
        chapitreDAODB.addChapitre(chapitre2);
        chapitreDAODB.addChapitre(chapitre3);

        assertEquals(chapitreDAODB.getChapitreCount(), expectedCount);
        List<Chapitre> chapitreList = new ArrayList<Chapitre>();
        chapitreList = chapitreDAODB.getAllChapitre();
        Object[] expectedArrayChapitre =  expectedChapitreList.toArray();
        Object[] arrayChapitreList =  chapitreList.toArray();


        //assertTrue(Arrays.deepEquals(expectedArrayChapitre, arrayChapitreList));
        for (Chapitre ch : chapitreList){
            Log.d("chapitre LIST ","value "+ch.getChapitreName());
        }
        for (Chapitre ch : expectedChapitreList){
            Log.d("EXPECTED chapitre LIST","value "+ch.getChapitreName());
        }


       // assertEquals(new HashSet<Chapitre>(chapitreList), new HashSet<Chapitre>(expectedChapitreList));



    }

    public void testGetReadDB() throws Exception {

        int expectedID = 2;
        //
        testCReateDB();
        Chapitre chapitre1 = new Chapitre("chap1");

        Chapitre chapitre2 = new Chapitre("chap2");
        Chapitre chapitre3 = new Chapitre("chap3");

        ChapitreDAODB chapitreDAODB = new ChapitreDAODB(mContext);
        chapitreDAODB.addChapitre(chapitre1);
        chapitreDAODB.addChapitre(chapitre2);
        chapitreDAODB.addChapitre(chapitre3);

        Chapitre chapitre  = chapitreDAODB.getChapitre(2);

        assertEquals(chapitre2.getChapitreName(),chapitre.getChapitreName());
        //assertEquals(chapitre3.getChapitreName(),chapitre.getChapitreName());
        //assertEquals(chapitre1.getChapitreName(),chapitre.getChapitreName());




    }



    public void validateCursor(Cursor valueCursor , ContentValues expextedValues){
        int idx=0;


        assertTrue(valueCursor.moveToFirst());

        assertEquals(expextedValues , valueCursor.getString(idx));



    }
}
