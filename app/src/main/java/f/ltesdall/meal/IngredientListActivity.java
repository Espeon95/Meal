package f.ltesdall.meal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class IngredientListActivity extends AppCompatActivity {
    private ListView mIngredientsList;
    List<Ingredient> mIngredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle sis) {
        super.onCreate(sis);
        setContentView(R.layout.list_view);

        mIngredientsList = findViewById(R.id.list_view);

        // Create the instance of database access class and open database connection
        DBAccess dbAccess = DBAccess.getInstance(this);
        dbAccess.open();

        mIngredients = dbAccess.getIngredients();
        dbAccess.close();

        Adapter adapter = new Adapter(mIngredients, this);
        this.mIngredientsList.setAdapter(adapter);

        mIngredientsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "is selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class Adapter extends BaseAdapter implements ListAdapter {
        private List<Ingredient> mList;
        private Context mContext;

        public Adapter(List<Ingredient> ingredients, Context context) {
            this.mList = ingredients;
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
                v = inflater.inflate(R.layout.ingredient_layout, null);
            }

            // Handle TextView and display string from your list
            TextView ingredient = (TextView) v.findViewById(R.id.name);
            ingredient.setText(mList.get(position).getName());

            // Handle Buttons and add onClickListeners
            Button mDeleteBtn = (Button) v.findViewById(R.id.delete);


            Button mAddBtn    = (Button) v.findViewById(R.id.add);
            mAddBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mList.get(position).getName() + " was added to the shopping list.", Toast.LENGTH_SHORT).show();
                }
            });

            return v;
        }
    }
}
