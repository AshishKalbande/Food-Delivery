package com.example.hingolifood.Phase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hingolifood.MainActivity;
import com.example.hingolifood.R;
import com.example.hingolifood.databinding.ActivitySignInActivityBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInActivityActivity extends AppCompatActivity {

    ActivitySignInActivityBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String username_L;
    String password_L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        username_L = binding.etUsername.getText().toString();
        password_L = binding.etpassword.getText().toString();


        binding.btnLogin.setOnClickListener(v -> {

//            if (!username_L.isEmpty()) {
//                binding.etUsername.setError(null);
//                binding.etUsername.setEnabled(false);
//                if (!password_L.isEmpty()) {
//                    binding.etpassword.setError(null);
//                    binding.etpassword.setEnabled(false);

//            if (username_L.isEmpty() | password_L.isEmpty()) {
//                Toast.makeText(getApplicationContext(), "pls enter the password", Toast.LENGTH_SHORT).show();
//
//            }else {
//                    binding.etpassword.setError("pls enter password");



                    final String username_data = binding.etUsername.getText().toString();
                    final String password_data = binding.etpassword.getText().toString();

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("Users");


                    //usernam ordeby child se chec krega
                    Query check_username = databaseReference.orderByChild("username").equalTo(username_data);
                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()) {
                                binding.etUsername.setError(null);
                                // ek bar gl daliya to fir dalpay error ht jay
                                binding.etUsername.setEnabled(false);
                                // string.class used for value string me hona chahiye
                                String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);
                                if (passwordcheck.equals(password_data)) {
                                    binding.etpassword.setError(null);
                                    binding.etpassword.setEnabled(false);
                                    Toast.makeText(getApplicationContext(), "Login Succefully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    binding.etpassword.setError("Wrong Password");
                                }
//
                            } else {
                                binding.etUsername.setError("User does not exist");
                            }
                        }
                        //
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


//                }



        });






    }

}