package com.example.stockproject.Activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockproject.Activities.model.UserStocksModel;
import com.example.stockproject.R;

import java.util.ArrayList;

public class usrStk_recyclerView_adapter extends RecyclerView.Adapter<usrStk_recyclerView_adapter.MyViewHolder> {
    private final recyclerView_interface usrStkRecViewInterface;
    Context context;
    ArrayList<UserStocksModel> userStocks;

    public usrStk_recyclerView_adapter(Context context, ArrayList<UserStocksModel> userStocks, recyclerView_interface usrStkRecViewInterface) {
        this.context = context;
        this.userStocks = userStocks;
        this.usrStkRecViewInterface = usrStkRecViewInterface;
    }

    @NonNull
    @Override
    public usrStk_recyclerView_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.userstock_row, parent, false);
        return new usrStk_recyclerView_adapter.MyViewHolder(view, usrStkRecViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull usrStk_recyclerView_adapter.MyViewHolder holder, int position) {
        holder.stockName.setText(userStocks.get(position).getTicker());
        holder.stockPosition.setText(userStocks.get(position).getPos());
        holder.stockPrice.setText(userStocks.get(position).getPrice());
        holder.stockQuantity.setText(userStocks.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return userStocks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stockPosition, stockName, stockPrice, stockQuantity;

        public MyViewHolder(@NonNull View itemView, recyclerView_interface usrStkRecViewInterface) {
            super(itemView);
            stockPosition = itemView.findViewById(R.id.stkPos);
            stockName = itemView.findViewById(R.id.stkName);
            stockPrice = itemView.findViewById(R.id.stkPrice);
            stockQuantity = itemView.findViewById(R.id.stkQuantity);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (usrStkRecViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            usrStkRecViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}

