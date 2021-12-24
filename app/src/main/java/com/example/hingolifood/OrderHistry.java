package com.example.hingolifood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.hingolifood.Adapters.OrderHistryAdapter;
import com.example.hingolifood.Phase1.SignInActivityActivity;
import com.example.hingolifood.databinding.ActivityOrderHistryBinding;
import com.example.hingolifood.models.OrderHistryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class OrderHistry extends AppCompatActivity {

    ActivityOrderHistryBinding binding;
    DatabaseReference databaseR;
    OrderHistryAdapter orderHistryAdapter;
    ArrayList<OrderHistryModel> list;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (mAuth.getCurrentUser() != null) {
//            startActivity(new Intent(this,SignInActivityActivity.class));
//            finish();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_order_histry);



        databaseR = FirebaseDatabase.getInstance().getReference("Order");
        binding.OrderHistoryRecyclerView.setHasFixedSize(true);
        binding.OrderHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        orderHistryAdapter = new OrderHistryAdapter(this,list);
        binding.OrderHistoryRecyclerView.setAdapter(orderHistryAdapter);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        // equal to me user ki uid dale
        Query quary = databaseR.orderByChild("userID").equalTo(mAuth.getUid());

       quary.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot :snapshot.getChildren()){

                   OrderHistryModel user = dataSnapshot.getValue(OrderHistryModel.class);
                   list.add(user);
               }
               orderHistryAdapter.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });


    }
}