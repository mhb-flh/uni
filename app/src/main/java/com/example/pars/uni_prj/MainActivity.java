package com.example.pars.uni_prj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static ApiInterface apiInterface;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = API.getAPI().create(ApiInterface.class);

        getSupportFragmentManager().beginTransaction().add(R.id.frg_container, new Register()).commit();

        loginText = findViewById(R.id.login_txt_home);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frg_container, new Login()).commit();
                loginText.setText("");
            }
        });
    }
}
