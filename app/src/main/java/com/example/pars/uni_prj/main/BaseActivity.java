package com.example.pars.uni_prj.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pars.uni_prj.classes.LocaleHelper;
import com.example.pars.uni_prj.classes.MySharePreference;


public class BaseActivity extends AppCompatActivity {

    public BaseActivity context;
    public MySharePreference mySharePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = BaseActivity.this;
        mySharePreference = MySharePreference.getInstance(context);
        LocaleHelper.setLocale(context, mySharePreference.getLanguage());
    }

    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }
}
