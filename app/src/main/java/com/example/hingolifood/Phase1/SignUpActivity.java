package com.example.hingolifood.Phase1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hingolifood.databinding.ActivitySignUpBinding;
import com.example.hingolifood.models.users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase dbref;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        dbref = FirebaseDatabase.getInstance();

        binding.RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.RegisterYourName.getText().toString();
                String email = binding.RegisterEmail.getText().toString();
                String phone = binding.RegisterPhone.getText().toString();
                String password = binding.RegisterPassword.getText().toString();


                if (!username.isEmpty()) {
                    binding.Username.setError(null);
                    binding.Username.setEnabled(false);
                    if (!password.isEmpty()) {
                        binding.RegisterPassword.setError(null);
                        binding.RegisterPassword.setEnabled(false);
                        if (!email.isEmpty()){
                            binding.RegisterEmail.setError(null);
                            binding.RegisterEmail.setEnabled(false);

                            if (!phone.isEmpty()){
                                binding.RegisterPhone.setError(null);
                                binding.RegisterPhone.setEnabled(false);

//                                if (!email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
                                if(!email.isEmpty())
                                {

                                    //firebases
                                    users user = new users(username,password,email,phone);
                                    dbref = FirebaseDatabase.getInstance();
                                    databaseReference = dbref.getReference("Users");
                                    databaseReference.child(username).setValue(user);
//                                    databaseReference.push().setValue(user);
                                    Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(SignUpActivity.this, SignInActivityActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK & Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                    //firebase end

                                }
                                else {
                                    binding.RegisterEmail.setError("Invalid Email");
                                }

                            }else {
                                binding.RegisterPhone.setError("please enter phone");
                            }

                        }else {
                            binding.RegisterEmail.setError("please enter the email");
                        }
                    } else{
                        binding.RegisterPassword.setError("please enter the password");
                    }
                }else {
                    binding.Username.setText("please enter username");
                }


                //

            }
        });



    }

}