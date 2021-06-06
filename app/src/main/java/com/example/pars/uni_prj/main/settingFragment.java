package com.example.pars.uni_prj.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pars.uni_prj.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class settingFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    String[] languages = {"English", "Persian"};
    String[] theme= {"Light", "Dark"};
    Spinner language_spinner,theme_spinner;

    public settingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        language_spinner = view.findViewById(R.id.spinner_language);
        theme_spinner= view.findViewById(R.id.spinner_theme);

        ArrayAdapter<String> adapter_language = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, languages);
        adapter_language.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language_spinner.setAdapter(adapter_language);
        language_spinner.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapter_theme = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, theme);
        adapter_theme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        theme_spinner.setAdapter(adapter_theme);
        theme_spinner.setOnItemSelectedListener(this);


        return view;


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
