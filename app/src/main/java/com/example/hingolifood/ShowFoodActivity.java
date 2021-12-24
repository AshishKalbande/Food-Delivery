package com.example.hingolifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hingolifood.databinding.ActivityShowFoodBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;


public class ShowFoodActivity extends AppCompatActivity {

    ActivityShowFoodBinding binding;
    public static String quntity2;
//    public static String priceQuntity;
    public static int FoodPrice2;
    public static int TotoalPice2;
    public static String username;


//    private FirebaseUser firebaseUser;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference databaseReference = database.getReference("users");
    private DatabaseReference reference = database.getReference("Users");
    private FirebaseAuth auth = FirebaseAuth.getInstance();



    public static  String FN;
    public static String FH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_food);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_food);

        binding.userNumber.setText(auth.getCurrentUser().getPhoneNumber());
        readData();
        Bundle bundle = getIntent().getExtras();

        FN = bundle.getString("foodName");
        FH = bundle.getString("foodHotel");
        String FoodPrice = bundle.getString("foodPrice");
        int FI = bundle.getInt("foodImg");




        // get food price
        int FoodPr = Integer.parseInt(FoodPrice);
        FoodPrice2 = FoodPr;

        binding.btnOrder.setOnClickListener(v -> {
                getOrder();
        });


        Glide.with(this).load(FI).into(binding.ShowFoodImg);
        binding.FoodHotelName.setText(FH);
        binding.FoodNameShow.setText(FN);
        String FPr = String.valueOf(FoodPrice);
        binding.FoodPrice.setText(FPr);

        binding.incrementImg.setOnClickListener(v -> {
            Increment();
        });
        binding.decementImg.setOnClickListener(v -> {
            Decrement();
        });


        // after that you will add function
    }

    private void getOrder() {




        HashMap<String,Object> m = new HashMap<String, Object>();
        m.put("foodName",FN);
        m.put("foodHotel",FH);
//        m.put("username",binding.EtUserName.getText().toString());
        m.put("username",username);
        m.put("location",binding.EtLocation.getText().toString());
        m.put("quantity",binding.foodQuantity.getText().toString());
        m.put("totalprice",binding.FoodPrice.getText().toString());
//        m.put("phone",binding.userNumber.getText().toString());
        m.put("phone",auth.getCurrentUser().getPhoneNumber());
        m.put("userID",auth.getUid());

//        String Username = binding.EtUserName.getText().toString();

        Toast.makeText(getApplicationContext(), "Order Succefully filled", Toast.LENGTH_SHORT).show();
        FirebaseDatabase.getInstance().getReference("Order").push().setValue(m);
//        reference.child(auth.).child("Order").setValue(m);
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = firebaseUser.getUid();
//        reference.child(firebaseUser.getUid()).child("Order").setValue(m);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void Increment(){
        
        String quntity = binding.foodQuantity.getText().toString();
        quntity2 = quntity;
        int current_quntity = Integer.parseInt(quntity2);
        int Increment = current_quntity + 1;
        String IncrementQuntity = String.valueOf(Increment);
        binding.foodQuantity.setText(IncrementQuntity);

        //p
        TotoalPice2 =  FoodPrice2* Increment;
        String IncrementQuntityPrice = String.valueOf(TotoalPice2);
        binding.FoodPrice.setText(IncrementQuntityPrice);


    }
    public void Decrement(){

//        if (quntity2.equals(0)) {
            String quntity = binding.foodQuantity.getText().toString();
            quntity2 = quntity;
            int current_quntity = Integer.parseInt(quntity2);
            int Increment = current_quntity - 1;
            String IncrementQuntity = String.valueOf(Increment);
            binding.foodQuantity.setText(IncrementQuntity);

            String decprice = binding.FoodPrice.getText().toString();
            int Decp = Integer.parseInt(decprice);
            int TotalPrice = Decp - FoodPrice2;
            String DecQuntityPrice = String.valueOf(TotalPrice);
            binding.FoodPrice.setText(DecQuntityPrice);
//        }
    }


    private void readData(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Profile");
        databaseReference.child(auth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){
//                    Toast.makeText(UserProfile.this, "Suceesfully", Toast.LENGTH_SHORT).show();
                    DataSnapshot dataSnapshot = task.getResult();
                    username = String.valueOf(dataSnapshot.child("name").getValue());

                    binding.EtUserName.setText(username);
                }else {
                    Toast.makeText(ShowFoodActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}