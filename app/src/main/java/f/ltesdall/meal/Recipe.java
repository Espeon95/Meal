package f.ltesdall.meal;

public class Recipe {

    private int    mRecipeID;
    private String mTitle;
    private int    mServings;
    private String mIngredients;
    private String mDirections;
    private String mType;

    public Recipe(int recipeID, String title, String type, int servings, String ingredients, String directions) {
        mRecipeID    = recipeID;
        mTitle       = title;
        mType        = type;
        mServings    = servings;
        mIngredients = ingredients;
        mDirections  = directions;
    }

    public int getRecipeID() { return mRecipeID; }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) { mTitle = title; }

    public String getType() { return mType; }

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
