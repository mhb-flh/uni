package com.example.pars.uni_prj.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pars.uni_prj.R;

public class buy extends AppCompatActivity {

    final static String urlAddress = "http://192.168.1.5/uni/img.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        getSupportFragmentManager().beginTransaction().replace(R.id.buy_container,new orders()).commit();


    }
}
