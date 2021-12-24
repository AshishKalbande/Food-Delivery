package com.example.hingolifood.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hingolifood.FoodModel;
import com.example.hingolifood.ShowFoodActivity;
import com.example.hingolifood.databinding.ItemsBinding;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {


    private Context context;
    private List<FoodModel> food = new ArrayList<>();

    public FoodAdapter(Context context, List<FoodModel> users) {
        this.context = context;
        this.food = users;
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemsBinding itemsBinding = ItemsBinding.inflate(layoutInflater, parent, false);
        return new FoodHolder(itemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull  FoodAdapter.FoodHolder holder, int position) {
        FoodModel model = food.get(position);
        holder.binding.setFood(model);

        Glide.with(context).load(model.getFoodImg()).into(holder.binding.imgDish);
        holder.binding.tvPrice.setText(model.getFoodPrice());

        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , ShowFoodActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("foodName" , model.getFoodName());
                bundle.putString("foodHotel" ,model.getFoodHotel());
                bundle.putString("foodPrice" , model.getFoodPrice());
                bundle.putInt("foodImg" , model.getFoodImg());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return food.size();
    }

    public static class FoodHolder extends RecyclerView.ViewHolder{

        ItemsBinding binding;
        public FoodHolder(@NonNull ItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
