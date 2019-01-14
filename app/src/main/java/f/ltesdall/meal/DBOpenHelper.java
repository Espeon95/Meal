package f.ltesdall.meal;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBOpenHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "MealPlan.db";
    private static final int DB_VERSION = 1;

    // Constructor
    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);


    }
}