package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by MOI on 24/11/2015.
 */
public class MathsDataSource {
    public SQLiteDatabase mDataBase;
    public MathHelper mMathHelper;
    public Context mContext;

    public MathsDataSource(Context context) {
        mContext = context;
        //instance db creation
       // mMathHelper = new MathHelper(mContext);
        mMathHelper = MathHelper.getInstance(mContext);
        //mDataBase = mMathHelper.getMyWDB();
    }


}
