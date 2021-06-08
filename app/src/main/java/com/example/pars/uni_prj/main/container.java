package com.example.pars.uni_prj.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.pars.uni_prj.R;

public class container extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.buy_container, new ordersFragment()).commit();

        Bundle bundle = getIntent().getExtras();


        String value = null;
        if (bundle != null) {
            value = bundle.getString("key");
        }

        if (value != null) {
            switch (value) {

                case "search":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.buy_container, new searchFragment()).commit();
                    break;
                case "setting":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.buy_container, new settingFragment()).commit();
                    break;
                case "profile":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.buy_container, new profileFragment()).commit();
                    break;
                case "shopping_cart":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.buy_container, new shoppingCartFragment()).commit();
                    break;
                case "favorites":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.buy_container, new favoriteFragment()).commit();
                    break;
                case "contact_us":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.buy_container, new contactUsFragment()).commit();
                    break;
                case "comments":

                    break;
                case "location":
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.buy_container, new locationFragment()).commit();

                    break;


                default:
                    //TODO default
                    break;
            }
        }


    }


}
