package com.example.user.foodku.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.foodku.R;
import com.example.user.foodku.model.OrdermenuModel;

import java.nio.DoubleBuffer;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context mContext;
    private OrdermenuModel list_order;

    public OrderAdapter(Context mContext, OrdermenuModel list_order) {
        this.mContext = mContext;
        this.list_order = list_order;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order,null);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderAdapter.ViewHolder holder, int position) {
        holder.tvPelanggan.setText(list_order.getOrdermenu().get(position).getPelanggan());
        holder.tvTable.setText(Integer.toString(list_order.getOrdermenu().get(position).getId_table()));
        holder.tvTanggal.setText(list_order.getOrdermenu().get(position).getTanggal());
        holder.tvTotal.setText(list_order.getOrdermenu().get(position).getTotal());
        holder.tvId.setText(list_order.getOrdermenu().get(position).getId());
    }

    @Override
    public int getItemCount() {
        return list_order.getOrdermenu().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPelanggan, tvTable, tvTanggal, tvTotal, tvId;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPelanggan = (TextView) itemView.findViewById(R.id.tvPelanggan);
            tvTable = (TextView) itemView.findViewById(R.id.tvMeja);
            tvTanggal = (TextView) itemView.findViewById(R.id.tvTanggal);
            tvTotal = (TextView) itemView.findViewById(R.id.tvTotal);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
        }
    }
}
