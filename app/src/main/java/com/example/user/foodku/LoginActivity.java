package com.example.user.foodku;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.foodku.model.LoginModel;
import com.example.user.foodku.rest.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    ProgressDialog loading;
    String email, password;

    Context mContext;
    APIService mApiService;
    Button btnMasuk;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getSupportActionBar().hide();
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnMasuk = (Button) findViewById(R.id.btnMasuk);

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });



        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO LOGIN
                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    doSignIn(email, password);
                } else {
                    Toast.makeText(LoginActivity.this, "data kosong", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void doSignIn(final String email, final String password) {
        loading = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        loading.setMessage("Authenticating..");
        loading.show();
        APIService.service_post.postLogin(email, password).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                // if untuk intent ke fragment
                if(response.code() == 200 ) {
                    loading.dismiss();
                    Intent dashboard = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(dashboard);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

}
