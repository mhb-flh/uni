package com.example.pars.uni_prj.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.Items;
import com.example.pars.uni_prj.data.listAdapter;
import com.example.pars.uni_prj.http.API;
import com.example.pars.uni_prj.http.ApiInterface;
import com.example.pars.uni_prj.http.Downloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class firstPage extends AppCompatActivity {

    private static final String TAG = "firstPage";

    private RecyclerView recycler;

    final List<Items> myItems = new ArrayList<>();
    listAdapter myAdapter;
    TextView productName, productCost;
    public static ApiInterface apiInterface;
    ImageView search, shop;

    private static HashMap<String, Bitmap> sBitmapCache = new HashMap<>();


    //TODO change URL
    final static String urlAddress = "http://192.168.174.1/uni/img.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        apiInterface = API.getAPI().create(ApiInterface.class);
        productName = findViewById(R.id.title);
        productCost = findViewById(R.id.price);
        recycler = findViewById(R.id.recycler);
        search = findViewById(R.id.search_icon);
        shop = findViewById(R.id.basket_icon);

        myAdapter = new listAdapter(myItems, firstPage.this);


        recycler.setLayoutManager(new LinearLayoutManager(firstPage.this, LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(myAdapter);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(firstPage.this, buy.class);
                intent.putExtra("search",1);
                startActivity(intent);
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        new Downloader(firstPage.this, urlAddress, recycler).execute();


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


}