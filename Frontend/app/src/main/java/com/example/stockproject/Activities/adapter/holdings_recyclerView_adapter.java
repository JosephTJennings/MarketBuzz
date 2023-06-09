package com.example.stockproject.Activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockproject.Activities.model.HoldingsModel;
import com.example.stockproject.R;

import java.util.ArrayList;
/**
 * This is the adapter for stocks in the recycler view.
 */
public class holdings_recyclerView_adapter extends RecyclerView.Adapter<holdings_recyclerView_adapter.MyViewHolder> {
    private final recyclerView_interface holdingsRecViewInterface;
    Context context;
    ArrayList<HoldingsModel> holdings;
    /**
     * This constructor creates an adapter.
     * @param context - A handle to the system.
     * @param holdings - A list of holdings models.
     * @param holdingsRecViewInterface - An interface to interact with holding items in the list
     */
    public holdings_recyclerView_adapter(Context context, ArrayList<HoldingsModel> holdings, recyclerView_interface holdingsRecViewInterface) {
        this.context = context;
        this.holdings = holdings;
        this.holdingsRecViewInterface = holdingsRecViewInterface;
    }

    @NonNull
    @Override
    /**
     * This class creates a view holder for the adapter on creation of this class.
     */
    public holdings_recyclerView_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.holding_row, parent, false);
        return new holdings_recyclerView_adapter.MyViewHolder(view, holdingsRecViewInterface);
    }

    @Override
    /**
     * This class assigns the view holder with values.
     * @param position - an integer position from the arrayList in the constructor.
     */
    public void onBindViewHolder(@NonNull holdings_recyclerView_adapter.MyViewHolder holder, int position) {
        holder.price.setText(String.valueOf(holdings.get(position).getPrice()));
        holder.ticker.setText(String.valueOf(holdings.get(position).getTicker()));
        holder.quantity.setText(String.valueOf(holdings.get(position).getQuantity()));
        holder.rank.setText(String.valueOf(holdings.get(position).getRank()));
        holder.total.setText(String.valueOf(holdings.get(position).getTotal()));
    }

    @Override
    /**
     * Returns the item count for the arrayList in the constructor.
     */
    public int getItemCount() {
        return holdings.size();
    }
    /**
     * This is the ViewHolder class for Holdings.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rank;
        TextView ticker;
        TextView price;
        TextView quantity;
        TextView total;
        /**
         * This constructor creates a view holder.
         * @param itemView The view of the item
         * @param holdingsRecViewInterface The interface used to interact with the item
         */
        public MyViewHolder(@NonNull View itemView, recyclerView_interface holdingsRecViewInterface) {
            super(itemView);
            rank = itemView.findViewById(R.id.rank);
            ticker = itemView.findViewById(R.id.ticker);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            total = itemView.findViewById(R.id.total);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holdingsRecViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            holdingsRecViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
