package com.example.pars.uni_prj.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pars.uni_prj.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class orders extends Fragment {

    TextView name,number,price;


    public orders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_orders, container, false);
        name=view.findViewById(R.id.productName);
        number=view.findViewById(R.id.productNumber);
        price=view.findViewById(R.id.productPrice);


        return view;
    }

}
