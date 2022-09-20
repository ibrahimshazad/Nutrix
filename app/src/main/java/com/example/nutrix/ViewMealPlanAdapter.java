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

public class ViewMealPlanAdapter extends RecyclerView.Adapter<ViewMealPlanAdapter.ViewHolder> {

    RecipeHelperClass[] recipearray;
    Context context;

    public ViewMealPlanAdapter(RecipeHelperClass[] recipearray, Context activity){
        this.recipearray = recipearray;
        this.context= activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.mealplan_display,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    //fix
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RecipeHelperClass recipelist = recipearray[position];
        // holder.recipeName.setText(recipelist.getRecipename());
        // if(!recipelist.getRecipename().equals("Name"))
        //  {
        holder.recipeName.setText(recipelist.getRecipename());
        holder.recipeTotaltime.setText(recipelist.getTotaltime());
        holder.recipeCalories.setText(recipelist.getCalories());
        String myUrl   = recipelist.getImage();

        Glide.with(context)
                .load(myUrl)
                .into(holder.recipeImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = recipelist.getRecipename();
                String totaltime = recipelist.getTotaltime();
                String calories = recipelist.getCalories();
                String image = recipelist.getImage();
                if(!name.equals("Name"))
                {
                    Intent intent = new Intent(context, RecipeFullDetail.class);
                    intent.putExtra("selectedrecipe",recipelist);
                    context.startActivity(intent);
                }
                else
                {
                    Toast.makeText(context, "Recipe Empty, Add Recipe in Edit Meal Plan", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    // }


    @Override
    public int getItemCount() {
        return recipearray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView recipeImage;
        TextView  recipeName,recipeTotaltime,recipeCalories;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            recipeImage = itemView.findViewById(R.id.mealImage);
            recipeCalories = itemView.findViewById(R.id.mealCal);
            recipeName = itemView.findViewById(R.id.mealName);
            recipeTotaltime = itemView.findViewById(R.id.mealTime);

        }
    }

}
