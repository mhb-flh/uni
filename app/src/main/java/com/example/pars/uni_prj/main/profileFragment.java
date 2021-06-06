package com.example.pars.uni_prj.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.loginPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class profileFragment extends Fragment {


    public profileFragment() {
        // Required empty public constructor
    }

    TextView username,name,email;
    Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        loginPrefManager prefManager = new loginPrefManager(getActivity());
        name=view.findViewById(R.id.user_name);
        username=view.findViewById(R.id.user_userName);
        email=view.findViewById(R.id.user_email);
        logout=view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.logoutUser();
            }
        });

        name.setText((CharSequence) prefManager.getUserDetails());

        return view;
    }

}
