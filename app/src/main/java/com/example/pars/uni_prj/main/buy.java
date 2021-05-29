package com.example.pars.uni_prj.main;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pars.uni_prj.R;

import java.util.Objects;

public class buy extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int value= bundle.getInt("search");
        if (value==1){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.buy_container,new searchFragment()).commit();
        }else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.buy_container,new orders()).commit();
        }


    }


}
