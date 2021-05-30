package com.example.pars.uni_prj.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pars.uni_prj.R;

public class buy extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Bundle bundle = getIntent().getExtras();
        int value = 0;
        if (bundle != null) value = bundle.getInt("key");
        if (value == 1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.buy_container, new searchFragment()).commit();
        } else if (value==2){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.buy_container, new settingFragment()).commit();
        }else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.buy_container, new orders()).commit();
        }


    }


}
