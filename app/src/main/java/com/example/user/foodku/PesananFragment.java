package com.example.user.foodku;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.foodku.model.OrdermenuModel;
import com.example.user.foodku.rest.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PesananFragment extends Fragment {

    Button btnOrder;
    TextView txtPelanggan, txtMeja;
    ProgressDialog loading;

    String sid_meja, spelanggan;

    public PesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pesanan, container, false);

        btnOrder = (Button) rootView.findViewById(R.id.btnOrder);
        txtMeja = (TextView) rootView.findViewById(R.id.txtMeja);
        txtPelanggan = (TextView) rootView.findViewById(R.id.txtPelanggan);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sid_meja = txtMeja.getText().toString();
                spelanggan = txtPelanggan.getText().toString();
                doCreate(sid_meja, spelanggan);
            }
        });
        return rootView;
    }

    private void doCreate(final String sid_meja, String spelanggan) {
        loading = new ProgressDialog(getContext(), R.style.Theme_AppCompat_DayNight_Dialog);
        loading.setMessage("Menambahkan..");
        loading.show();
        APIService.service_post.postOrder(sid_meja, spelanggan).enqueue(new Callback<OrdermenuModel>() {
            @Override
            public void onResponse(Call<OrdermenuModel> call, Response<OrdermenuModel> response) {
                // if untuk intent ke fragment
                if(response.code() == 200 ) {
                    loading.dismiss();
                    Intent dashboard = new Intent(getActivity(), OrderDetailActivity.class);
                    startActivity(dashboard);
                }
                else{
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<OrdermenuModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

}
