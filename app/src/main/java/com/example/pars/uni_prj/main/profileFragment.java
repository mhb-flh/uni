package com.example.pars.uni_prj.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.loginPrefManager;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class profileFragment extends Fragment {


    public profileFragment() {
        // Required empty public constructor
    }

    TextView username,name,email;
    Button logout;
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USER_NAME = "username";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        loginPrefManager prefManager = new loginPrefManager(getContext());
//        name=view.findViewById(R.id.user_name);
        username=view.findViewById(R.id.user_userName);
        email=view.findViewById(R.id.user_email);
        logout=view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prefManager.logoutUser();

            }
        });

         HashMap<String,String > userDetails=prefManager.getUserDetails();

         username.setText(userDetails.get(KEY_USER_NAME));
         email.setText(userDetails.get(KEY_EMAIL));

        return view;
    }

}
