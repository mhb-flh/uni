package com.example.pars.uni_prj.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.Items;
import com.example.pars.uni_prj.data.listAdapter;

import java.util.ArrayList;
import java.util.List;


public class searchFragment extends Fragment {


    private RecyclerView search_recycler;
    SearchView searchView;
    listAdapter myAdapter;
    final List<Items> myItems = new ArrayList<>();

    public searchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchView=view.findViewById(R.id.search_view);
        search_recycler=view.findViewById(R.id.search_recycler);
        myAdapter = new listAdapter(myItems, getActivity());


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                myAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        return view;
    }

}
