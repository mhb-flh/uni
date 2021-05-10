package com.example.pars.uni_prj.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.loginPrefManager;
import com.example.pars.uni_prj.http.API;
import com.example.pars.uni_prj.http.ApiInterface;

public class MainActivity extends AppCompatActivity {

    public static ApiInterface apiInterface;
    TextView start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = API.getAPI().create(ApiInterface.class);
        start = findViewById(R.id.login_start);
        loginPrefManager prefManager=new loginPrefManager(this);

        if (prefManager.isLoggedIn()){
            Intent i = new Intent(this, firstPage.class);
            startActivity(i);
            finish();
        }

            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frg_container, new Login()).commit();

                    start.setVisibility(View.GONE);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frg_container, new Register()).commit();
                }

                   });


    }
}