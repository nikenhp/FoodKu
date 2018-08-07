package com.example.user.foodku.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.foodku.DetailActivity;
import com.example.user.foodku.R;
import com.example.user.foodku.controller.AppConfig;
import com.example.user.foodku.model.Menu;
import com.example.user.foodku.model.MenuModel;
import com.google.gson.GsonBuilder;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private Context mContext;
    private MenuModel list_data_menu;

    List<Menu> menu;

    public MenuAdapter(Context mContext, MenuModel list_data_menu) {
        this.mContext = mContext;
        this.list_data_menu = list_data_menu;
        menu = list_data_menu.getMenu();
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_makanan, null);
        return new MenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.tvNama.setText(list_data_menu.getMenu().get(position).getNama_menu());
        holder.tvHarga.setText(list_data_menu.getMenu().get(position).getHarga());
        holder.tvDesc.setText(list_data_menu.getMenu().get(position).getDeskripsi());

        Log.d("gambar", "onBindViewHolder: " + AppConfig.URL_PICTURE + list_data_menu.getMenu().get(position).getGambar());
        Glide.with(mContext)
                .load(AppConfig.URL_PICTURE + list_data_menu.getMenu().get(position).getGambar())
                .centerCrop()
                .crossFade().placeholder(R.drawable.nav)
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu data = menu.get(position);
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("menu", new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list_data_menu.getMenu().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvHarga, tvDesc;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHarga);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);

            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
