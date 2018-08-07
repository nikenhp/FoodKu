package com.example.user.foodku;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.foodku.model.Menu;
import com.google.gson.GsonBuilder;

import static com.example.user.foodku.controller.AppConfig.URL_PICTURE;

public class DetailActivity extends AppCompatActivity {

    ImageView imgdetail;
    TextView tvNama, tvDesc, tvHarga;
    ImageButton btnBack;

    Menu results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgdetail = (ImageView) findViewById(R.id.imgdetail);
        tvNama = (TextView) findViewById(R.id.tvNama);
        tvHarga = (TextView) findViewById(R.id.tvHarga);
        tvDesc = (TextView) findViewById(R.id.tvDesc);

        btnBack = (ImageButton) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        results = new GsonBuilder()
                .create()
                .fromJson(getIntent().getStringExtra("menu"), Menu.class);

        Glide.with(DetailActivity.this)
                .load(URL_PICTURE+results.getGambar())
                .into(imgdetail);

        tvNama.setText(results.getNama_menu());
        tvDesc.setText(results.getDeskripsi());
        tvHarga.setText(results.getHarga());
    }
}
