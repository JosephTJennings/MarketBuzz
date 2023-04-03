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

public class usr_recyclerView_adapter extends RecyclerView.Adapter<usr_recyclerView_adapter.MyViewHolder>{
    private final recyclerView_interface usrRecViewInterface;
    Context context;
    ArrayList<UsersModel> users;

    public usr_recyclerView_adapter(Context context, ArrayList<UsersModel> users, recyclerView_interface usrRecViewInterface) {
        this.context = context;
        this.users = users;
        this.usrRecViewInterface = usrRecViewInterface;
    }

    @NonNull
    @Override
    public usr_recyclerView_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.leaderboard_row, parent, false);
        return new usr_recyclerView_adapter.MyViewHolder(view, usrRecViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull usr_recyclerView_adapter.MyViewHolder holder, int position) {
        holder.userName.setText(users.get(position).getUsername());
        holder.pos.setText(users.get(position).getPos());
        holder.valuation.setText(users.get(position).getValuation());
        holder.imageView.setImageResource(users.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView userName, pos, valuation;

        public MyViewHolder(@NonNull View itemView, recyclerView_interface usrRecyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.valuationImage);
            userName = itemView.findViewById(R.id.username);
            pos = itemView.findViewById(R.id.position);
            valuation = itemView.findViewById(R.id.valuation);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (usrRecyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            usrRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
