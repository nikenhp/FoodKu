package com.example.user.foodku;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.foodku.model.Menu;
import com.example.user.foodku.model.MenuModel;
import com.example.user.foodku.model.Ordermenu;
import com.example.user.foodku.model.OrdermenuModel;
import com.example.user.foodku.model.OrdermenudetailModel;
import com.example.user.foodku.rest.APIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {

    private Spinner spList, spMenu;
    Context mContext;

    ArrayList<String> dataOrder;
    ArrayList<String> dataMenu;
    ProgressDialog loading;
    String[] stringIdNama;
    String[] stringIdMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        spList = (Spinner) findViewById(R.id.spList);
        spMenu = (Spinner) findViewById(R.id.spMenu);
        loadDataPelanggan();
        loadDataMenu();

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        final EditText kuantitas = (EditText) findViewById(R.id.kuantitas);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skuantitas = kuantitas.getText().toString();
                String sid_order= stringIdNama[spList.getSelectedItemPosition()];
                String sid_menu = stringIdMenu[spMenu.getSelectedItemPosition()];
                createOrder(sid_order, sid_menu, skuantitas);
            }
        });
    }

    private void createOrder (final String sid_order, String sid_menu, String skuantitas) {
        loading = new ProgressDialog(OrderDetailActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        loading.setMessage("Menambahkan..");
        loading.show();
        APIService.service_post.postDetail(sid_order, sid_menu, skuantitas).enqueue(new Callback<OrdermenudetailModel>() {
            @Override
            public void onResponse(Call<OrdermenudetailModel> call, Response<OrdermenudetailModel> response) {
                if(response.code() == 200 ) {
                    loading.dismiss();
                    Intent dashboard = new Intent(OrderDetailActivity.this, MainActivity.class);
                    startActivity(dashboard);
                }else if(response.code() == 201){
                    loading.dismiss();
                    Intent dashboard = new Intent(OrderDetailActivity.this, MainActivity.class);
                    startActivity(dashboard);
                }else {
                    Toast.makeText(OrderDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }
            @Override
            public void onFailure(Call<OrdermenudetailModel> call, Throwable t) {
                Toast.makeText(OrderDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }


    private void loadDataPelanggan() {
        APIService.service_get.getOrder().enqueue(new Callback<OrdermenuModel>() {
            @Override
            public void onResponse(Call<OrdermenuModel> call, Response<OrdermenuModel> response) {
                ArrayList<Ordermenu> dataOrderModelArrayList= response.body().getOrdermenu();
                dataOrder = new ArrayList<>();
                String[] stringArray = new String[dataOrderModelArrayList.size()];
                stringIdNama = new String[dataOrderModelArrayList.size()];
                Log.v("lala", dataOrderModelArrayList.size() + "");
                for (int i = 0 ; i<dataOrderModelArrayList.size() ; i++)
                {
                    Log.v("asd", dataOrderModelArrayList.get(i).getPelanggan());
                    stringArray[i] = dataOrderModelArrayList.get(i).getPelanggan();
                    stringIdNama[i] = dataOrderModelArrayList.get(i).getId();
//                    Log.v("asd", dataOrderModelArrayList.get(i).getId());
//                    stringArray[i] = dataOrderModelArrayList.get(i).getId();
                }
                // inisialiasi Array Adapter dengan memasukkan string array di atas
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(OrderDetailActivity.this,
                        android.R.layout.simple_spinner_item, stringArray);

                // mengeset Array Adapter tersebut ke Spinner
                spList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<OrdermenuModel> call, Throwable t) {
            }
        });
    }

    private  void loadDataMenu() {
        APIService.service_get.getMenu().enqueue(new Callback<MenuModel>() {
            @Override
            public void onResponse(Call<MenuModel> call, Response<MenuModel> response) {
                ArrayList<Menu> dataMenuModelArrayList= response.body().getMenu();
                dataMenu = new ArrayList<String>();
                String[] stringArray = new String[dataMenuModelArrayList.size()];
                stringIdMenu = new String[dataMenuModelArrayList.size()];
                Log.v("lala", dataMenuModelArrayList.size() + "");
                for (int i = 0 ; i<dataMenuModelArrayList.size() ; i++)
                {
                    Log.v("asd", dataMenuModelArrayList.get(i).getId());
                    stringArray[i] = dataMenuModelArrayList.get(i).getNama_menu();
                    stringIdMenu[i] = dataMenuModelArrayList.get(i).getId();

                }
                // inisialiasi Array Adapter dengan memasukkan string array di atas
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(OrderDetailActivity.this,
                        android.R.layout.simple_spinner_item, stringArray);

                // mengeset Array Adapter tersebut ke Spinner
                spMenu.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MenuModel> call, Throwable t) {
            }
        });
    }
}
