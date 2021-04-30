package com.example.pars.uni_prj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static ApiInterface apiInterface;
    TextView start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = API.getAPI().create(ApiInterface.class);
        start = findViewById(R.id.login_start);

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
