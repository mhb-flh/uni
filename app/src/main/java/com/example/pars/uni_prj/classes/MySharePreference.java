package com.example.pars.uni_prj.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.pars.uni_prj.classes.AppClass.getintance;

public class MySharePreference {

    private static MySharePreference instance;
    private static SharedPreferences pref;

    public MySharePreference(Context context)
    {
        if (context != null)
        {
            pref = PreferenceManager.getDefaultSharedPreferences(context);
        }
        else
        {
            pref = PreferenceManager.getDefaultSharedPreferences(getintance());
        }
    }

    public static MySharePreference getInstance(Context context)
    {
        if (instance == null || pref == null)
        {
            instance = new MySharePreference(context);
        }
        return instance;
    }


    public String getLanguage()
    {
        return pref.getString("appLanguage", "en");
    }

    public void setLanguage(String b)
    {
        pref.edit().putString("appLanguage", b).apply();
    }

}
