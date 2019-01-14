package f.ltesdall.meal;

public class Recipe {

    private String mTitle;
    private int mServings;
    private String mIngredients;
    private String mDirections;

    public Recipe(String title, int servings, String ingredients, String directions) {
        mTitle       = title;
        mServings    = servings;
        mIngredients = ingredients;
        mDirections  = directions;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) { mTitle = title; }

    public int getServings() {
        return mServings;
    }

    public String getIngredients() {
        return mIngredients;
    }

    public String getDirections() {
        return mDirections;
    }
}
