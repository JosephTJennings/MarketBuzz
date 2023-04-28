package com.example.stockproject.Activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockproject.Activities.model.UsersModel;
import com.example.stockproject.R;

import java.util.ArrayList;

/**
 * This is the adapter for followers in the recycler view.
 */
public class usr_recyclerView_adapter extends RecyclerView.Adapter<usr_recyclerView_adapter.MyViewHolder>{
    private final recyclerView_interface usrRecViewInterface;
    Context context;
    ArrayList<UsersModel> users;
    /**
     * This constructor creates an adapter.
     * @param context - A handle to the system.
     * @param users - A list of user models.
     * @param usrRecViewInterface the interface to interact with user models.
     */
    public usr_recyclerView_adapter(Context context, ArrayList<UsersModel> users, recyclerView_interface usrRecViewInterface) {
        this.context = context;
        this.users = users;
        this.usrRecViewInterface = usrRecViewInterface;
    }

    @NonNull
    @Override
    /**
     * This class creates a view holder for the adapter on creation of this class.
     */
    public usr_recyclerView_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.leaderboard_row, parent, false);
        return new usr_recyclerView_adapter.MyViewHolder(view, usrRecViewInterface);
    }

    @Override
    /**
     * This class assigns the view holder with values.
     * @param position - an integer position from the arrayList in the constructor.
     */
    public void onBindViewHolder(@NonNull usr_recyclerView_adapter.MyViewHolder holder, int position) {
        holder.userName.setText(users.get(position).getUsername());
        holder.pos.setText(users.get(position).getPos());
        holder.valuation.setText(users.get(position).getValuation());
        holder.imageView.setImageResource(users.get(position).getImage());
    }

    @Override
    /**
     * Returns the item count for the arrayList in the constructor.
     */
    public int getItemCount() {
        return users.size();
    }
    /**
     * This is the ViewHolder class for Users.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView userName, pos, valuation;
        /**
         * This constructor creates a view holder.
         * @param itemView the view of the item
         * @param usrRecyclerViewInterface the interface used to interact with the item
         */
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
