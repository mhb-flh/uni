package com.example.pars.uni_prj.main;


import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends Fragment {

    TextInputLayout regName, regUsername, regEmail, regPassword;
    Button regBtn;
    TextView goToLogin;

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View regView = inflater.inflate(R.layout.fragment_register, container, false);

        regName = regView.findViewById(R.id.reg_name);
        regUsername = regView.findViewById(R.id.reg_username);
        regEmail = regView.findViewById(R.id.reg_email);
        regPassword = regView.findViewById(R.id.reg_password);
        regBtn = regView.findViewById(R.id.register_btn);
        goToLogin = regView.findViewById(R.id.goToLogin);

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
                        break;
                    case "REGISTERED":
                        Toast.makeText(getActivity(), "You registered already!", Toast.LENGTH_SHORT).show();
                        break;
                    case "ERROR":
                        Toast.makeText(getActivity(), "A problem occurred...", Toast.LENGTH_SHORT).show();
                        break;
                }



                /*  if Statement:

                        if (response.body().getApiResposnse().equals("SUCCESS")) {
                        Toast.makeText(getActivity(), "You registered successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else if (response.body().getApiResposnse().equals("REGISTERED")) {
                        Toast.makeText(getActivity(), "You registered already!", Toast.LENGTH_SHORT).show();
                    }
                    else if (response.body().getApiResposnse().equals("ERROR")) {
                        Toast.makeText(getActivity(), "A problem occurred...", Toast.LENGTH_SHORT).show();
                }
                 */

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                String regError = "Connection Error!";
                Toast.makeText(getActivity(), regError + t, Toast.LENGTH_SHORT).show();

            }
        });

//        regName.settext("");
//        regUsername.setText("");
//        regEmail.setText("");
//        regPassword.setText("");

    }

}
