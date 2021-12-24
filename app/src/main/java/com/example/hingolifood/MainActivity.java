package com.example.hingolifood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.hingolifood.Adapters.FoodAdapter;
import com.example.hingolifood.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        auth = FirebaseAuth.getInstance();



        binding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.mRecyclerView.setHasFixedSize(true);
        binding.mRecyclerView.setAdapter(new FoodAdapter(this,GetUsers()));

        binding.btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, PhoneActivity.class));
        });

        binding.Tvhistry.setOnClickListener(v -> {
            startActivity(new Intent(this,OrderHistry.class));
        });
        binding.tvProfile.setOnClickListener(v -> {
            startActivity(new Intent(this,UserProfile.class));
        });


    }

    private List<FoodModel> GetUsers(){

        List<FoodModel> food = new ArrayList<>();

        food.add(new FoodModel("Pizza","McDonal" ,"200",R.drawable.pizzas));
        food.add(new FoodModel("Pasta","Veg Hotel" ,"120",R.drawable.pasta));
        food.add(new FoodModel("Biryani","Nonveg Hotel" ,"200",R.drawable.biryani));
        food.add(new FoodModel("Burger","McDonal" ,"75",R.drawable.burger));
        food.add(new FoodModel("Mater Panir","Veg Hotel" ,"200",R.drawable.matter_panir));
        food.add(new FoodModel("French Fry","McDonal" ,"80",R.drawable.french_fri));
        food.add(new FoodModel("Manchurian","Chinese Hotel" ,"90",R.drawable.manchurian));

        return food;
    }
}