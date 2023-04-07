package com.example.stockproject.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockproject.R;

import java.util.ArrayList;

public class stk_recyclerView_adapter extends RecyclerView.Adapter<stk_recyclerView_adapter.MyViewHolder> {
    private final recyclerView_interface stkRecViewInterface;
    Context context;
    ArrayList<StocksModel> stocks;

    public stk_recyclerView_adapter(Context context, ArrayList<StocksModel> stocks, recyclerView_interface stkRecViewInterface) {
        this.context = context;
        this.stocks = stocks;
        this.stkRecViewInterface = stkRecViewInterface;
    }

    @NonNull
    @Override
    public stk_recyclerView_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stock_row, parent, false);
        return new stk_recyclerView_adapter.MyViewHolder(view, stkRecViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull stk_recyclerView_adapter.MyViewHolder holder, int position) {
        holder.stockName.setText(stocks.get(position).getStockName());
        holder.value.setText(stocks.get(position).getValue());
        holder.changeImage.setImageResource(stocks.get(position).getChange());
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stockName, value;
        ImageView changeImage;
        public MyViewHolder(@NonNull View itemView, recyclerView_interface stkRecyclerViewInterface) {
            super(itemView);
            value = itemView.findViewById(R.id.value);
            stockName = itemView.findViewById(R.id.stockName);
            changeImage = itemView.findViewById(R.id.valuationImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (stkRecyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            stkRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
