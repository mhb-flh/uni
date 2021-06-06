package com.example.pars.uni_prj.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.User;
import com.example.pars.uni_prj.data.loginPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends Fragment {

    private TextInputLayout regName;
    TextInputLayout regUsername;
    TextInputLayout regEmail;
    TextInputLayout regPassword;
    Button regBtn;
    TextView goToLogin;
    loginPrefManager prefManager;

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View regView = inflater.inflate(R.layout.fragment_register, container, false);

        regName = regView.findViewById(R.id.reg_name);
        regUsername = regView.findViewById(R.id.reg_username);
        regEmail = regView.findViewById(R.id.reg_email);
        regPassword = regView.findViewById(R.id.reg_password);
        regBtn = regView.findViewById(R.id.register_btn);
        goToLogin = regView.findViewById(R.id.goToLogin);
        prefManager = new loginPrefManager(getContext());

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frg_container, new Login()).commit();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAction();
            }
        });

        return regView;

    }

    public void registerAction() {

        String rName = regName.getEditText().getText().toString().trim();
        String rUsername = regUsername.getEditText().getText().toString().trim();
        String rPassword = regPassword.getEditText().getText().toString().trim();
        String rEmail = regEmail.getEditText().getText().toString().trim();
        Call<User> rCall = MainActivity.apiInterface.regCall(rName, rUsername, rEmail, rPassword);

        rCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {


                switch (response.body().getApiResposnse()) {
                    case "SUCCESS":
                        Toast.makeText(getContext(), "You registered successfully!", Toast.LENGTH_SHORT).show();
                        prefManager.setLogin(true);
                        Intent i = new Intent(getActivity(), firstPage.class);
                        startActivity(i);
                        getActivity().finish();

                        break;
                    case "REGISTERED":
                        Toast.makeText(getActivity(), "You registered already!", Toast.LENGTH_SHORT).show();
                        break;
                    case "ERROR":
                        Toast.makeText(getActivity(), "A problem occurred...", Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                String regError = "Connection Error!";
                Toast.makeText(getActivity(), regError + t, Toast.LENGTH_SHORT).show();

            }
        });

        regName.getEditText().getText().clear();
        regUsername.getEditText().getText().clear();
        regEmail.getEditText().getText().clear();
        regPassword.getEditText().getText().clear();

    }

}
