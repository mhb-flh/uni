 package com.example.pars.uni_prj;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.design.widget.TextInputLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends Fragment {

    TextInputLayout loginUser, loginPassword,loginEmail;
    Button loginBtn;
    TextView welcomeText;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View loginView = inflater.inflate(R.layout.fragment_login, container, false);

        loginUser = loginView.findViewById(R.id.login_user);
        loginEmail=loginView.findViewById(R.id.login_email);
        loginPassword = loginView.findViewById(R.id.login_password);
        loginBtn = loginView.findViewById(R.id.login_btn);
        welcomeText = loginView.findViewById(R.id.wlc_txt);

        welcomeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frg_container,new Register()).commit();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAction();
            }
        });

        return loginView;

    }

    public void loginAction() {

        String logUser = loginUser.getEditText().toString();
        String logEmail = loginEmail.getEditText().toString();
        String logPassword = loginPassword.getEditText().toString();

        Call<User> logCall = MainActivity.apiInterface.loginCall(logUser,logEmail, logPassword);

        logCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getApiResposnse().equals("SUCCESS")) {
                    Toast.makeText(getActivity(), "You login successfully!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), firstPage.class);
                    startActivity(i);


                }
                else if (response.body().getApiResposnse().equals("FAILED")) {
                    Toast.makeText(getActivity(), "Login failed!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Username or Password incorrect. please try again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                String loginError = "Connection Error!";
                Toast.makeText(getActivity(), loginError + t, Toast.LENGTH_SHORT).show();

            }
        });

    }

}
