package f.ltesdall.meal;

public class Meal {
    private String mName;
    private int    mID;

    public Meal(int id, String name) {
        mName = name;
        mID   = id;
    }

    public void setName(String name) { mName = name; }

    public String getName() { return mName; }

    public void setID(int id) { mID = id; }

    public int getID() { return mID; }
}
