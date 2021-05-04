package com.example.pars.uni_prj;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.util.ArrayList;
import java.util.List;

public class firstPage extends AppCompatActivity {

    private Context context;
    RecyclerView recycler;
    List<items> myItems;
    listAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        recycler=findViewById(R.id.recycler);
        prepareData();
        showSata();

    }

    private void showSata() {
        myAdapter=new listAdapter(myItems);
        recycler.setAdapter(myAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());
    }

    private void prepareData() {
        if (myItems==null)
            myItems=new ArrayList<items>();
        else
            myItems.clear();


        myItems.add(new items(0,"hi"));
        myItems.add(new items(0,"hi"));
        myItems.add(new items(0,"hi"));
        myItems.add(new items(0,"hi"));

    }
}
