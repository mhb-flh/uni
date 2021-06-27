package com.example.pars.uni_prj.classes;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class AppClass extends MultiDexApplication {

    private static AppClass appClass;
    public static AppClass getintance()
    {
        return appClass;
    }


    @Override
    public void onCreate()
    {
        super.onCreate();
        appClass = this;
        MySharePreference.getInstance(this);
    }


    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

}
