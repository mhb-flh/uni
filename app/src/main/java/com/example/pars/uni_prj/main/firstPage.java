package com.example.pars.uni_prj.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import java.util.Objects;

public class firstPage extends AppCompatActivity {

    private static final String TAG = "firstPage";

    final List<Items> myItems = new ArrayList<>();
    listAdapter myAdapter;
    TextView productName, productCost;
    public static ApiInterface apiInterface;
    ImageView search, shop;
    Toolbar mToolbar;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    //TODO change URL
    final static String urlAddress = "http://192.168.174.1/uni/img.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
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




//        mToolbar =findViewById(R.id.m_toolbar);
//        setSupportActionBar(mToolbar);

        myAdapter = new listAdapter(myItems, firstPage.this);

        recycler.setLayoutManager(new LinearLayoutManager(firstPage.this, LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(myAdapter);
        new Downloader(firstPage.this, urlAddress, recycler).execute();

        search.setOnClickListener(view -> {
            Intent intent = new Intent(firstPage.this, buy.class);
            intent.putExtra("key", 1);
            startActivity(intent);
        });

        shop.setOnClickListener(view -> {
            //TODO shopping cart
        });


        //onClick Recycler
        myAdapter.setOnItemClickListener(new listAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                Intent intent = new Intent(firstPage.this, buy.class);
                String img = myItems.get(position).getImage();
                String imageName = myItems.get(position).getTitle();
                String price = myItems.get(position).getPrice();
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

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            switch (item.getItemId()){
                case R.id.nav_account:

                    break;

                case R.id.nav_settings:
                    Intent intent = new Intent(firstPage.this, buy.class);
                    intent.putExtra("key", 2);
                    startActivity(intent);
                    break;

                case R.id.nav_logout:
                    loginPrefManager loginPrefManager=new loginPrefManager(firstPage.this);

                    loginPrefManager.logoutUser();
                    break;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
