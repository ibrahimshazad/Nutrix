package com.example.nutrix;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ValueEventListener;
import android.view.View;

public class RecipeBrowserAdapter extends RecyclerView.Adapter<RecipeBrowserAdapter.ViewHolder> {

    RecipeHelperClass[] recipearray;
    Context context;
    String choice;

    public RecipeBrowserAdapter(RecipeHelperClass[] recipearray, Context activity){
        this.recipearray = recipearray;
        this.context= activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    //fix
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RecipeHelperClass recipelist = recipearray[position];
        holder.recipeName.setText(recipelist.getRecipename());
        holder.recipeTotaltime.setText(recipelist.getTotaltime());
        holder.recipeCalories.setText(recipelist.getCalories());
        holder.recipeDescription.setText(recipelist.getDescription());
        int price = recipelist.getPrice();
        holder.recipePrice.setText("Price:\n$"+String.valueOf(price));
        String myUrl   = recipelist.getImage();

        Glide.with(context)
                .load(myUrl)
                .into(holder.recipeImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = recipelist.getRecipename();
                String price = String.valueOf(recipelist.getPrice());
                String description = recipelist.getDescription();
                String preptime = recipelist.getPreptime();
                String totaltime = recipelist.getTotaltime();
                String calories = recipelist.getCalories();
                String image = recipelist.getImage();
                //String instructions = recipelist.getInstructions();
                Intent intent = new Intent(context, RecipeFullDetail.class);
                intent.putExtra("selectedrecipe",recipelist);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return recipearray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView recipeImage;
        TextView  recipeName,recipeTotaltime, recipePrice,recipeDescription,recipeCalories;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            recipeImage = itemView.findViewById(R.id.browsePreview);
            recipeName = itemView.findViewById(R.id.browseName);
            recipeDescription = itemView.findViewById(R.id.browseDescription);
            recipeTotaltime = itemView.findViewById(R.id.browseCookTime);
            recipePrice = itemView.findViewById(R.id.browsePrice);
            recipeCalories = itemView.findViewById(R.id.browseCalories);
            recipeImage =  itemView.findViewById(R.id.browsePreview);
        }
    }

}
