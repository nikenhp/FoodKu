package com.example.user.foodku;


import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.foodku.adapter.MenuAdapter;
import com.example.user.foodku.model.Menu;
import com.example.user.foodku.model.MenuModel;
import com.example.user.foodku.rest.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MakananFragment extends Fragment {

    RecyclerView rvMenu;
    RelativeLayout detail;
    MenuModel listdataMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_makanan, container, false);
        rvMenu = (RecyclerView) rootView.findViewById(R.id.rv_category);

        LinearLayoutManager llmData = new LinearLayoutManager(getActivity().getApplicationContext());
        rvMenu.setLayoutManager(llmData);

        APIService.service_get.getMenu().enqueue(new Callback<MenuModel>() {
            @Override
            public void onResponse(Call<MenuModel> call, Response<MenuModel> response) {
                listdataMenu = response.body();
                MenuAdapter adapter = new MenuAdapter(getContext(), listdataMenu);
                rvMenu.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MenuModel> call, Throwable t) {

            }
        });

        return rootView;
    }


}
