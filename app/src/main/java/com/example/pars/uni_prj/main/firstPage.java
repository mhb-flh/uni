package com.example.pars.uni_prj.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.gridAdapter;

public class firstPage extends AppCompatActivity {

    GridView gridView;
    gridAdapter grid_Adapter;
    String INTENT_NAME ="key";


    public firstPage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        String[] grid_menu_items = getResources().getStringArray(R.array.grid_menu_items);

        int[] grid_icons = {R.drawable.ic_product, R.drawable.ic_user, R.drawable.ic_shopping_cart,
                R.drawable.ic_favorites, R.drawable.ic_diamond, R.drawable.ic_cantact_us,
                R.drawable.ic_settings, R.drawable.ic_comments, R.drawable.ic_location};

        gridView = findViewById(R.id.grid_view);
        grid_Adapter = new gridAdapter(firstPage.this, grid_menu_items, grid_icons);
        gridView.setAdapter(grid_Adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent;

                switch (position) {

                    case 0:
                        intent = new Intent(firstPage.this, products.class);
                        intent.putExtra(INTENT_NAME, "products");
                        startActivity(intent);
                        break;

                    case 1:
                        intent = new Intent(firstPage.this, container.class);
                        intent.putExtra(INTENT_NAME, "profile");
                        startActivity(intent);
                        break;

                    case 2:
                        intent = new Intent(firstPage.this, container.class);
                        intent.putExtra(INTENT_NAME, "shopping_cart");
                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(firstPage.this, container.class);
                        intent.putExtra(INTENT_NAME, "favorites");
                        startActivity(intent);
                        break;

                    case 5:
                        intent = new Intent(firstPage.this, container.class);
                        intent.putExtra(INTENT_NAME, "contact_us");
                        startActivity(intent);
                        break;

                    case 6:
                        intent = new Intent(firstPage.this, container.class);
                        intent.putExtra(INTENT_NAME, "setting");
                        startActivity(intent);
                        break;

                    case 7:
                        intent = new Intent(firstPage.this, container.class);
                        intent.putExtra(INTENT_NAME, "comments");
                        startActivity(intent);
                        break;

                    case 8:
                        intent = new Intent(firstPage.this, container.class);
                        intent.putExtra(INTENT_NAME, "location");
                        startActivity(intent);
                        break;

                }
            }


        });


    }


}
