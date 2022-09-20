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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.view.View;
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    RecipeHelperClass[] recipearray;
    Context context;
    RecipeHelperClass addrecipe;
    String day;
    public LocationAdapter(String day,RecipeHelperClass[] recipearray,RecipeHelperClass addrecipe, Context activity){
        this.recipearray = recipearray;
        this.context= activity;
        this.addrecipe = addrecipe;
        this.day = day;
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
        final RecipeHelperClass recipeadd = addrecipe;
        final String day2= day;
        final int count = position;
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
                int daypostion = 0;
                int position  = holder.getAdapterPosition();;
               DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mealplan").child("days").child(day2).child(""+position);
                ref.child("Description").setValue((recipeadd.getDescription()));
                ref.child("calories").setValue(recipeadd.getCalories());
                ref.child("image").setValue(recipeadd.getImage());
                ref.child("itemName").setValue(recipeadd.getRecipename());
                ref.child("preptime").setValue(recipeadd.getPreptime());
                ref.child("price").setValue(recipeadd.getPrice());
                ref.child("recipeid").setValue(recipeadd.getNameid());
                ref.child("steps").setValue(recipeadd.getInstructions());
                ref.child("totaltime").setValue(recipeadd.getTotaltime());
                ref.child("category").setValue(recipeadd.getCategory());
               // ref.child("ingredients").setValue(recipeadd.g);

                switch(day2){
                    case "Monday":daypostion=0;break;
                    case "Tuesday":daypostion=1;break;
                    case "Wednesday":daypostion=2;break;
                    case "Thursday":daypostion=3;break;
                    case "Friday":daypostion=4;break;
                    case "Saturday":daypostion=5;break;
                    case "Sunday":daypostion=6;break;
                }
                final int dpos = daypostion;
                String[][] ingredients2= recipeadd.getIngredients();
                int i = ingredients2[0].length;
                int j;
                for(j = 0;j<i;j++)
                {
                    ref.child("ingredients").child(""+j).child("name").setValue(ingredients2[j][0]);
                    ref.child("ingredients").child(""+j).child("price").setValue(ingredients2[j][1]);
                    ref.child("ingredients").child(""+j).child("quantity").setValue(ingredients2[j][2]);
                }
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                Query reference = rootRef.child("cart");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                       @Override
                                                       public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                                                           for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                                                                        int location = dpos+position-1;
                                                                       rootRef.child("cart").child(""+location).child("quantity").setValue("1");
                                                                       rootRef.child("cart").child(""+location).child("name").setValue(recipeadd.getRecipename());
                                                                       rootRef.child("cart").child(""+location).child("price").setValue(recipeadd.getPrice());

                                                           }
                                                       }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
                Toast.makeText(context, "Added to Meal Plan!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,MealPlanActivity.class);
                context.startActivity(intent);
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
