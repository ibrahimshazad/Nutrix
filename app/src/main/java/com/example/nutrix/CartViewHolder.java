package com.example.nutrix;


import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductPrice, txtProductQuantity;
    private AdapterView.OnItemClickListener itemClickListener;


    //fix
    public CartViewHolder(View itemView)
    {
        super(itemView);

        txtProductName = itemView.findViewById(R.id.cart_product_name);
        txtProductPrice = itemView.findViewById(R.id.cart_product_price);
        txtProductQuantity = itemView.findViewById(R.id.cart_product_quantity);
    }

    @Override
    public void onClick(View v)
    {
        // itemClickListener.onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
}

