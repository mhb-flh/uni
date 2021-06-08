package com.example.pars.uni_prj.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pars.uni_prj.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ordersFragment extends Fragment {

    TextView number;
    TextView price;
    ImageView img;
    Button buy;


    public ordersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_orders, container, false);
        TextView name = view.findViewById(R.id.productName);
        number=view.findViewById(R.id.productNumber);
        price=view.findViewById(R.id.productPrice);
        img=view.findViewById(R.id.img);
        buy=view.findViewById(R.id.buy_btn);

        Intent intent=Objects.requireNonNull(getActivity()).getIntent();
        String Price=  intent.getStringExtra("price");
        String imageName=  intent.getStringExtra("imageName");
        String img2=  intent.getStringExtra("img");
        price.setText(Price);
        name.setText(imageName);
        if (img!=null){
            Log.d("order","img is available");
            Picasso.get().load(img2).into(img);
        }


        buy.setOnClickListener(view1 -> {
            //TODO directed to buying page
            Toast.makeText(getActivity(), "go to bank page", Toast.LENGTH_SHORT).show();
        });






        return view;
    }

}
