package com.example.pars.uni_prj.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.loginPrefManager;
import com.example.pars.uni_prj.http.API;
import com.example.pars.uni_prj.http.ApiInterface;

public class MainActivity extends AppCompatActivity {

    public static ApiInterface apiInterface;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = API.getAPI().create(ApiInterface.class);
        //start = findViewById(R.id.login_start);
        img = findViewById(R.id.splash_img);

        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(2500);
        rotate.setRepeatCount(Animation.INFINITE);
        img.setAnimation(rotate);


        new Handler().postDelayed(() -> {
            loginPrefManager prefManager = new loginPrefManager(MainActivity.this);
            if (prefManager.isLoggedIn()) {
                Intent i = new Intent(getBaseContext(), firstPage.class);
                startActivity(i);
                finish();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frg_container, new Login()).commit();


                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frg_container, new Register()).commit();
            }
//TODO change the time
        }, 20);


    }
}
