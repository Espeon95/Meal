package f.ltesdall.meal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class DBAccess {
/*
    private static final String SPAGHETTI                       = "Spaghetti";
    private static final String FISH_AND_RICE                   = "Fish and Rice";
    private static final String ORANGE_GINGER_CHICKEN           = "Orange Ginger Chicken";
    private static final String TERIYAKI_CHICKEN                = "Teriyaki Chicken";
    private static final String CHICKEN_ALFREDO                 = "Chicken Alfredo";
    private static final String CRESCENT_ROLL_CHICKEN           = "Crescent Roll Chicken";
    private static final String BBQ_MEATLOAF                    = "BBQ Meatloaf";
    private static final String ENCHILADAS                      = "Enchiladas";
    private static final String POTATOES_AND_CHILLI             = "Potatoes and Chilli";
    private static final String HONEY_AND_CHICKEN               = "Honey and Chicken";
    private static final String PARMESAN_CHICKEN_BITES          = "Parmesan Chicken Bites";
    private static final String KATSUDON                        = "Katsudon";
    private static final String PIZZA                           = "Pizza";
    private static final String STROMBOLI                       = "Stromboli";
    private static final String CHICKEN_BROCCOLI_RICE_CASSEROLE = "Chicken Broccoli Rice Casserole";
    private static final String TACO_SALAD                      = "Taco Salad";
    private static final String CHEESEBURGER_SALAD              = "CheeseBurger Salad";
    private static final String POTATO_SOUP                     = "Potato Soup";
    private static final String CHINESE_BUNS                    = "Chinese Buns";

    private static final int SPAGHETTI                       = 1;
    private static final int FISH_AND_RICE                   = 2;
    private static final int ORANGE_GINGER_CHICKEN           = 3;
    private static final int TERIYAKI_CHICKEN                = 4;
    private static final int CHICKEN_ALFREDO                 = 5;
    private static final int CRESCENT_ROLL_CHICKEN           = 6;
    private static final int BBQ_MEATLOAF                    = 7;
    private static final int ENCHILADAS                      = 8;
    private static final int POTATOES_AND_CHILLI             = 9;
    private static final int HONEY_AND_CHICKEN               = 10;
    private static final int PARMESAN_CHICKEN_BITES          = 11;
    private static final int KATSUDON                        = 12;
    private static final int PIZZA                           = 13;
    private static final int STROMBOLI                       = 14;
    private static final int CHICKEN_BROCCOLI_RICE_CASSEROLE = 15;
    private static final int TACO_SALAD                      = 16;
    private static final int CHEESEBURGER_SALAD              = 17;
    private static final int POTATO_SOUP                     = 18;
    private static final int CHINESE_BUNS                    = 19;
   */

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

        Collections.sort(list, new Comparator<Ingredient>() {
            public int compare(Ingredient obj1, Ingredient obj2) {
                // ## Ascending order
                return obj1.getName().compareToIgnoreCase(obj2.getName()); // To compare string values
            }
        });

        return list;
    }

    public List<Meal> getMeals() {
        Meal m;
        List<Meal> list = new ArrayList<>();

        c = db.rawQuery("select * from Meals", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            m = new Meal(c.getInt(0), c.getString(1));
            list.add(m);
            c.moveToNext();
        }
        c.close();

        Collections.sort(list, new Comparator<Meal>() {
            public int compare(Meal obj1, Meal obj2) {
                // ## Ascending order
                return obj1.getName().compareToIgnoreCase(obj2.getName()); // To compare string values
            }
        });

        return list;
    }

    public List<Recipe> getMeal(String recipe, int meal) {
        Recipe r;
        List<Recipe> list = new ArrayList<>();
        c = db.rawQuery("select * from " + recipe, null);
        c.moveToFirst();
        //while(!c.isAfterLast()) {
            r = new Recipe(recipe, c.getInt(0), c.getString(1), c.getString(2));
            list.add(r);
            c.moveToNext();
       // }
        c.close();

        return list;
    }
}
