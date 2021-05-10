package com.example.pars.uni_prj.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.MainViewModel;
import com.example.pars.uni_prj.data.items;
import com.example.pars.uni_prj.data.listAdapter;
import com.example.pars.uni_prj.data.loginPrefManager;
import com.example.pars.uni_prj.http.ApiServiceSingleton;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class firstPage extends AppCompatActivity {

    private static final String TAG = "firstPage";
    private Context context;
    RecyclerView recycler;
    List<items> myItems;
    listAdapter myAdapter;
    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
//        recycler=findViewById(R.id.recycler);
//        prepareData();
//        showData();

//        loginPrefManager prefManager=new loginPrefManager(this);
//
//        if (!prefManager.isLoggedIn()){
//            Intent i = new Intent(this, MainActivity.class);
//            startActivity(i);
//            finish();
//        }


        MainViewModel mainViewModel = new MainViewModel(ApiServiceSingleton.getInstance());
        mainViewModel.getProduct().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<items>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(List<items> myItems) {
                        Log.i(TAG, "onSuccess: " + myItems);
                        recycler = findViewById(R.id.recycler);
                        recycler.setLayoutManager(new LinearLayoutManager(firstPage.this, LinearLayoutManager.HORIZONTAL, false));
                        myAdapter = new listAdapter(myItems, firstPage.this);
                        recycler.setAdapter(myAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(firstPage.this, "error : " + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}


//    private void showData() {
//        myAdapter=new listAdapter(myItems);
//        recycler.setAdapter(myAdapter);
//        recycler.setLayoutManager(new LinearLayoutManager(this));
//        recycler.setItemAnimator(new DefaultItemAnimator());
//    }
//
//    private void prepareData() {
//        if (myItems==null)
//            myItems=new ArrayList<items>();
//        else
//            myItems.clear();
//
//
//        myItems.add(new items(0,"hi"));
//        myItems.add(new items(0,"hi"));
//        myItems.add(new items(0,"hi"));
//        myItems.add(new items(0,"hi"));
//
//    }
//}
