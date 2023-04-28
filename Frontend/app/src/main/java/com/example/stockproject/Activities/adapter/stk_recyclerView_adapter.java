package com.example.stockproject.Activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockproject.Activities.model.StocksModel;
import com.example.stockproject.R;
import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;
/**
 * This is the adapter for stocks in the recycler view.
 */
public class stk_recyclerView_adapter extends RecyclerView.Adapter<stk_recyclerView_adapter.MyViewHolder> {
    private final recyclerView_interface stkRecViewInterface;
    Context context;
    ArrayList<StocksModel> stocks;
    /**
     * This constructor creates an adapter.
     * @param context - A handle to the system.
     * @param stocks - A list of stock models.
     * @param stkRecViewInterface - An interface that allows users to interact with the stock models.
     */
    public stk_recyclerView_adapter(Context context, ArrayList<StocksModel> stocks, recyclerView_interface stkRecViewInterface) {
        this.context = context;
        this.stocks = stocks;
        this.stkRecViewInterface = stkRecViewInterface;
    }

    @NonNull
    @Override
    /**
     * This class creates a view holder for the adapter on creation of this class.
     */
    public stk_recyclerView_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stock_row, parent, false);
        return new stk_recyclerView_adapter.MyViewHolder(view, stkRecViewInterface);
    }

    @Override
    /**
     * This class assigns the view holder with values.
     * @param position - an integer position from the arrayList in the constructor.
     */
    public void onBindViewHolder(@NonNull stk_recyclerView_adapter.MyViewHolder holder, int position) {
        holder.stockName.setText(stocks.get(position).getStockName());
        holder.value.setText(stocks.get(position).getValue());
        holder.changeImage.setImageResource(stocks.get(position).getChange());
        holder.graph = new GraphView(this.context);
    }

    @Override
    /**
     * Returns the item count for the arrayList in the constructor.
     */
    public int getItemCount() {
        return stocks.size();
    }
    /**
     * This is the ViewHolder class for Stocks.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stockName, value;
        ImageView changeImage;
        GraphView graph;
        /**
         * This constructor creates a view holder.
         * @param itemView The view of the item
         * @param stkRecyclerViewInterface The interface used to interact with the item
         */
        public MyViewHolder(@NonNull View itemView, recyclerView_interface stkRecyclerViewInterface) {
            super(itemView);
            value = itemView.findViewById(R.id.value);
            stockName = itemView.findViewById(R.id.stockName);
            changeImage = itemView.findViewById(R.id.changeImage);
            graph = itemView.findViewById(R.id.idGraphView);

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
