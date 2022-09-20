package com.example.nutrix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> itemNameList;
    ArrayList<String>caloriesList;
    ArrayList<String>imageList;

     class SearchViewHolder extends RecyclerView.ViewHolder{
         ImageView image;
         TextView itemName, calories;


         public SearchViewHolder(@NonNull View itemView) {
             super(itemView);
             image = itemView.findViewById(R.id.image);
             itemName = itemView.findViewById(R.id.itemName);
             calories = itemView.findViewById(R.id.calories);

         }
     }

    public SearchAdapter(Context context, ArrayList<String> itemNameList, ArrayList<String> caloriesList, ArrayList<String> imageList) {
        this.context = context;
        this.itemNameList = itemNameList;
        this.caloriesList = caloriesList;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(context).inflate(R.layout.search_list_items,parent,false);
         return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

         holder.itemName.setText(itemNameList.get(position));
        holder.calories.setText(caloriesList.get(position));

        //Glide.with(context).asBitmap().load(imageList.get(position)).placeholder(R.mipmap.ic_launcher_round).into(holder.image);

        //Glide.with(context).asBitmap().load(imageList.get(position)).placeholder(R.mipmap.ic_launcher_round).into(holder.image);

    }


    @Override
    public int getItemCount() {
        return itemNameList.size();
    }
}
