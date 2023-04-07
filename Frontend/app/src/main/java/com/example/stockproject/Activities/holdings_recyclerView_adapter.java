package com.example.stockproject.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockproject.R;

import java.util.ArrayList;

public class holdings_recyclerView_adapter extends RecyclerView.Adapter<holdings_recyclerView_adapter.MyViewHolder> {
    Context context;
    ArrayList<HoldingsModel> holdings;

    public holdings_recyclerView_adapter(Context context, ArrayList<HoldingsModel> holdings) {
        this.context = context;
        this.holdings = holdings;
    }

    @NonNull
    @Override
    public holdings_recyclerView_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.holding_row, parent, false);
        return new holdings_recyclerView_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holdings_recyclerView_adapter.MyViewHolder holder, int position) {
        holder.price.setText(String.valueOf(holdings.get(position).getPrice()));
        holder.ticker.setText(String.valueOf(holdings.get(position).getTicker()));
        holder.quantity.setText(String.valueOf(holdings.get(position).getQuantity()));
        holder.rank.setText(String.valueOf(holdings.get(position).getRank()));
        holder.total.setText(String.valueOf(holdings.get(position).getTotal()));
    }

    @Override
    public int getItemCount() {
        return holdings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rank;
        TextView ticker;
        TextView price;
        TextView quantity;
        TextView total;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("Setting shit...");
            rank = itemView.findViewById(R.id.rank);
            ticker = itemView.findViewById(R.id.ticker);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            total = itemView.findViewById(R.id.total);
            System.out.println("finished" + total);
        }
    }
}
