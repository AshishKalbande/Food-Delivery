package com.example.hingolifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;


import com.example.hingolifood.databinding.MlActivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class mLActivity extends AppCompatActivity {

    MlActivityBinding binding;
    FirebaseAuth mAuth;
    String email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.ml_activity);


        mAuth = FirebaseAuth.getInstance();

        binding.registerLink.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        binding.btnLogin.setOnClickListener(v -> {
            validateData();
        });

    }

        @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            openMain();
        }
    }
    private void openMain() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void validateData(){
         email = binding.etemail.getText().toString();
         password = binding.etpassword.getText().toString();
         if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
             Toast.makeText(this, "Pls enter email/password", Toast.LENGTH_SHORT).show();
         }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        Toast.makeText(mLActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(mLActivity.this, MainActivity.class));
                    }else {
                        Toast.makeText(mLActivity.this, "Enter Valid Email/Password", Toast.LENGTH_SHORT).show();
                    }

                }
            });
         }
    }


}