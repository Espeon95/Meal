package f.ltesdall.meal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MealListActivity extends AppCompatActivity {
    private ListView mMealList;
    List<Meal> mMeals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle sis) {
        super.onCreate(sis);
        setContentView(R.layout.ingredient_list);

        mMealList = findViewById(R.id.ingredient_list);

        // Create the instance of database access class and open database connection
        DBAccess dbMealAccess = DBAccess.getInstance(this);
        dbMealAccess.open();

        mMeals = dbMealAccess.getMeals();
        dbMealAccess.close();

        Adapter adapter = new Adapter(mMeals, this);
        this.mMealList.setAdapter(adapter);

        mMealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "is selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class Adapter extends BaseAdapter implements ListAdapter {
        private List<Meal> mList;
        private Context mContext;

        public Adapter(List<Meal> meals, Context context) {
            this.mList = meals;
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
                v = inflater.inflate(R.layout.meal_layout, null);
            }

            // Handle TextView and display string from your list
            TextView meal = (TextView) v.findViewById(R.id.meal);
            meal.setText(mList.get(position).getName());

            return v;
        }
    }
}
