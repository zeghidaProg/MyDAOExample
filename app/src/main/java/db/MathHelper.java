package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MOI on 24/11/2015.
 */
public class MathHelper extends SQLiteOpenHelper {

    public static MathHelper instance;
    public static SQLiteDatabase db;

    public static final String DB_NAME="maths";
    private static final int DB_VERSION=1;



    //database scemma
    //public static final String TABLE_CHAPITRE="chapitre";
    //column
    //public static final String KEY_ID = "_ID";
    //public static final String KEY_CHAPITRE_NAME="nomChapitre";

    private static final String DB_CREATE = "CREATE TABLE "+ MathCotracts.ChapitreEntry.TABLE_CHAPITRE +" ("+
            MathCotracts.ChapitreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MathCotracts.ChapitreEntry.KEY_CHAPITRE_NAME +" TEXT "+" )" ;




    public MathHelper(Context context) {
        
        super(context, DB_NAME, null, DB_VERSION);
    }

    //singleton instance
    public static synchronized void initialisation(Context context){
        if (instance==null){
            instance = new MathHelper(context);

        }

    }
    public static synchronized MathHelper getInstance(Context context){

        if (instance==null){
            instance = new MathHelper(context);
        }
        return instance;
    }

    public  synchronized SQLiteDatabase getMyWDB(){
        //si instance non null
        db= instance.getWritableDatabase();
        return db;
    }

    public  synchronized SQLiteDatabase getMyRDB(){

        //si instance non null
        db= instance.getReadableDatabase();
        return db;
    }

    public  synchronized void closeDB(){

        //
        db.close();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
