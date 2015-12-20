package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import BO.Chapitre;

/**
 * Created by MOI on 24/11/2015.
 */
public class ChapitreDAODB extends  MathsDataSource implements IChapitreDAO {
    public ChapitreDAODB(Context context) {
        super(context);

    }

    public long addChapitre(Chapitre chapitre){
        this.mDataBase = this.mMathHelper.getMyWDB();

        ContentValues values = new ContentValues();
        values.put(MathCotracts.ChapitreEntry.KEY_CHAPITRE_NAME, chapitre.getChapitreName());
        //ouverture database
        //this.open();
        //insert

        long  result =  this.mDataBase.insert(MathCotracts.ChapitreEntry.TABLE_CHAPITRE, null , values);
        mDataBase.close();
        //this.close();
        return result;

    }

    public List<Chapitre> getAllChapitre( ){

        //this.open();
        this.mDataBase = this.mMathHelper.getMyWDB();

      List<Chapitre> chapitres = new ArrayList<Chapitre>();
        String selectQuery = "SELECT * FROM "+ MathCotracts.ChapitreEntry.TABLE_CHAPITRE;
        Cursor cursor = this.mDataBase.rawQuery(selectQuery ,null);

        if (cursor.moveToFirst()){
            do {
                Chapitre chapitre = new Chapitre();
                chapitre.set_id(Integer.parseInt(cursor.getString(0)));
                chapitre.setChapitreName(cursor.getString(1));
                chapitres.add(chapitre);

            }while (cursor.moveToNext());

        }
        cursor.close();
        mDataBase.close();
        return chapitres;


    }

    @Override
    public Chapitre getChapitre(int id) {

        Chapitre chapitre = new Chapitre();
        this.mDataBase = this.mMathHelper.getMyWDB();
        Cursor cursor = mDataBase.query(MathCotracts.ChapitreEntry.TABLE_CHAPITRE ,
                new String[]{MathCotracts.ChapitreEntry._ID , MathCotracts.ChapitreEntry.KEY_CHAPITRE_NAME} , MathCotracts.ChapitreEntry._ID+"=?",
                new String[]{String.valueOf(id)}
                ,null
                ,null
                ,null
                ,null);


        if (cursor.moveToFirst() && cursor.getCount()==1) {
            //cursor.moveToNext();
           // Chapitre chapitre = new Chapitre(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
            chapitre.set_id(Integer.parseInt(cursor.getString(0)));
            chapitre.setChapitreName(cursor.getString(1));


        }
            mDataBase.close();
            cursor.close();


        return chapitre;
    }

    @Override
    public int updateChapitre(Chapitre chapitre) {

        int result;
        mDataBase = mMathHelper.getMyWDB();
        ContentValues values = new ContentValues();
        values.put(MathCotracts.ChapitreEntry.KEY_CHAPITRE_NAME , chapitre.getChapitreName());
        result=  mDataBase.update(MathCotracts.ChapitreEntry.TABLE_CHAPITRE ,
                values ,
                MathCotracts.ChapitreEntry._ID+"=?",
                new String[]{String.valueOf(chapitre.get_id())});
        mDataBase.close();

        return result;

    }

    @Override
    public int deleteChapitre(Chapitre chapitre) {
        int result;
        mDataBase = mMathHelper.getMyWDB();

        result= mDataBase.delete(MathCotracts.ChapitreEntry.TABLE_CHAPITRE, MathCotracts.ChapitreEntry._ID+"=?", new String[]{String.valueOf(chapitre.get_id())}  );
        mDataBase.close();
        return result;
    }

    @Override
    public int getChapitreCount() {
        int result;
        mDataBase = mMathHelper.getMyWDB();
        String query = "SELECT * FROM "+ MathCotracts.ChapitreEntry.TABLE_CHAPITRE;

       Cursor cursor = mDataBase.rawQuery(query, null);
        result = cursor.getCount();
        cursor.close();

        mDataBase.close();
        return result;
    }

}
