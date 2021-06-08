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
import android.widget.Toast;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.data.User;
import com.example.pars.uni_prj.data.loginPrefManager;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    private TextInputLayout loginUser;
    TextInputLayout loginPassword;
    TextInputLayout loginEmail;
    Button loginBtn, welcomeText;
    loginPrefManager prefManager;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View loginView = inflater.inflate(R.layout.fragment_login, container, false);

        loginUser = loginView.findViewById(R.id.login_user);
        loginEmail = loginView.findViewById(R.id.login_email);
        loginPassword = loginView.findViewById(R.id.login_password);
        loginBtn = loginView.findViewById(R.id.login_btn);
        welcomeText = loginView.findViewById(R.id.wlc_txt);
        prefManager = new loginPrefManager(getContext());


        welcomeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getFragmentManager()).beginTransaction().replace(R.id.frg_container, new RegisterFragment()).commit();
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

        String logUser = Objects.requireNonNull(loginUser.getEditText()).getText().toString().trim();
        String logEmail = Objects.requireNonNull(loginEmail.getEditText()).getText().toString().trim();
        String logPassword = Objects.requireNonNull(loginPassword.getEditText()).getText().toString().trim();

        Call<User> logCall = MainActivity.apiInterface.loginCall(logUser, logEmail, logPassword);

        logCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (Objects.requireNonNull(response.body()).getApiResposnse().equals("SUCCESS")) {
                    Toast.makeText(getActivity(), "You login successfully!", Toast.LENGTH_SHORT).show();
//                    if (prefManager.isLoggedIn()){
                    prefManager.setLogin(true);
                    prefManager.createLoginSession(logUser,logEmail);
                    Intent i = new Intent(getActivity(), firstPage.class);
                    startActivity(i);
                    Objects.requireNonNull(getActivity()).finish();

//                    }

                } else if (response.body().getApiResposnse().equals("FAILED")) {
                    Toast.makeText(getActivity(), "LoginFragment failed!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Username or Password incorrect. please try again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                String loginError = "Connection Error!";
                Toast.makeText(getContext(), loginError + t, Toast.LENGTH_SHORT).show();

            }
        });

        loginEmail.getEditText().getText().clear();
        loginPassword.getEditText().getText().clear();
        loginUser.getEditText().getText().clear();


    }

}
