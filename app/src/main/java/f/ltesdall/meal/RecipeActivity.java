package f.ltesdall.meal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mServings;
    private TextView mIngredients;
    private TextView mDirections;

    private int mMealID;
    private String mRecipeName;

    private ListView mRecipes;
    private List<Recipe> mRecipe = new ArrayList<>();

    public static Intent createActivity(Context context, String meal, int mealID) {
        Intent i = new Intent(context, RecipeActivity.class);
        i.putExtra("meal_id", mealID);
        i.putExtra("meal_name", meal);
        return i;
    }

    @Override
    protected void onCreate(Bundle sis) {
        super.onCreate(sis);
        setContentView(R.layout.list_view);

        mRecipes = findViewById(R.id.list_view);

        mMealID     = getIntent().getIntExtra("meal_id", 0);
        mRecipeName = getIntent().getStringExtra("meal_name");

        DBAccess dbAccess = DBAccess.getInstance(this);
        dbAccess.open();

        mRecipe = dbAccess.getMeal(mRecipeName, mMealID);
        dbAccess.close();

        Adapter adapter = new Adapter(mRecipe, this);
        this.mRecipes.setAdapter(adapter);
    }

    public class Adapter extends BaseAdapter implements ListAdapter {
        private List<Recipe>  mList = new ArrayList<>();
        private Context mContext;

        public Adapter(List<Recipe> recipe, Context context) {
            this.mList = recipe;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.recipe_layout, null);
            }

            // Handle TextView and display string from your list
            mTitle       = v.findViewById(R.id.title_info);
            mTitle.setText(mList.get(position).getTitle());

            mServings    = v.findViewById(R.id.servings_info);
            mServings.setText(Integer.toString(mList.get(position).getServings()));

            mIngredients = v.findViewById(R.id.ingredients_info);
            mIngredients.setText(mList.get(position).getIngredients());

            mDirections  = v.findViewById(R.id.directions_info);
            mDirections.setText(mList.get(position).getDirections());

            return v;
        }
    }

}
