package com.example.pars.uni_prj.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.Items;
import com.example.pars.uni_prj.data.listAdapter;
import com.example.pars.uni_prj.http.API;
import com.example.pars.uni_prj.http.ApiInterface;
import com.example.pars.uni_prj.http.Downloader;

import java.util.ArrayList;
import java.util.List;

public class firstPage extends AppCompatActivity {

    private static final String TAG = "firstPage";
    private Context context;
    RecyclerView recycler;
    List<Items> myItems=new ArrayList<>();
    listAdapter myAdapter;
    TextView productName, productCost;
    public static ApiInterface apiInterface;
    int RecyclerViewItemPosition ;

    final static String urlAddress = "http://192.168.43.25/uni/img.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        apiInterface = API.getAPI().create(ApiInterface.class);
        productName = findViewById(R.id.title);
        myAdapter = new listAdapter(myItems, context);
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(firstPage.this, LinearLayoutManager.HORIZONTAL, false));
        myAdapter = new listAdapter(myItems, firstPage.this);
        recycler.setAdapter(myAdapter);
        new Downloader(firstPage.this, urlAddress, recycler).execute();

        productCost = findViewById(R.id.price);


        //onClick Recycler
        myAdapter.setOnItemClickListener(new listAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                Intent intent = new Intent(firstPage.this, buy.class);
                String img=myItems.get(position).getImage();
                String imageName =myItems.get(position).getTitle();
                String price=myItems.get(position).getPrice();
                intent.putExtra("img",img );
                intent.putExtra("imageName", imageName);
                intent.putExtra("price",price );
                startActivity(intent);


            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d(TAG, "onItemLongClick pos = " + position);
            }
        });

    }
}