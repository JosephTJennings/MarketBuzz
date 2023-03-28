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
    Context context;
    ArrayList<FollowersModel> followers;

    public stk_recyclerView_adapter(Context context, ArrayList<FollowersModel> followers) {
        this.context = context;
        this.followers = followers;
    }

    @NonNull
    @Override
    public stk_recyclerView_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.follower_row, parent, false);
        return new stk_recyclerView_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull stk_recyclerView_adapter.MyViewHolder holder, int position) {
        holder.userName.setText(followers.get(position).getFollowerName());
        holder.imageView.setImageResource(followers.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return followers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView userName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            userName = itemView.findViewById(R.id.userName);
        }
    }
}
