package com.example.stockproject.Activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockproject.Activities.model.FollowersModel;
import com.example.stockproject.R;

import java.util.ArrayList;
/**
 * This is the adapter for followers in the recycler view.
 */
public class fol_recyclerView_adapter extends RecyclerView.Adapter<fol_recyclerView_adapter.MyViewHolder> {
    Context context;
    ArrayList<FollowersModel> followers;

    /**
     * This constructor creates an adapter.
     * @param context - A handle to the system.
     * @param followers - A list of follower models.
     */
    public fol_recyclerView_adapter(Context context, ArrayList<FollowersModel> followers) {
        this.context = context;
        this.followers = followers;
    }

    @NonNull
    @Override
    /**
     * This class creates a view holder for the adapter on creation of this class.
     */
    public fol_recyclerView_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.follower_row, parent, false);
        return new fol_recyclerView_adapter.MyViewHolder(view);
    }

    @Override
    /**
     * This class assigns the view holder with values.
     * @param position - an integer position from the arrayList in the constructor.
     */
    public void onBindViewHolder(@NonNull fol_recyclerView_adapter.MyViewHolder holder, int position) {
        holder.userName.setText(followers.get(position).getFollowerName());
        holder.imageView.setImageResource(followers.get(position).getImage());
    }

    @Override
    /**
     * Returns the item count for the arrayList in the constructor.
     */
    public int getItemCount() {
        return followers.size();
    }

    /**
     * This is the ViewHolder class for Followers.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView userName;
        /**
         * This constructor creates a view holder.
         * @param itemView the view of the item
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            userName = itemView.findViewById(R.id.rank);
        }
    }
}
