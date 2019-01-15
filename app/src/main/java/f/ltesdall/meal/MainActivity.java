package f.ltesdall.meal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mIngredientsBtn;
    private Button mMealsBtn;
    private Button mShoppingListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIngredientsBtn = findViewById(R.id.ingredientsBtn);
        mIngredientsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, IngredientListActivity.class);
                startActivity(i);
            }
        });

        mMealsBtn = findViewById(R.id.mealsBtn);
        mMealsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MealListActivity.class);
                startActivity(i);
            }
        });

        mShoppingListBtn = findViewById(R.id.shoppingListBtn);
        mShoppingListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShoppingListActivity.class);
                startActivity(i);
            }
        });
    }
}
