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

public class usr_recyclerView_adapter extends RecyclerView.Adapter<usr_recyclerView_adapter.MyViewHolder> {
    Context context;
    ArrayList<UsersModel> users;

    public usr_recyclerView_adapter(Context context, ArrayList<UsersModel> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public usr_recyclerView_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.leaderboard_row, parent, false);
        return new usr_recyclerView_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull usr_recyclerView_adapter.MyViewHolder holder, int position) {
        holder.userName.setText(users.get(position).getUsername());
        holder.position.setText(users.get(position).getPosition());
        holder.valuation.setText(users.get(position).getValuation());
        holder.change.setText(users.get(position).getChange());
        holder.imageView.setImageResource(users.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView userName, position, valuation, change;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.valuationImage2);
            userName = itemView.findViewById(R.id.userName);
            position = itemView.findViewById(R.id.position);
            valuation = itemView.findViewById(R.id.valuation);
            change = itemView.findViewById(R.id.change);
        }
    }
}
