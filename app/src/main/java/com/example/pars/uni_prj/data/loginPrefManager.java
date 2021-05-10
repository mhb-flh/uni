package com.example.pars.uni_prj.data;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.main.Login;
import com.example.pars.uni_prj.main.MainActivity;

import java.util.HashMap;

public class loginPrefManager {


    SharedPreferences pref;
    Context context;
    SharedPreferences.Editor editor;

    private static final String PREF_NAME = "login_pref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    // Constructor
    public loginPrefManager(Context context){
            this.context = context;
            pref = context.getSharedPreferences(PREF_NAME,context. MODE_PRIVATE);
            editor = pref.edit();
        }

        /**
         * Create login session
         * */
        public void createLoginSession(String name, String email){
            // Storing login value as TRUE
            editor.putBoolean(IS_LOGIN, true);
            editor.putString(KEY_NAME, name);
            editor.putString(KEY_EMAIL, email);
            editor.commit();
        }

        /**
         * Check login method wil check user login status
         * If false it will redirect user to login page
         * Else won't do anything
         * */
        public void checkLogin(){
            // Check login status
            if(!this.isLoggedIn()){
                // user is not logged in redirect him to Login Activity

                Intent i = new Intent(context, MainActivity.class);
                // Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
                context.startActivity(i);
            }

        }



        /**
         * Get stored session data
         * */
        public HashMap<String, String> getUserDetails(){
            HashMap<String, String> user = new HashMap<String, String>();
            // user name
            user.put(KEY_NAME, pref.getString(KEY_NAME, null));

            // user email id
            user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

            // return user
            return user;
        }

        /**
         * Clear session details
         * */
        public void logoutUser(){
            // Clearing all data from Shared Preferences
            editor.clear();
            editor.commit();

            // After logout redirect user to Loing Activity
            Intent i = new Intent(context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
        }

        /**
         * Quick check for login
         * **/
        // Get Login State
        public boolean isLoggedIn(){
            return pref.getBoolean(IS_LOGIN, false);
        }

    public void setLogin(Boolean start){
        pref.edit().putBoolean(IS_LOGIN,start).apply();
    }


    }


//    private Context context;
//    private SharedPreferences pref;
//    public static final String PREF_NAME="login_pref";
//    public static final String PREF_KEY="show_login";
//
//    public loginPrefManager(Context context) {
//        this.context = context;
//        pref=context.getSharedPreferences(PREF_NAME,context.MODE_PRIVATE);
//    }
//
//    public boolean show_login(){
//            return pref.getBoolean(PREF_KEY,true);
//    }
//
//    public void setLogin(Boolean start){
//        pref.edit().putBoolean(PREF_KEY,start).apply();
//    }
//

