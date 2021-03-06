package f.ltesdall.meal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DBAccess instance;
    Cursor c = null;

    // private constructor so that object creation from outside the class is avoided
    private DBAccess(Context context) {
        this.openHelper = new DBOpenHelper(context);
    }

    // To return the single instance of the database
    public static DBAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DBAccess(context);
        }
        return instance;
    }

    // To open the database connection
    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    // To close the database connection
    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    // Queries the database and returns the result from the database
    public List<Ingredient> getIngredients() {
        Ingredient i;
        List<Ingredient> list = new ArrayList<>();

        c = db.rawQuery("select * from Ingredients", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            i = new Ingredient(c.getInt(0), c.getString(1));
            list.add(i);
            c.moveToNext();
        }
        c.close();

        return list;
    }
    /*
    public List<String> getIngredients() {
        List<String> list = new ArrayList<>();

        c = db.rawQuery("select * from Ingredients", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            list.add(c.getString(0));
            c.moveToNext();
        }
        c.close();

        return list;
    }
    */
}
