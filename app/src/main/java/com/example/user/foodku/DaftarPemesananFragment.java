package com.example.user.foodku;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.foodku.adapter.OrderAdapter;
import com.example.user.foodku.model.OrdermenuModel;
import com.example.user.foodku.rest.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarPemesananFragment extends Fragment {
    RecyclerView rvOrder;
    OrdermenuModel list_order;

    public DaftarPemesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daftar_pemesanan, container, false);

        rvOrder = (RecyclerView) view.findViewById(R.id.rv_order);

        LinearLayoutManager llmData = new LinearLayoutManager(getActivity().getApplicationContext());
        rvOrder.setLayoutManager(llmData);

        APIService.service_get.getOrder().enqueue(new Callback<OrdermenuModel>() {
            @Override
            public void onResponse(Call<OrdermenuModel> call, Response<OrdermenuModel> response) {
                list_order = response.body();
                OrderAdapter orderadapter = new OrderAdapter(getContext(), list_order);
                rvOrder.setAdapter(orderadapter);
            }

            @Override
            public void onFailure(Call<OrdermenuModel> call, Throwable t) {

            }
        });

        return view;
    }

}
