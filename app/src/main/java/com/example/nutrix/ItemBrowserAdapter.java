package com.example.nutrix;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemBrowserAdapter extends RecyclerView.Adapter<ItemBrowserAdapter.ViewHolder> {

    Cart[] cart1;
    Context context;

    public ItemBrowserAdapter(Cart[] cart1, Context activity){
        this.cart1 = cart1;
        this.context= activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_items_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    //fix
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Cart cart2 = cart1[position];
        holder.itemName.setText("Name: "+cart2.getName());
        holder.itemPrice.setText("Price: "+cart2.getPrice());
        holder.itemQ.setText("Quanitity: "+cart2.getQuantity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ConfirmFinalOrderActivity.class);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return cart1.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView  itemName,itemPrice,itemQ;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.cart_product_name);
            itemPrice = itemView.findViewById(R.id.cart_product_price);
            itemQ = itemView.findViewById(R.id.cart_product_quantity);

        }
    }

}
