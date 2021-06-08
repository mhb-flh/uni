package com.example.pars.uni_prj.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.Items;
import com.example.pars.uni_prj.data.listAdapter;
import com.example.pars.uni_prj.data.loginPrefManager;
import com.example.pars.uni_prj.http.API;
import com.example.pars.uni_prj.http.ApiInterface;
import com.example.pars.uni_prj.http.Downloader;
import java.util.ArrayList;
import java.util.List;

public class products extends AppCompatActivity {

    private static final String TAG = "products";
    String INTENT_NAME = "key";

    public ActionBarDrawerToggle actionBarDrawerToggle;
    final List<Items> myItems = new ArrayList<>();
    public DrawerLayout drawerLayout;
    public static ApiInterface apiInterface;

    listAdapter myAdapter;
    TextView productName, productCost;
    ImageView search, shop;



    //TODO change URL
    final static String urlAddress = "http://192.168.1.4/uni/img.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        apiInterface = API.getAPI().create(ApiInterface.class);

        productName = findViewById(R.id.title);
        productCost = findViewById(R.id.price);
        RecyclerView recycler = findViewById(R.id.recycler);
        search = findViewById(R.id.search_icon);
        shop = findViewById(R.id.basket_icon);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        myAdapter = new listAdapter(myItems, products.this);

        recycler.setLayoutManager(new LinearLayoutManager(products.this, LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(myAdapter);
        new Downloader(products.this, urlAddress, recycler).execute();

        //onClick Recycler
        myAdapter.setOnItemClickListener(new listAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                String img = myItems.get(position).getImage();
                String imageName = myItems.get(position).getTitle();
                String price = myItems.get(position).getPrice();
                Intent intent = new Intent(products.this, container.class);
                intent.putExtra("img", img);
                intent.putExtra("imageName", imageName);
                intent.putExtra("price", price);
                startActivity(intent);
            }


            @Override
            public void onItemLongClick(int position, View v) {
                Log.d(TAG, "onItemLongClick pos = " + position);
            }
        });


        search.setOnClickListener(view -> {
            Intent intent = new Intent(products.this, container.class);
            intent.putExtra(INTENT_NAME, "search");
            startActivity(intent);
        });

        shop.setOnClickListener(view -> {
            //TODO shopping cart
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            switch (item.getItemId()) {
                case R.id.nav_account:

                    break;

                case R.id.nav_settings:
                    Intent intent = new Intent(products.this, container.class);
                    intent.putExtra("key", "setting");
                    startActivity(intent);
                    break;

                case R.id.nav_logout:
                    loginPrefManager loginPrefManager = new loginPrefManager(products.this);

                    loginPrefManager.logoutUser();
                    break;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
